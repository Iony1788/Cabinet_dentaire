/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dentisterie2.dentisterie2.controller.route;

import com.dentisterie2.dentisterie2.model.route.EtatRoute;
import com.dentisterie2.dentisterie2.model.route.NiveauRecuperationRoute;
import com.dentisterie2.dentisterie2.model.route.Patient;
import com.dentisterie2.dentisterie2.model.route.ResultatApresSoin;
import com.dentisterie2.dentisterie2.model.route.Route;
import com.dentisterie2.dentisterie2.repository.EtatRouteRepository;
import com.dentisterie2.dentisterie2.repository.NiveauRecuperationRouteRepository;
import com.dentisterie2.dentisterie2.repository.RouteRepository;
import com.dentisterie2.dentisterie2.service.EtatRouteService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Ionisoa
 */
@Controller
public class RouteController {
    private final RouteRepository dentRepository;
    
    @Autowired
    private NiveauRecuperationRouteRepository niveau_repository;
    
    @Autowired
    private EtatRouteRepository route_repo;
    
    @Autowired
    private EtatRouteService route_service;

    public RouteController(RouteRepository dentRepository) {
        this.dentRepository = dentRepository;
    }
    

    @GetMapping("/importRouteData")
    public String importDentData() {
        try {
            FileInputStream file = new FileInputStream(new File("D:/s5/projet/spring mvc/dentisterie2/donnee/routes1.xlsx"));
            //InputStream inputStream = getClass().getResourceAsStream("/dents.xlsx");
            Workbook workbook = WorkbookFactory.create(file);
           // Workbook workbook = WorkbookFactory.create(getClass().getResourceAsStream("D://s5/projet/spring mvc/dentisterie/donnee/dents.xlsx"));

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Route dent = new Route();
                dent.setId(Math.round(row.getCell(0).getNumericCellValue()));
                dent.setCout_traitement((double)row.getCell(1).getNumericCellValue());
                dent.setCout_remplacement((double)row.getCell(2).getNumericCellValue());
                dent.setVal_priorite_economique((int) row.getCell(3).getNumericCellValue());
                dent.setVal_priorite_decoration((int) row.getCell(4).getNumericCellValue());
                dent.setCout_nettoyage((double) row.getCell(5).getNumericCellValue());
                dent.setCout_enlevement((double) row.getCell(6).getNumericCellValue());

                dentRepository.save(dent);
            }

            return "redirect:/createRoute";
        } catch (IOException e) {
            System.out.println("erreur = "+e.getMessage());
            return "redirect:/errorPage";
        }
    }
    
    @GetMapping("/importNiveauRoute")
    public String importNiveauRouteData() {
        try {
            FileInputStream file = new FileInputStream(new File("D:/s5/projet/spring mvc/dentisterie2/donnee/niveau.xlsx"));
            Workbook workbook = WorkbookFactory.create(file);

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                NiveauRecuperationRoute dent = new NiveauRecuperationRoute();
                dent.setNiveau((int)(row.getCell(0).getNumericCellValue()));
                dent.setNiveau_min((int)row.getCell(1).getNumericCellValue());
                dent.setNiveau_max((int)row.getCell(2).getNumericCellValue());
                dent.setNiveau_apres((int) row.getCell(3).getNumericCellValue());

                niveau_repository.save(dent);
            }

            return "redirect:/createRoute";
        } catch (IOException e) {
            System.out.println("erreur = "+e.getMessage());
            return "redirect:/errorPage";
        }
    }
    
    public double calculerSommePrix(List<ResultatApresSoin> resultat) {
        double somme = 0.0;

        for (ResultatApresSoin resultatApresSoin : resultat) {
            somme += resultatApresSoin.getPrix();
        }

        return somme;
    }

    @GetMapping("/createRoute")
    public String showCreatePatientForm(Model model) {
        model.addAttribute("dents", dentRepository.findAll());
        model.addAttribute("patient", new Patient());
        return "createRoute";
    }
    
    @PostMapping("/createRoute")
    public String createPatient(Patient patient,Model model) {
        List<String> detail = new ArrayList<>();
        List<NiveauRecuperationRoute> niveau = niveau_repository.findAll();
        //List<EtatRoute> etat_avant = patient_service.getEtatRouteFromExcel();
        List<EtatRoute> etat_avant = route_repo.findAll();
        patient.setEtat(etat_avant);
        List<ResultatApresSoin> resultat = patient.getResultatApresSoin(niveau);
        
        System.out.println("route1 avant: " + etat_avant.get(0).getRoute().getId());
        System.out.println("route1 apres: " + resultat.get(0).getRoute().getId());
        
        for(int i = 0;i<etat_avant.size();i++){
            double diff = resultat.get(i).getNiveau_complementaire()-etat_avant.get(i).getNiveau();
            if(diff!= 0){
               detail.add("PK"+resultat.get(i).getRoute().getId()+":    "+resultat.get(i).getNotif()+ " Prix : "+resultat.get(i).getPrix());
            }  
        }
        
        Collections.sort(resultat, Comparator.comparingLong(route -> route.getRoute().getId()));
        
        model.addAttribute("etat_avant", route_repo.findAll());
        model.addAttribute("etat_apres", resultat);
        model.addAttribute("montant_total",this.calculerSommePrix(resultat));
        model.addAttribute("detail",detail);

        return "showPropositionRoute";
    }
    
    @GetMapping("/ajouter_route")
    public String afficherFormulaire() {
        return "ajouterEtatRoute";
    }

    @PostMapping("/ajouter_route")
    public String ajouterEtatRoute(@RequestParam("idsDent") String idsDent, @RequestParam("niveaux") String niveaux) {
        route_service.ajouterEtatAnomalie(idsDent, niveaux);
        return "redirect:ajouter_route";
    }
    
    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
