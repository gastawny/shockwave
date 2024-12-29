package com.gastawny.shockwave.models;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class ExplosiveDataTypeId {

    private Long explosiveId;

    private Long dataTypeId;
}
