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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hexabinome.saladetomateoignon.DetailRestaurantActivity;
import com.hexabinome.saladetomateoignon.PrefUtils;
import com.hexabinome.saladetomateoignon.R;
import com.hexabinome.saladetomateoignon.modele.PointDeRestauration;
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

    public static final String TAG ="FavorisFragment";


    private ListView preferenceListView;
    private TextView empty_favoris_textView;
    private AlphaInAnimationAdapter animationAdapter;
    private RestaurantAdapter restaurantAdapter;

    public FavorisFragment() {
        // Required empty public constructor
    }

    boolean isNewFavorisAdded = false;

    public void setIsNewFavorisAdded(boolean isNewFavorisAdded) {
        this.isNewFavorisAdded = isNewFavorisAdded;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View inflatedView = inflater.inflate(R.layout.fragment_favoris, container, false);
        empty_favoris_textView = (TextView) inflatedView.findViewById(R.id.empty_favoris_list);

        Utilisateur user = PrefUtils.recupererUtilisateur(getContext());

        List<PointDeRestauration> preferenceList = new ArrayList<>(user.getFavoris());

        if (!preferenceList.isEmpty())
        {
            empty_favoris_textView.setVisibility(View.GONE);
        }

        // Access the ListView

        preferenceListView = (ListView) inflatedView.findViewById(R.id.favoris_listview);


        // Create an ArrayAdapter for the ListView
        restaurantAdapter = new RestaurantAdapter(preferenceList, getContext());
        animationAdapter = new AlphaInAnimationAdapter(restaurantAdapter);
        animationAdapter.setAbsListView(preferenceListView);


        // Set the ListView to use the ArrayAdapter
        preferenceListView.setAdapter(animationAdapter);

        preferenceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DetailRestaurantActivity.class);
                PointDeRestauration pointDeRestauration = restaurantAdapter.getItem(position);
                String restaurantJson = new Gson().toJson(pointDeRestauration);
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
        if(isVisibleToUser && isNewFavorisAdded){

            Utilisateur user = PrefUtils.recupererUtilisateur(getContext());

            List<PointDeRestauration> preferenceList = new ArrayList<>(user.getFavoris());
            preferenceList = sortRestaurantList(preferenceList,user);
            if (!preferenceList.isEmpty())
            {
                empty_favoris_textView.setVisibility(View.GONE);
            }

            restaurantAdapter.setPointDeRestaurationList(preferenceList);

            animationAdapter.reset();
            animationAdapter.notifyDataSetChanged();
            isNewFavorisAdded = false;
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
        void onFavorisFragmentInteraction(Uri uri);
    }


    private static List<PointDeRestauration> sortRestaurantList(List<PointDeRestauration> pointDeRestaurations, Utilisateur user) {

        // sort the restaurants Note > price > distance
        List<PointDeRestauration> sortedList = new ArrayList<PointDeRestauration>();


        PointDeRestauration prev = null;
        PointDeRestauration cur = null;

        while (!pointDeRestaurations.isEmpty()) {

            prev = pointDeRestaurations.get(0);

            for (int i = 0; i < pointDeRestaurations.size(); i++) {
                cur = pointDeRestaurations.get(i);
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
            pointDeRestaurations.remove(prev);
        }
        return sortedList;
    }
}
