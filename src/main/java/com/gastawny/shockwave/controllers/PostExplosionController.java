package com.gastawny.shockwave.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gastawny.shockwave.models.BombThreat;
import com.gastawny.shockwave.models.PostExplosion;
import com.gastawny.shockwave.services.PostExplosionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Post Explosions Endpoint")
@RestController
@RequestMapping(path = "/api/postExplosions")
public class PostExplosionController {

    private final PostExplosionService postExplosionService;

    public PostExplosionController(PostExplosionService postExplosionService) {
        this.postExplosionService = postExplosionService;
    }

    @GetMapping("getCircles/{vestigeDistance}")
    public ResponseEntity<?> getCircles(@PathVariable("vestigeDistance") Double vestigeDistance) {
        return ResponseEntity.ok(postExplosionService.getCircles(vestigeDistance));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> save(
            @RequestPart(name = "multipartFiles", required = false) MultipartFile[] files,
            @RequestPart("data") String dataJson
    ) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        PostExplosion data = mapper.readValue(dataJson, PostExplosion.class);
        return ResponseEntity.ok(postExplosionService.saveOrUpdate(data, files));
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> update(
            @RequestPart("multipartFiles") MultipartFile[] files,
            @RequestPart("data") PostExplosion data
    ) {
        return ResponseEntity.ok(postExplosionService.saveOrUpdate(data, files));
    }
}
