package com.gastawny.shockwave.models;

import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
public class ValueNum extends Value {

    private Double value;

    public ValueNum() { }

    public ValueNum(Long dataId, Data data, Double value) {
        super(dataId, data);
        this.value = value;
    }
}
