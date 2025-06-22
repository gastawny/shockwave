package com.gastawny.shockwave.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "formula_compositions")
@Getter
@Setter
public class FormulaComposition {

    @EmbeddedId
    private FormulaCompositionId id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("mainFormulaId")
    @JoinColumn(name = "main_formula_id", referencedColumnName = "formula_id")
    private Formula mainFormula;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("componentFormulaId")
    @JoinColumn(name = "component_formula_id", referencedColumnName = "formula_id")
    private Formula componentFormula;

    @Column(length = 50, nullable = false)
    private String alias;
}