package com.gastawny.shockwave.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "located_objects")
@Getter
@Setter
public class LocatedObject extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "located_object_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "explosive_id")
    private Explosive explosive;

    @ManyToOne
    @JoinColumn(name = "ground_id")
    private Ground ground;
}
