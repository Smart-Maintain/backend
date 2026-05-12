package com.smartmaintain.equipementservice.controllers;

import com.smartmaintain.equipementservice.entities.Piece;
import com.smartmaintain.equipementservice.services.PieceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pieces")
public class PieceController {
    private final PieceService pieceService;

    public PieceController(PieceService pieceService) {
        this.pieceService = pieceService;
    }

    @GetMapping("/filter/{taxonomieId}")
    public ResponseEntity<List<Piece>> filterByTaxonomie(@PathVariable Long taxonomieId) {
        return ResponseEntity.ok(pieceService.getPiecesByTaxonomie(taxonomieId));
    }
}
