package com.hexabinome.saladetomateoignon.modele;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haidara on 20/12/15.
 */
public class Preferences {

    private List<Restaurant.TypePointDeRestauration> typePointDeRestaurations;



    private double distance;

    private int tempsDattente;

    private double prix;

    private int note;


    private Restaurant.TypeRegime typeRegime;


    public Preferences(double distance, int tempsDattente, double prix, Restaurant.TypeRegime typeRegime, int note){
        this.distance = distance;
        this.tempsDattente = tempsDattente;
        this.prix = prix;
        this.typePointDeRestaurations = new ArrayList<>();
        this.typeRegime = typeRegime;
        this.note = note;
    }

    public static Preferences getDefaultPreferences(){
        double distance = 1.0; // 1 km
        int tempsDattente =  15; // 15mn
        double prix = 4.5; // 4.5 €
        Restaurant.TypeRegime type = Restaurant.TypeRegime.PAS_DE_REGIME;
        int note = 3; // 3/5

        return new Preferences(distance,tempsDattente,prix,type,note);
    }

    public List<Restaurant.TypePointDeRestauration> getTypePointDeRestaurations() {
        return typePointDeRestaurations;
    }

    public void setTypePointDeRestaurations(List<Restaurant.TypePointDeRestauration> typePointDeRestaurations) {
        this.typePointDeRestaurations = typePointDeRestaurations;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getTempsDattente() {
        return tempsDattente;
    }

    public void setTempsDattente(int tempsDattente) {
        this.tempsDattente = tempsDattente;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public Restaurant.TypeRegime getTypeRegime() {
        return typeRegime;
    }

    public void setTypeRegime(Restaurant.TypeRegime typeRegime) {
        this.typeRegime = typeRegime;
    }

    @Override
    public String toString() {
        return "Preferences{" +
                "typePointDeRestaurations=" + typePointDeRestaurations +
                ", distance=" + distance +
                ", tempsDattente=" + tempsDattente +
                ", prix=" + prix +
                ", note=" + note +
                ", typeRegime=" + typeRegime +
                '}';
    }
}
