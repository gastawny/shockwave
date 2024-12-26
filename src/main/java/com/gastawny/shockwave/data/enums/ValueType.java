package com.gastawny.shockwave.data.enums;

public enum ValueType {

    STRING("str"),
    NUMBER("num"),
    TEXT("text");

    private final String value;

    ValueType(String value) {
        this.value = value;
    }
}
