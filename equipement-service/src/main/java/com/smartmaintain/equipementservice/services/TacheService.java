package com.smartmaintain.equipementservice.services;

import com.smartmaintain.equipementservice.entities.Tache;
import com.smartmaintain.equipementservice.repositories.TacheRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TacheService {
    private final TacheRepository tacheRepository;
    // Constructor injection...
    public TacheService(TacheRepository tacheRepository) {
        this.tacheRepository = tacheRepository;
    }

    public List<Tache> getTachesByTaxonomie(Long taxonomieId) {
        return tacheRepository.findByTaxonomieId(taxonomieId);
    }
}
