/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dentisterie2.dentisterie2.controller;

import com.dentisterie2.dentisterie2.model.NiveauRecuperation;
import com.dentisterie2.dentisterie2.repository.NiveauRecuperationRepository;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Ionisoa
 */
@Controller
public class NiveauController {
    @Autowired
    private final NiveauRecuperationRepository niveau_Repository;

    public NiveauController(NiveauRecuperationRepository niveau_Repository) {
        this.niveau_Repository = niveau_Repository;
    }
    

    @GetMapping("/importNiveau")
    public String importDentData() {
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
                NiveauRecuperation dent = new NiveauRecuperation();
                dent.setNiveau((int)(row.getCell(0).getNumericCellValue()));
                dent.setNiveau_min((int)row.getCell(1).getNumericCellValue());
                dent.setNiveau_max((int)row.getCell(2).getNumericCellValue());
                dent.setNiveau_apres((int) row.getCell(3).getNumericCellValue());

                niveau_Repository.save(dent);
            }

            return "redirect:/createPatient";
        } catch (IOException e) {
            System.out.println("erreur = "+e.getMessage());
            return "redirect:/errorPage";
        }
    }
    
}
