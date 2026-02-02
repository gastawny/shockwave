package com.gastawny.shockwave.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "post_explosions")
@Getter
@Setter
public class PostExplosion extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_explosion_id")
    private Long id;

    @Column
    private String name;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "vestige_distance")
    private Double vestigeDistance;

    @Column
    private String description;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "post_explosion_file",
            joinColumns = {@JoinColumn(name = "post_explosion_id")}, inverseJoinColumns = {@JoinColumn(name = "file_id")}
    )
    private List<File> files = new ArrayList<>();
}
