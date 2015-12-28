package com.hexabinome.saladetomateoignon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import com.google.gson.Gson;
import com.hexabinome.saladetomateoignon.modele.PointDeRestauration;
import com.hexabinome.saladetomateoignon.modele.Utilisateur;

public class DetailRestaurantActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static final String RESTAURANT_EXTRA = "restaurant_courant";


    private PointDeRestauration pointDeRestauration;

    private TextView noteTextview,priceTextView,timeTextView, distanceTextView,descriptionTextView;


    private ImageView imageView;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_restaurant);

        Intent intent = getIntent();
        final String restaurant_json = intent.getStringExtra(RESTAURANT_EXTRA);
        pointDeRestauration = new Gson().fromJson(restaurant_json, PointDeRestauration.class);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(pointDeRestauration.getName());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Utilisateur utilisateur = PrefUtils.recupererUtilisateur(this);

        noteTextview = (TextView) findViewById(R.id.note);
        priceTextView = (TextView) findViewById(R.id.priceRestaurant);
        timeTextView = (TextView) findViewById(R.id.restaurantTemps);
        distanceTextView = (TextView) findViewById(R.id.distanceRestaurant);
        descriptionTextView = (TextView) findViewById(R.id.description);
        imageView = (ImageView) findViewById(R.id.imageRestaurant);

        timeTextView.setText(String.format(getString(R.string.temps), pointDeRestauration.getTempsAttenteMoy()));
        priceTextView.setText(String.format(getString(R.string.prix_restaurant), pointDeRestauration.getPrix()));
        distanceTextView.setText(String.format(getString(R.string.distance_restaurant), pointDeRestauration.getDistance(utilisateur.getLongitude(), utilisateur.getLatitude())));
        noteTextview.setText(String.format(getString(R.string.note_restaurant), pointDeRestauration.getNote()));
        descriptionTextView.setText(pointDeRestauration.getDescription());
        if(pointDeRestauration.getIdPhoto() != PointDeRestauration.NO_PHOTO){
            imageView.setImageDrawable(getDrawable(pointDeRestauration.getIdPhoto()));
        }

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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //TODO: refactor in model in a getLatLng
        double latitude = pointDeRestauration.getLocation().getLatitude();
        double longitude = pointDeRestauration.getLocation().getLongitude();

        LatLng restaurant_to_show = new LatLng(latitude, longitude);

        mMap.addMarker(new MarkerOptions().position(restaurant_to_show).title(pointDeRestauration.getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(restaurant_to_show));
        // zoom to la doua
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(pointDeRestauration.LADOUA_LATLNGBOUNDS, 0));
    }
}
