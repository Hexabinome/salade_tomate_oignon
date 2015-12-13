package com.hexabinome.saladetomateoignon;

import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hexabinome.saladetomateoignon.fragment.cantinder.CantinderFragment;
import com.hexabinome.saladetomateoignon.fragment.CustomFragmentPagerAdapter;
import com.hexabinome.saladetomateoignon.fragment.favoris.FavorisFragment;
import com.hexabinome.saladetomateoignon.fragment.preferences.PreferencesFragment;


public class MainActivity extends AppCompatActivity implements
        CantinderFragment.OnCantinderFragmentInteractionListener,
        FavorisFragment.OnFavorisFragmentInteractionListener,
        PreferencesFragment.OnPreferencesFragmentInteractionListener, TabLayout.OnTabSelectedListener {

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
        tabLayout.setOnTabSelectedListener(this);

        // Iterate over all tabs and set the custom view
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null)
                tab.setCustomView(customFragmentPagerAdapter.getTabView(i));
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(POSITION, tabLayout.getSelectedTabPosition());
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

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        View view = tab.getCustomView();
        if(view != null){
            TextView textView = (TextView) view.findViewById(R.id.tabName);
            textView.setTextColor(ContextCompat.getColor(this,R.color.colorPrimary));

            ImageView imageView = (ImageView) view.findViewById(R.id.tab_image);
            imageView.setColorFilter(ContextCompat.getColor(this,R.color.colorPrimary));

        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        View view = tab.getCustomView();
        if(view != null){
            TextView textView = (TextView) view.findViewById(R.id.tabName);
            textView.setTextColor(Color.BLACK);

            ImageView imageView = (ImageView) view.findViewById(R.id.tab_image);
            imageView.clearColorFilter();

        }
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
