package com.gastawny.shockwave.models;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class FormulaCompositionId {

    private Long mainFormulaId;
    private Long componentFormulaId;
}
