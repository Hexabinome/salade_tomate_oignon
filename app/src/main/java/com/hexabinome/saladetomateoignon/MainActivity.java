package com.hexabinome.saladetomateoignon;

import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.hexabinome.saladetomateoignon.fragment.CantinderFragment;
import com.hexabinome.saladetomateoignon.fragment.CustomFragmentPagerAdapter;
import com.hexabinome.saladetomateoignon.fragment.FavorisFragment;
import com.hexabinome.saladetomateoignon.fragment.PreferencesFragment;



public class MainActivity extends AppCompatActivity implements CantinderFragment.OnFragmentInteractionListener, FavorisFragment.OnFragmentInteractionListener,PreferencesFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        viewPager.setAdapter(new CustomFragmentPagerAdapter(getSupportFragmentManager(),
                MainActivity.this));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
