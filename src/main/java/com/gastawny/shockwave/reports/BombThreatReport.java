package com.gastawny.shockwave.reports;

import com.gastawny.shockwave.models.LocatedObject;
import com.gastawny.shockwave.repositories.BombThreatRepository;
import com.gastawny.shockwave.services.FormulaService;
import com.gastawny.shockwave.shared.exports.pdfs.PdfExporter;
import com.gastawny.shockwave.shared.exports.pdfs.PdfExporterFactory;
import com.gastawny.shockwave.shared.exports.pdfs.PdfExporterType;
import com.gastawny.shockwave.shared.exports.pdfs.styles.TextSpan;
import com.gastawny.shockwave.shared.exports.pdfs.styles.TextStyle;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
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

            var formulasValues = resolveFormulas(lo);

            for (var entry : formulasValues) {
                pdf.addInlineText(List.of(
                        TextSpan.of(entry.getKey() + ": ", new TextStyle().bold()),
                        TextSpan.of(entry.getValue())
                ));
            }

        } else {
            pdf.addText("Nenhum Objeto Localizado associado a esta Ameaça de Bomba", new TextStyle().bold().fontSize(18));

            pdf.addInlineText(List.of(
                    TextSpan.of("Informações sobre a ocorrência: ", new TextStyle().bold()),
                    TextSpan.of(bombThreat.getObjectNotFoundDescription())
            ));
        }

        pdf.finish();
    }

    private List<Map.Entry<String, String>> resolveFormulas(LocatedObject locatedObject) {
        var valuesParameters = formulaService.getParameterValues(Map.of(
                "locatedObjectId", locatedObject.getId().toString()
        ));

        var formulas = formulaService.get2Report(valuesParameters);
//        formulas = formulas.stream().filter(f -> f.getId() < 25L).toList();

        return formulas
                .stream()
                .map(formula -> {
                    try {
                        Double result = formulaService.execute(formula, valuesParameters);
                        return Map.entry(formula.getName(), result.toString());
                    } catch (Throwable t) {
                        return Map.entry(formula.getId().toString(), "ERROR: " + t.getMessage());
                    }
                })
                .toList();
    }
}
