package com.gastawny.shockwave.entities;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class ExplosiveDataTypeId {

    private Long explosiveId;

    private Long dataTypeId;

    public ExplosiveDataTypeId() { }

    public ExplosiveDataTypeId(Long explosiveId, Long dataTypeId) {
        this.explosiveId = explosiveId;
        this.dataTypeId = dataTypeId;
    }

    public Long getExplosiveId() {
        return explosiveId;
    }

    public void setExplosiveId(Long explosiveId) {
        this.explosiveId = explosiveId;
    }

    public Long getDataTypeId() {
        return dataTypeId;
    }

    public void setDataTypeId(Long dataTypeId) {
        this.dataTypeId = dataTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExplosiveDataTypeId that = (ExplosiveDataTypeId) o;
        return Objects.equals(explosiveId, that.explosiveId) && Objects.equals(dataTypeId, that.dataTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(explosiveId, dataTypeId);
    }
}
