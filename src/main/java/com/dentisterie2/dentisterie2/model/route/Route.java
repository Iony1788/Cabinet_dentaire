/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dentisterie2.dentisterie2.model.route;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 *
 * @author Ionisoa
 */
@Entity
public class Route{
    @Id
    private Long id;
    private double cout_nettoyage;
    private double cout_traitement;
    private double cout_enlevement;
    private double cout_remplacement;
    private int val_priorite_economique;
    private int val_priorite_decoration;

    public Route() {
    }

    public Route(Long id, double cout_nettoyage, double cout_traitement, double cout_enlevement, double cout_remplacement, int val_priorite_sante, int val_priorite_beaute) {
        this.id = id;
        this.cout_nettoyage = cout_nettoyage;
        this.cout_traitement = cout_traitement;
        this.cout_enlevement = cout_enlevement;
        this.cout_remplacement = cout_remplacement;
        this.val_priorite_economique = val_priorite_sante;
        this.val_priorite_decoration = val_priorite_beaute;
    }

    public double getCout_nettoyage() {
        return cout_nettoyage;
    }

    public void setCout_nettoyage(double cout_nettoyage) {
        this.cout_nettoyage = cout_nettoyage;
    }

    public double getCout_enlevement() {
        return cout_enlevement;
    }

    public void setCout_enlevement(double cout_enlevement) {
        this.cout_enlevement = cout_enlevement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getCout_traitement() {
        return cout_traitement;
    }

    public void setCout_traitement(double cout_traitement) {
        this.cout_traitement = cout_traitement;
    }

    public double getCout_remplacement() {
        return cout_remplacement;
    }

    public void setCout_remplacement(double cout_remplacement) {
        this.cout_remplacement = cout_remplacement;
    }

    public int getVal_priorite_economique() {
        return val_priorite_economique;
    }

    public void setVal_priorite_economique(int val_priorite_economique) {
        this.val_priorite_economique = val_priorite_economique;
    }

    public int getVal_priorite_decoration() {
        return val_priorite_decoration;
    }

    public void setVal_priorite_decoration(int val_priorite_decoration) {
        this.val_priorite_decoration = val_priorite_decoration;
    }
}
