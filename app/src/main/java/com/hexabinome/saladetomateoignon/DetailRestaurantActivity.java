package com.hexabinome.saladetomateoignon;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class DetailRestaurantActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;

    LinearLayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_restaurant);

        Toolbar toolbar =   (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("test");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ViewCompat.setTransitionName(findViewById(R.id.appBarLayout), "Name");

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Titre");
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));



        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
