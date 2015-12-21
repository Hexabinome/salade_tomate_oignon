package com.hexabinome.saladetomateoignon.modele;

/**
 * Created by haidara on 20/12/15.
 */
public class Utilisateur {


    private String nom;
    private String email;
    private String motDePasse;


    private Preferences preferences;
    private String prenom;

    public Utilisateur(String nom,String prenom,String email, String motDePasse){
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.preferences = Preferences.getDefaultPreferences();
        this.prenom = prenom;

    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
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
