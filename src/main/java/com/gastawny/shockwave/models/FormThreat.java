package com.gastawny.shockwave.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "form_threats")
@Getter
@Setter
public class FormThreat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "form_threat_id")
    private Long id;

    @Column
    private String name;
}
