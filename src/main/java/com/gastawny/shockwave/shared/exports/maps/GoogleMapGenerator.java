package com.gastawny.shockwave.shared.exports.maps;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class GoogleMapGenerator {

    // Default values from the React component
    private static final double DEFAULT_LAT = -25.432120740315266;
    private static final double DEFAULT_LNG = -49.31332254701506;
    private static final int DEFAULT_ZOOM = 12;
    private static final String DEFAULT_MAP_TYPE = "roadmap";
    private static final String DEFAULT_SIZE = "600x300";

    /**
     * Generates a URL for the Google Maps Static API.
     *
     * @param lat       The latitude for the center of the map.
     * @param lng       The longitude for the center of the map.
     * @param zoom      The zoom level of the map.
     * @param size      The size of the map image (e.g., "600x300").
     * @param mapType   The type of map (e.g., "roadmap", "satellite").
     * @return The generated URL.
     */
    // Backwards-compatible overload: no apiKey passed — resolver will find configured key
    public static String generateMapUrl(double lat, double lng, int zoom, String size, String mapType) {
        return generateMapUrl((String) null, lat, lng, zoom, size, mapType);
    }

    public static String generateMapUrl(String apiKey, double lat, double lng, int zoom, String size, String mapType) {
        try {
            String effectiveApiKey = resolveApiKey(apiKey);
            String center = lat + "," + lng;
            String encodedCenter = URLEncoder.encode(center, "UTF-8");

            StringBuilder urlBuilder = new StringBuilder("https://maps.googleapis.com/maps/api/staticmap");
            urlBuilder.append("?center=").append(encodedCenter);
            urlBuilder.append("&zoom=").append(zoom);
            urlBuilder.append("&size=").append(size);
            urlBuilder.append("&maptype=").append(mapType);
            urlBuilder.append("&markers=color:red%7C").append(encodedCenter);
            if (effectiveApiKey != null && !effectiveApiKey.isBlank()) {
                urlBuilder.append("&key=").append(effectiveApiKey);
            }

            return urlBuilder.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Generate map URL with optional circles to draw (approximated as polygons).
     */
    public static String generateMapUrl(String apiKey, double lat, double lng, int zoom, String size, String mapType, List<CircleConfig> circles) {
        try {
            String effectiveApiKey = resolveApiKey(apiKey);
            String center = lat + "," + lng;
            String encodedCenter = URLEncoder.encode(center, "UTF-8");

            if(zoom == 0 && circles != null && !circles.isEmpty()) {
                int biggestRadius = (int) Math.ceil(circles.stream()
                        .max((c1, c2) -> Double.compare(c1.getRadius(), c2.getRadius()))
                        .orElse(null)
                        .getRadius());

                if (biggestRadius < 15) {
                    zoom = 20;
                }else if (biggestRadius < 25) {
                    zoom = 19;
                } else if (biggestRadius < 50) {
                    zoom = 18;
                } else if (biggestRadius < 100) {
                    zoom = 17;
                } else if (biggestRadius < 250) {
                    zoom = 16;
                } else if (biggestRadius < 500) {
                    zoom = 15;
                } else if (biggestRadius < 1000) {
                    zoom = 14;
                } else if (biggestRadius < 5000) {
                    zoom = 12;
                } else if (biggestRadius < 10000) {
                    zoom = 11;
                } else {
                    zoom = 10;
                }
            }

            StringBuilder urlBuilder = new StringBuilder("https://maps.googleapis.com/maps/api/staticmap");
            urlBuilder.append("?center=").append(encodedCenter);
            urlBuilder.append("&zoom=").append(zoom);
            urlBuilder.append("&size=").append(size);
            urlBuilder.append("&maptype=").append(mapType);
            urlBuilder.append("&markers=color:red%7C").append(encodedCenter);
            // append key later only if available (avoid duplicate or empty key param)

            // add circle(s) as encoded path(s)
            if (circles != null && !circles.isEmpty()) {
                for (CircleConfig circle : circles) {
                    String path = circleToPath(circle);
                    if (path != null && !path.isEmpty()) {
                        urlBuilder.append("&path=").append(URLEncoder.encode(path, "UTF-8"));
                    }
                }
            }

            if (effectiveApiKey != null && !effectiveApiKey.isBlank()) {
                urlBuilder.append("&key=").append(effectiveApiKey);
            }

            return urlBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    // Backwards-compatible overload with circles
    public static String generateMapUrl(double lat, double lng, int zoom, String size, String mapType, List<CircleConfig> circles) {
        return generateMapUrl(null, lat, lng, zoom, size, mapType, circles);
    }

    private static String circleToPath(CircleConfig circle) {
        if (circle == null) return "";

        // default values
        String fillColor = circle.getFillColor() != null ? circle.getFillColor() : "#FF0000";
        String strokeColor = circle.getStrokeColor() != null ? circle.getStrokeColor() : "#FF0000";
        int strokeWeight = circle.getStrokeWeight() != null ? circle.getStrokeWeight() : 2;
        double fillOpacity = circle.getFillOpacity() != null ? circle.getFillOpacity() : 0.2;

        String fillHex = toGoogleHexWithAlpha(fillColor, fillOpacity);
        String strokeHex = toGoogleHex(strokeColor);

        GeoLocation center = circle.getCenter();
        if (center == null) return "";

        // approximate circle with polygon of N points
        int points = 36;
        StringBuilder sb = new StringBuilder();
        sb.append("fillcolor:").append(fillHex).append("|");
        sb.append("color:").append(strokeHex).append("|");
        sb.append("weight:").append(strokeWeight).append("|");

        // generate points around the circle
        double centerLat = center.getLat();
        double centerLng = center.getLng();
        double radiusMeters = circle.getRadius();

        for (int i = 0; i < points; i++) {
            double angle = Math.toRadians(((double) i / points) * 360.0);
            double[] latLng = destinationPoint(centerLat, centerLng, angle, radiusMeters);
            sb.append(latLng[0]).append(",").append(latLng[1]);
            if (i < points - 1) sb.append("|");
        }

        return sb.toString();
    }

    private static String toGoogleHex(String hex) {
        if (hex == null) return "0xFF0000"; // default
        String s = hex.trim();
        if (s.startsWith("#")) s = s.substring(1);
        if (s.length() == 3) s = "" + s.charAt(0) + s.charAt(0) + s.charAt(1) + s.charAt(1) + s.charAt(2) + s.charAt(2);
        if (s.length() != 6) return "0xFF0000";
        return "0x" + s.toUpperCase();
    }

    private static String toGoogleHexWithAlpha(String hex, double opacity) {
        String base = toGoogleHex(hex); // returns like 0xRRGGBB
        // compute alpha (00..FF)
        int alpha = (int) Math.round(opacity * 255);
        if (alpha < 0) alpha = 0;
        if (alpha > 255) alpha = 255;
        String alphaHex = String.format("%02X", alpha);
        // Google Static Map expects 0xRRGGBBAA for fillcolor
        return base + alphaHex;
    }

    // Haversine-based destination point: given start lat/lng, bearing (radians) and distance (meters)
    private static double[] destinationPoint(double lat, double lng, double bearingRad, double distanceMeters) {
        double R = 6378137.0; // Earth radius in meters (WGS84)
        double dDivR = distanceMeters / R;
        double latRad = Math.toRadians(lat);
        double lngRad = Math.toRadians(lng);

        double destLat = Math.asin(Math.sin(latRad) * Math.cos(dDivR) + Math.cos(latRad) * Math.sin(dDivR) * Math.cos(bearingRad));
        double destLng = lngRad + Math.atan2(Math.sin(bearingRad) * Math.sin(dDivR) * Math.cos(latRad), Math.cos(dDivR) - Math.sin(latRad) * Math.sin(destLat));

        return new double[]{Math.toDegrees(destLat), Math.toDegrees(destLng)};
    }

    private static String resolveApiKey(String passed) {
        if (passed != null && !passed.isBlank()) return passed;

        String candidate = "keys.google-maps";

         try (var is = ClassLoader.getSystemResourceAsStream("application.yml")) {
             if (is != null) {
                 try (var br = new java.io.BufferedReader(new java.io.InputStreamReader(is))) {
                     String line;
                     while ((line = br.readLine()) != null) {
                         String trimmed = line.trim();
                             String simple = candidate.contains(".") ? candidate.substring(candidate.lastIndexOf('.') + 1) : candidate;
                             if (trimmed.startsWith(candidate + ":") || trimmed.startsWith(simple + ":") || trimmed.contains(candidate + ":") || trimmed.contains(simple + ":")) {
                                 String[] parts = trimmed.split(":", 2);
                                 if (parts.length == 2) {
                                    String val = parts[1].trim();
                                    if ((val.startsWith("\"") && val.endsWith("\"")) || (val.startsWith("'") && val.endsWith("'"))) {
                                        val = val.substring(1, val.length() - 1);
                                    }
                                    if (!val.isBlank()) return expandPlaceholder(val);
                                 }
                             }
                     }
                 }
             }
         } catch (Exception ignored) { }

         return "";
     }

    private static String expandPlaceholder(String val) {
        if (val == null) return null;
        String s = val.trim();
        // ${ENV_VAR:default}
        if (s.startsWith("${") && s.endsWith("}")) {
            String inner = s.substring(2, s.length() - 1);
            int colon = inner.indexOf(":");
            if (colon > 0) {
                String var = inner.substring(0, colon);
                String def = inner.substring(colon + 1);
                String env = System.getenv(var);
                if (env != null && !env.isBlank()) return env;
                String prop = System.getProperty(var);
                if (prop != null && !prop.isBlank()) return prop;
                return def;
            } else {
                String env = System.getenv(inner);
                if (env != null && !env.isBlank()) return env;
                String prop = System.getProperty(inner);
                if (prop != null && !prop.isBlank()) return prop;
                return "";
            }
        }
        return s;
    }
}
