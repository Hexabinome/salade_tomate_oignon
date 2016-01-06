package com.hexabinome.saladetomateoignon.modele;

/**
 * Created by robinroyer on 07/01/2016.
 */
public class Avis {

    private double note;
    private String commentaire;
    private String auteur;

    public Avis(double maNote, String monCommentaire, String monUserName){
        note = maNote;
        commentaire = monCommentaire;
        auteur = monUserName;
    }
}
