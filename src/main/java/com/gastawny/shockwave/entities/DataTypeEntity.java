package com.gastawny.shockwave.entities;

import com.gastawny.shockwave.data.enums.ValueType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "data_types")
@Getter
@Setter
public class DataTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "data_type_id")
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    private ValueType valueType;
}
