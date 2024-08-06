/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dentisterie2.dentisterie2.controller;

/**
 *
 * @author TOAVINA
 */

import com.dentisterie2.dentisterie2.model.EtatAnomalie;
import com.dentisterie2.dentisterie2.model.NiveauRecuperation;
import com.dentisterie2.dentisterie2.model.Patient;
import com.dentisterie2.dentisterie2.model.ResultatApresSoin;
import com.dentisterie2.dentisterie2.repository.DentRepository;
import com.dentisterie2.dentisterie2.repository.EtatAnomalieRepository;
import com.dentisterie2.dentisterie2.repository.NiveauRecuperationRepository;
import com.dentisterie2.dentisterie2.service.PatientService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PatientController {
    @Autowired
    private final DentRepository dentRepository;
    
    @Autowired
    private NiveauRecuperationRepository niveau_repository;
    
    @Autowired
    private PatientService patient_service;
    
    @Autowired
    private EtatAnomalieRepository etat_repo;

    public PatientController(DentRepository dentRepository) {
        this.dentRepository = dentRepository;
    }
    
    public double calculerSommePrix(List<ResultatApresSoin> resultat) {
        double somme = 0.0;

        for (ResultatApresSoin resultatApresSoin : resultat) {
            somme += resultatApresSoin.getPrix();
        }

        return somme;
    }

    @GetMapping("/createPatient")
    public String showCreatePatientForm(Model model) {
        model.addAttribute("dents", dentRepository.findAll());
        model.addAttribute("patient", new Patient());
        return "createPatient";
    }
    
    @PostMapping("/createPatient")
    public String createPatient(Patient patient,Model model) {
        List<String> detail = new ArrayList<>();
        List<NiveauRecuperation> niveau = niveau_repository.findAll();
        //List<EtatAnomalie> etat_avant = patient_service.getAnomaliesFromExcel();
        List<EtatAnomalie> etat_avant = etat_repo.findAll();
        patient.setEtat(etat_avant);
        List<ResultatApresSoin> resultat = patient.getResultatApresSoin(niveau);
        
        for(int i = 0;i<etat_avant.size();i++){
            double diff = resultat.get(i).getNiveau_complementaire()-etat_avant.get(i).getNiveau();
            if(diff!= 0){
               detail.add("id:"+resultat.get(i).getDent().getId()+":    "+resultat.get(i).getNotif()+ " Prix : "+resultat.get(i).getPrix());
            }  
        }
        
        Collections.sort(resultat, Comparator.comparingLong(dent -> dent.getDent().getId()));
        
        model.addAttribute("etat_avant", etat_repo.findAll());
        model.addAttribute("etat_apres", resultat);
        model.addAttribute("montant_total",this.calculerSommePrix(resultat));
        model.addAttribute("reste",patient.getBudget() - this.calculerSommePrix(resultat));
        model.addAttribute("detail",detail);

        return "showProposition";
    }

}

