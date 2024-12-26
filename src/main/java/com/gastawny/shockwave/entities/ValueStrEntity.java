package com.gastawny.shockwave.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ValueStrEntity extends ValueEntity{

    private String value;

    public ValueStrEntity() { }

    public ValueStrEntity(Long dataId, DataEntity data, String value) {
        super(dataId, data);
        this.value = value;
    }
}
