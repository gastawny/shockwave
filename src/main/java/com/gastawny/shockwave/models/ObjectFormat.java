package com.gastawny.shockwave.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "object_formats")
@Getter
@Setter
public class ObjectFormat extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "object_format_id")
    private Long id;

    @OneToMany(mappedBy = "objectFormat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ObjectFormulaParameter> objectFormulaParameters;

    @Column
    private String name;
}
