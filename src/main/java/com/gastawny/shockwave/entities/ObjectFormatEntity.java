package com.gastawny.shockwave.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "object_formats")
public class ObjectFormatEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "object_format_id")
    private Long id;

    @OneToMany(mappedBy = "objectFormat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ObjectFormulaParameterEntity> objectFormulaParameters;

    @Column
    private String name;

    public ObjectFormatEntity() { }

    public ObjectFormatEntity(Long id, List<ObjectFormulaParameterEntity> objectFormulaParameters, String name) {
        this.id = id;
        this.objectFormulaParameters = objectFormulaParameters;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ObjectFormulaParameterEntity> getObjectFormulaParameters() {
        return objectFormulaParameters;
    }

    public void setObjectFormulaParameters(List<ObjectFormulaParameterEntity> objectFormulaParameters) {
        this.objectFormulaParameters = objectFormulaParameters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectFormatEntity that = (ObjectFormatEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(objectFormulaParameters, that.objectFormulaParameters) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, objectFormulaParameters, name);
    }
}
