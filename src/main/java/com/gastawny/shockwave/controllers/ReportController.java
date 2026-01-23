package com.gastawny.shockwave.controllers;

import com.gastawny.shockwave.dto.bombThreat.BombThreatReportDTO;
import com.gastawny.shockwave.services.BombThreatService;
import com.gastawny.shockwave.services.ExplosiveService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Tag(name = "Reports Endpoint")
@RestController
@RequestMapping(path = "/api/reports")
public class ReportController {

    private final BombThreatService bombThreatService;
    private final ExplosiveService explosiveService;

    public ReportController(BombThreatService bombThreatService, ExplosiveService explosiveService) {
        this.bombThreatService = bombThreatService;
        this.explosiveService = explosiveService;
    }

    @GetMapping(path = "bombThreats/{id}")
    public void getBombThreatReportById(HttpServletResponse response, @PathVariable Long id) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader(
                "Content-Disposition",
                "attachment; filename=ameaca_bomba.pdf"
        );

        bombThreatService.getReportById(response.getOutputStream(),id);
    }

    @GetMapping(path = "bombThreats")
    public ResponseEntity<List<BombThreatReportDTO>> getBombThreatReports() {
        return ResponseEntity.ok(bombThreatService.getReports());
    }

    @GetMapping(path = "explosives")
    public void ExplosiveById(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader(
                "Content-Disposition",
                "attachment; filename=ameaca_bomba.pdf"
        );

        explosiveService.getReportById(response.getOutputStream());
    }
}
