package com.gastawny.shockwave.shared.enums;

import java.util.Set;

public enum ValueType {

    STRING("STR"),
    NUMBER("NUM"),
    TEXT("TEXT");

    private final String value;

    ValueType(String value) {
        this.value = value;
    }

    private static final Set<String> NUM_TYPES = Set.of(
            "Long", "Integer", "Double", "Float", "Short", "Byte"
    );

    private static final Set<String> STR_TYPES = Set.of(
            "String"
    );

    public static ValueType fromString(String value) {
        if (value == null) return null;

        String normalized = value.trim();

        if (STR_TYPES.contains(normalized)) {
            return STRING;
        }

        if (NUM_TYPES.contains(normalized)) {
            return NUMBER;
        }

        return null;
    }

}
