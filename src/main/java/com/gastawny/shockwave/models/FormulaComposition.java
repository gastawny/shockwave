package com.gastawny.shockwave.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
        name = "formula_compositions",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_main_component_formula",
                        columnNames = {"main_formula_id", "component_formula_id"}
                )
        }
)
@Getter
@Setter
public class FormulaComposition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "formula_composition_id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "main_formula_id", referencedColumnName = "formula_id", nullable = false)
    private Formula mainFormula;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "component_formula_id", referencedColumnName = "formula_id", nullable = false)
    private Formula componentFormula;

    @Column(length = 50, nullable = false)
    private String alias;
}
