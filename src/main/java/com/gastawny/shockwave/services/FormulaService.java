package com.gastawny.shockwave.services;

import com.gastawny.shockwave.dto.formula.CircleDTO;
import com.gastawny.shockwave.dto.formula.FormulaExpandedDTO;
import com.gastawny.shockwave.models.*;
import com.gastawny.shockwave.repositories.FormulaRepository;
import com.gastawny.shockwave.shared.formulas.Calculation;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FormulaService {

    private final LocatedObjectService locatedObjectService;
    private final FormulaRepository formulaRepository;
    private final ParameterService parameterService;

    public FormulaService(FormulaRepository formulaRepository, ParameterService parameterService, LocatedObjectService locatedObjectService) {
        this.formulaRepository = formulaRepository;
        this.parameterService = parameterService;
        this.locatedObjectService = locatedObjectService;
    }

    public Optional<Formula> getFormulaById(Long id) {
        return formulaRepository.findById(id);
    }

    public List<Formula> get2Report(Map<String, String> values) {
        var formulas = formulaRepository.findDistinctByTableTableName("located_objects");

        var filteredDependencies = formulas.stream().filter(
                f -> {
                    var isReferenced = formulas.stream().anyMatch(otherFormula ->
                            otherFormula.getDependencies().stream().anyMatch(dependency ->
                                    dependency.getMappings().stream().anyMatch(mapping ->
                                            mapping.getFormula().getId().equals(f.getId())
                                    )
                            )
                    );

                    if(!isReferenced) return true;

                    return formulas.stream().anyMatch(otherFormula ->
                            otherFormula.getDependencies().stream().anyMatch(dependency ->
                                    values.containsKey(dependency.getAlias()) &&
                                            dependency.getMappings().stream().anyMatch(mapping ->
                                                    mapping.getFormula().getId().equals(f.getId()) &&
                                                            String.valueOf(mapping.getReferenceId()).equals(values.get(dependency.getAlias()))
                                            )
                            )
                    );
                }
        ).toList();

        return filteredDependencies.stream().filter(
                f -> {
                    var isReferenced = formulas.stream().anyMatch(otherFormula ->
                            otherFormula.getDecision() != null &&
                                    otherFormula.getDecision().getChoices().stream().anyMatch(choice ->
                                            choice.getId().equals(f.getId()
                                    )
                            )
                    );

                    return !isReferenced;
                }
        ).toList();
    }

    public FormulaExpandedDTO getFormulaExpanded(Long id, Map<String, String> params) {
        var formula = formulaRepository.findById(id).orElseThrow();
        var formulaExpanded = new FormulaExpandedDTO();

        formulaExpanded.setName(formula.getName());
        formulaExpanded.setExpression(this.expand(formula,false, params));
        formulaExpanded.setConstants(getAllConstants(formula));
        formulaExpanded.setParameters(getAllParameters(formula));

        return formulaExpanded;
    }

    public Double execute(Formula formula, Map<String, String> values) {
        if(formula.getDecision() != null) {
            for(var choice : formula.getDecision().getChoices()) {
                var formulaAux = new Formula();
                formulaAux.setComponents(choice.getComponents());
                formulaAux.setConstants(choice.getConstants());
                formulaAux.setDependencies(choice.getDependencies());
                formulaAux.setParameters(choice.getParameters());

                formulaAux.setExpression(choice.getName());

                if(calculateBoolean(formulaAux, values)) {
                    return calculateNumber(choice, values);
                }
            }
        }

        return calculateNumber(formula, values);
    }

    public Double execute(Map<String, String> params, Long formulaId) {
        var formula = formulaRepository.findById(formulaId).orElseThrow();
        var values = getParameterValues(params);

        return execute(formula, values);
    }

    public Double calculateNumber(Formula formula, Map<String, String> values) {
        return calculate(formula, values, Double.class);
    }

    public Boolean calculateBoolean(Formula formula, Map<String, String> values) {
        return calculate(formula, values, Boolean.class);
    }

    public <T> T calculate(Formula formula, Map<String, String> values, Class<T> type) {
        var expression = expand(formula, true, values);

        for (var e : values.entrySet()) {
            expression = expression.replace("{" + e.getKey() + "}", e.getValue());
        }

        expression = Calculation.runAll(expression);

        return Calculation.calculateExpression(expression, type);
    }

    public String expand(Formula formula, Boolean showConstantsValues, Map<String, String> values) {
        String expression = formula.getExpression();

        expression = getExpressionDependencies(formula, showConstantsValues, expression, values);
        expression = getExpressionComponents(formula, showConstantsValues, expression, values);
        expression = getExpressionConstants(formula, showConstantsValues, expression);

        return expression;
    }

    private String getExpressionDependencies(Formula formula, Boolean showConstantsValues, String expression, Map<String, String> values) {
        for (var dependency : formula.getDependencies()) {
            Long id = (long) Double.parseDouble(values.get(dependency.getAlias()));

            var map = dependency.getMappings().stream()
                    .filter(m -> m.getReferenceId().equals(id))
                    .toList().getFirst();

            String exp = expand(map.getFormula(), showConstantsValues, values);
            String placeholder = "{" + dependency.getAlias() + "}";

            expression = expression.replace(placeholder, "(" + exp + ")");
        }
        return expression;
    }

    private String getExpressionComponents(Formula formula, Boolean showConstantsValues, String expressionInProgress, Map<String, String> values) {
        if (formula.getComponents() != null) {
            for (FormulaComposition component : formula.getComponents()) {
                Formula componentFormula = component.getComponentFormula();
                String alias = component.getAlias();

                String subExpression = "";

                if(componentFormula.getDecision() != null) {
                    for(var choice : componentFormula.getDecision().getChoices()) {
                        var formulaAux = new Formula();
                        formulaAux.setComponents(choice.getComponents());
                        formulaAux.setConstants(choice.getConstants());
                        formulaAux.setDependencies(choice.getDependencies());
                        formulaAux.setParameters(choice.getParameters());

                        formulaAux.setExpression(choice.getName());

                        if(calculateBoolean(formulaAux, values)) {
                            subExpression = expand(choice, showConstantsValues, values);
                            break;
                        }
                    }
                } else {
                    subExpression = expand(componentFormula, showConstantsValues, values);
                }


                String placeholder = "{" + alias + "}";
                expressionInProgress = expressionInProgress.replace(placeholder, "(" + subExpression + ")");

                expressionInProgress = getExpressionConstants(componentFormula, showConstantsValues, expressionInProgress);
            }
        }
        return expressionInProgress;
    }

    private String getExpressionConstants(Formula formula, Boolean showConstantsValues, String expressionInProgress) {
        if (formula.getConstants() != null && showConstantsValues) {
            for (Constant constant : formula.getConstants()) {
                String constantPlaceholder = "{" + constant.getSymbol() + "}";
                expressionInProgress = expressionInProgress.replace(constantPlaceholder, constant.getValue().getValue().toString());
            }
        }
        return expressionInProgress;
    }

    public Map<String, String> getParameterValues(Map<String, String> ids) {
        Map<String, String> values = new HashMap<>(Map.of());

        if(ids.containsKey("locatedObjectId")) {
            Long id = Long.parseLong(ids.get("locatedObjectId"));
            values.putAll(locatedObjectService.getValues(id));
        }

        return values;
    }

    private List<Constant> getAllConstantsRecursion(List<Constant> constants, Formula formula) {
        if (formula.getComponents() != null) {
            for (FormulaComposition component : formula.getComponents()) {
                Formula componentFormula = component.getComponentFormula();
                constants.addAll(getAllConstantsRecursion(constants, componentFormula));
            }
        }

        if (formula.getConstants() != null) {
            constants.addAll(formula.getConstants());
        }

        return constants;
    }

    private List<Constant> getAllConstants(Formula formula) {
        List<Constant> allConstants = this.getAllConstantsRecursion(formula.getConstants(), formula);

        return allConstants.stream()
                .distinct()
                .toList();
    }

    private List<Parameter> getAllParametersRecursion(List<Parameter> parameters, Formula formula) {
        for(FormulaDependency dependency : formula.getDependencies()) {
            for(FormulaDependencyMapping mapping : dependency.getMappings()) {
                Formula dependencyFormula = mapping.getFormula();
                parameters.addAll(getAllParametersRecursion(parameters, dependencyFormula));
            }
        }

        for (FormulaComposition component : formula.getComponents()) {
            Formula componentFormula = component.getComponentFormula();
            parameters.addAll(getAllParametersRecursion(parameters, componentFormula));
        }

        if (formula.getParameters() != null) {
            parameters.addAll(formula.getParameters());
        }

        return parameters.stream()
                .distinct()
                .toList();
    }

    private List<Parameter> getAllParameters(Formula formula) {
        return getAllParametersRecursion(formula.getParameters(), formula);
    }

    public List<CircleDTO> getCircles(Long locateObjectId) {
        var formulas = formulaRepository.findDistinctByTableTableName("locatedObjectsCircles");

        Map<String, String> valuesParameters = getParameterValues(Map.of(
                "locatedObjectId", locateObjectId.toString()
        ));

        return formulas
                .stream()
                .map(formula -> {
                    try {
                        Double result = execute(formula, valuesParameters);
                        var dto = new CircleDTO();
                        dto.setName(formula.getName());
                        dto.setRadius(result);

                        if(formula.getCircle() != null) {
                            dto.setColor(formula.getCircle().getColor());
                        } else {
                            dto.setColor("#FF0000");
                        }

                        return dto;
                    } catch (Throwable e) {
                        var dto = new CircleDTO();
                        dto.setName(formula.getName());
                        dto.setRadius(0.0);
                        dto.setColor("#FF0000");
                        return dto;
                    }
                })
                .toList();
    }
}
