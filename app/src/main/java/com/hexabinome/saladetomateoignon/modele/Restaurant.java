package com.hexabinome.saladetomateoignon.modele;

/**
 * Created by robinroyer on 10/12/2015.
 */
public class Restaurant {
    //TODO: ADD PHOTOS OF RESTAURANTS
    private String name;
    private double price;
    private double tempsAttenteMoy;
    private double distance; // TODO à remplacer par position, puis calculer la distance

    public Restaurant(String myname, double myprice, double tempsAttenteMoy, double distance) {
        price = myprice;
        name = myname;
        this.tempsAttenteMoy = tempsAttenteMoy;
        this.distance = distance;
    }


    @Override
    public String toString(){
        return getName() + "         " +getPrice() + " €";
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getTempsAttenteMoy() { return tempsAttenteMoy; }

    public double getDistance() { return distance; }
}
