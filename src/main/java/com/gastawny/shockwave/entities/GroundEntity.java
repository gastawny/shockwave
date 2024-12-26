package com.gastawny.shockwave.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "grounds")
@Getter
@Setter
public class GroundEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ground_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private Double k;
}
