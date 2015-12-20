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
        preference.add(new Restaurant("CROUS : Restaurant universitaire", 3.20, 15, 1.3, 3, Restaurant.TypePointDeRestauration.RESTAURANT_UNIVERSITAIRE,null));
        preference.add(new Restaurant("Olivier", 4.40, 25, 1.7, 3, Restaurant.TypePointDeRestauration.RESTAURANT_UNIVERSITAIRE,null));
        preference.add(new Restaurant("Grillon", 4.40, 20, 1.8, 4, Restaurant.TypePointDeRestauration.RESTAURANT_UNIVERSITAIRE,null));
        preference.add(new Restaurant("La grignotte", 5.30, 5, 1.7, 3, Restaurant.TypePointDeRestauration.RESTAURANT_UNIVERSITAIRE,null));
        preference.add(new Restaurant("Le Castor et Pollux", 4.40, 10, 0.5, 1, Restaurant.TypePointDeRestauration.RESTAURANT_UNIVERSITAIRE,null));
        preference.add(new Restaurant("Beurk", 4.40, 10, 0.5, 1, Restaurant.TypePointDeRestauration.RESTAURANT_UNIVERSITAIRE,null));
        preference.add(new Restaurant("Supermarché : Carrefour Market", 5.60, 10, 0.5, 1, Restaurant.TypePointDeRestauration.SUPERMARCHE,null));

        return preference;
    }
}
