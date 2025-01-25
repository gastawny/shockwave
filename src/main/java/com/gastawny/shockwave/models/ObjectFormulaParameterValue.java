package com.gastawny.shockwave.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "object_formula_parameter_values")
@Getter
@Setter
public class ObjectFormulaParameterValue extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "object_formula_parameter_value_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "object_formula_parameter_id")
    private ObjectFormulaParameter objectFormulaParameter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "located_object_id")
    private LocatedObject locatedObject;

    @Column
    private Double value;
}
