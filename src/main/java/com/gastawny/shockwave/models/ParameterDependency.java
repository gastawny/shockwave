package com.gastawny.shockwave.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "parameter_dependencies")
@Getter
@Setter
public class ParameterDependency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parameter_dependency_id")
    private Long id;

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parameter_id", nullable = false)
    private Parameter parameter;

    @Column(length = 50, nullable = false)
    private String referenceTable;

    @Column(length = 50, nullable = false)
    private String referenceColumn;

    @Column
    private Long referenceId;

    @Column(length = 50)
    private String contextFkColumn;
}
