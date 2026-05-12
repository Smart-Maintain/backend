package com.smartmaintain.equipementservice.controllers;

import com.smartmaintain.equipementservice.entities.Tache;
import com.smartmaintain.equipementservice.services.TacheService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/taches")
public class TacheController {
    private final TacheService tacheService;

    public TacheController(TacheService tacheService) {
        this.tacheService = tacheService;
    }

    @GetMapping("/filter/{taxonomieId}")
    public ResponseEntity<List<Tache>> filterByTaxonomie(@PathVariable Long taxonomieId) {
        return ResponseEntity.ok(tacheService.getTachesByTaxonomie(taxonomieId));
    }
}
