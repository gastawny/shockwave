package com.gastawny.shockwave.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.*;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@SoftDelete
@JsonIgnoreProperties({"createdAt", "updatedAt"})
public abstract class BaseModel {

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "timestamp default null", insertable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
