package com.hexabinome.saladetomateoignon.fragment.favoris;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.hexabinome.saladetomateoignon.modele.Restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette class permet de remplir chaque item de la liste
 */
public class RestaurantAdapter extends BaseAdapter {


    private List<Restaurant> restaurantList;

    private LayoutInflater layoutInflater;


    public RestaurantAdapter(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
        restaurantList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return restaurantList.size();
    }

    @Override
    public Restaurant getItem(int position) {
        return restaurantList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    private static class ViewHolder{
        // TODO : put the fields for each item in the list
    }

    public void setRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }
}
