/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dentisterie2.dentisterie2.model.route;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Ionisoa
 */
public class Patient {
    private String nom;
    private List<EtatRoute> etat = new ArrayList<>();
    
    //5 pour santé
    //15 pour beauté
    private int priorite;
    private double budget;

    public Patient() {
    }
    
    public Patient(String nom, List<EtatRoute> etat, int priorite, double budget) {
        this.nom = nom;
        this.etat = etat;
        this.priorite = priorite;
        this.budget = budget;
    }
    
    public Patient(String nom, int priorite, double budget) {
        this.nom = nom;
        this.priorite = priorite;
        this.budget = budget;
    }

    public List<EtatRoute> getEtat() {
        return etat;
    }

    public void setEtat(List<EtatRoute> etat) {
        this.etat = etat;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPriorite() {
        return priorite;
    }

    public void setPriorite(int priorite) {
        this.priorite = priorite;
    }
    
    public void trierListeEtatParPrioriteRoute() {
        // Divisez la liste en deux
        int taille = etat.size();
        List<EtatRoute> premiereListe = etat.subList(0, taille / 2);
        List<EtatRoute> deuxiemeListe = etat.subList(taille / 2, taille);
        
        //decoration
        if (priorite == 5) {
            Collections.reverse(deuxiemeListe);
        } else if (priorite == 15) {
            Collections.reverse(premiereListe);
        }

        List<EtatRoute> nouvelleListe = new ArrayList<>();
        int index = 0;

        while (index < premiereListe.size() || index < deuxiemeListe.size()) {
            EtatRoute etatPremiere = (index < premiereListe.size()) ? premiereListe.get(index) : null;
            EtatRoute etatDeuxieme = (index < deuxiemeListe.size()) ? deuxiemeListe.get(index) : null;

            if (etatPremiere != null && etatDeuxieme != null) {
                if (etatPremiere.getNiveau() < etatDeuxieme.getNiveau()) {
                    nouvelleListe.add(etatPremiere);
                    nouvelleListe.add(etatDeuxieme);
                } else if (etatPremiere.getNiveau() > etatDeuxieme.getNiveau()) {
                    nouvelleListe.add(etatDeuxieme);
                    nouvelleListe.add(etatPremiere);
                } else {
                    nouvelleListe.add(etatPremiere);
                    nouvelleListe.add(etatDeuxieme);
                }
            } else if (etatPremiere != null) {
                nouvelleListe.add(etatPremiere);
            } else if (etatDeuxieme != null) {
                nouvelleListe.add(etatDeuxieme);
            }

            index++;
        }

        etat.clear();
        etat.addAll(nouvelleListe);
    }

    
    public List<ResultatApresSoin> getResultatApresSoin(List<NiveauRecuperationRoute> niveauxRecuperation) {
        List<ResultatApresSoin> resultats = new ArrayList<>();
        trierListeEtatParPrioriteRoute();
        double budget_patient = this.budget;

        for (EtatRoute etatAnomalie : etat) {
            ResultatApresSoin resultat = etatAnomalie.getResultat(budget_patient, niveauxRecuperation);
            budget_patient -= resultat.getPrix();
            resultats.add(resultat);
        }

        return resultats;
    }
    
    
    public static void main(String args[]){
        List<NiveauRecuperationRoute> niveauxRecuperation = new ArrayList<>();
        niveauxRecuperation.add(new NiveauRecuperationRoute(0, 0, 0, 4));
        niveauxRecuperation.add(new NiveauRecuperationRoute(1, 1, 3, 0));
        niveauxRecuperation.add(new NiveauRecuperationRoute(2, 4, 6, 3));
        niveauxRecuperation.add(new NiveauRecuperationRoute(3, 7, 9, 4));
        niveauxRecuperation.add(new NiveauRecuperationRoute(4, 10, 10, -1));
    
        Route d1 = new Route(1L,10000,20000,30000,40000,1,3);
        Route d2 = new Route(2L,10000,20000,30000,40000,2,2);
        Route d3 = new Route(3L,10000,20000,30000,40000,3,1);
        Route d4 = new Route(4L,10000,20000,30000,40000,3,1);
        
        EtatRoute e1 = new EtatRoute(d1,1);
        EtatRoute e2 = new EtatRoute(d2,9);
        EtatRoute e3 = new EtatRoute(d3,5);
        EtatRoute e4 = new EtatRoute(d4,3);
        
        List<EtatRoute> etat = new ArrayList<>();
        etat.add(e1);
        etat.add(e2);
        etat.add(e3);
        etat.add(e4);
        
        Patient p = new Patient("patient_1",etat,15,40000);
        //p.trierListeEtatParPrioriteRoute();
       
        List<ResultatApresSoin> anomalie = p.getResultatApresSoin(niveauxRecuperation);
        
        for(int i = 0;i<anomalie.size();i++){
            System.out.println("Dent "+anomalie.get(i).getRoute().getId()+ "niveau_compelementaire "+anomalie.get(i).getNiveau_complementaire()+"Prix total:"+anomalie.get(i).getPrix()+"niveau: "+p.getEtat().get(i).getNiveau()+ "notif: "+anomalie.get(i).getNotif()+"\n");
        }
        
        /*for(int i = 0;i<p.getEtat().size();i++){
            System.out.println("Route "+p.getEtat().get(i).getRoute().getId()+"\n");
        }*/
        
    }
    
    
}
