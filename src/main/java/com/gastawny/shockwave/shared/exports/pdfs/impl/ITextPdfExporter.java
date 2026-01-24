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
import java.util.Base64;
import java.util.List;

public class ITextPdfExporter implements PdfExporter {

    private Document document;
    private PdfPTable currentTable;

    private PdfPTable currentRowTable;

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
            String safeText = text == null ? "" : text;
            Paragraph p = new Paragraph(safeText, resolveFont(style));
            if (style != null && style.getHorizontalAlignment() != null) {
                p.setAlignment(style.getHorizontalAlignment());
            }
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
            LineSeparator ls = new LineSeparator();
            ls.setOffset(0);
            document.add(new Chunk(ls));
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void startRow(int columns) {
        currentRowTable = new PdfPTable(columns);
        currentRowTable.setWidthPercentage(100);
        currentRowTable.setSpacingBefore(DEFAULT_PARAGRAPH_SPACING_BEFORE);
        currentRowTable.setSpacingAfter(DEFAULT_PARAGRAPH_SPACING_AFTER);
        try {
            currentRowTable.setSplitRows(true);
            currentRowTable.setSplitLate(false);
        } catch (Exception ignored) {}
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
        String safeText = text == null ? "" : text;
        PdfPCell cell = new PdfPCell(new Phrase(safeText, resolveFont(style)));

        if (style != null && style.getHorizontalAlignment() != null) {
            cell.setHorizontalAlignment(style.getHorizontalAlignment());
        }
        if (style != null && style.getVerticalAlignment() != null) {
            cell.setVerticalAlignment(style.getVerticalAlignment());
        }

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
    }

