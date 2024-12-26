package com.gastawny.shockwave.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "bomb_threats")
@Getter
@Setter
public class BombThreatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bomb_threat_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "form_threat_id")
    private FormThreatEntity formThreat;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "located_object_id")
    private LocatedObjectEntity locatedObject;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "bomb_threat_archive",
            joinColumns = {@JoinColumn(name = "bomb_threat_id")}, inverseJoinColumns = {@JoinColumn(name = "archive_id")}
    )
    private List<ArchiveEntity> archives;

    @Column
    private String name;

    @Column(name = "form_threat_description")
    private String formThreatDescription;

    @Column(name = "is_located_object")
    private Boolean isLocatedObject;

    @Column(name = "object_not_found_description")
    private String objectNotFoundDescription;
}
