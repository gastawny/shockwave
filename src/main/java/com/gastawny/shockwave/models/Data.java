package com.gastawny.shockwave.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "datas")
@Getter
@Setter
public class Data extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "data_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "data_type_id", nullable = false)
    private DataType dataType;

    @ManyToOne
    @JoinColumn(name = "explosive_id", nullable = false)
    private Explosive explosive;

    @OneToOne(mappedBy = "data", cascade = CascadeType.ALL)
    private Value value;
}
