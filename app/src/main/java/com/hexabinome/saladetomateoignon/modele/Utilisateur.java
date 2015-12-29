package com.hexabinome.saladetomateoignon.modele;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by haidara on 20/12/15.
 */
public class Utilisateur {

    private String prenom;
    private String nom;
    private String email;
    private String motDePasse;
    private Set<PointDeRestauration> favoris = new HashSet<PointDeRestauration>();

    private double longitude,latitude;

    private TypeUtilisateur typeUtilisateur;

    private Preferences preferences;

    public Utilisateur(String nom,String prenom,String email, String motDePasse,TypeUtilisateur typeUtilisateur){
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.preferences = Preferences.getDefaultPreferences();
        this.prenom = prenom;

        // default position to IT department
        this.longitude = 4.872990;
        this.latitude = 45.783924;
        this.typeUtilisateur = typeUtilisateur;
    }


    public Set<PointDeRestauration> getFavoris() {
        return favoris;
    }

    public TypeUtilisateur getTypeUtilisateur() {
        return typeUtilisateur;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {this.prenom = prenom;}

    public String getNom() {
        return nom;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }

    public void addToFavorites(PointDeRestauration r) {
        favoris.add(r);
    }

    public boolean isFavorite(PointDeRestauration r) {
        return favoris.contains(r);
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", favoris=" + favoris +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", typeUtilisateur=" + typeUtilisateur +
                ", preferences=" + preferences +
                '}';
    }

    public enum TypeUtilisateur {
        ETUDIANT("Étudiant"),
        PROFESSEUR("Professeur");

        private String name;

        TypeUtilisateur(String name){
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
