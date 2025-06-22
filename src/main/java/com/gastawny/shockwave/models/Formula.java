package com.gastawny.shockwave.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.beans.Expression;
import java.util.*;

@Entity(name = "formulas")
@Getter
@Setter
public class Formula extends BaseModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "formula_id")
    private Long id;

    @Column(unique = true)
    private String name;

    @Column
    private String expression;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "formula_parameters",
            joinColumns = {@JoinColumn(name = "formula_id")}, inverseJoinColumns = {@JoinColumn(name = "parameter_id")}
    )
    private List<Parameter> parameters;

    @OneToMany(
            mappedBy = "mainFormula",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<FormulaComposition> components;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "formula_constants",
            joinColumns = {@JoinColumn(name = "formula_id")}, inverseJoinColumns = {@JoinColumn(name = "constant_id")}
    )
    private List<Constant> constants;

    public String expand() {
        String expressionInProgress = this.expression;

        if (this.components != null) {
            for (FormulaComposition component : this.components) {
                Formula componentFormula = component.getComponentFormula();
                String alias = component.getAlias();

                String subExpression = componentFormula.expand();

                String placeholder = "{" + alias + "}";
                expressionInProgress = expressionInProgress.replace(placeholder, "(" + subExpression + ")");

                if (componentFormula.getConstants() != null) {
                    for (Constant constant : componentFormula.getConstants()) {
                        String constantPlaceholder = "{" + constant.getSymbol() + "}";
                        expressionInProgress = expressionInProgress.replace(constantPlaceholder, constant.getValue().getValue().toString());
                    }
                }
            }
        }

        if (this.constants != null) {
            for (Constant constant : this.constants) {
                String constantPlaceholder = "{" + constant.getSymbol() + "}";
                expressionInProgress = expressionInProgress.replace(constantPlaceholder, constant.getValue().getValue().toString());
            }
        }

        return expressionInProgress;
    }

    public Double resolve(Map<String, Double> placeholders) throws Exception {
        String expression = this.expand();

        for (Map.Entry<String, Double> entry : placeholders.entrySet()) {
            String placeholder = "{" + entry.getKey() + "}";
            expression = expression.replace(placeholder, entry.getValue().toString());
        }

        ExpressionParser parser = new SpelExpressionParser();

        return parser.parseExpression(expression).getValue(Double.class);
    }
}
