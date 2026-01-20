package com.gastawny.shockwave.shared.exports.pdfs;

import com.gastawny.shockwave.shared.exports.pdfs.styles.CellStyle;
import com.gastawny.shockwave.shared.exports.pdfs.styles.ImageStyle;
import com.gastawny.shockwave.shared.exports.pdfs.styles.TextSpan;
import com.gastawny.shockwave.shared.exports.pdfs.styles.TextStyle;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public interface PdfExporter {

    void start(OutputStream outputStream) throws IOException;

    void addText(String text);
    void addText(String text, TextStyle style);

    void addInlineText(List<TextSpan> spans);

    void startTable(int columns);

    void addTableHeader(String text);
    void addTableHeader(String text, CellStyle style);

    void addTableCell(String text);
    void addTableCell(String text, CellStyle style);

    void addImage(String imageResource, ImageStyle style) throws IOException;

    // Separator: horizontal line
    void addSeparator();

    // Simple row/grid API: start a row with N columns, add cells, then end the row
    void startRow(int columns);
    void addRowCell(String text);
    void addRowCell(String text, TextStyle style);
    void endRow();

    void finish();
}