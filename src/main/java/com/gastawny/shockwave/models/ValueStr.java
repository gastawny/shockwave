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

    public ValueStr(Long dataId, Data data, String value) {
        super(dataId, data);
        this.value = value;
    }
}
