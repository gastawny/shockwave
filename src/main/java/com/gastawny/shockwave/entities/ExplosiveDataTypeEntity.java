package com.gastawny.shockwave.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "explosive_data_type")
@Getter
@Setter
public class ExplosiveDataTypeEntity {

    @EmbeddedId
    private ExplosiveDataTypeId id;

    @ManyToOne
    @MapsId("explosiveId")
    @JoinColumn(name = "explosive_id", referencedColumnName = "explosive_id")
    private ExplosiveEntity explosive;

    @ManyToOne
    @MapsId("dataTypeId")
    @JoinColumn(name = "data_type_id", referencedColumnName = "data_type_id")
    private DataTypeEntity dataType;

    @Column(name = "sequence", nullable = false)
    private Byte sequence;
}
