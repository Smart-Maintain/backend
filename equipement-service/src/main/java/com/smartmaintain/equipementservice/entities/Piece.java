package com.smartmaintain.equipementservice.entities;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Piece {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String reference;
    private String nom;
    private int quantite;
    private double prix;

    public Piece() {}

    public Piece(UUID id, String reference, String nom, int quantite, double prix) {
        this.id = id;
        this.reference = reference;
        this.nom = nom;
        this.quantite = quantite;
        this.prix = prix;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
    @ManyToOne
    @JoinColumn(name = "taxonomie_id")
    private Taxonomie taxonomie;
}