package com.gastawny.shockwave.shared.exports.pdfs.styles;

public class TextSpan {

    private final String text;
    private final TextStyle style;

    public TextSpan(String text, TextStyle style) {
        this.text = text;
        this.style = style;
    }

    public String getText() {
        return text;
    }

    public TextStyle getStyle() {
        return style;
    }

    public static TextSpan of(String text) {
        return new TextSpan(text, null);
    }

    public static TextSpan of(String text, TextStyle style) {
        return new TextSpan(text, style);
    }
}
