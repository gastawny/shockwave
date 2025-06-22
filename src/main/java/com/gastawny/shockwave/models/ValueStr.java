package com.gastawny.shockwave.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ValueStr extends Value {

    private String value;

    public ValueStr() { }

    public ValueStr(Long valueId, String value) {
        super(valueId);
        this.value = value;
    }
}
