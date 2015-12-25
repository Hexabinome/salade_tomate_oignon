package com.hexabinome.saladetomateoignon.fragment.favoris;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.hexabinome.saladetomateoignon.DetailRestaurantActivity;
import com.hexabinome.saladetomateoignon.PrefUtils;
import com.hexabinome.saladetomateoignon.modele.Mock;
import com.hexabinome.saladetomateoignon.R;
import com.hexabinome.saladetomateoignon.modele.Restaurant;
import com.hexabinome.saladetomateoignon.modele.Utilisateur;
import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFavorisFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FavorisFragment extends Fragment {

    private OnFavorisFragmentInteractionListener mListener;


    private ListView preferenceListView;
    private AlphaInAnimationAdapter animationAdapter;

    public FavorisFragment() {
        // Required empty public constructor
    }

    boolean isPreferencesChanged = false;

    public void setIsPreferencesChanged(boolean isPreferencesChanged) {
        this.isPreferencesChanged = isPreferencesChanged;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //TODO : change the layout of the list

        View inflatedView = inflater.inflate(R.layout.fragment_favoris, container, false);


        Utilisateur user = PrefUtils.recupererUtilisateur(getContext());

        List<Restaurant> preferenceList = sortRestaurantList(Mock.getRestaurantLaDoua(), user);

        // Access the ListView

        preferenceListView = (ListView) inflatedView.findViewById(R.id.favoris_listview);


        // Create an ArrayAdapter for the ListView
        final RestaurantAdapter mArrayAdapter;
        mArrayAdapter = new RestaurantAdapter(preferenceList, getContext());
        animationAdapter = new AlphaInAnimationAdapter(mArrayAdapter);
        animationAdapter.setAbsListView(preferenceListView);


        // Set the ListView to use the ArrayAdapter
        preferenceListView.setAdapter(animationAdapter);

        preferenceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DetailRestaurantActivity.class);
                Restaurant restaurant = mArrayAdapter.getItem(position);
                String restaurantJson = new Gson().toJson(restaurant);
                intent.putExtra(DetailRestaurantActivity.RESTAURANT_EXTRA, restaurantJson);
                startActivity(intent);
            }
        });


        return inflatedView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFavorisFragmentInteractionListener) {
            mListener = (OnFavorisFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFavorisFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser && isPreferencesChanged){
            animationAdapter.reset();
            animationAdapter.notifyDataSetChanged();
            isPreferencesChanged = false;
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFavorisFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFavorisFragmentInteraction(Uri uri);
    }


    private List<Restaurant> sortRestaurantList(List<Restaurant> restaurants, Utilisateur user) {

        // sort the restaurants Note > price > distance
        List<Restaurant> sortedList = new ArrayList<Restaurant>();


        Restaurant prev = null;
        Restaurant cur = null;

        while (!restaurants.isEmpty()) {

            prev = restaurants.get(0);

            for (int i = 0; i < restaurants.size(); i++) {
                cur = restaurants.get(i);
                if (cur.getNote() > prev.getNote()) {
                    prev = cur;
                } else if (cur.getNote() == prev.getNote()) {

                    if (cur.getPrix() < prev.getPrix()) {
                        prev = cur;
                    } else if (cur.getPrix() == prev.getPrix()) {
                        if (cur.getDistance(user.getLongitude(), user.getLatitude()) < prev.getDistance(user.getLongitude(), user.getLatitude())) {
                            prev = cur;
                        }
                    }
                }
            }

            sortedList.add(prev);
            restaurants.remove(prev);
        }
        return sortedList;
    }
}
