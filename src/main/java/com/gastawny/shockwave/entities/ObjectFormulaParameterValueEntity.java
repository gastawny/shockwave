package com.gastawny.shockwave.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "object_formula_parameter_values")
@Getter
@Setter
public class ObjectFormulaParameterValueEntity {

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
}
