package com.smartmaintain.equipementservice.entities;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Tache {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String description;
    private String priorite;
    private String status;

    @ManyToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;

    public Tache() {}

    public Tache(UUID id, String description, String priorite, String status, Equipe equipe) {
        this.id = id;
        this.description = description;
        this.priorite = priorite;
        this.status = status;
        this.equipe = equipe;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriorite() {
        return priorite;
    }

    public void setPriorite(String priorite) {
        this.priorite = priorite;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }
    @ManyToOne
    @JoinColumn(name = "taxonomie_id")
    private Taxonomie taxonomie;
}