package com.gastawny.shockwave.shared.exports.maps;

public class GeoLocation {
    private double lat;
    private double lng;

    public GeoLocation() {}

    public GeoLocation(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public GeoLocation setLat(double lat) {
        this.lat = lat;
        return this;
    }

    public double getLng() {
        return lng;
    }

    public GeoLocation setLng(double lng) {
        this.lng = lng;
        return this;
    }
}

