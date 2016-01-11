package com.hexabinome.saladetomateoignon.modele;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Représente les preferences de l'utilisateur pour la recherche de restaurant.
 */
public class Preferences {

    /**
     * Les types de point de restauration voulus par l'utilisateur
     */
    private Set<PointDeRestauration.TypePointDeRestauration> typePointDeRestaurations;

    /**
     * La distance de recherche maximale souhaitée
     */
    private int distance;

    /**
     * Temps d'attente max dans le restaurant
     */
    private int tempsDattente;

    /**
     * Le prix maximal pour un repas normal
     */
    private int prix;

    /**
     * Sa note minimale
     */
    private double note;


    private PointDeRestauration.TypeRegime typeRegime;


    public Preferences(int distance, int tempsDattente, int prix, PointDeRestauration.TypeRegime typeRegime, double note) {
        this.distance = distance;
        this.tempsDattente = tempsDattente;
        this.prix = prix;
        this.typePointDeRestaurations = new HashSet<>();
        this.typeRegime = typeRegime;
        this.note = note;
    }

    public Preferences(Preferences preferences) {
        this(preferences.getDistance(), preferences.getTempsDattente(), preferences.getPrix(), preferences.getTypeRegime(), preferences.getNote());
        Set<PointDeRestauration.TypePointDeRestauration> set = new HashSet<>(preferences.getTypePointDeRestaurations());
        setTypePointDeRestaurations(set);
    }

    public static Preferences getDefaultPreferences() {
        int distance = 500; // 500m
        int tempsDattente = 15; // 15mn
        int prix = 5; // 5 €
        PointDeRestauration.TypeRegime type = PointDeRestauration.TypeRegime.PAS_DE_REGIME;
        int note = 3; // 3
        Set<PointDeRestauration.TypePointDeRestauration> set = new HashSet<>();
        set.add(PointDeRestauration.TypePointDeRestauration.CAFETERIA);
        set.add(PointDeRestauration.TypePointDeRestauration.RESTAURANT_UNIVERSITAIRE);

        Preferences defaultPref = new Preferences(distance, tempsDattente, prix, type, note);
        defaultPref.setTypePointDeRestaurations(set);
        return defaultPref;
    }

    public Set<PointDeRestauration.TypePointDeRestauration> getTypePointDeRestaurations() {
        return typePointDeRestaurations;
    }

    public void setTypePointDeRestaurations(Set<PointDeRestauration.TypePointDeRestauration> typePointDeRestaurations) {
        this.typePointDeRestaurations = typePointDeRestaurations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Preferences that = (Preferences) o;
        return Objects.equals(getDistance(), that.getDistance()) &&
                Objects.equals(getTempsDattente(), that.getTempsDattente()) &&
                Objects.equals(getPrix(), that.getPrix()) &&
                Objects.equals(getNote(), that.getNote()) &&
                Objects.equals(getTypePointDeRestaurations(), that.getTypePointDeRestaurations()) &&
                Objects.equals(getTypeRegime(), that.getTypeRegime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTypePointDeRestaurations(), getDistance(), getTempsDattente(), getPrix(), getNote(), getTypeRegime());
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getTempsDattente() {
        return tempsDattente;
    }

    public void setTempsDattente(int tempsDattente) {
        this.tempsDattente = tempsDattente;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public PointDeRestauration.TypeRegime getTypeRegime() {
        return typeRegime;
    }

    public void setTypeRegime(PointDeRestauration.TypeRegime typeRegime) {
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
