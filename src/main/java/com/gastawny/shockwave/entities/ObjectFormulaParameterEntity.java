package com.gastawny.shockwave.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.util.Objects;

@Entity
@Table(name = "object_formula_parameters")
public class ObjectFormulaParameterEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "object_formula_parameters_id")
    private Long id;

    @Column
    private Byte sequence;

    @Column
    private String content;

    @Column
    private String description;

    @Column(name = "is_required")
    private Boolean isRequired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "object_format_id")
    private ObjectFormatEntity objectFormat;

    public ObjectFormulaParameterEntity() { }

    public ObjectFormulaParameterEntity(Long id, Byte sequence, String content, String description, Boolean isRequired, ObjectFormatEntity objectFormat) {
        this.id = id;
        this.sequence = sequence;
        this.content = content;
        this.description = description;
        this.isRequired = isRequired;
        this.objectFormat = objectFormat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getSequence() {
        return sequence;
    }

    public void setSequence(Byte sequence) {
        this.sequence = sequence;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getRequired() {
        return isRequired;
    }

    public void setRequired(Boolean required) {
        isRequired = required;
    }

    public ObjectFormatEntity getObjectFormat() {
        return objectFormat;
    }

    public void setObjectFormat(ObjectFormatEntity objectFormat) {
        this.objectFormat = objectFormat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectFormulaParameterEntity that = (ObjectFormulaParameterEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(sequence, that.sequence) && Objects.equals(content, that.content) && Objects.equals(description, that.description) && Objects.equals(isRequired, that.isRequired) && Objects.equals(objectFormat, that.objectFormat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sequence, content, description, isRequired, objectFormat);
    }
}
