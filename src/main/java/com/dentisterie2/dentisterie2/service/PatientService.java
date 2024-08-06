/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dentisterie2.dentisterie2.service;

import com.dentisterie2.dentisterie2.model.EtatAnomalie;
import com.dentisterie2.dentisterie2.repository.DentRepository;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author TOAVINA
 */
@Service
public class PatientService {
    @Autowired
    private DentRepository dentRepository;
    public List<EtatAnomalie> getAnomaliesFromExcel() {
        try {
            FileInputStream file = new FileInputStream(new File("D:/s5/projet/spring mvc/dentisterie2/donnee/anomalie.xlsx"));

            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            List<EtatAnomalie> anomalies = new ArrayList<>();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                if (row.getRowNum() == 0) {
                    continue;
                }

                Long dentId = (long) row.getCell(0).getNumericCellValue();
                int status = (int) row.getCell(1).getNumericCellValue();

                EtatAnomalie anomalie = new EtatAnomalie();
                anomalie.setDent(dentRepository.findById(dentId).orElse(null));
                anomalie.setNiveau(status);
                anomalies.add(anomalie);
            }

            workbook.close();

            return anomalies;
        } catch (IOException e) {
            System.out.println("ito ny erreur == "+e);
            return null;
        }
    }
}
