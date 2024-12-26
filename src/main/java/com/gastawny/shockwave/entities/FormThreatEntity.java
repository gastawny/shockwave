package com.gastawny.shockwave.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "form_threats")
@Getter
@Setter
public class FormThreatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "form_threat_id")
    private Long id;

    @Column
    private String name;
}
