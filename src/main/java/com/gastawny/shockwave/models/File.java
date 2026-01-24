package com.gastawny.shockwave.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "files")
@Getter
@Setter
public class File extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    @Column
    private String name;

    @Column
    private String path;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] data;
}
