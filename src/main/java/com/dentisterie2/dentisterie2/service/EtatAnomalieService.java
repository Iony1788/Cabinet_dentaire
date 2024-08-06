/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dentisterie2.dentisterie2.service;

import com.dentisterie2.dentisterie2.model.Dent;
import com.dentisterie2.dentisterie2.model.EtatAnomalie;
import com.dentisterie2.dentisterie2.repository.DentRepository;
import com.dentisterie2.dentisterie2.repository.EtatAnomalieRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author TOAVINA
 */

@Service
public class EtatAnomalieService {
    @Autowired
    private EtatAnomalieRepository etatAnomalieRepository;
    
    @Autowired
    private DentRepository dentRepository;

    /*public void ajouterEtatAnomalie(String idsDent, String niveaux) {
        String[] idsDents;
        String[] niveauxArray;

        if (idsDent.contains("-")) {
            String[] range = idsDent.split("-");
            int start = Integer.parseInt(range[0]);
            int end = Integer.parseInt(range[1]);

            idsDents = new String[end - start + 1];
            niveauxArray = new String[end - start + 1];

            for (int i = 0; i <= end - start; i++) {
                idsDents[i] = String.valueOf(start + i);
                niveauxArray[i] = niveaux;
            }
        } else {
            idsDents = idsDent.split(",");
            niveauxArray = niveaux.split(",");
        }

        for (int i = 0; i < idsDents.length; i++) {
            Optional<Dent> optionalDent = dentRepository.findById(Long.parseLong(idsDents[i]));

            if (optionalDent.isPresent()) {
                Dent dent = optionalDent.get();

                dent.setId(Long.parseLong(idsDents[i]));

                EtatAnomalie etatAnomalie = new EtatAnomalie();
                etatAnomalie.setDent(dent);
                etatAnomalie.setNiveau(Integer.parseInt(niveauxArray[i]));

                etatAnomalieRepository.save(etatAnomalie);
            } else {

            }
        }
    }*/
    
    
    public void ajouterEtatAnomalie(String idsDent, String niveaux) {
    String[] idsDents;
    String[] niveauxArray;

    if (idsDent.startsWith("-")) {
        int end = Integer.parseInt(idsDent.substring(1));
        idsDents = new String[end];
        niveauxArray = new String[end];

        for (int i = 0; i < end; i++) {
            idsDents[i] = String.valueOf(i + 1);
            niveauxArray[i] = niveaux;
        }
    } else if (idsDent.endsWith("-")) {
        int start = Integer.parseInt(idsDent.substring(0, idsDent.length() - 1));
        int end = 32;  // Assuming the maximum value for the second case
        idsDents = new String[end - start + 1];
        niveauxArray = new String[end - start + 1];

        for (int i = 0; i <= end - start; i++) {
            idsDents[i] = String.valueOf(start + i);
            niveauxArray[i] = niveaux;
        }
    } else {
        idsDents = idsDent.split(",");
        niveauxArray = niveaux.split(",");
    }

    for (int i = 0; i < idsDents.length; i++) {
        Optional<Dent> optionalDent = dentRepository.findById(Long.parseLong(idsDents[i]));

        if (optionalDent.isPresent()) {
            Dent dent = optionalDent.get();

            dent.setId(Long.parseLong(idsDents[i]));

            EtatAnomalie etatAnomalie = new EtatAnomalie();
            etatAnomalie.setDent(dent);
            etatAnomalie.setNiveau(Integer.parseInt(niveauxArray[i]));

            etatAnomalieRepository.save(etatAnomalie);
        } else {
            // Handle the case where the Dent is not found
        }
    }
}

    
    
    
    


}

