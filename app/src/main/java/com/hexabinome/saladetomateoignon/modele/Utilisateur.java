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
    private Set<Restaurant> favoris = new HashSet<Restaurant>();

    private Preferences preferences;

    public Utilisateur(String nom,String prenom,String email, String motDePasse){
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.preferences = Preferences.getDefaultPreferences();
        this.prenom = prenom;

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

    public void addToFavorites(Restaurant r) {
        favoris.add(r);
    }

    public boolean isFavorite(Restaurant r) {
        return favoris.contains(r);
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", preferences=" + preferences +
                ", prenom='" + prenom + '\'' +
                '}';
    }
}
