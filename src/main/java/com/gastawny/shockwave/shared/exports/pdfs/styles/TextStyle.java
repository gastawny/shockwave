package com.gastawny.shockwave.shared.exports.pdfs.styles;

public class TextStyle {

    private Integer fontSize;
    private Boolean bold;
    private Boolean italic;
    private Integer horizontalAlignment;
    private Integer verticalAlignment;

    public Integer getFontSize() {
        return fontSize;
    }

    public TextStyle fontSize(int size) {
        this.fontSize = size;
        return this;
    }

    public Boolean getBold() {
        return bold;
    }

    public TextStyle bold() {
        this.bold = true;
        return this;
    }

    public Boolean getItalic() {
        return italic;
    }

    public TextStyle italic() {
        this.italic = true;
        return this;
    }

    public Integer getHorizontalAlignment() {
        return horizontalAlignment;
    }

    public TextStyle alignHorizontal(int align) {
        this.horizontalAlignment = align;
        return this;
    }

    public Integer getVerticalAlignment() {
        return verticalAlignment;
    }

    public TextStyle alignVertical(int align) {
        this.verticalAlignment = align;
        return this;
    }
}
