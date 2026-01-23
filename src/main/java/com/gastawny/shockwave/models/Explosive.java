package com.gastawny.shockwave.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity(name = "explosives")
@Getter
@Setter
@ToString(exclude = "explosiveParameters")
public class Explosive extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "explosive_id")
    private Long id;

    @Column
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "explosive", cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<ExplosiveParameter> explosiveParameters;

    public void addExplosiveParameter(ExplosiveParameter value) {
        explosiveParameters.add(value);
        value.setExplosive(this);
    }
}
