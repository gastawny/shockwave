package com.gastawny.shockwave.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.util.Objects;

@Entity
@Table(name = "grounds")
public class GroundEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ground_id")
    private Long id;

    @Column
    private String k;

    public GroundEntity() { }

    public GroundEntity(Long id, String k) {
        this.id = id;
        this.k = k;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroundEntity that = (GroundEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(k, that.k);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, k);
    }
}
