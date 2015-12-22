package com.hexabinome.saladetomateoignon.modele;



import android.location.Location;

import java.util.List;

/**
 * Created by robinroyer on 10/12/2015.
 */
public class Restaurant {
    //TODO: ADD PHOTOS OF RESTAURANTS
    private String name;
    private double prix;
    private int tempsAttenteMoy;
    private double note;
    private TypePointDeRestauration typePointDeRestauration;
    private List<TypeRegime> regimeList;
   private Location location;


    public Restaurant(String myname, double myprice, int tempsAttenteMoy,
                      double note, TypePointDeRestauration typePointDeRestauration, List<TypeRegime> regimes, double longitude, double latitude) {
        this.prix = myprice;
        this.name = myname;
        this.tempsAttenteMoy = tempsAttenteMoy;
        this.note = note;
        this.typePointDeRestauration = typePointDeRestauration;
        this.regimeList = regimes;
        location = new Location(this.name);
        location.setLongitude(longitude);
        location.setLatitude(latitude);
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

    public double getDistance(double destinationLongitude, double destinationLatitude) {
        Location destionation = new Location("UserLocation");
        destionation.setLatitude(destinationLatitude);
        destionation.setLongitude(destinationLongitude);

        return location.distanceTo(destionation);
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
