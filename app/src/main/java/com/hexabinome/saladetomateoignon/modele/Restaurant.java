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
    private String description;


    public Restaurant(String myname, double myprice, int tempsAttenteMoy,
                      double note, TypePointDeRestauration typePointDeRestauration, List<TypeRegime> regimes, double longitude, double latitude, String description) {
        this.prix = myprice;
        this.name = myname;
        this.tempsAttenteMoy = tempsAttenteMoy;
        this.note = note;
        this.typePointDeRestauration = typePointDeRestauration;
        this.regimeList = regimes;
        this.location = new Location(this.name);
        this.location.setLongitude(longitude);
        this.location.setLatitude(latitude);
        this.description = description;
    }

    public TypePointDeRestauration getTypePointDeRestauration() {
        return typePointDeRestauration;
    }

    public List<TypeRegime> getRegimeList() {
        return regimeList;
    }

    public String getDescription() {
        return description;
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
        PAS_DE_REGIME("Pas de régime"),
        VEGETALIEN("Végétalien"),
        VEGETARIEN("Végétarien"),
        SANS_PORC("Sans porc");

        private String name;

        TypeRegime(String name){
            this.name = name;
        }
        @Override
        public String toString() {
            return name;
        }
    }

    public enum TypePointDeRestauration {
        FASTFOOD("Fast food"),
        SUPERMARCHE("Supermarché"),
        RESTAURANT_UNIVERSITAIRE("Restaurant universitaire"),
        COMMERCE("Commerce");

        private String name;

        TypePointDeRestauration(String name){
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
