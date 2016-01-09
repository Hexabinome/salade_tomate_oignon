package com.hexabinome.saladetomateoignon;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.h6ah4i.android.tablayouthelper.TabLayoutHelper;
import com.hexabinome.saladetomateoignon.fragment.cantinder.CantinderFragment;
import com.hexabinome.saladetomateoignon.fragment.favoris.FavorisFragment;
import com.hexabinome.saladetomateoignon.fragment.preferences.PreferencesFragment;
import com.hexabinome.saladetomateoignon.modele.Utilisateur;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;


public class MainActivity extends AppCompatActivity implements
        CantinderFragment.OnCantinderFragmentInteractionListener,
        FavorisFragment.OnFavorisFragmentInteractionListener,
        PreferencesFragment.OnPreferencesFragmentInteractionListener,
        TabLayout.OnTabSelectedListener {

    private Utilisateur currentUser;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public final static String POSITION = "POSITION";
    private Toolbar toolbar;
    CustomFragmentPagerAdapter customFragmentPagerAdapter;
    private boolean isDisconnectButtonClicked = false;
    private static final String SHOWCASE_ID = "help";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        //get back the user
        currentUser = PrefUtils.recupererUtilisateur(this);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        customFragmentPagerAdapter = new CustomFragmentPagerAdapter(getSupportFragmentManager(), this);

        customFragmentPagerAdapter.addFragment(new FavorisFragment(), "Favoris", R.drawable.ic_star2);
        customFragmentPagerAdapter.addFragment(new CantinderFragment(), "Cantinder", R.drawable.ic_eye);
        customFragmentPagerAdapter.addFragment(new PreferencesFragment(), "Préférences", R.drawable.ic_preferences);

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

        CustomTabLayoutHelper tabLayoutHelper = new CustomTabLayoutHelper(tabLayout,viewPager);
        tabLayoutHelper.setAutoAdjustTabModeEnabled(true);
        tabLayoutHelper.setOnTabSelectedListener(this);

        // set the tab showed at launch
        if (PrefUtils.getBooleanFromPrefs(this,PrefUtils.PREFS_FIRST_LAUNCH,true)) {
            PrefUtils.saveBooleanToPrefs(this,PrefUtils.PREFS_FIRST_LAUNCH,false);
            viewPager.setCurrentItem(2);
        }else {
            viewPager.setCurrentItem(1);
        }
        showCasePresentation();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_a_propos:
                Toast.makeText(this, "TODO : afficher un dialogue avec quelques info", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onFavorisFragmentInteraction(Uri uri) {

    }

    @Override
    public void onCantinderFragmentInteraction(int tabNumber) {
        if (viewPager != null)
            viewPager.setCurrentItem(tabNumber, true);

    }

    @Override
    public void onNewFavorisAdded() {
        FavorisFragment favorisFragment = (FavorisFragment) customFragmentPagerAdapter.getItem(0);
        favorisFragment.setIsNewFavorisAdded(true);

    }

    @Override
    public void onPreferencesChanged() {
        CantinderFragment cantinderFragment = (CantinderFragment) customFragmentPagerAdapter.getItem(1);
        cantinderFragment.setIsPreferencesChanged(true);
    }

    @Override
    public void onPreferencesDisconnectButtonClicked() {
        PrefUtils.resetPrefs(this);
        Intent intent = new Intent(this, LoginActivity.class);
        finish();
        startActivity(intent);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        View view = tab.getCustomView();
        if (view != null) {
            TextView textView = (TextView) view.findViewById(R.id.tabName);
            textView.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));

            ImageView imageView = (ImageView) view.findViewById(R.id.tab_image);
            imageView.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary));

            viewPager.setCurrentItem(tab.getPosition());
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        View view = tab.getCustomView();
        if (view != null) {
            TextView textView = (TextView) view.findViewById(R.id.tabName);
            textView.setTextColor(Color.BLACK);

            ImageView imageView = (ImageView) view.findViewById(R.id.tab_image);
            imageView.clearColorFilter();

        }
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 1) {
            super.onBackPressed();
        } else {
            viewPager.setCurrentItem(1, true);
        }
    }

    private void showCasePresentation(){
        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(500); // half second between each showcase view

        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this, SHOWCASE_ID);

        sequence.setConfig(config);

        sequence.addSequenceItem(tabLayout.getTabAt(2).getCustomView(),
                "Ici vous pourrez modifiez vos criteres minimum de choix des points de " +
                        "restauration", "J'ai compris");

        sequence.addSequenceItem(tabLayout.getTabAt(1).getCustomView(),
                "Retrouvez ici les points de restaurations correspondant a vos preferences, Vous" +
                        " pourrez les liker ou les disliker en fonction de vos goûts", "J'ai compris");

        sequence.addSequenceItem(tabLayout.getTabAt(0).getCustomView(),
                "Tous vos points des restaurations favoris se retrouveront ici," +
                        " libre a vous de les noter, de consulter leurs localisations, descriptions" +
                        " ou bien de les supprimer de vos favoris", "J'ai compris");

        sequence.start();
    }

}
