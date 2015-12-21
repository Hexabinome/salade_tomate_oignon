package com.hexabinome.saladetomateoignon;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Integer> fragmentsImageId = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();
    private List<String> mFragmentTitles = new ArrayList<>();

    private Context context;

    public CustomFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    public void addFragment(Fragment fragment, String titles, int imageId) {
        mFragments.add(fragment);
        mFragmentTitles.add(titles);
        fragmentsImageId.add(imageId);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }


    public View getTabView(int position) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_tab, null);
        TextView tv = (TextView) v.findViewById(R.id.tabName);
        tv.setText(mFragmentTitles.get(position));
        ImageView img = (ImageView) v.findViewById(R.id.tab_image);
        img.setImageResource(fragmentsImageId.get(position));
        return v;
    }
}
