package com.hexabinome.saladetomateoignon.modele.filtres;

import com.hexabinome.saladetomateoignon.modele.PointDeRestauration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haidara on 28/12/15.
 */
public class FiltreDistance implements Filtre<Integer> {

    private double longitude;
    private double latitude;

    public FiltreDistance(double longitude,double latitude){
        this.longitude = longitude;
        this.latitude = latitude;
    }


    @Override
    public List<PointDeRestauration> appliqueFiltre(final List<PointDeRestauration> pointDeRestaurations, Integer distance) {
        List<PointDeRestauration> deRestaurationArrayList = new ArrayList<>();

        for (PointDeRestauration p : pointDeRestaurations) {
            if(p.getDistance(longitude,latitude) <= distance){
                deRestaurationArrayList.add(p);
            }
        }

        return deRestaurationArrayList;
    }



}
