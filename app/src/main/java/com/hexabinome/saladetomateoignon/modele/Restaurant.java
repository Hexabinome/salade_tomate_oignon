package com.hexabinome.saladetomateoignon.modele;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by robinroyer on 10/12/2015.
 */
public class Restaurant {
    //TODO: ADD PHOTOS OF RESTAURANTS
    private String name;
    private double prix;
    private int tempsAttenteMoy;
    private double distance; // TODO à remplacer par position, puis calculer la distance
    private double note;
    private TypePointDeRestauration typePointDeRestauration;
    private List<TypeRegime> regimeList;


    public Restaurant(String myname, double myprice, int tempsAttenteMoy, double distance,
                      double note, TypePointDeRestauration typePointDeRestauration, List<TypeRegime> regimes) {
        this.prix = myprice;
        this.name = myname;
        this.tempsAttenteMoy = tempsAttenteMoy;
        this.distance = distance;
        this.note = note;
        this.typePointDeRestauration = typePointDeRestauration;
        this.regimeList = regimes;
    }

    public String getName() {
        return name;
    }

    public double getPrix() {
        return prix;
    }

    public int getTempsAttenteMoy() {
        return tempsAttenteMoy;
    }

    public double getDistance() {
        return distance;
    }

    public double getNote() {
        return note;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Restaurant)) {
            return false;
        }

        Restaurant r = (Restaurant) o;
        return this.getName().equals(r.getName());
    }

    public enum TypeRegime {
        VEGETALIEN,
        VEGETARIEN,
        SANS_PORC,
        PAS_DE_REGIME
    }

    public enum TypePointDeRestauration {
        FASTFOOD,
        SUPERMARCHE,
        RESTAURANT_UNIVERSITAIRE,
        COMMERCE
    }
}
