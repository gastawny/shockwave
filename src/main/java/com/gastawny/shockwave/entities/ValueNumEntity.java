package com.gastawny.shockwave.entities;

import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
public class ValueNumEntity extends ValueEntity{

    private Double value;

    public ValueNumEntity() { }

    public ValueNumEntity(Long dataId, DataEntity data, Double value) {
        super(dataId, data);
        this.value = value;
    }
}
