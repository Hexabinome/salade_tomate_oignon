package com.hexabinome.saladetomateoignon;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.hexabinome.saladetomateoignon.view.SlidingTabLayout;
import com.hexabinome.saladetomateoignon.view.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CharSequence tabTitle[] = new CharSequence[3];
        tabTitle[0] = "Favoris";
        tabTitle[1] = "Cantinder";
        tabTitle[2] = "Preferences";

        ViewPagerAdapter coucou = new ViewPagerAdapter(getSupportFragmentManager(),tabTitle,3);

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(coucou);

        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) findViewById(R.id.tabs);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);

        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return Color.WHITE;
            }
        });
        slidingTabLayout.setViewPager(viewPager);
    }
}
