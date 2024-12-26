package com.gastawny.shockwave.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "archives")
@Getter
@Setter
public class ArchiveEntity {

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
}
