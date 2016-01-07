package com.hexabinome.saladetomateoignon;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
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

    private TextView noteTextview, prixTextView, tempsAttenteTextView, distanceTextView,descriptionTextView, avisTextView;
    private LinearLayout criticLayout;
    private Button ajoutAvis;
    private RatingBar noteRatingBar;

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
        noteRatingBar = (RatingBar) findViewById(R.id.notation);
        avisTextView = (TextView) findViewById(R.id.avis);
        ajoutAvis = (Button) findViewById(R.id.addcomment);
        tempsAttenteTextView.setText(String.format(getString(R.string.temps), pointDeRestauration.getTempsAttenteMoy()));

        if(pointDeRestauration.getTypePointDeRestauration().contains(PointDeRestauration.TypePointDeRestauration.SUPERMARCHE)){

            prixTextView.setText("-- €");
        } else {
            prixTextView.setText(String.format(getString(R.string.prix_restaurant), pointDeRestauration.getPrix()));
        }

        final List<Avis> avis = pointDeRestauration.getAvisList();
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
        ajoutAvis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utilisateur user = PrefUtils.recupererUtilisateur(getApplicationContext());
                Avis avis = new Avis(noteRatingBar.getRating(),avisTextView.getText().toString(), user.getPrenom()+user.getNom());
                pointDeRestauration.addAvis(avis);
                finish();
                startActivity(getIntent());
            }
        });

        //initilialiser snack bar
        final View coordinatorLayoutView = findViewById(R.id.main_content);
        Snackbar.make(coordinatorLayoutView, "Snackbar", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    private void addComment(LinearLayout layout, Avis avis){

        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.comment, null);

        TextView nameTextView = (TextView) view.findViewById(R.id.name);
        RatingBar noteRatingBar = (RatingBar) view.findViewById(R.id.note);
        TextView commentTextView = (TextView) view.findViewById(R.id.comment);


        String name = avis.getAuteur();
        String comment = avis.getCommentaire();
        double note = avis.getNote();
        nameTextView.setText(name);
        noteRatingBar.setRating(((float) note));
        commentTextView.setText(comment);
        layout.addView(view);

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
