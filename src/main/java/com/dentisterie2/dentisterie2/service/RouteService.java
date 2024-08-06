/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dentisterie2.dentisterie2.service;

import com.dentisterie2.dentisterie2.model.route.EtatRoute;
import com.dentisterie2.dentisterie2.repository.RouteRepository;
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
public class RouteService {
    @Autowired
    private RouteRepository routeRepository;
    public List<EtatRoute> getEtatRouteFromExcel() {
        try {
            FileInputStream file = new FileInputStream(new File("D:/s5/projet/spring mvc/dentisterie2/donnee/etat_route.xlsx"));

            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            List<EtatRoute> anomalies = new ArrayList<>();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                if (row.getRowNum() == 0) {
                    continue;
                }

                Long routeId = (long) row.getCell(0).getNumericCellValue();
                int status = (int) row.getCell(1).getNumericCellValue();

                EtatRoute anomalie = new EtatRoute();
                anomalie.setRoute(routeRepository.findById(routeId).orElse(null));
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
