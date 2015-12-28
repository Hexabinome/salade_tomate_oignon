package com.hexabinome.saladetomateoignon.modele.filtres;

import com.hexabinome.saladetomateoignon.modele.PointDeRestauration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haidara on 28/12/15.
 */
public class FiltreTempsDAttente implements Filtre<Integer> {
    @Override
    public List<PointDeRestauration> appliqueFiltre(final List<PointDeRestauration> pointDeRestaurationList, Integer temps) {
        List<PointDeRestauration> deRestaurationArrayList = new ArrayList<>();


        for (PointDeRestauration pointDeRestauration : pointDeRestaurationList){
            if(pointDeRestauration.getTempsAttenteMoy() <= temps){
                deRestaurationArrayList.add(pointDeRestauration);
            }
        }

        return deRestaurationArrayList;
    }
}
