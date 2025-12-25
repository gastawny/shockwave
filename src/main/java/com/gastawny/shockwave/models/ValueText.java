package com.gastawny.shockwave.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ValueText extends Value {

    private String value;

    public ValueText() { }
}
