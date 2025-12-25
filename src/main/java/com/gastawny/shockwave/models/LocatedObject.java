package com.gastawny.shockwave.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "located_objects")
@Getter
@Setter
public class LocatedObject extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "located_object_id")
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "explosive_id")
    private Explosive explosive;

    @ManyToOne
    @JoinColumn(name = "ground_id")
    private Ground ground;

    @ManyToOne
    @JoinColumn(name = "object_format_id")
    private ObjectFormat objectFormat;

    @OneToMany(
            mappedBy = "locatedObject",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<ObjectFormatParameterValue> objectFormatParameterValues = new ArrayList<>();

    public void addObjectFormatParameterValue(ObjectFormatParameterValue value) {
        objectFormatParameterValues.add(value);
        value.setLocatedObject(this);
    }
}
