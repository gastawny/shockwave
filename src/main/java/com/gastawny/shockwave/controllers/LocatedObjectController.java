package com.gastawny.shockwave.controllers;

import com.gastawny.shockwave.dto.locatedObject.ReqLocatedObjectDTO;
import com.gastawny.shockwave.models.Ground;
import com.gastawny.shockwave.models.LocatedObject;
import com.gastawny.shockwave.services.LocatedObjectService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Located Objects Endpoint")
@RestController
@RequestMapping(path = "/api/located-objects")
public class LocatedObjectController {

    private LocatedObjectService locatedObjectService;


    public LocatedObjectController(LocatedObjectService locatedObjectService) {
        this.locatedObjectService = locatedObjectService;
    }

    @PostMapping
    public ResponseEntity<ReqLocatedObjectDTO> saveLocatedObject(@RequestBody ReqLocatedObjectDTO req) {
        return ResponseEntity.ok(locatedObjectService.save(req));
    }
}
