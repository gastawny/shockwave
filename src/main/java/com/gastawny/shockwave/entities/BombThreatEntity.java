package com.gastawny.shockwave.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "bomb_threats")
public class BombThreatEntity {

    @Serial
    private static final long serialVersionUID = 1L;

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

    public BombThreatEntity() { }

    public BombThreatEntity(Long id, UserEntity user, FormThreatEntity formThreat, LocatedObjectEntity locatedObject, List<ArchiveEntity> archives, String name, String formThreatDescription, Boolean isLocatedObject, String objectNotFoundDescription) {
        this.id = id;
        this.user = user;
        this.formThreat = formThreat;
        this.locatedObject = locatedObject;
        this.archives = archives;
        this.name = name;
        this.formThreatDescription = formThreatDescription;
        this.isLocatedObject = isLocatedObject;
        this.objectNotFoundDescription = objectNotFoundDescription;
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

    public FormThreatEntity getFormThreat() {
        return formThreat;
    }

    public void setFormThreat(FormThreatEntity formThreat) {
        this.formThreat = formThreat;
    }

    public LocatedObjectEntity getLocatedObject() {
        return locatedObject;
    }

    public void setLocatedObject(Boolean locatedObject) {
        isLocatedObject = locatedObject;
    }

    public String getObjectNotFoundDescription() {
        return objectNotFoundDescription;
    }

    public void setObjectNotFoundDescription(String objectNotFoundDescription) {
        this.objectNotFoundDescription = objectNotFoundDescription;
    }

    public void setLocatedObject(LocatedObjectEntity locatedObject) {
        this.locatedObject = locatedObject;
    }

    public List<ArchiveEntity> getArchives() {
        return archives;
    }

    public void setArchives(List<ArchiveEntity> archives) {
        this.archives = archives;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormThreatDescription() {
        return formThreatDescription;
    }

    public void setFormThreatDescription(String formThreatDescription) {
        this.formThreatDescription = formThreatDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BombThreatEntity that = (BombThreatEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(user, that.user) && Objects.equals(formThreat, that.formThreat) && Objects.equals(locatedObject, that.locatedObject) && Objects.equals(archives, that.archives) && Objects.equals(name, that.name) && Objects.equals(formThreatDescription, that.formThreatDescription) && Objects.equals(isLocatedObject, that.isLocatedObject) && Objects.equals(objectNotFoundDescription, that.objectNotFoundDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, formThreat, locatedObject, archives, name, formThreatDescription, isLocatedObject, objectNotFoundDescription);
    }
}
