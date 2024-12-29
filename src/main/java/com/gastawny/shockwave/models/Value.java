package com.gastawny.shockwave.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class Value {

    @Id
    private Long dataId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "data_id")
    private Data data;

    public Value() { }

    public Value(Long dataId, Data data) {
        this.dataId = dataId;
        this.data = data;
    }
}
