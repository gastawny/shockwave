package com.gastawny.shockwave.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "object_format_parameter_values")
@Getter
@Setter
public class ObjectFormatParameterValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "object_format_parameter_value_id")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "located_object_id", nullable = false)
    @JsonIgnore
    private LocatedObject locatedObject;

    @ManyToOne(optional = false)
    @JoinColumn(name = "object_format_parameter_id", nullable = false)
    private ObjectFormatParameter objectFormatParameter;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "value_id", nullable = false)
    private ValueNum value;
}
