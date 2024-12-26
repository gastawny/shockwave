package com.gastawny.shockwave.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ValueTextEntity extends ValueEntity{

    private String value;

    public ValueTextEntity() { }

    public ValueTextEntity(Long dataId, DataEntity data, String value) {
        super(dataId, data);
        this.value = value;
    }
}
