package com.hexabinome.saladetomateoignon.modele.filtres;

import com.hexabinome.saladetomateoignon.modele.PointDeRestauration;

import java.util.List;

/**
 * Created by haidara on 28/12/15.
 */
public interface Filtre {
    /**
     * Effectue un filtre sur une liste de restaurant.
     * @param pointDeRestaurations la liste en entrée sur laquelle on doit effectuer le tri
     * @return la liste filtrée
     */
    List<PointDeRestauration> appliqueFiltre(final List<PointDeRestauration> pointDeRestaurations);
}
