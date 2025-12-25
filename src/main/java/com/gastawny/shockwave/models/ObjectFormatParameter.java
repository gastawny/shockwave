package com.gastawny.shockwave.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "object_format_parameters")
@Getter
@Setter
public class ObjectFormatParameter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "object_format_parameter_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "object_format_id")
    private ObjectFormat objectFormat;

    @ManyToOne
    @JoinColumn(name = "parameter_id")
    private Parameter parameter;
}
