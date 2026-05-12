package com.smartmaintain.equipementservice.services;

import com.smartmaintain.equipementservice.entities.Piece;
import com.smartmaintain.equipementservice.repositories.PieceRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PieceService {
    private final PieceRepository pieceRepository;

    public PieceService(PieceRepository pieceRepository) {
        this.pieceRepository = pieceRepository;
    }

    public List<Piece> getPiecesByTaxonomie(Long taxonomieId) {
        return pieceRepository.findByTaxonomieId(taxonomieId);
    }
}
