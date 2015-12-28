package com.hexabinome.saladetomateoignon.modele;


import android.location.Location;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.Set;

/**
 * Created by robinroyer on 10/12/2015.
 */
public class PointDeRestauration {
    private String name;
    private double prix;
    private int tempsAttenteMoy;
    private double note;
    private Set<TypePointDeRestauration> typePointDeRestauration;
    private Set<TypeRegime> regimeSet;
    private Location location;
    private String description;
    private int idPhoto;

    public static LatLngBounds LADOUA_LATLNGBOUNDS = new LatLngBounds(new LatLng(45.77476, 4.86031), new LatLng(45.78827,4.88769));
    public static final int NO_PHOTO = -1;

    public PointDeRestauration(String myname, double myprice, int tempsAttenteMoy,
                               double note, Set<TypePointDeRestauration> typePointDeRestauration, Set<TypeRegime> regimes, double longitude, double latitude, String description, int idPhoto) {
        this.prix = myprice;
        this.name = myname;
        this.tempsAttenteMoy = tempsAttenteMoy;
        this.note = note;
        this.typePointDeRestauration = typePointDeRestauration;
        this.regimeSet = regimes;
        this.location = new Location(this.name);
        this.location.setLongitude(longitude);
        this.location.setLatitude(latitude);
        this.description = description;
        this.idPhoto = idPhoto;
    }

    public int getIdPhoto() {
        return idPhoto;
    }

    public Set<TypePointDeRestauration> getTypePointDeRestauration() {
        return typePointDeRestauration;
    }

    public Set<TypeRegime> getRegimeSet() {
        return regimeSet;
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

    public Location getLocation(){ return this.location; }

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
        if (o == null || !(o instanceof PointDeRestauration)) {
            return false;
        }

        PointDeRestauration r = (PointDeRestauration) o;
        return this.getName().equals(r.getName());
    }

    public enum TypeRegime {
        PAS_DE_REGIME("Pas de régime"),
        SANS_PORC("Sans porc"),
        VEGETALIEN("Végétalien"),
        VEGETARIEN("Végétarien");

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
        CAFETERIA("Caféteria"),
        COMMERCE("Commerce"),
        FASTFOOD("Fast food"),
        RESTAURANT_UNIVERSITAIRE("Restaurant universitaire"),
        SUPERMARCHE("Supermarché");

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
