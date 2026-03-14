package com.gastawny.shockwave.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity(name = "formulas")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class Formula extends BaseModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "formula_id")
    private Long id;

    @Column(unique = true)
    private String name;

    @Column
    private String expression;

    @Column
    private String unit;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "formula_parameters",
            joinColumns = {@JoinColumn(name = "formula_id")}, inverseJoinColumns = {@JoinColumn(name = "parameter_id")}
    )
    private List<Parameter> parameters;

    @OneToMany(
            mappedBy = "mainFormula",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<FormulaComposition> components;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "formula_constants",
            joinColumns = {@JoinColumn(name = "formula_id")}, inverseJoinColumns = {@JoinColumn(name = "constant_id")}
    )
    private List<Constant> constants;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "formula_formula_dependencies",
            joinColumns = {@JoinColumn(name = "formula_id")}, inverseJoinColumns = {@JoinColumn(name = "formula_dependency_id")}
    )
    private List<FormulaDependency> dependencies;

    @OneToOne(mappedBy = "formula", orphanRemoval = true)
    private FormulaDecision decision;

    @OneToOne(mappedBy = "formula", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private FormulaCircle circle;

    @JsonIgnore
    @OneToMany(
            mappedBy = "formula",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<FormulaTable> table;
}
