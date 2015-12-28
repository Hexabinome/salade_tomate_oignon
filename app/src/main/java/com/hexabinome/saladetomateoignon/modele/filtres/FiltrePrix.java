package com.hexabinome.saladetomateoignon.modele.filtres;

import com.hexabinome.saladetomateoignon.modele.PointDeRestauration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haidara on 28/12/15.
 */
public class FiltrePrix implements Filtre<Double> {
    @Override
    public List<PointDeRestauration> appliqueFiltre(final List<PointDeRestauration> pointDeRestaurations, Double prix) {
        List<PointDeRestauration> deRestaurationArrayList = new ArrayList<>();


        for(PointDeRestauration pointDeRestauration : pointDeRestaurations){
            if(pointDeRestauration.getPrix() <= prix){
                deRestaurationArrayList.add(pointDeRestauration);
            }
        }


        return deRestaurationArrayList;
    }
}
