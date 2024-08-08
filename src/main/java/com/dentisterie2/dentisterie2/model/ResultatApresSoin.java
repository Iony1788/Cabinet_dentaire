/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dentisterie2.dentisterie2.model;

/**
 *
 * @author Ionisoa
 */

public class ResultatApresSoin {
    private Dent dent;
    private int niveau_complementaire;
    private double prix;
    private String notif;

    public ResultatApresSoin() {
    }

    public ResultatApresSoin(Dent dent, int niveau_complementaire, double prix) {
        this.dent = dent;
        this.niveau_complementaire = niveau_complementaire;
        this.prix = prix;
    }

    public String getNotif() {
        return notif;
    }

    public void setNotif(String notif) {
        this.notif = notif;
    }

    public Dent getDent() {
        return dent;
    }

    public void setDent(Dent dent) {
        this.dent = dent;
    }

    public double getNiveau_complementaire() {
        return niveau_complementaire;
    }

    public void setNiveau_complementaire(int niveau_complementaire) {
        this.niveau_complementaire = niveau_complementaire;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
    
}
