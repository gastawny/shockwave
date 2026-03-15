package com.gastawny.shockwave.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gastawny.shockwave.models.PostExplosion;
import com.gastawny.shockwave.services.FormulaService;
import com.gastawny.shockwave.services.PostExplosionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Located Objects Endpoint")
@RestController
@RequestMapping(path = "/api/locatedObjects")
public class LocatedObjectController {

    private final FormulaService formulaService;

    public LocatedObjectController(FormulaService formulaService) {
        this.formulaService = formulaService;
    }

    @GetMapping("getCircles/{locateObjectId}")
    public ResponseEntity<?> getCircles(@PathVariable("locateObjectId") Long locateObjectId) {
        return ResponseEntity.ok(formulaService.getCircles(locateObjectId));
    }
}
