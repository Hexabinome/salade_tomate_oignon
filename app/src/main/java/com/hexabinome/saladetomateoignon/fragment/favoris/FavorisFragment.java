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

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFavorisFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FavorisFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavorisFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFavorisFragmentInteractionListener mListener;


    public FavorisFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavorisFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavorisFragment newInstance(String param1, String param2) {
        FavorisFragment fragment = new FavorisFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //TODO : change the layout of the list
        
        View inflatedView = inflater.inflate(R.layout.fragment_favoris, container, false);

        ListView preferenceListView;
        final RestaurantAdapter mArrayAdapter;


        Utilisateur user = PrefUtils.recupererUtilisateur(getContext());

        ArrayList preferenceList;
        preferenceList = sortRestaurantList(Mock.getRestaurantLaDoua(), user);


        // Access the ListView
        preferenceListView = (ListView) inflatedView.findViewById(R.id.favoris_listview);



        // Create an ArrayAdapter for the ListView
        mArrayAdapter = new RestaurantAdapter(preferenceList,getContext());


        // Set the ListView to use the ArrayAdapter
        preferenceListView.setAdapter(mArrayAdapter);


        // deleting previous view
        ((ViewGroup) preferenceListView.getParent()).removeView(preferenceListView);

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


        // Inflate the layout for this fragment
        return preferenceListView;
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


    private ArrayList <Restaurant> sortRestaurantList(ArrayList<Restaurant> restaurants, Utilisateur user){

        // sort the restaurants Note > price > distance
        ArrayList<Restaurant> sortedList = new ArrayList<Restaurant>();


        Restaurant prev = null;
        Restaurant cur = null;

        while(!restaurants.isEmpty()) {

            prev = restaurants.get(0);

            for (int i = 0; i < restaurants.size(); i++) {
                cur = restaurants.get(i);
                if (cur.getNote() > prev.getNote()) {
                    prev = cur;
                }
                else if (cur.getNote() == prev.getNote()){

                    if(cur.getPrix() < prev.getPrix()) {
                        prev = cur;
                    }
                    else if (cur.getPrix() == prev.getPrix()){
                        if(cur.getDistance(user.getLongitude(),user.getLatitude()) < prev.getDistance(user.getLongitude(),user.getLatitude())) {
                            prev = cur;
                        }
                    }
                }
            }
            Log.d("trace","" +prev.getNote());
            Log.d("trace","" +prev.getPrix());
            Log.d("trace","" +prev.getDistance(user.getLongitude(),user.getLatitude()));


            sortedList.add(prev);
            restaurants.remove(prev);
        }
        return  sortedList;
    }
}
