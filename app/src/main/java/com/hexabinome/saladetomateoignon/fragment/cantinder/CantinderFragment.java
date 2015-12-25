package com.hexabinome.saladetomateoignon.fragment.cantinder;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hexabinome.saladetomateoignon.PrefUtils;
import com.hexabinome.saladetomateoignon.R;
import com.hexabinome.saladetomateoignon.modele.Mock;
import com.hexabinome.saladetomateoignon.modele.Preferences;
import com.hexabinome.saladetomateoignon.modele.Restaurant;
import com.hexabinome.saladetomateoignon.modele.Utilisateur;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnCantinderFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CantinderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CantinderFragment extends Fragment implements View.OnClickListener {

    private OnCantinderFragmentInteractionListener mListener;

    private Utilisateur currentUser;

    private Restaurant currentRestaurant;
    private Restaurant previousRestaurant;
    private List<Restaurant> refused = new ArrayList<Restaurant>(); // TODO reinit when come back to this fragment

    private TextView restaurantTitle;
    private TextView restaurantPrice;
    private TextView restaurantDistance;
    private TextView restaurantTempsAttente;
    private RatingBar rateBar;

    private ImageButton declineButton;
    private ImageButton acceptButton;

    private LinearLayout cantinder_layout;
    private LinearLayout cantinder_like_dislike_layout;


    public CantinderFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static CantinderFragment newInstance(String param1, String param2) {
        CantinderFragment fragment = new CantinderFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_cantinder, container, false);
        declineButton = (ImageButton) inflatedView.findViewById(R.id.reject);
        acceptButton = (ImageButton) inflatedView.findViewById(R.id.accept);

        cantinder_layout = (LinearLayout) inflatedView.findViewById(R.id.cantinder_board);
        cantinder_like_dislike_layout = (LinearLayout) inflatedView.findViewById(R.id.cantinder_like_dislike_layout);

        declineButton.setOnClickListener(this);
        acceptButton.setOnClickListener(this);


        restaurantTitle = (TextView) inflatedView.findViewById(R.id.restaurantTitle);
        restaurantTempsAttente = (TextView) inflatedView.findViewById(R.id.restaurantTempsAttente);
        restaurantDistance = (TextView) inflatedView.findViewById(R.id.restaurantDistance);
        restaurantPrice = (TextView) inflatedView.findViewById(R.id.restaurantPrice);
//        restaurantGrade = (TextView) inflatedView.findViewById(R.id.restaurantGrade);
        rateBar = (RatingBar) inflatedView.findViewById(R.id.restaurantGrade);

        currentRestaurant = getNextRestaurant();
        displayRestaurant();

        return inflatedView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCantinderFragmentInteractionListener) {
            mListener = (OnCantinderFragmentInteractionListener) context;
            currentUser = PrefUtils.recupererUtilisateur(getActivity());
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
    public void onClick(View v) {
        if (v.getId() == acceptButton.getId()){
            acceptRestaurant();
        } else if (v.getId() == declineButton.getId()){
            declineRestaurant();
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
    public interface OnCantinderFragmentInteractionListener {
        // TODO: Update argument type and name
        void onCantinderFragmentInteraction(Uri uri);
    }

    /**
     * Refuse le restaurant actuellement affiché
     * Passe au restaurant suivant
     */
    public void declineRestaurant() {
        if (currentRestaurant != null) {
            refused.add(currentRestaurant);
        }


        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this.getContext(), R.anim.dislike);
//        getView().startAnimation(hyperspaceJumpAnimation);
        cantinder_layout.startAnimation(hyperspaceJumpAnimation);


        currentRestaurant = getNextRestaurant();
        displayRestaurant();

    }

    /**
     * Accepte le restaurant actuellement affiché.
     * Ajoute le restaurant actuel, affiche le bouton détail du restaurant, et passe au restaurant
     * suivant
     */
    public void acceptRestaurant() {

        if (currentRestaurant != null) {
            // Add current restaurant to favorties
            currentUser.addToFavorites(currentRestaurant);
        }
        // Next restaurant


        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this.getContext(), R.anim.like);
        //getView().startAnimation(hyperspaceJumpAnimation);
        cantinder_layout.startAnimation(hyperspaceJumpAnimation);
        currentRestaurant = getNextRestaurant();
        displayRestaurant();
    }


    /**
     * Gets the next restaurant, corresponding to the preferences and not already in the favorites
     * @return The next restaurant that might match. Null if no restaurant left (all in favorites)
     */
    private Restaurant getNextRestaurant() {
        previousRestaurant = currentRestaurant;
        for (Restaurant r : getMostMatchingRestaurants()) {
            if (!isFavorite(r) && !isRefused(r)) {
                return r;
            }
        }

        Toast.makeText(getContext(), "No restaurant found", Toast.LENGTH_SHORT).show();
        return null;
        //throw new RuntimeException("All restaurants are favorites or no restaurant found");
    }

    private SortedSet<Restaurant> getMostMatchingRestaurants() {
        SortedSet<Restaurant> mostMatchingRestaurants = new TreeSet<>(new Comparator<Restaurant>() {
            private Preferences pref;

            /**
             * Tends to negative if good (because the biggest score is the geatest and will be at the end, we want the best to be at the beginning)
             * @param r
             * @return
             */
            private double getScore(Restaurant r) {
                double score = 0;
                if (r.getDistance(currentUser.getLongitude(),currentUser.getLatitude()) > pref.getDistance()) {
                    score--;
                }
                else {
                    score++;
                }

                if (r.getNote() > pref.getNote()) {
                    score--;
                }
                else {
                    score++;
                }

                if (r.getPrix() > pref.getPrix()) {
                    score--;
                }
                else {
                    score++;
                }

                if (r.getTempsAttenteMoy() > pref.getTempsDattente()) {
                    score--;
                }
                else {
                    score++;
                }
                return score;
            }

            @Override
            public int compare(Restaurant lhs, Restaurant rhs) {
                pref = currentUser.getPreferences();
                double scoreLeft = getScore(lhs);
                double scoreRight = getScore(rhs);
                return (int) (scoreRight - scoreLeft);
            }
        });

        mostMatchingRestaurants.addAll(Mock.getRestaurantLaDoua());

        return mostMatchingRestaurants;
    }

    private boolean isFavorite(Restaurant r) {
        return currentUser.isFavorite(r);
    }

    private boolean isRefused(Restaurant r) {
        return refused.contains(r);
    }

    /**
     * Fills restaurant displays
     */
    private void displayRestaurant() {
        String name = "", distance = "", price = "", tempsAtt = "";
        int grade = 0;
        if (restaurantTitle.isCursorVisible() && currentRestaurant != null) {
            name = currentRestaurant.getName();
            distance = String.valueOf((int) currentRestaurant.getDistance(currentUser.getLongitude(), currentUser.getLatitude()));
            price = String.valueOf(currentRestaurant.getPrix());
            tempsAtt = String.valueOf(currentRestaurant.getTempsAttenteMoy());
            grade = (int)currentRestaurant.getNote();
        }else{
            cantinder_layout.setVisibility(View.GONE);
            cantinder_like_dislike_layout.setVisibility(View.GONE);
        }

        restaurantTitle.setText(name);
        restaurantDistance.setText(distance + " mètres");
        restaurantPrice.setText(price + "€");
        restaurantTempsAttente.setText(tempsAtt + " minutes");
        rateBar.setNumStars(grade);
    }
}
