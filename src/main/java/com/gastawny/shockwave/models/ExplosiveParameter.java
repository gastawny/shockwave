package com.gastawny.shockwave.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "explosive_parameters")
@Getter
@Setter
public class ExplosiveParameter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "explosive_parameter_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "explosive_id")
    private Explosive explosive;

    @ManyToOne
    @JoinColumn(name = "parameter_id")
    private Parameter parameter;

    @Column(name = "sequence", nullable = false)
    private Byte sequence;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "value_id")
    private Value value;
}
