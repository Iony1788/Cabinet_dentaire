/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dentisterie2.dentisterie2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 *
 * @author TOAVINA
 */

@Entity
public class NiveauRecuperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int niveau;
    private int niveau_min;
    private int niveau_max;
    private int niveau_apres;

    public NiveauRecuperation() {
    }

    public NiveauRecuperation(Long id, int niveau, int niveau_min, int niveau_max, int niveau_apres) {
        this.id = id;
        this.niveau = niveau;
        this.niveau_min = niveau_min;
        this.niveau_max = niveau_max;
        this.niveau_apres = niveau_apres;
    }
    
    public NiveauRecuperation(int niveau, int niveau_min, int niveau_max, int niveau_apres) {
        this.niveau = niveau;
        this.niveau_min = niveau_min;
        this.niveau_max = niveau_max;
        this.niveau_apres = niveau_apres;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public int getNiveau_min() {
        return niveau_min;
    }

    public void setNiveau_min(int niveau_min) {
        this.niveau_min = niveau_min;
    }

    public int getNiveau_max() {
        return niveau_max;
    }

    public void setNiveau_max(int niveau_max) {
        this.niveau_max = niveau_max;
    }

    public int getNiveau_apres() {
        return niveau_apres;
    }

    public void setNiveau_apres(int niveau_apres) {
        this.niveau_apres = niveau_apres;
    }
     
}
