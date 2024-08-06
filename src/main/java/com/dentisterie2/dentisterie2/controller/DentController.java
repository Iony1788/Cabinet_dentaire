/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dentisterie2.dentisterie2.controller;

/**
 *
 * @author TOAVINA
 */
import com.dentisterie2.dentisterie2.model.Dent;
import com.dentisterie2.dentisterie2.repository.DentRepository;
import com.dentisterie2.dentisterie2.service.EtatAnomalieService;
import java.io.File;
import java.io.FileInputStream;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DentController {

    @Autowired
    private final DentRepository dentRepository;
    
    @Autowired
    private EtatAnomalieService etatAnomalieService;

    public DentController(DentRepository dentRepository) {
        this.dentRepository = dentRepository;
    }
    

    @GetMapping("/importDentData")
    public String importDentData() {
        try {
            FileInputStream file = new FileInputStream(new File("D:/s5/projet/spring mvc/dentisterie2/donnee/dents.xlsx"));
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
                Dent dent = new Dent();
                dent.setId(Math.round(row.getCell(0).getNumericCellValue()));
                dent.setCout_traitement((double)row.getCell(1).getNumericCellValue());
                dent.setCout_remplacement((double)row.getCell(2).getNumericCellValue());
                dent.setVal_priorite_sante((int) row.getCell(3).getNumericCellValue());
                dent.setVal_priorite_beaute((int) row.getCell(4).getNumericCellValue());
                dent.setCout_nettoyage((double) row.getCell(5).getNumericCellValue());
                dent.setCout_enlevement((double) row.getCell(6).getNumericCellValue());

                dentRepository.save(dent);
            }

            return "redirect:/createPatient";
        } catch (IOException e) {
            System.out.println("erreur = "+e.getMessage());
            return "redirect:/errorPage";
        }
    }
    
    

    @GetMapping("/ajouter_dent")
    public String afficherFormulaire() {
        return "ajouterEtatAnomalie";
    }

    @PostMapping("/ajouter_dent")
    public String ajouterEtatAnomalie(@RequestParam("idsDent") String idsDent, @RequestParam("niveaux") String niveaux) {
        etatAnomalieService.ajouterEtatAnomalie(idsDent, niveaux);
        return "redirect:ajouter_dent";
    }
}