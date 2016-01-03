package com.hexabinome.saladetomateoignon.modele.filtres;

import com.hexabinome.saladetomateoignon.modele.PointDeRestauration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haidara on 28/12/15.
 */
public class FiltreNote implements Filtre {

    private double note;

    public FiltreNote(double note){
        this.note = note;
    }

    @Override
    public List<PointDeRestauration> appliqueFiltre(final List<PointDeRestauration> pointDeRestaurations) {
        List<PointDeRestauration> deRestaurationArrayList = new ArrayList<>();

        for(PointDeRestauration pointDeRestauration : pointDeRestaurations){
            if(pointDeRestauration.getNote() >= note){
                deRestaurationArrayList.add(pointDeRestauration);
            }
        }

        return deRestaurationArrayList;
    }
}
