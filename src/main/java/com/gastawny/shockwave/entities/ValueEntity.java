package com.gastawny.shockwave.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ValueEntity {

    @Serial
    private static final long serialVersionUID = 1L;

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

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValueEntity that = (ValueEntity) o;
        return Objects.equals(dataId, that.dataId) && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataId, data);
    }
}
