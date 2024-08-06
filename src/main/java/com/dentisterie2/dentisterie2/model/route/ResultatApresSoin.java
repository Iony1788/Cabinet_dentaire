/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dentisterie2.dentisterie2.model.route;


/**
 *
 * @author TOAVINA
 */

public class ResultatApresSoin {
    private Route route;
    private int niveau_complementaire;
    private double prix;
    private String notif;

    public ResultatApresSoin() {
    }

    public ResultatApresSoin(Route route, int niveau_complementaire, double prix) {
        this.route = route;
        this.niveau_complementaire = niveau_complementaire;
        this.prix = prix;
    }

    public String getNotif() {
        return notif;
    }

    public void setNotif(String notif) {
        this.notif = notif;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route dent) {
        this.route = dent;
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
