package com.hexabinome.saladetomateoignon.modele;

/**
 * Created by haidara on 20/12/15.
 */
public class Utilisateur {


    private String nom;
    private String email;
    private String motDePasse;


    private Preferences preferences;

    public Utilisateur(String nom,String email, String motDePasse, Preferences preferences){
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.preferences = preferences;

    }

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
}
