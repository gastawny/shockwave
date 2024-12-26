package com.gastawny.shockwave.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.util.Objects;

@Entity
@Table(name = "datas")
public class DataEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "data_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "data_type_id", nullable = false)
    private DataTypeEntity dataType;

    @ManyToOne
    @JoinColumn(name = "located_object_id", nullable = false)
    private LocatedObjectEntity locatedObject;

    @OneToOne(mappedBy = "data", cascade = CascadeType.ALL)
    private ValueEntity value;

    public DataEntity() { }

    public DataEntity(Long id, DataTypeEntity dataType, LocatedObjectEntity locatedObject, ValueEntity value) {
        this.id = id;
        this.dataType = dataType;
        this.locatedObject = locatedObject;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DataTypeEntity getDataType() {
        return dataType;
    }

    public void setDataType(DataTypeEntity dataType) {
        this.dataType = dataType;
    }

    public LocatedObjectEntity getLocatedObject() {
        return locatedObject;
    }

    public void setLocatedObject(LocatedObjectEntity locatedObject) {
        this.locatedObject = locatedObject;
    }

    public ValueEntity getValue() {
        return value;
    }

    public void setValue(ValueEntity value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataEntity that = (DataEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(dataType, that.dataType) && Objects.equals(locatedObject, that.locatedObject) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataType, locatedObject, value);
    }
}
