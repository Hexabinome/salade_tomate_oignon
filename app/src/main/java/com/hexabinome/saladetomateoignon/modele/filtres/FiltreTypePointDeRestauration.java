package com.hexabinome.saladetomateoignon.modele.filtres;

import com.hexabinome.saladetomateoignon.modele.PointDeRestauration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by haidara on 28/12/15.
 */
public class FiltreTypePointDeRestauration implements Filtre<Set<PointDeRestauration.TypePointDeRestauration>> {
    @Override
    public List<PointDeRestauration> appliqueFiltre(final List<PointDeRestauration> pointDeRestaurations, Set<PointDeRestauration.TypePointDeRestauration> typePointDeRestaurationSet) {
        List<PointDeRestauration> deRestaurationArrayList = new ArrayList<>();

        for(PointDeRestauration pointDeRestauration : pointDeRestaurations){
            for(PointDeRestauration.TypePointDeRestauration typePointDeRestauration : typePointDeRestaurationSet){
                if(pointDeRestauration.getTypePointDeRestauration() != null && pointDeRestauration.getTypePointDeRestauration().contains(typePointDeRestauration)){
                    deRestaurationArrayList.add(pointDeRestauration);
                    break;
                }
            }
        }

        return deRestaurationArrayList;
    }
}
