package com.gastawny.shockwave.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "post_explosions")
@Getter
@Setter
public class PostExplosionEntity {

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
}
