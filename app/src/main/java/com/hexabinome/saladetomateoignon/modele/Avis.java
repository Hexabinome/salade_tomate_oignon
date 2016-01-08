package com.hexabinome.saladetomateoignon.modele;

/**
 * Created by robinroyer on 07/01/2016.
 */
public class Avis {

    private double note;
    private String commentaire;
    private String auteur;
    private String date;

    public Avis(double maNote, String monCommentaire, String monUserName, String date){
        this.note = maNote;
        this.commentaire = monCommentaire;
        this.auteur = monUserName;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public double getNote() {
        return note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public String getAuteur() {
        return auteur;
    }
}
