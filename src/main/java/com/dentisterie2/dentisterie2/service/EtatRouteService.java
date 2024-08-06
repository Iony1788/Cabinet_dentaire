/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dentisterie2.dentisterie2.service;

import com.dentisterie2.dentisterie2.model.route.EtatRoute;
import com.dentisterie2.dentisterie2.model.route.Route;
import com.dentisterie2.dentisterie2.repository.EtatRouteRepository;
import com.dentisterie2.dentisterie2.repository.RouteRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author TOAVINA
 */
@Service
public class EtatRouteService {
    @Autowired
    private EtatRouteRepository etatAnomalieRepository;
    
    @Autowired
    private RouteRepository routeRepository;

    /*public void ajouterEtatRoute(String idsDent, String niveaux) {
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
            Optional<Route> optionalRoute = routeRepository.findById(Long.parseLong(idsDents[i]));

            if (optionalRoute.isPresent()) {
                Route route = optionalRoute.get();

                route.setId(Long.parseLong(idsDents[i]));

                EtatRoute etatAnomalie = new EtatRoute();
                etatAnomalie.setRoute(route);
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
        Optional<Route> optionalRoute = routeRepository.findById(Long.parseLong(idsDents[i]));

        if (optionalRoute.isPresent()) {
            Route dent = optionalRoute.get();

            dent.setId(Long.parseLong(idsDents[i]));

            EtatRoute etatAnomalie = new EtatRoute();
            etatAnomalie.setRoute(dent);
            etatAnomalie.setNiveau(Integer.parseInt(niveauxArray[i]));

            etatAnomalieRepository.save(etatAnomalie);
        } else {
            // Handle the case where the Dent is not found
        }
    }
}

}
