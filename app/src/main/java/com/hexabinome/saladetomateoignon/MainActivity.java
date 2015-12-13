package com.hexabinome.saladetomateoignon;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hexabinome.saladetomateoignon.fragment.cantinder.CantinderFragment;
import com.hexabinome.saladetomateoignon.fragment.CustomFragmentPagerAdapter;
import com.hexabinome.saladetomateoignon.fragment.favoris.FavorisFragment;
import com.hexabinome.saladetomateoignon.fragment.preferences.PreferencesFragment;



public class MainActivity extends AppCompatActivity implements CantinderFragment.OnCantinderFragmentInteractionListener, FavorisFragment.OnFavorisFragmentInteractionListener,PreferencesFragment.OnPreferencesFragmentInteractionListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    public final static String POSITION = "POSITION";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         // Get the ViewPager and set it's PagerAdapter so that it can display items
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        CustomFragmentPagerAdapter customFragmentPagerAdapter = new CustomFragmentPagerAdapter(getSupportFragmentManager(), MainActivity.this);
        viewPager.setAdapter(customFragmentPagerAdapter);

        // Give the TabLayout the ViewPager
         tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        // Iterate over all tabs and set the custom view
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(customFragmentPagerAdapter.getTabView(i));
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(POSITION,tabLayout.getSelectedTabPosition());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        viewPager.setCurrentItem(savedInstanceState.getInt(POSITION));
    }

    @Override
    public void onFavorisFragmentInteraction(Uri uri) {

    }

    @Override
    public void onCantinderFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPreferencesFragmentInteraction(Uri uri) {

    }
}
