package com.hexabinome.saladetomateoignon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hexabinome.saladetomateoignon.modele.Restaurant;

public class DetailRestaurantActivity extends AppCompatActivity {

    public static final String RESTAURANT_EXTRA = "restaurant_courant";


    private Restaurant restaurant;

    private TextView noteTextview,priceTextView,timeTextView, distanceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_restaurant);

        Intent intent = getIntent();
        final String restaurant_json = intent.getStringExtra(RESTAURANT_EXTRA);
        restaurant = new Gson().fromJson(restaurant_json, Restaurant.class);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(restaurant.getName());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        noteTextview = (TextView) findViewById(R.id.note);
        priceTextView = (TextView) findViewById(R.id.priceRestaurant);
        timeTextView = (TextView) findViewById(R.id.restaurantTemps);
        distanceTextView = (TextView) findViewById(R.id.distanceRestaurant);

        timeTextView.setText(String.format(getString(R.string.temps),restaurant.getTempsAttenteMoy()));
        priceTextView.setText(String.format(getString(R.string.prix_restaurant),restaurant.getPrix()));
        distanceTextView.setText(String.format(getString(R.string.distance_restaurant),restaurant.getDistance()));
        noteTextview.setText(String.format(getString(R.string.note_restaurant),restaurant.getNote()));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
