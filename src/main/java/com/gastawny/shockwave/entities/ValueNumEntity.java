package com.gastawny.shockwave.entities;

import jakarta.persistence.Entity;

import java.io.Serial;
import java.util.Objects;

@Entity
public class ValueNumEntity extends ValueEntity{

    @Serial
    private static final long serialVersionUID = 1L;

    private Double value;

    public ValueNumEntity() { }

    public ValueNumEntity(Long dataId, DataEntity data, Double value) {
        super(dataId, data);
        this.value = value;
    }

    public ValueNumEntity(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ValueNumEntity that = (ValueNumEntity) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), value);
    }
}
