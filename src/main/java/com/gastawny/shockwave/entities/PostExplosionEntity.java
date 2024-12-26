package com.gastawny.shockwave.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "post_explosions")
public class PostExplosionEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_explosion_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "vestige_distance")
    private Double vestigeDistance;

    @Column
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "post_explosion_archive",
            joinColumns = {@JoinColumn(name = "post_explosion_id")}, inverseJoinColumns = {@JoinColumn(name = "archive_id")}
    )
    private List<ArchiveEntity> archives;

    public PostExplosionEntity() { }

    public PostExplosionEntity(Long id, UserEntity user, Double vestigeDistance, String description, List<ArchiveEntity> archives) {
        this.id = id;
        this.user = user;
        this.vestigeDistance = vestigeDistance;
        this.description = description;
        this.archives = archives;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Double getVestigeDistance() {
        return vestigeDistance;
    }

    public void setVestigeDistance(Double vestigeDistance) {
        this.vestigeDistance = vestigeDistance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ArchiveEntity> getArchives() {
        return archives;
    }

    public void setArchives(List<ArchiveEntity> archives) {
        this.archives = archives;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostExplosionEntity that = (PostExplosionEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(user, that.user) && Objects.equals(vestigeDistance, that.vestigeDistance) && Objects.equals(description, that.description) && Objects.equals(archives, that.archives);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, vestigeDistance, description, archives);
    }
}
