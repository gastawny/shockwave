package com.gastawny.shockwave.reports;

import com.gastawny.shockwave.dto.formula.FormulaResult;
import com.gastawny.shockwave.models.LocatedObject;
import com.gastawny.shockwave.repositories.BombThreatRepository;
import com.gastawny.shockwave.services.FormulaService;
import com.gastawny.shockwave.shared.exports.maps.CircleConfig;
import com.gastawny.shockwave.shared.exports.maps.GeoLocation;
import com.gastawny.shockwave.shared.exports.maps.GoogleMapGenerator;
import com.gastawny.shockwave.shared.exports.pdfs.PdfExporter;
import com.gastawny.shockwave.shared.exports.pdfs.PdfExporterFactory;
import com.gastawny.shockwave.shared.exports.pdfs.PdfExporterType;
import com.gastawny.shockwave.shared.exports.pdfs.styles.ImageStyle;
import com.gastawny.shockwave.shared.exports.pdfs.styles.TextSpan;
import com.gastawny.shockwave.shared.exports.pdfs.styles.TextStyle;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class BombThreatReport {

    private final PdfExporter pdf = PdfExporterFactory.create(PdfExporterType.ITEXT);
    private final BombThreatRepository bombThreatRepository;
    private final FormulaService formulaService;

    public BombThreatReport(BombThreatRepository bombThreatRepository, FormulaService formulaService) {
        this.bombThreatRepository = bombThreatRepository;
        this.formulaService = formulaService;
    }

    public void getById(OutputStream outputStream, Long id) throws IOException {
        var bombThreat = bombThreatRepository.findById(id).orElseThrow();

        pdf.start(outputStream);

        var lo = bombThreat.getLocatedObject();

        pdf.addText("Relatório Ameaça de Bomba", new TextStyle().bold().fontSize(24));

        pdf.addInlineText(List.of(
                TextSpan.of("Identificação: ", new TextStyle().bold()),
                TextSpan.of(bombThreat.getName())
        ));

        pdf.addInlineText(List.of(
                TextSpan.of("Forma de Ameaça: ", new TextStyle().bold()),
                TextSpan.of(bombThreat.getFormThreat().getName())
        ));

        pdf.addInlineText(List.of(
                TextSpan.of("Observações da Forma de Ameaça: ", new TextStyle().bold()),
                TextSpan.of(bombThreat.getFormThreatDescription())
        ));

        pdf.addSeparator();

        if (lo != null) {
            var formulasValues = resolveFormulas(lo);

            pdf.addText("Dados Objeto Localizado", new TextStyle().bold().fontSize(18));

            pdf.addInlineText(List.of(
                    TextSpan.of("Identificação: ", new TextStyle().bold()),
                    TextSpan.of(lo.getName())
            ));

            pdf.addInlineText(List.of(
                    TextSpan.of("Tipo de Explosivo: ", new TextStyle().bold()),
                    TextSpan.of(lo.getExplosive().getName())
            ));

            pdf.addInlineText(List.of(
                    TextSpan.of("Tipo de Solo: ", new TextStyle().bold()),
                    TextSpan.of(lo.getGround().getName())
            ));

            pdf.addInlineText(List.of(
                    TextSpan.of("Formato do Objeto: ", new TextStyle().bold()),
                    TextSpan.of(lo.getObjectFormat().getName())
            ));

            pdf.addText("Dados Formato do Objeto", new TextStyle().italic().fontSize(14));

            for(var values : lo.getObjectFormatParameterValues()) {
                pdf.addInlineText(List.of(
                        TextSpan.of(values.getObjectFormatParameter().getParameter().getName() + ": ", new TextStyle().bold()),
                        TextSpan.of(values.getValue().getValue().toString())
                ));
            }

            pdf.addSeparator();

            pdf.addText("Fórmulas Calculadas", new TextStyle().bold().fontSize(18));

            for (var entry : formulasValues) {
                var result = entry.getValue().getResult();

                if (result == null) {
                    pdf.addInlineText(List.of(
                            TextSpan.of(entry.getKey() + ": ", new TextStyle().bold()),
                            TextSpan.of("Erro ao calcular a fórmula")
                    ));
                    continue;
                }

                pdf.addInlineText(List.of(
                        TextSpan.of(entry.getKey() + ": ", new TextStyle().bold()),
                        TextSpan.of(result.toString())
                ));
            }

            pdf.addSeparator();

            setMapImages(lo, formulasValues);

        } else {
            pdf.addText("Nenhum Objeto Localizado associado a esta Ameaça de Bomba", new TextStyle().bold().fontSize(18));

            pdf.addInlineText(List.of(
                    TextSpan.of("Informações sobre a ocorrência: ", new TextStyle().bold()),
                    TextSpan.of(bombThreat.getObjectNotFoundDescription())
            ));
        }

        pdf.finish();
    }

    private void setMapImages(LocatedObject lo, List<Map.Entry<String, FormulaResult>> formulasValues) {
        if (lo.getLatitude() != null && lo.getLongitude() != null) {
            String mapUrl;
            List<CircleConfig> circles = new ArrayList<>(List.of());

            try {
                for (var entry : formulasValues) {
                    var circle = entry.getValue().getFormula().getCircle();

                    if(circle == null) {
                        continue;
                    }

                    circles.add(new CircleConfig()
                            .setCenter(new GeoLocation(lo.getLatitude(), lo.getLongitude()))
                            .setRadius(entry.getValue().getResult())
                            .setFillColor(circle.getColor())
                            .setStrokeColor(circle.getColor())
                    );
                }

                pdf.addText("Mapas: ", new TextStyle().bold());
                mapUrl = GoogleMapGenerator.generateMapUrl(
                        lo.getLatitude(),
                        lo.getLongitude(),
                        0,
                        "600x280",
                        "roadmap",
                        circles
                );

                pdf.addImage(mapUrl, new ImageStyle().scale(86));

                pdf.addText("Imagem do mapa via satélite:", new TextStyle().bold());
                mapUrl = GoogleMapGenerator.generateMapUrl(
                        lo.getLatitude(),
                        lo.getLongitude(),
                        0,
                        "600x280",
                        "satellite",
                        circles
                );

                pdf.addImage(mapUrl, new ImageStyle().scale(86));

                pdf.addInlineText(List.of(
                        TextSpan.of("Coordenadas: ", new TextStyle().bold()),
                        TextSpan.of(lo.getLatitude() + ", " + lo.getLongitude())
                ));
            } catch (Exception e) {
                pdf.addInlineText(List.of(
                        TextSpan.of("Mapa não disponível: ", new TextStyle().bold()),
                        TextSpan.of(e.getMessage())
                ));
            }
        }
    }

    private List<Map.Entry<String, FormulaResult>> resolveFormulas(LocatedObject locatedObject) {
        var valuesParameters = formulaService.getParameterValues(Map.of(
                "locatedObjectId", locatedObject.getId().toString()
        ));

        var formulas = formulaService.get2Report(valuesParameters);

        return formulas
                .stream()
                .map(formula -> {
                    try {
                        Double result = formulaService.execute(formula, valuesParameters);
                        return Map.entry(formula.getName(), new FormulaResult(formula, result));
                    } catch (Throwable t) {
                        return Map.entry(formula.getId().toString(), new FormulaResult(formula, null));
                    }
                })
                .toList();
    }
}
