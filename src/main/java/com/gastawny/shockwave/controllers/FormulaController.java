package com.gastawny.shockwave.controllers;

import com.gastawny.shockwave.dto.formula.FormulaExpandedDTO;
import com.gastawny.shockwave.models.Formula;
import com.gastawny.shockwave.services.FormulaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Tag(name = "Formulas Endpoint")
@RestController
@RequestMapping(path = "/api/formulas")
public class FormulaController {

    private final FormulaService formulaService;

    public FormulaController(FormulaService formulaService) {
        this.formulaService = formulaService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Formula>> getFormulaById(@PathVariable Long id) {
        return ResponseEntity.ok(formulaService.getFormulaById(id));
    }

    @GetMapping(path = "/executable/{id}")
    public ResponseEntity<Double> getExecutableExpressionById(@PathVariable Long id) {
        return ResponseEntity.ok(formulaService.getExecutableExpressionById(id));
    }

    @GetMapping(path = "/expanded/{id}")
    public ResponseEntity<FormulaExpandedDTO> getFormulaExpanded(@PathVariable Long id) {
        return ResponseEntity.ok(formulaService.getFormulaExpanded(id));
    }
}
