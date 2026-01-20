package com.gastawny.shockwave.controllers;

import com.gastawny.shockwave.dto.formula.FormulaExpandedDTO;
import com.gastawny.shockwave.models.Formula;
import com.gastawny.shockwave.services.FormulaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
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

    @GetMapping(path = "/execute/{id}")
    public ResponseEntity<Double> getExecutableExpressionById(@PathVariable Long id, @RequestParam Map<String, String> params) {
        return ResponseEntity.ok(formulaService.execute(params, id));
    }

    @GetMapping(path = "/expanded/{id}")
    public ResponseEntity<FormulaExpandedDTO> getFormulaExpanded(@PathVariable Long id, @RequestParam Map<String, String> params) {
        return ResponseEntity.ok(formulaService.getFormulaExpanded(id, params));
    }
}
