package com.gastawny.shockwave.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity(name = "formula_decision")
@Getter
@Setter
@ToString
public class FormulaDecision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "formula_decision_id")
    private Long id;

    @JsonIgnore
    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "formula_id")
    private Formula formula;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "formula_decision_mappings",
            joinColumns = @JoinColumn(name = "formula_decision_id"),
            inverseJoinColumns = @JoinColumn(name = "formula_id")
    )
    private List<Formula> choices;
}
