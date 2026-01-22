package com.gastawny.shockwave.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gastawny.shockwave.shared.enums.ValueType;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "parameters")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Parameter extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parameter_id")
    private Long id;

    @Column(length = 50, unique = true)
    private String symbol;

    @Column(length = 100, unique = true)
    private String name;

    @Column(length = 20)
    private String unit;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ValueType valueType;

    @JsonIgnore
    @OneToOne(mappedBy = "parameter", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private ParameterDependency dependency;

    @JsonIgnore
    @Column(length = 50)
    private String table;
}
