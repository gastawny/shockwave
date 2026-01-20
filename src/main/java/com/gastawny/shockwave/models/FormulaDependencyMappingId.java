package com.gastawny.shockwave.models;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class FormulaDependencyMappingId {

    private Long FormulaDependencyId;
    private Long FormulaId;
}
