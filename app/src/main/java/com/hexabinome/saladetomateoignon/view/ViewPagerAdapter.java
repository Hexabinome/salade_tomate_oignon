package com.hexabinome.saladetomateoignon.view;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.hexabinome.saladetomateoignon.fragment.CantinderFragment;
import com.hexabinome.saladetomateoignon.fragment.FavorisFragment;
import com.hexabinome.saladetomateoignon.fragment.PreferencesFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private CharSequence titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    private int numOfTables; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created

    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm, CharSequence titles[],int numOfTables) {
        super(fm);
        this.titles = titles;
        this.numOfTables =numOfTables;
    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new FavorisFragment();
        } else if(position == 1 )
        {
            return new CantinderFragment();
        }
        else
        {
            return new PreferencesFragment();
        }
    }

    // This method return the Number of tabs for the tabs Strip
    @Override
    public int getCount() {
        return numOfTables;
    }

    // This method return the titles for the Tabs in the Tab Strip
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}

