package com.gastawny.shockwave.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "explosives")
public class ExplosiveEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "explosive_id")
    private Long id;

    @Column
    private String name;

    public ExplosiveEntity() { }

    public ExplosiveEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExplosiveEntity that = (ExplosiveEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
