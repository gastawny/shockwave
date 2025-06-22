package com.gastawny.shockwave.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "parameters")
@Getter
@Setter
public class Parameter extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parameter_id")
    private Long id;

    @Column(length = 50, unique = true)
    private String symbol;

    @Column
    private String name;

    @Column(length = 20)
    private String unit;
}
