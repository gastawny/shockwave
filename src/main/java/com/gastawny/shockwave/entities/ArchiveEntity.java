package com.gastawny.shockwave.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "archives")
public class ArchiveEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "archive_id")
    private Long id;

    @Column
    private String name;

    @Column
    private String path;

    @Column
    private byte[] data;

    public ArchiveEntity() { }

    public ArchiveEntity(Long id, String name, String path, byte[] data) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.data = data;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArchiveEntity that = (ArchiveEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(path, that.path) && Objects.deepEquals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, path, Arrays.hashCode(data));
    }
}
