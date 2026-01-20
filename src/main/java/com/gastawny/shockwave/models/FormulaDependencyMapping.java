package com.gastawny.shockwave.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "formula_dependency_mappings")
@Getter
@Setter
@EqualsAndHashCode
public class FormulaDependencyMapping {

    @EmbeddedId
    private FormulaDependencyMappingId id;

    @JsonIgnore
    @ManyToOne
    @MapsId("FormulaDependencyId")
    @JoinColumn(name = "formula_dependency_id", referencedColumnName = "formula_dependency_id")
    private FormulaDependency dependency;

    @ManyToOne
    @MapsId("FormulaId")
    @JoinColumn(name = "formula_id", referencedColumnName = "formula_id")
    private Formula formula;

    @Column(nullable = false)
    private Long referenceId;
}
