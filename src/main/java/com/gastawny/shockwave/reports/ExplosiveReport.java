package com.gastawny.shockwave.reports;

import com.gastawny.shockwave.repositories.ExplosiveRepository;
import com.gastawny.shockwave.shared.exports.pdfs.PdfExporter;
import com.gastawny.shockwave.shared.exports.pdfs.PdfExporterFactory;
import com.gastawny.shockwave.shared.exports.pdfs.PdfExporterType;
import com.gastawny.shockwave.shared.exports.pdfs.styles.TextSpan;
import com.gastawny.shockwave.shared.exports.pdfs.styles.TextStyle;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Component
public class ExplosiveReport {

    private final PdfExporter pdf = PdfExporterFactory.create(PdfExporterType.ITEXT);
    private final ExplosiveRepository explosiveRepository;

    public ExplosiveReport(ExplosiveRepository explosiveRepository) {
        this.explosiveRepository = explosiveRepository;
    }

    public void getById(OutputStream outputStream) throws IOException {
        var explosives = explosiveRepository.findAll();

        pdf.start(outputStream);

        pdf.addText("Relatório Explosivos", new TextStyle().bold().fontSize(24));

        for (var explosive : explosives) {
            var explosiveData = explosiveRepository.findDataByExplosiveId(explosive.getId());

            pdf.addText(explosive.getName(), new TextStyle().bold().fontSize(18));

            for (var data : explosiveData) {
                var value = "";
                if(data.containsKey("value") && data.get("value") != null) {
                    value = data.get("value").toString();
                }else{
                    value = "N/A";
                }
                pdf.addInlineText(List.of(
                        TextSpan.of(data.get("name")+": ", new TextStyle().bold()),
                        TextSpan.of(value)
                ));
            }

            pdf.addSeparator();
        }

        pdf.finish();
    }
}
