package com.hexabinome.saladetomateoignon;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.ScrollView;
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

public class DetailRestaurantActivity extends AppCompatActivity implements OnMapReadyCallback,
        CustomMarkFragment.CustomMarkFragmentListener {

    public static final String RESTAURANT_EXTRA = "restaurant_courant";

    public static final String TAG = "DetailRestaurantActivi";

    private PointDeRestauration pointDeRestauration;

    private TextView noteTextview, prixTextView, tempsAttenteTextView, distanceTextView, descriptionTextView, menuDuJourTextView;

    private LinearLayout avisLayout;
    private RatingBar noteRatingBar;
    private FloatingActionButton deleteFloatingActionButton;

    private ImageView imageView;
    private GoogleMap mMap;
    private View userAvisView;

    private float oldMark;
    LayoutInflater inflater;

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



        final NestedScrollView mainScrollView = (NestedScrollView) findViewById(R.id.nested_scroll_view);
        ImageView transparentImageView = (ImageView) findViewById(R.id.transparent_image);

        // override the OnTouchlistener to let the user scroll on the map and on the scrollView
        transparentImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        mainScrollView.requestDisallowInterceptTouchEvent(true);
                        // Disable touch on transparent view
                        return false;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        mainScrollView.requestDisallowInterceptTouchEvent(false);
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        mainScrollView.requestDisallowInterceptTouchEvent(true);
                        return false;

                    default:
                        return true;
                }
            }
        });


        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);


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
        avisLayout = (LinearLayout) findViewById(R.id.comments);
        imageView = (ImageView) findViewById(R.id.imageRestaurant);
        noteRatingBar = (RatingBar) findViewById(R.id.notation);
        deleteFloatingActionButton = (FloatingActionButton) findViewById(R.id.delete);
        tempsAttenteTextView.setText(
                String.format(getString(R.string.temps), pointDeRestauration.getTempsAttenteMoy()));
        menuDuJourTextView = (TextView) findViewById(R.id.menuDujour);

        if(pointDeRestauration.getMenuDuJour() != null){
            menuDuJourTextView.setText(pointDeRestauration.getMenuDuJour());
        } else {
            findViewById(R.id.menuCardview).setVisibility(View.GONE);
        }


        if (pointDeRestauration.getTypePointDeRestauration()
                .contains(PointDeRestauration.TypePointDeRestauration.SUPERMARCHE)) {

            prixTextView.setText("-- €");
        } else {
            if (utilisateur.getTypeUtilisateur() == Utilisateur.TypeUtilisateur.PROFESSEUR)
                prixTextView.setText(String.format(getString(R.string.prix_restaurant),
                        pointDeRestauration.getPrix() + PointDeRestauration.DIFFERENCE_PRIX));
            {
                prixTextView.setText(String.format(getString(R.string.prix_restaurant),
                        pointDeRestauration.getPrix()));
            }
        }
        // adding static avis
        final List<Avis> avis = pointDeRestauration.getAvisList();
        for (int i = 0; i < avis.size(); i++) {
            addComment(avisLayout, inflater, avis.get(i), false);
        }
        //adding user's avis
        Avis userAvis = PrefUtils.getAvisRestaurantFromPref(this, pointDeRestauration.getName());
        if (userAvis != null) {
            userAvisView = addComment(avisLayout, inflater, userAvis, true);
        }
        distanceTextView.setText(String.format(getString(R.string.distance_restaurant),
                pointDeRestauration.getDistance(
                        utilisateur.getLongitude(), utilisateur.getLatitude())));
        noteTextview.setText(String.format(getString(R.string.note_restaurant),
                pointDeRestauration.getNote()));
        descriptionTextView.setText(pointDeRestauration.getDescription());

        if (pointDeRestauration.getIdPhoto() != PointDeRestauration.NO_PHOTO) {
            imageView.setImageDrawable(getDrawable(pointDeRestauration.getIdPhoto()));
        }

        noteRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (fromUser) {
                    CustomMarkFragment dialog = new CustomMarkFragment();
                    dialog.setParam(pointDeRestauration.getName(), rating);
                    dialog.show(getFragmentManager(), "tag");
                }
            }

        });

        deleteFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        DetailRestaurantActivity.this);
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.setTitle("Suppression");
                alertDialog.setMessage(
                        "Voulez vous supprimer le point de restaurant " + pointDeRestauration.getName());
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Oui",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Utilisateur user = PrefUtils.recupererUtilisateur(getApplicationContext());
                                user.getFavoris().remove(pointDeRestauration);

                                PrefUtils.sauvegardeUtilisateur(DetailRestaurantActivity.this,
                                        user);
                                finish();
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Non",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });
        oldMark = 0;
    }


    private View addComment(LinearLayout layout, LayoutInflater inflater, Avis avis,
                            boolean userComment) {

        View view = inflater.inflate(R.layout.comment, null);

        TextView nameTextView = (TextView) view.findViewById(R.id.name);
        final RatingBar noteRatingBarComment = (RatingBar) view.findViewById(R.id.note);
        TextView commentTextView = (TextView) view.findViewById(R.id.comment);
        TextView dateTextView = (TextView) view.findViewById(R.id.date);
        ImageButton imageButton = (ImageButton) view.findViewById(R.id.delete_comment);

        if (userComment) {
            imageButton.setVisibility(View.VISIBLE);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            DetailRestaurantActivity.this);
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.setTitle("Suppression");
                    alertDialog.setMessage("Voulez vous supprimer vraiment votre commentaire ? ");
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Oui",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    noteRatingBar.setRating(0);
                                    userAvisView.setVisibility(View.GONE);
                                    PrefUtils.saveAvisRestaurantFromPref(
                                            DetailRestaurantActivity.this,
                                            pointDeRestauration.getName(), null);
                                }
                            });
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Non",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();

                }
            });
        }

        String name = avis.getAuteur();
        String comment = avis.getCommentaire();
        double note = avis.getNote();
        nameTextView.setText(name);
        noteRatingBarComment.setRating(((float) note));
        commentTextView.setText(comment);
        dateTextView.setText(avis.getDate());

        layout.addView(view);
        return view;
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

        final LatLng restaurant_to_show = new LatLng(latitude, longitude);

        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                mMap.addMarker(new MarkerOptions().position(restaurant_to_show)
                        .title(pointDeRestauration.getName()));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(restaurant_to_show));
                // zoom to la doua
                mMap.moveCamera(
                        CameraUpdateFactory.newLatLngBounds(PointDeRestauration.LADOUA_LATLNGBOUNDS,
                                0));
            }
        });


    }

    public void onMarkPositiveClick(String avis) {
        oldMark = noteRatingBar.getRating();

        if (userAvisView != null) {
            userAvisView.setVisibility(View.GONE);
        }
        Utilisateur user = PrefUtils.recupererUtilisateur(getApplicationContext());
        Avis newAvis = new Avis(noteRatingBar.getRating(), avis,
                user.getPrenom() + user.getNom(), "12/12/2015");
        userAvisView = addComment(avisLayout, inflater, newAvis, true);
        PrefUtils.saveAvisRestaurantFromPref(this, pointDeRestauration.getName(), newAvis);
    }

    public void onMarkNegativeClick() {
        noteRatingBar.setRating(oldMark);
    }
}
