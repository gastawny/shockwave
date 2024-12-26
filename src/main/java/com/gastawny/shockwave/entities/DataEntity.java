package com.gastawny.shockwave.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "datas")
@Getter
@Setter
public class DataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "data_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "data_type_id", nullable = false)
    private DataTypeEntity dataType;

    @ManyToOne
    @JoinColumn(name = "explosive_id", nullable = false)
    private ExplosiveEntity explosive;

    @OneToOne(mappedBy = "data", cascade = CascadeType.ALL)
    private ValueEntity value;
}
