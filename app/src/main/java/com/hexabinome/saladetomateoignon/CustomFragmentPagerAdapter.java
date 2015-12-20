package com.hexabinome.saladetomateoignon;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hexabinome.saladetomateoignon.R;
import com.hexabinome.saladetomateoignon.fragment.cantinder.CantinderFragment;
import com.hexabinome.saladetomateoignon.fragment.cantinder.SwipeTestFragment;
import com.hexabinome.saladetomateoignon.fragment.favoris.FavorisFragment;
import com.hexabinome.saladetomateoignon.fragment.preferences.PreferencesFragment;

public class CustomFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "Favoris", "Cantinder", "Préférences" };
    private int imagesId[] = {R.drawable.ic_star2,R.drawable.ic_eye,R.drawable.ic_preferences};

    private Context context;

    public CustomFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new FavorisFragment();
        } else if(position == 1 )
        {
            return new SwipeTestFragment();
        }
        else
        {
            return new PreferencesFragment();
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }


    public View getTabView(int position){
        View v = LayoutInflater.from(context).inflate(R.layout.custom_tab, null);
        TextView tv = (TextView) v.findViewById(R.id.tabName);
        tv.setText(tabTitles[position]);
        ImageView img = (ImageView) v.findViewById(R.id.tab_image);
        img.setImageResource(imagesId[position]);
        return v;
    }
}
