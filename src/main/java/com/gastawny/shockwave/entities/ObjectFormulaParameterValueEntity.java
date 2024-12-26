package com.gastawny.shockwave.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.util.Objects;

@Entity
@Table(name = "object_formula_parameter_values")
public class ObjectFormulaParameterValueEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "object_formula_parameter_value_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "object_formula_parameter_id")
    private ObjectFormulaParameterEntity objectFormulaParameter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "located_object_id")
    private LocatedObjectEntity locatedObject;

    @Column
    private Double value;

    public ObjectFormulaParameterValueEntity() { }

    public ObjectFormulaParameterValueEntity(Long id, ObjectFormulaParameterEntity objectFormulaParameter, LocatedObjectEntity locatedObject, Double value) {
        this.id = id;
        this.objectFormulaParameter = objectFormulaParameter;
        this.locatedObject = locatedObject;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ObjectFormulaParameterEntity getObjectFormulaParameter() {
        return objectFormulaParameter;
    }

    public void setObjectFormulaParameter(ObjectFormulaParameterEntity objectFormulaParameter) {
        this.objectFormulaParameter = objectFormulaParameter;
    }

    public LocatedObjectEntity getLocatedObject() {
        return locatedObject;
    }

    public void setLocatedObject(LocatedObjectEntity locatedObject) {
        this.locatedObject = locatedObject;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectFormulaParameterValueEntity that = (ObjectFormulaParameterValueEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(objectFormulaParameter, that.objectFormulaParameter) && Objects.equals(locatedObject, that.locatedObject) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, objectFormulaParameter, locatedObject, value);
    }
}
