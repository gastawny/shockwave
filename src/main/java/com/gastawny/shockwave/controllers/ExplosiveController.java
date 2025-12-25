package com.gastawny.shockwave.controllers;

import com.gastawny.shockwave.models.Explosive;
import com.gastawny.shockwave.services.ExplosiveService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Tag(name = "Explosives Endpoint")
@RestController
@RequestMapping(path = "/api/explosives")
public class ExplosiveController {

    private ExplosiveService explosiveService;

    public ExplosiveController(ExplosiveService explosiveService) {
        this.explosiveService = explosiveService;
    }

    @GetMapping(path = "datas/{id}")
    public ResponseEntity<List<Map<String, Object>>> getDataByExplosiveId(@PathVariable Long id) {
        return ResponseEntity.ok(explosiveService.getDataByExplosiveId(id));
    }
}
