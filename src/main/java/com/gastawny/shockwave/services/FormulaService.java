package com.gastawny.shockwave.services;

import com.gastawny.shockwave.dto.formula.FormulaExpandedDTO;
import com.gastawny.shockwave.models.Formula;
import com.gastawny.shockwave.repositories.FormulaRepository;
import org.springframework.stereotype.Service;

import java.beans.Expression;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

@Service
public class FormulaService {

    private final FormulaRepository formulaRepository;

    public FormulaService(FormulaRepository formulaRepository) {
        this.formulaRepository = formulaRepository;
    }

    public Optional<Formula> getFormulaById(Long id) {
        return formulaRepository.findById(id);
    }

    public Double getExecutableExpressionById(Long id) {
        Optional<Formula> formulaOptional = formulaRepository.findById(id);

        if (formulaOptional.isPresent()) {
            Formula formula = formulaOptional.get();
            Map<String, Double> a = new HashMap<>();
            a.put("r", (double) 2);
            a.put("h", (double) 3);

            try {
                return formula.resolve(a);
            } catch (Exception e) {
                return null;
            }
        }

        return null;
    }

    public FormulaExpandedDTO getFormulaExpanded(Long id) {
        Optional<Formula> formulaOptional = formulaRepository.findById(id);

        if (formulaOptional.isPresent()) {
            Formula formula = formulaOptional.get();
            try {
                var formulaExpanded = new FormulaExpandedDTO();
                var constants = formulaOptional.get().getAllConstants();
                var parameters = formulaOptional.get().getAllParameters();

                formulaExpanded.setId(formula.getId());
                formulaExpanded.setName(formula.getName());
                formulaExpanded.setExpression(formula.expand(false));
                formulaExpanded.setConstants(constants);
                formulaExpanded.setParameters(parameters);

                return formulaExpanded;
            } catch (Exception e) {
                return null;
            }
        }

        return null;
    }
}
