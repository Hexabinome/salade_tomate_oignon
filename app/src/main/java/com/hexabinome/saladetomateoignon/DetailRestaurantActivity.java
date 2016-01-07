package com.hexabinome.saladetomateoignon;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import com.google.gson.Gson;
import com.hexabinome.saladetomateoignon.modele.Avis;
import com.hexabinome.saladetomateoignon.modele.PointDeRestauration;
import com.hexabinome.saladetomateoignon.modele.Utilisateur;

import java.util.List;

public class DetailRestaurantActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static final String RESTAURANT_EXTRA = "restaurant_courant";


    private PointDeRestauration pointDeRestauration;

    private TextView noteTextview, prixTextView, tempsAttenteTextView, distanceTextView,descriptionTextView;
    private LinearLayout criticLayout;


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
        prixTextView = (TextView) findViewById(R.id.priceRestaurant);
        tempsAttenteTextView = (TextView) findViewById(R.id.restaurantTemps);
        distanceTextView = (TextView) findViewById(R.id.distanceRestaurant);
        descriptionTextView = (TextView) findViewById(R.id.description);
        criticLayout = (LinearLayout) findViewById(R.id.comments);
        imageView = (ImageView) findViewById(R.id.imageRestaurant);

        tempsAttenteTextView.setText(String.format(getString(R.string.temps), pointDeRestauration.getTempsAttenteMoy()));

        if(pointDeRestauration.getTypePointDeRestauration().contains(PointDeRestauration.TypePointDeRestauration.SUPERMARCHE)){

            prixTextView.setText("-- €");
        } else {
            prixTextView.setText(String.format(getString(R.string.prix_restaurant), pointDeRestauration.getPrix()));
        }

        List<Avis> avis = pointDeRestauration.getAvisList();
        for(int i =0; i < avis.size(); i++)
        {
            addComment(criticLayout, avis.get(i));
        }
        distanceTextView.setText(String.format(getString(R.string.distance_restaurant), pointDeRestauration.getDistance(utilisateur.getLongitude(), utilisateur.getLatitude())));
        noteTextview.setText(String.format(getString(R.string.note_restaurant), pointDeRestauration.getNote()));
        descriptionTextView.setText(pointDeRestauration.getDescription());
        if(pointDeRestauration.getIdPhoto() != PointDeRestauration.NO_PHOTO){
            imageView.setImageDrawable(getDrawable(pointDeRestauration.getIdPhoto()));
        }

    }

    private void addComment(LinearLayout layout, Avis avis){
        LinearLayout HorLayout = new LinearLayout(this);
        HorLayout.setOrientation(LinearLayout.HORIZONTAL);
        HorLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        HorLayout.setPadding(20,30,20,30);
        String name = avis.getAuteur();
        String comment = avis.getCommentaire();
        double note = avis.getNote();
        TextView nameTextView = new TextView(this);
        nameTextView.setText(name+'\n'+String.valueOf((int)note)+"/5");
        nameTextView.setTypeface(null, Typeface.BOLD);
        nameTextView.setGravity(Gravity.CENTER);
        TextView commentTextView = new TextView(this);
        commentTextView.setText(comment);
        HorLayout.addView(nameTextView);
        HorLayout.addView(commentTextView);
        layout.addView(HorLayout);
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
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(PointDeRestauration.LADOUA_LATLNGBOUNDS, 0));
    }
}
