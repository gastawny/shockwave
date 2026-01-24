package com.gastawny.shockwave.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "bomb_threat_file",
            joinColumns = {@JoinColumn(name = "bomb_threat_id")}, inverseJoinColumns = {@JoinColumn(name = "file_id")}
    )
    private List<File> files = new ArrayList<>();

    @Column
    private String name;

    @Column(name = "form_threat_description")
    private String formThreatDescription;

    @Column(name = "object_not_found_description")
    private String objectNotFoundDescription;
}
