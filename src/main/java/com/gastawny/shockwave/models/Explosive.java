package com.gastawny.shockwave.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "explosives")
@Getter
@Setter
public class Explosive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "explosive_id")
    private Long id;

    @Column
    private String name;
}
