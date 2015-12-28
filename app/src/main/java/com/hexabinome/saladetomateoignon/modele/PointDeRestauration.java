package com.hexabinome.saladetomateoignon.modele;


import android.location.Location;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by robinroyer on 10/12/2015.
 */
public class PointDeRestauration implements Comparable<PointDeRestauration> {
    private String name;
    private double prix;
    private int tempsAttenteMoy;
    private double note;
    private Set<TypePointDeRestauration> typePointDeRestauration;
    private Set<TypeRegime> regimeSet;
    private Location location;
    private String description;
    private int idPhoto;
    private String adresse;

    public static LatLngBounds LADOUA_LATLNGBOUNDS = new LatLngBounds(new LatLng(45.77476, 4.86031), new LatLng(45.78827, 4.88769));
    public static final int NO_PHOTO = -1;


    private PointDeRestauration() {
        typePointDeRestauration = new HashSet<>();
        regimeSet = new HashSet<>();
        location = new Location("?");
    }

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

    public String getAdresse() {
        return adresse;
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

    public Location getLocation() {
        return this.location;
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

    public static Builder builder(){
        return new Builder();
    }

    @Override
    public int compareTo(PointDeRestauration another) {
        if(this.equals(another))
            return 0;
        else
            return 1;
    }

    public static class Builder{
        private PointDeRestauration instance = new PointDeRestauration();

        public Builder name(String name){
            instance.name = name;

            return this;
        }

        public Builder prix(double prix){
            instance.prix = prix;
            return this;
        }

        public Builder note(double note){
            instance.note = note;
            return this;
        }

        public Builder idPhoto(int idPhoto){
            instance.idPhoto = idPhoto;
            return this;
        }

        public Builder adresse(String adresse){
            instance.adresse = adresse;
            return this;
        }

        public Builder location(Location location){
            instance.location = location;
            return this;
        }

        public Builder longitude(double longitude){
            instance.location.setLongitude(longitude);
            return this;
        }

        public Builder latitude(double latitude){
            instance.location.setLatitude(latitude);
            return this;
        }

        public Builder tempsAttenteMoy(int tempsAttenteMoy){
            instance.tempsAttenteMoy = tempsAttenteMoy;
            return this;
        }

        public Builder description(String description){
            instance.description = description;
            return this;
        }

        public Builder addTypePointDeRestauration(PointDeRestauration.TypePointDeRestauration typePointDeRestauration){
            instance.typePointDeRestauration.add(typePointDeRestauration);
            return this;
        }

        public Builder addTypeDeRegime(PointDeRestauration.TypeRegime typeRegime){
            instance.regimeSet.add(typeRegime);
            return this;
        }

        public PointDeRestauration build(){
            return instance;
        }
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

        TypeRegime(String name) {
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

        TypePointDeRestauration(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
