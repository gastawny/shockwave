package com.gastawny.shockwave.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "explosive_data_type")
@Getter
@Setter
public class ExplosiveDataType {

    @EmbeddedId
    private ExplosiveDataTypeId id;

    @ManyToOne
    @MapsId("explosiveId")
    @JoinColumn(name = "explosive_id", referencedColumnName = "explosive_id")
    private Explosive explosive;

    @ManyToOne
    @MapsId("dataTypeId")
    @JoinColumn(name = "data_type_id", referencedColumnName = "data_type_id")
    private DataType dataType;

    @Column(name = "sequence", nullable = false)
    private Byte sequence;
}
