package com.hexabinome.saladetomateoignon.modele;

import java.util.ArrayList;

/**
 * Cette classe permettra de générer des données pour l'interface graphique.
 * Elle contiendra des méthodes statiques etc...
 * @author Mohamed El Mouctar HAIDARA
 */
public final class Mock {

    private Mock(){

    }


    public static ArrayList<Restaurant> getRestaurantLaDoua(){
        ArrayList <Restaurant> preference = new ArrayList<Restaurant>();
        //TODO : create a real list of the restaurants around INSA
        preference.add(new Restaurant("Restaurant universitaire", 3.20));
        preference.add(new Restaurant("Beurk", 4.40));
        preference.add(new Restaurant("Olivier", 4.40));
        preference.add(new Restaurant("grillon", 4.40));
        preference.add(new Restaurant("La grignotte", 5.30));
        preference.add(new Restaurant("Le Castor et Pollux", 4.40));
        preference.add(new Restaurant("Restaurant universitaire", 3.20));
        preference.add(new Restaurant("Beurk", 4.40));
        preference.add(new Restaurant("Olivier", 4.40));
        preference.add(new Restaurant("grillon", 4.40));
        preference.add(new Restaurant("La grignotte", 5.30));
        preference.add(new Restaurant("Le Castor et Pollux", 4.40));
        preference.add(new Restaurant("Restaurant universitaire", 3.20));
        preference.add(new Restaurant("Beurk", 4.40));
        preference.add(new Restaurant("Olivier", 4.40));
        preference.add(new Restaurant("grillon", 4.40));
        preference.add(new Restaurant("La grignotte", 5.30));
        preference.add(new Restaurant("Le Castor et Pollux", 4.40));

        return preference;
    }
}
