package com.gastawny.shockwave.models;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("string")
public class ValueStr extends Value {

    @Column(name = "value")
    private String val;

    public ValueStr() { }

    @Override
    public Object getValue() {
        return val;
    }

    @Override
    public void setRaw(Object v) {
        this.val = v == null ? null : v.toString();
    }
}
