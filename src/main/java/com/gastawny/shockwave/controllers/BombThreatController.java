package com.gastawny.shockwave.controllers;

import com.gastawny.shockwave.dto.bombThreat.ReqBombThreatDTO;
import com.gastawny.shockwave.services.BombThreatService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Bomb Threat Endpoint")
@RestController
@RequestMapping(path = "/api/bomb-threats")
public class BombThreatController {

    private final BombThreatService bombThreatService;

    public BombThreatController(BombThreatService bombThreatService) {
        this.bombThreatService = bombThreatService;
    }

    @PostMapping
    public ResponseEntity<ReqBombThreatDTO> saveLocatedObject(@RequestBody ReqBombThreatDTO req) {
        return ResponseEntity.ok(bombThreatService.save(req));
    }
}
