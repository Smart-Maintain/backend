package com.smartmaintain.equipementservice.services;

import com.smartmaintain.equipementservice.entities.Equipement;
import com.smartmaintain.equipementservice.repositories.EquipementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class EquipementService {
    private final EquipementRepository repository;

    public EquipementService(EquipementRepository repository) {
        this.repository = repository;
    }

    public List<Equipement> getAll() { return repository.findAll(); }

    public List<Equipement> getByTaxonomy(String code) {
        return repository.findByTaxonomieCode(code);
    }

    public Equipement save(Equipement e) { return repository.save(e); }

    public void delete(Long id) { repository.deleteById(id); }
}