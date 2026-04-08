package com.gastawny.shockwave.controllers;

import com.gastawny.shockwave.models.File;
import com.gastawny.shockwave.services.ExplosiveService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tag(name = "Explosives Endpoint")
@RestController
@RequestMapping(path = "/api/explosives")
public class ExplosiveController {

    private final ExplosiveService explosiveService;

    public ExplosiveController(ExplosiveService explosiveService) {
        this.explosiveService = explosiveService;
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<File> getImage(@PathVariable("id") Long id) {
        File image = explosiveService.getImage(id);
        if (image == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(image);
    }

    @PutMapping(path = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateImage(
            @PathVariable("id") Long id,
            @RequestPart(name = "image", required = false) MultipartFile image
    ) throws IOException {
        return ResponseEntity.ok(explosiveService.updateImage(id, image));
    }
}
