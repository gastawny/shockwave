package com.gastawny.shockwave.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "object_formula_parameters")
@Getter
@Setter
public class ObjectFormulaParameter {

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
    private ObjectFormat objectFormat;
}
