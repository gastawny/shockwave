package com.gastawny.shockwave.shared.exports.maps;

public class CircleConfig {

    private GeoLocation center;
    private double radius; // meters
    private String fillColor;
    private String strokeColor;
    private Integer strokeWeight;
    private Double fillOpacity; // 0..1

    public GeoLocation getCenter() {
        return center;
    }

    public CircleConfig setCenter(GeoLocation center) {
        this.center = center;
        return this;
    }

    public double getRadius() {
        return radius;
    }

    public CircleConfig setRadius(double radius) {
        this.radius = radius;
        return this;
    }

    public String getFillColor() {
        return fillColor;
    }

    public CircleConfig setFillColor(String fillColor) {
        this.fillColor = fillColor;
        return this;
    }

    public String getStrokeColor() {
        return strokeColor;
    }

    public CircleConfig setStrokeColor(String strokeColor) {
        this.strokeColor = strokeColor;
        return this;
    }

    public Integer getStrokeWeight() {
        return strokeWeight;
    }

    public CircleConfig setStrokeWeight(Integer strokeWeight) {
        this.strokeWeight = strokeWeight;
        return this;
    }

    public Double getFillOpacity() {
        return fillOpacity;
    }

    public CircleConfig setFillOpacity(Double fillOpacity) {
        this.fillOpacity = fillOpacity;
        return this;
    }
}

