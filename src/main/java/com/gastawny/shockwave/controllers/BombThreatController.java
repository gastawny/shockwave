package com.gastawny.shockwave.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gastawny.shockwave.models.BombThreat;
import com.gastawny.shockwave.services.BombThreatService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Bomb Threats Endpoint")
@RestController
@RequestMapping(path = "/api/bombThreats")
public class BombThreatController {

    private final BombThreatService bombThreatService;

    public BombThreatController(BombThreatService bombThreatService) {
        this.bombThreatService = bombThreatService;
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> save(
            @RequestPart(name = "multipartFiles", required = false) MultipartFile[] files,
            @RequestPart("data") String dataJson
    ) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        BombThreat data = mapper.readValue(dataJson, BombThreat.class);
        return ResponseEntity.ok(bombThreatService.saveOrUpdate(data, files));
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> update(
            @RequestPart("multipartFiles") MultipartFile[] files,
            @RequestPart("data") BombThreat data
    ) {
        return ResponseEntity.ok(bombThreatService.saveOrUpdate(data, files));
    }
}
