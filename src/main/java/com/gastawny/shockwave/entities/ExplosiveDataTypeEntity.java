package com.gastawny.shockwave.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.util.Objects;

@Entity
@Table(name = "explosive_data_type")
public class ExplosiveDataTypeEntity {

    @Serial
    private static final long serialVersionUID = 1L;

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

    public ExplosiveDataTypeEntity() { }

    public ExplosiveDataTypeEntity(ExplosiveDataTypeId id, ExplosiveEntity explosive, DataTypeEntity dataType, Byte sequence) {
        this.id = id;
        this.explosive = explosive;
        this.dataType = dataType;
        this.sequence = sequence;
    }

    public ExplosiveDataTypeId getId() {
        return id;
    }

    public void setId(ExplosiveDataTypeId id) {
        this.id = id;
    }

    public ExplosiveEntity getExplosive() {
        return explosive;
    }

    public void setExplosive(ExplosiveEntity explosive) {
        this.explosive = explosive;
    }

    public DataTypeEntity getDataType() {
        return dataType;
    }

    public void setDataType(DataTypeEntity dataType) {
        this.dataType = dataType;
    }

    public Byte getSequence() {
        return sequence;
    }

    public void setSequence(Byte sequence) {
        this.sequence = sequence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExplosiveDataTypeEntity that = (ExplosiveDataTypeEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(explosive, that.explosive) && Objects.equals(dataType, that.dataType) && Objects.equals(sequence, that.sequence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, explosive, dataType, sequence);
    }
}
