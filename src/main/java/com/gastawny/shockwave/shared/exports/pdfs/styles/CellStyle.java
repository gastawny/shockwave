package com.gastawny.shockwave.shared.exports.pdfs.styles;

public class CellStyle {

    private String backgroundColorHex;
    private Integer horizontalAlignment;
    private Integer verticalAlignment;

    public String getBackgroundColorHex() {
        return backgroundColorHex;
    }

    public CellStyle background(String hex) {
        this.backgroundColorHex = hex;
        return this;
    }

    public Integer getHorizontalAlignment() {
        return horizontalAlignment;
    }

    public CellStyle alignHorizontal(int align) {
        this.horizontalAlignment = align;
        return this;
    }

    public Integer getVerticalAlignment() {
        return verticalAlignment;
    }

    public CellStyle alignVertical(int align) {
        this.verticalAlignment = align;
        return this;
    }
}