    @Override
    public void addRowCellWithImage(String caption, String imageResource, ImageStyle style) throws IOException {
        if (currentRowTable == null) {
            throw new IllegalStateException("startRow(...) must be called before addRowCellWithImage(...)");
        }

        try {
            if (imageResource == null || imageResource.isBlank()) {
                PdfPCell empty = new PdfPCell(new Phrase(caption == null ? "" : caption, resolveFont(new TextStyle())));
                empty.setBorder(PdfPCell.NO_BORDER);
                currentRowTable.addCell(empty);
                return;
            }

            Image img;

                if (imageResource.startsWith("http://") || imageResource.startsWith("https://")) {
                img = Image.getInstance(java.net.URI.create(imageResource).toURL());
            } else {
                java.net.URL resourceUrl = ClassLoader.getSystemResource(imageResource);
                if (resourceUrl != null) {
                    img = Image.getInstance(Paths.get(resourceUrl.toURI()).toString());
                } else {
                    img = Image.getInstance(imageResource);
                }
            }

            applyImageStyle(img, style);

            try {
                int cols = currentRowTable.getNumberOfColumns();
                float usableWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
                float columnWidth = usableWidth / Math.max(1, cols);
                float usableHeight = document.getPageSize().getHeight() - document.topMargin() - document.bottomMargin();
                float maxImgWidth = columnWidth - 2f;
                float maxImgHeight = Math.max(usableHeight - 60f, 50f);
                if (img.getScaledWidth() > maxImgWidth || img.getScaledHeight() > maxImgHeight) {
                    img.scaleToFit(maxImgWidth, maxImgHeight);
                }

                if (img.getScaledHeight() > usableHeight - 20f) {
                    PdfPTable single = new PdfPTable(1);
                    single.setWidthPercentage(100);

                    PdfPCell capCell = new PdfPCell(new Phrase(caption == null ? "" : caption, resolveFont(new TextStyle().bold())));
                    capCell.setBorder(PdfPCell.NO_BORDER);
                    capCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    capCell.setPaddingBottom(2f);
                    single.addCell(capCell);

                    PdfPCell imgCell = new PdfPCell();
                    imgCell.setBorder(PdfPCell.NO_BORDER);
                    imgCell.addElement(img);
                    single.addCell(imgCell);

                    try {
                        document.add(single);
                    } catch (DocumentException de) {
                        throw new IOException(de);
                    }

                    currentRowTable = null;
                    return;
                }
            } catch (Exception ignored) {}

            PdfPCell cell = new PdfPCell();
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setPaddingTop(4f);
            cell.setPaddingBottom(4f);
            cell.setPaddingLeft(0f);
            cell.setPaddingRight(0f);
            Paragraph p = new Paragraph(caption == null ? "" : caption, resolveFont(new TextStyle().bold()));
            p.setAlignment(Element.ALIGN_CENTER);
            p.setSpacingAfter(2f);
            cell.addElement(p);
            cell.addElement(img);
            currentRowTable.addCell(cell);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    public void addRowCellWithImage(String caption, String[] imageBase64Chunks, ImageStyle style) throws IOException {
        if (currentRowTable == null) {
            throw new IllegalStateException("startRow(...) must be called before addRowCellWithImage(...)");
        }

        try {
            if (imageBase64Chunks == null || imageBase64Chunks.length == 0) {
                PdfPCell empty = new PdfPCell(new Phrase(caption == null ? "" : caption, resolveFont(new TextStyle())));
                empty.setBorder(PdfPCell.NO_BORDER);
                currentRowTable.addCell(empty);
                return;
            }

            String joined = String.join("", imageBase64Chunks);
            int commaIdx = joined.indexOf(",");
            String base64Part = joined;
            if (commaIdx >= 0) {
                String prefix = joined.substring(0, commaIdx);
                if (prefix.contains("base64")) {
                    base64Part = joined.substring(commaIdx + 1);
                }
            }

            byte[] bytes = Base64.getDecoder().decode(base64Part);
            Image img = Image.getInstance(bytes);

            applyImageStyle(img, style);

            try {
                int cols = currentRowTable.getNumberOfColumns();
                float usableWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
                float columnWidth = usableWidth / Math.max(1, cols);
                float usableHeight = document.getPageSize().getHeight() - document.topMargin() - document.bottomMargin();
                float maxImgWidth = columnWidth - 2f;
                float maxImgHeight = Math.max(usableHeight - 60f, 50f);
                if (img.getScaledWidth() > maxImgWidth || img.getScaledHeight() > maxImgHeight) {
                    img.scaleToFit(maxImgWidth, maxImgHeight);
                }

                if (img.getScaledHeight() > usableHeight - 20f) {
                    PdfPTable single = new PdfPTable(1);
                    single.setWidthPercentage(100);

                    PdfPCell capCell = new PdfPCell(new Phrase(caption == null ? "" : caption, resolveFont(new TextStyle().bold())));
                    capCell.setBorder(PdfPCell.NO_BORDER);
                    capCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    capCell.setPaddingBottom(2f);
                    single.addCell(capCell);

                    PdfPCell imgCell = new PdfPCell();
                    imgCell.setBorder(PdfPCell.NO_BORDER);
                    imgCell.addElement(img);
                    single.addCell(imgCell);

                    try {
                        document.add(single);
                    } catch (DocumentException de) {
                        throw new IOException(de);
                    }

                    currentRowTable = null;
                    return;
                }
            } catch (Exception ignored) {}

            PdfPCell cell = new PdfPCell();
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setPaddingTop(4f);
            cell.setPaddingBottom(4f);
            cell.setPaddingLeft(0f);
            cell.setPaddingRight(0f);
            Paragraph p = new Paragraph(caption == null ? "" : caption, resolveFont(new TextStyle().bold()));
            p.setAlignment(Element.ALIGN_CENTER);
            p.setSpacingAfter(2f);
            cell.addElement(p);
            cell.addElement(img);
            currentRowTable.addCell(cell);
        } catch (IllegalArgumentException iae) {
            throw new IOException("Invalid Base64 image data", iae);
        } catch (Exception e) {
            throw new IOException(e);
        }
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
            Image img;
            if (imageResource == null || imageResource.isBlank()) {
                return;
            }

            if (imageResource.startsWith("http://") || imageResource.startsWith("https://")) {
                img = Image.getInstance(java.net.URI.create(imageResource).toURL());
            } else {
                java.net.URL resourceUrl = ClassLoader.getSystemResource(imageResource);
                if (resourceUrl != null) {
                    img = Image.getInstance(Paths.get(resourceUrl.toURI()).toString());
                } else {
                    img = Image.getInstance(imageResource);
                }
            }

            applyImageStyle(img, style);

            try {
                float usableWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
                float usableHeight = document.getPageSize().getHeight() - document.topMargin() - document.bottomMargin();
                float maxImgWidth = usableWidth - 2f;
                float maxImgHeight = Math.max(usableHeight - 60f, 50f);
                if (img.getScaledWidth() > maxImgWidth || img.getScaledHeight() > maxImgHeight) {
                    img.scaleToFit(maxImgWidth, maxImgHeight);
                }
            } catch (Exception ignored) {}

            document.add(img);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    public void addImage(String[] imageBase64Chunks, ImageStyle style) throws IOException {
        try {
            if (imageBase64Chunks == null || imageBase64Chunks.length == 0) return;

            String joined = String.join("", imageBase64Chunks);
            int commaIdx = joined.indexOf(",");
            String base64Part = joined;
            if (commaIdx >= 0) {
                String prefix = joined.substring(0, commaIdx);
                if (prefix.contains("base64")) {
                    base64Part = joined.substring(commaIdx + 1);
                }
            }

            byte[] bytes = Base64.getDecoder().decode(base64Part);
            Image img = Image.getInstance(bytes);

            applyImageStyle(img, style);

            try {
                float usableWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
                float usableHeight = document.getPageSize().getHeight() - document.topMargin() - document.bottomMargin();
                float maxImgWidth = usableWidth - 2f;
                float maxImgHeight = Math.max(usableHeight - 60f, 50f);
                if (img.getScaledWidth() > maxImgWidth || img.getScaledHeight() > maxImgHeight) {
                    img.scaleToFit(maxImgWidth, maxImgHeight);
                }
            } catch (Exception ignored) {}

            document.add(img);
        } catch (IllegalArgumentException iae) {
            throw new IOException("Invalid Base64 image data", iae);
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
        if (s.length() == 3) {
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
