package com.gastawny.shockwave.entities;

import jakarta.persistence.Entity;

import java.io.Serial;
import java.util.Objects;

@Entity
public class ValueTextEntity extends ValueEntity{

    @Serial
    private static final long serialVersionUID = 1L;

    private String value;

    public ValueTextEntity() { }

    public ValueTextEntity(Long dataId, DataEntity data, String value) {
        super(dataId, data);
        this.value = value;
    }

    public ValueTextEntity(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ValueTextEntity that = (ValueTextEntity) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), value);
    }
}
