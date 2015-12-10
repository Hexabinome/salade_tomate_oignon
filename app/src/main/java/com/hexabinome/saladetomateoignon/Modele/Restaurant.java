package com.hexabinome.saladetomateoignon.Modele;

/**
 * Created by robinroyer on 10/12/2015.
 */
public class Restaurant {
    //TODO: ADD PHOTOS OF RESTAURANTS
    private String name;
    private Double price;

    public Restaurant(String myname, Double myprice){
        price = myprice;
        name = myname;
    }


    @Override
    public String toString(){
        return "Name : " + getName() + "Price : " +getPrice();
    }

    // getter to display our restaurants
    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }
}
