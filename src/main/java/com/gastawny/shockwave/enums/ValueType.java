package com.gastawny.shockwave.enums;

public enum ValueType {

    STRING("STR"),
    NUMBER("NUM"),
    TEXT("TEXT");

    private final String value;

    ValueType(String value) {
        this.value = value;
    }
}
