package com.gastawny.shockwave.models;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@DiscriminatorValue("number")
public class ValueNum extends Value {

    @Column(name = "value")
    private Double val;

    public ValueNum() { }

    @Override
    public Object getValue() {
        return val;
    }

    @Override
    public void setRaw(Object v) {
        if (v == null) {
            this.val = null;
        } else if (v instanceof Number) {
            this.val = ((Number) v).doubleValue();
        } else {
            try {
                this.val = Double.parseDouble(v.toString());
            } catch (NumberFormatException ex) {
                this.val = null;
            }
        }
    }
}
