package com.gastawny.shockwave.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class ValueEntity {

    @Id
    private Long dataId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "data_id")
    private DataEntity data;

    public ValueEntity() { }

    public ValueEntity(Long dataId, DataEntity data) {
        this.dataId = dataId;
        this.data = data;
    }
}
