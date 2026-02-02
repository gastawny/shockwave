package com.gastawny.shockwave.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "formula_tables")
@Getter
@Setter
@ToString
public class FormulaTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tableName;

    @ManyToOne
    @JoinColumn(name = "formula_id")
    private Formula formula;
}
