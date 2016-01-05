package com.hexabinome.saladetomateoignon;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.h6ah4i.android.tablayouthelper.TabLayoutHelper;


public class CustomTabLayoutHelper extends TabLayoutHelper {

    /**
     * Constructor.
     *
     * @param tabLayout TabLayout instance
     * @param viewPager ViewPager instance
     */
    public CustomTabLayoutHelper(TabLayout tabLayout,
                                 ViewPager viewPager) {
        super(tabLayout, viewPager);
    }


    @Override
    protected void updateTab(TabLayout.Tab tab) {
        CustomFragmentPagerAdapter customFragmentPagerAdapter = (CustomFragmentPagerAdapter) mViewPager
                .getAdapter();
        View view = customFragmentPagerAdapter.getTabView(tab.getPosition());
        tab.setCustomView(view);
    }
}
