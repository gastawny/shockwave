package com.gastawny.shockwave.controllers;

import com.gastawny.shockwave.models.Ground;
import com.gastawny.shockwave.services.GroundService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Grounds Endpoint")
@RestController
@RequestMapping(path = "/api/grounds")
public class GroundController {

    private GroundService groundService;

    public GroundController(GroundService groundService) {
        this.groundService = groundService;
    }

    @GetMapping
    public ResponseEntity<List<Ground>> getAllGrounds() {
        return ResponseEntity.ok(groundService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Ground> getGroundById(@PathVariable Long id) {
        return ResponseEntity.ok(groundService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Ground> saveGround(@RequestBody Ground ground) {
        return ResponseEntity.ok(groundService.save(ground));
    }

    @PutMapping
    public ResponseEntity<Ground> updateGround(@RequestBody Ground ground) {
        return ResponseEntity.ok(groundService.update(ground));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteGroundById(@PathVariable Long id) {
        groundService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
