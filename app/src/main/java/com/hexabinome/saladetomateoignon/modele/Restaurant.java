package com.hexabinome.saladetomateoignon.modele;

import java.util.Date;

/**
 * Created by robinroyer on 10/12/2015.
 */
public class Restaurant {
    //TODO: ADD PHOTOS OF RESTAURANTS
    private String name;
    private Double price;
    private Double tempsAttenteMoy;
    private Double distance; // TODO à remplacer par position, puis calculer la distance
    private Integer grade;

    public Restaurant(String myname, double myprice, double tempsAttenteMoy, double distance, int grade) {
        price = myprice;
        name = myname;
        this.tempsAttenteMoy = tempsAttenteMoy;
        this.distance = distance;
        this.grade = grade;
    }


    @Override
    public String toString(){
        return getName() + "         " +getPrice() + " €";
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Double getTempsAttenteMoy() { return tempsAttenteMoy; }

    public Double getDistance() { return distance; }

    public Integer getGrade() { return grade; }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Restaurant)) {
            return false;
        }

        Restaurant r = (Restaurant)o;
        return this.getName().equals(r.getName());
    }
}
