package com.smartmaintain.equipementservice.controllers;

import com.smartmaintain.equipementservice.dto.PredictionAlertRequest;
import com.smartmaintain.equipementservice.entities.PredictionAlert;
import com.smartmaintain.equipementservice.entities.Tache;
import com.smartmaintain.equipementservice.services.PredictionAlertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/alerts")
public class PredictionAlertController {

    private final PredictionAlertService alertService;

    public PredictionAlertController(PredictionAlertService alertService) {
        this.alertService = alertService;
    }

    @PostMapping
    public ResponseEntity<PredictionAlert> createAlert(@RequestBody PredictionAlertRequest request) {
        return ResponseEntity.ok(alertService.createAlert(request));
    }

    @GetMapping
    public ResponseEntity<List<PredictionAlert>> getAllAlerts() {
        return ResponseEntity.ok(alertService.getAllAlerts());
    }

    @PostMapping("/{id}/convert")
    public ResponseEntity<Tache> convertToTask(@PathVariable Long id, @RequestParam(required = false) UUID equipeId) {
        return ResponseEntity.ok(alertService.convertToTask(id, equipeId));
    }
}
