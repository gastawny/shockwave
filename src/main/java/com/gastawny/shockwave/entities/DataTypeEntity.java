package com.gastawny.shockwave.entities;

import com.gastawny.shockwave.data.enums.ValueType;
import jakarta.persistence.*;

import java.io.Serial;
import java.util.Objects;

@Entity
@Table(name = "data_types")
public class DataTypeEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "data_type_id")
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column(name = "is_multiple")
    private boolean isMultiple;

    @Enumerated(EnumType.STRING)
    private ValueType valueType;

    public DataTypeEntity() { }

    public DataTypeEntity(Long id, String name, String description, boolean isMultiple, ValueType valueType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isMultiple = isMultiple;
        this.valueType = valueType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isMultiple() {
        return isMultiple;
    }

    public void setMultiple(boolean multiple) {
        isMultiple = multiple;
    }

    public ValueType getValueType() {
        return valueType;
    }

    public void setValueType(ValueType valueType) {
        this.valueType = valueType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataTypeEntity that = (DataTypeEntity) o;
        return isMultiple == that.isMultiple && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && valueType == that.valueType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, isMultiple, valueType);
    }
}
