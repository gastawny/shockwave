package com.gastawny.shockwave.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "constants")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Constant extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "constant_id")
    private Long id;

    @Column(length = 50, unique = true)
    private String symbol;

    @Column
    private String name;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "value_id")
    private Value value;

    @Column(length = 20)
    private String unit;
}
