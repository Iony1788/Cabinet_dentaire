/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dentisterie2.dentisterie2.model.route;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.List;

/**
 *
 * @author Ionisoa
 */
@Entity
@Table(name = "etat_route")
public class EtatRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "id_route")
    private Route route;
    private int niveau;

    public EtatRoute() {
    }

    public EtatRoute(Route dent, int niveau) {
        this.route = dent;
        this.niveau = niveau;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route dent) {
        this.route = dent;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }
    
    public String getNotif(int niveau_apres){
        String notif = "";
        int difference = niveau_apres-niveau;
        if(difference>0){
            notif = " +"+difference;
        }
        
        else if(difference == 0){
            notif = " sans modification";
        }
        
        else{
            notif = " "+difference;
        }
        
        return notif;
    }
    
    
    public double getCout(int niveau){
        if(niveau == 0){
            return this.getRoute().getCout_remplacement();
        }
        
        else if(niveau >= 1 && niveau<=4){
            return this.getRoute().getCout_enlevement();
        }
        
        else if(niveau >= 5 && niveau<=7){
            return this.getRoute().getCout_traitement();
        }
        
        else if(niveau >= 8 && niveau<=9){
            return this.getRoute().getCout_nettoyage();
        }
        
        else{
            return 0;
        }
    }
    
    public ResultatApresSoin getResultat(double budget, List<NiveauRecuperationRoute> niveauxRecuperation) {
       
        double budget_actuel = budget;
        double cout_total = 0;
        double cout_actuel;
        int niveau_apres ;
        
        ResultatApresSoin resultat = new ResultatApresSoin();
        resultat.setRoute(this.getRoute());
        resultat.setNiveau_complementaire(this.getNiveau());
        
        if(this.niveau == 0){
            resultat.setNiveau_complementaire(10);
            resultat.setPrix(this.getCout(0));
            resultat.setNotif(this.getNotif(10));
        }
        else{
            for(int i = this.getNiveau(); i<=10;i++){
            cout_actuel = this.getCout(i);
            
            if(budget_actuel<cout_actuel){
                break;
            }
            
            if(budget_actuel>=cout_actuel){
                budget_actuel -= cout_actuel;
                System.out.println(i);
               
                cout_total += cout_actuel;
                niveau_apres = i;
                resultat.setNiveau_complementaire(niveau_apres);
                resultat.setPrix(cout_total);
                resultat.setNotif(this.getNotif(niveau_apres));
                 
               

                System.out.println("budget = "+budget_actuel);
                System.out.println("cout total = "+cout_total);
                  
            }
                 
        }
        }
        
         
        return resultat;
    }


    
}
