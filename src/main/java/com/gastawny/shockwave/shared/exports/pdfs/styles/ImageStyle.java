package com.gastawny.shockwave.shared.exports.pdfs.styles;

public class ImageStyle {

    private Integer scalePercent;
    private Integer alignment;

    public Integer getScalePercent() {
        return scalePercent;
    }

    public ImageStyle scale(int percent) {
        this.scalePercent = percent;
        return this;
    }

    public Integer getAlignment() {
        return alignment;
    }

    public ImageStyle align(int alignment) {
        this.alignment = alignment;
        return this;
    }
}
