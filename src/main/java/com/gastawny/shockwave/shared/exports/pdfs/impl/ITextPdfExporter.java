package com.gastawny.shockwave.shared.exports.pdfs.impl;

import com.gastawny.shockwave.shared.exports.pdfs.PdfExporter;
import com.gastawny.shockwave.shared.exports.pdfs.styles.CellStyle;
import com.gastawny.shockwave.shared.exports.pdfs.styles.ImageStyle;
import com.gastawny.shockwave.shared.exports.pdfs.styles.TextSpan;
import com.gastawny.shockwave.shared.exports.pdfs.styles.TextStyle;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.List;

public class ITextPdfExporter implements PdfExporter {

    private Document document;
    private PdfPTable currentTable;

    // Row/grid support
    private PdfPTable currentRowTable;
    // private int currentRowColumns = 0; // (we don't need to track columns count separately for now)

    // Increased default paragraph spacing so elements are not "colados" unsafely
    private static final float DEFAULT_PARAGRAPH_SPACING_BEFORE = 6f;
    private static final float DEFAULT_PARAGRAPH_SPACING_AFTER = 4f;

    @Override
    public void start(OutputStream outputStream) throws IOException {
        try {
            document = new Document();
            PdfWriter.getInstance(document, outputStream);
            document.open();
        } catch (DocumentException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void addText(String text) {
        addText(text, new TextStyle());
    }

    @Override
    public void addText(String text, TextStyle style) {
        try {
            // guard null text
            String safeText = text == null ? "" : text;
            Paragraph p = new Paragraph(safeText, resolveFont(style));
            if (style != null && style.getHorizontalAlignment() != null) {
                p.setAlignment(style.getHorizontalAlignment());
            }
            // espaçamento vertical padrão para separar elementos
            p.setSpacingBefore(DEFAULT_PARAGRAPH_SPACING_BEFORE);
            p.setSpacingAfter(DEFAULT_PARAGRAPH_SPACING_AFTER);
            document.add(p);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addSeparator() {
        try {
            // Use iText's LineSeparator to draw a horizontal line
            LineSeparator ls = new LineSeparator();
            ls.setOffset(0);
            document.add(new Chunk(ls));
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void startRow(int columns) {
        // initialize a table that will act as a row with given number of columns
        currentRowTable = new PdfPTable(columns);
        // full width
        currentRowTable.setWidthPercentage(100);
        // add vertical spacing between rows
        currentRowTable.setSpacingBefore(DEFAULT_PARAGRAPH_SPACING_BEFORE);
        currentRowTable.setSpacingAfter(DEFAULT_PARAGRAPH_SPACING_AFTER);
    }

    @Override
    public void addRowCell(String text) {
        addRowCell(text, new TextStyle());
    }

    @Override
    public void addRowCell(String text, TextStyle style) {
        if (currentRowTable == null) {
            throw new IllegalStateException("startRow(...) must be called before addRowCell(...)");
        }
        // guard null text
        String safeText = text == null ? "" : text;
        PdfPCell cell = new PdfPCell(new Phrase(safeText, resolveFont(style)));
        // apply text style alignment if present
        if (style != null && style.getHorizontalAlignment() != null) {
            cell.setHorizontalAlignment(style.getHorizontalAlignment());
        }
        if (style != null && style.getVerticalAlignment() != null) {
            cell.setVerticalAlignment(style.getVerticalAlignment());
        }
        // remove default cell borders to make it look like plain columns unless user wants styling
        cell.setBorder(PdfPCell.NO_BORDER);
        currentRowTable.addCell(cell);
    }

    @Override
    public void endRow() {
        if (currentRowTable == null) return;
        try {
            document.add(currentRowTable);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        currentRowTable = null;
        // currentRowColumns = 0;
    }

    @Override
    public void addInlineText(List<TextSpan> spans) {
        try {
            if (spans == null || spans.isEmpty()) return;
            Paragraph paragraph = new Paragraph();

            for (TextSpan span : spans) {
                Font font = resolveFont(span.getStyle());
                String safeText = span.getText() == null ? "" : span.getText();
                paragraph.add(new Chunk(safeText, font));
            }

            // since we already returned for null/empty, it's safe to read the first span
            TextStyle firstStyle = spans.get(0).getStyle();
            if (firstStyle != null && firstStyle.getHorizontalAlignment() != null) {
                paragraph.setAlignment(firstStyle.getHorizontalAlignment());
            }

            paragraph.setSpacingBefore(DEFAULT_PARAGRAPH_SPACING_BEFORE);
            paragraph.setSpacingAfter(DEFAULT_PARAGRAPH_SPACING_AFTER);

            document.add(paragraph);

        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void startTable(int columns) {
        currentTable = new PdfPTable(columns);
    }

    @Override
    public void addTableHeader(String text) {
        addTableHeader(text, new CellStyle());
    }

    @Override
    public void addTableHeader(String text, CellStyle style) {
        String safeText = text == null ? "" : text;
        PdfPCell cell = new PdfPCell(new Phrase(safeText));
        applyCellStyle(cell, style);
        currentTable.addCell(cell);
    }

    @Override
    public void addTableCell(String text) {
        addTableCell(text, new CellStyle());
    }

    @Override
    public void addTableCell(String text, CellStyle style) {
        String safeText = text == null ? "" : text;
        PdfPCell cell = new PdfPCell(new Phrase(safeText));
        applyCellStyle(cell, style);
        currentTable.addCell(cell);
    }

    @Override
    public void addImage(String imageResource, ImageStyle style) throws IOException {
        try {
            Image img = Image.getInstance(
                    Paths.get(ClassLoader.getSystemResource(imageResource).toURI())
                            .toString()
            );
            applyImageStyle(img, style);
            document.add(img);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    public void finish() {
        try {
            if (currentTable != null) {
                document.add(currentTable);
                currentTable = null;
            }
            // if a row was left open, close it
            if (currentRowTable != null) {
                document.add(currentRowTable);
                currentRowTable = null;
            }
            document.close();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    private Font resolveFont(TextStyle style) {
        if (style == null) {
            return FontFactory.getFont(FontFactory.HELVETICA, 12);
        }

        int fontStyle = Font.NORMAL;
        if (Boolean.TRUE.equals(style.getBold())) fontStyle |= Font.BOLD;
        if (Boolean.TRUE.equals(style.getItalic())) fontStyle |= Font.ITALIC;

        float size = style.getFontSize() != null ? style.getFontSize() : 12;

        return FontFactory.getFont(FontFactory.HELVETICA, size, fontStyle);
    }


    private void applyCellStyle(PdfPCell cell, CellStyle style) {
        if (style == null) return;

        if (style.getBackgroundColorHex() != null) {
            BaseColor color = parseHexToBaseColor(style.getBackgroundColorHex());
            if (color != null) {
                cell.setBackgroundColor(color);
            }
        }

        if (style.getHorizontalAlignment() != null) {
            cell.setHorizontalAlignment(style.getHorizontalAlignment());
        }

        if (style.getVerticalAlignment() != null) {
            cell.setVerticalAlignment(style.getVerticalAlignment());
        }
    }

    private void applyImageStyle(Image img, ImageStyle style) {
        if (style == null) return;

        if (style.getScalePercent() != null) {
            img.scalePercent(style.getScalePercent());
        }

        if (style.getAlignment() != null) {
            img.setAlignment(style.getAlignment());
        }
    }

    private BaseColor parseHexToBaseColor(String hex) {
        if (hex == null) return null;
        String s = hex.trim();
        if (s.startsWith("#")) {
            s = s.substring(1);
        }
        if (s.length() == 3) { // expand short form like "f0a" -> "ff00aa"
            s = "" + s.charAt(0) + s.charAt(0)
                    + s.charAt(1) + s.charAt(1)
                    + s.charAt(2) + s.charAt(2);
        }
        if (s.length() != 6) return null;
        try {
            int rgb = Integer.parseInt(s, 16);
            int r = (rgb >> 16) & 0xFF;
            int g = (rgb >> 8) & 0xFF;
            int b = rgb & 0xFF;
            return new BaseColor(r, g, b);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
