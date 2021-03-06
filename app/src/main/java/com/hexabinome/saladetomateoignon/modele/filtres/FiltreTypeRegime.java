package com.hexabinome.saladetomateoignon.modele.filtres;

import com.hexabinome.saladetomateoignon.modele.PointDeRestauration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haidara on 28/12/15.
 */
public class FiltreTypeRegime implements Filtre {

    private PointDeRestauration.TypeRegime typeRegime;

    public FiltreTypeRegime(PointDeRestauration.TypeRegime typeRegime){
        this.typeRegime = typeRegime;
    }

    @Override
    public List<PointDeRestauration> appliqueFiltre(final List<PointDeRestauration> pointDeRestaurations) {
        List<PointDeRestauration> deRestaurations = new ArrayList<>();

        if(typeRegime == PointDeRestauration.TypeRegime.PAS_DE_REGIME){
            return pointDeRestaurations;
        }


        for(PointDeRestauration pointDeRestauration : pointDeRestaurations){
            if(pointDeRestauration.getRegimeSet() != null && pointDeRestauration.getRegimeSet().contains(typeRegime)){
                deRestaurations.add(pointDeRestauration);
            }
        }

        return deRestaurations;
    }
}
