package com.gastawny.shockwave.entities;

import jakarta.persistence.Entity;

import java.io.Serial;
import java.util.Objects;

@Entity
public class ValueStrEntity extends ValueEntity{

    @Serial
    private static final long serialVersionUID = 1L;

    private String value;

    public ValueStrEntity() { }

    public ValueStrEntity(Long dataId, DataEntity data, String value) {
        super(dataId, data);
        this.value = value;
    }

    public ValueStrEntity(String value) {
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
        ValueStrEntity that = (ValueStrEntity) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), value);
    }
}
