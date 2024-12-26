package com.gastawny.shockwave.entities;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Embeddable
@Getter
@Setter
public class ExplosiveDataTypeId {

    private Long explosiveId;

    private Long dataTypeId;
}
