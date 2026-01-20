package com.gastawny.shockwave.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "formula_dependencies")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class FormulaDependency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "formula_dependency_id")
    private Long id;

    @Column(length = 50, nullable = false)
    private String alias;

    @Column(length = 50, nullable = false)
    private String referenceTable;

    @OneToMany(
            mappedBy = "dependency",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<FormulaDependencyMapping> mappings = new ArrayList<>();
}
