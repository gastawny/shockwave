package com.gastawny.shockwave.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "bomb_threats")
@Getter
@Setter
public class BombThreat extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bomb_threat_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "form_threat_id")
    private FormThreat formThreat;

    @OneToOne
    @JoinColumn(name = "located_object_id")
    private LocatedObject locatedObject;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "bomb_threat_archive",
            joinColumns = {@JoinColumn(name = "bomb_threat_id")}, inverseJoinColumns = {@JoinColumn(name = "archive_id")}
    )
    @JsonIgnore
    private List<Archive> archives;

    @Column
    private String name;

    @Column(name = "form_threat_description")
    private String formThreatDescription;

    @Column(name = "object_not_found_description")
    private String objectNotFoundDescription;
}
