/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dentisterie2.dentisterie2.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Ionisoa
 */
public class Patient {
    private String nom;
    private List<EtatAnomalie> etat = new ArrayList<>();
    
    //5 pour santé
    //15 pour beauté
    private int priorite;
    private double budget;

    public Patient() {
    }
    
    public Patient(String nom, List<EtatAnomalie> etat, int priorite, double budget) {
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

    public List<EtatAnomalie> getEtat() {
        return etat;
    }

    public void setEtat(List<EtatAnomalie> etat) {
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
    
    public void trierEtatParPrioriteSante() {
        if (priorite == 5) {
            Collections.sort(etat, Comparator.comparingInt(anomalie -> anomalie.getDent().getVal_priorite_sante()));
        } else if (priorite == 15) {
            Collections.sort(etat, Comparator.comparingInt(anomalie -> anomalie.getDent().getVal_priorite_beaute()));
        }
    }
    
    public List<ResultatApresSoin> getResultatApresSoin(List<NiveauRecuperation> niveauxRecuperation) {
        List<ResultatApresSoin> resultats = new ArrayList<>();
        trierEtatParPrioriteSante();
        double budget_patient = this.budget;

        for (EtatAnomalie etatAnomalie : etat) {
            ResultatApresSoin resultat = etatAnomalie.getResultat(budget_patient, niveauxRecuperation);
            budget_patient -= resultat.getPrix();
            resultats.add(resultat);
            
            System.out.println("resultat final = "+resultat.getNiveau_complementaire());
        }

        return resultats;
    }
    
    
    /*public static void main(String args[]){
        List<NiveauRecuperation> niveauxRecuperation = new ArrayList<>();
        niveauxRecuperation.add(new NiveauRecuperation(0, 0, 0, 4));
        niveauxRecuperation.add(new NiveauRecuperation(1, 1, 3, 0));
        niveauxRecuperation.add(new NiveauRecuperation(2, 4, 6, 3));
        niveauxRecuperation.add(new NiveauRecuperation(3, 7, 9, 4));
        niveauxRecuperation.add(new NiveauRecuperation(4, 10, 10, -1));
    
        Dent d1 = new Dent(1L,10000,20000,30000,40000,1,3);
        Dent d2 = new Dent(2L,10000,20000,30000,40000,2,2);
        Dent d3 = new Dent(3L,10000,20000,30000,40000,3,1);
        
        EtatAnomalie e1 = new EtatAnomalie(d1,1);
        EtatAnomalie e2 = new EtatAnomalie(d2,9);
        EtatAnomalie e3 = new EtatAnomalie(d3,0);
        
        List<EtatAnomalie> etat = new ArrayList<>();
        etat.add(e1);
        etat.add(e2);
        etat.add(e3);
        
        Patient p = new Patient("patient_1",etat,15,130000);
       
        List<ResultatApresSoin> anomalie = p.getResultatApresSoin(niveauxRecuperation);
        
        for(int i = 0;i<anomalie.size();i++){
            System.out.println("Dent "+anomalie.get(i).getDent().getId()+ "niveau_compelementaire "+anomalie.get(i).getNiveau_complementaire()+"Prix total:"+anomalie.get(i).getPrix()+"niveau: "+p.getEtat().get(i).getNiveau()+ "notif: "+anomalie.get(i).getNotif()+"\n");
        }
    
    1,2,3,4 : reparation 5000Ar
    5-7: reparation 2000
    
    8-10 nettoyage 500AR
    
    remplacement 100000Ar
        
    9- (9 hatram farany)
    -9 (valeur alohan ny 9 rehetra)
    }*/
    
    
}
