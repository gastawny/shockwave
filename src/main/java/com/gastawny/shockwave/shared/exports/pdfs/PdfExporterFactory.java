package com.gastawny.shockwave.shared.exports.pdfs;

import com.gastawny.shockwave.shared.exports.pdfs.impl.ITextPdfExporter;

public final class PdfExporterFactory {

    private PdfExporterFactory() {

    }

    public static PdfExporter create(PdfExporterType type) {
        return switch (type) {
            case ITEXT -> new ITextPdfExporter();
        };
    }
}
