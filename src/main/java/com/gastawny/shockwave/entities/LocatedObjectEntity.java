package com.gastawny.shockwave.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.util.Objects;

@Entity
@Table(name = "located_objects")
public class LocatedObjectEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "located_object_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "explosive_id")
    private ExplosiveEntity explosive;

    @ManyToOne
    @JoinColumn(name = "ground_id")
    private GroundEntity ground;

    public LocatedObjectEntity() { }

    public LocatedObjectEntity(Long id, ExplosiveEntity explosive, GroundEntity ground) {
        this.id = id;
        this.explosive = explosive;
        this.ground = ground;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ExplosiveEntity getExplosive() {
        return explosive;
    }

    public void setExplosive(ExplosiveEntity explosive) {
        this.explosive = explosive;
    }

    public GroundEntity getGround() {
        return ground;
    }

    public void setGround(GroundEntity ground) {
        this.ground = ground;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocatedObjectEntity that = (LocatedObjectEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(explosive, that.explosive) && Objects.equals(ground, that.ground);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, explosive, ground);
    }
}
