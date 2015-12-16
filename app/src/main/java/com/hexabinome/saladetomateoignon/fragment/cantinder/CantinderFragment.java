package com.hexabinome.saladetomateoignon.fragment.cantinder;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.hexabinome.saladetomateoignon.R;
import com.hexabinome.saladetomateoignon.modele.Mock;
import com.hexabinome.saladetomateoignon.modele.Restaurant;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    private Restaurant currentRestaurant;
    private Restaurant previousRestaurant;
    private List<Restaurant> refused = new ArrayList<Restaurant>(); // TODO reinit when come back to this fragment

    private TextView restaurantTitle;
    private TextView restaurantPrice;
    private TextView restaurantDistance;
    private TextView restaurantTempsAttente;
    private TextView restaurantGrade;

    private ImageButton declineButton;
    private ImageButton acceptButton;
    private Button detailButton;


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
        declineButton = (ImageButton) inflatedView.findViewById(R.id.declineButton);
        acceptButton = (ImageButton) inflatedView.findViewById(R.id.acceptButton);
        detailButton = (Button) inflatedView.findViewById(R.id.detailRestaurant);

        declineButton.setOnClickListener(this);
        acceptButton.setOnClickListener(this);
        detailButton.setOnClickListener(this);

        restaurantTitle = (TextView) inflatedView.findViewById(R.id.restaurantTitle);
        restaurantTempsAttente = (TextView) inflatedView.findViewById(R.id.restaurantTempsAttente);
        restaurantDistance = (TextView) inflatedView.findViewById(R.id.restaurantDistance);
        restaurantPrice = (TextView) inflatedView.findViewById(R.id.restaurantPrice);
        restaurantGrade = (TextView) inflatedView.findViewById(R.id.restaurantGrade);

        currentRestaurant = getNextRestaurant();
        displayRestaurant();

        return inflatedView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCantinderFragmentInteractionListener) {
            mListener = (OnCantinderFragmentInteractionListener) context;
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
        } else if (v.getId() == detailButton.getId()) {
            detailRestaurant();
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
        detailButton.setVisibility(View.INVISIBLE);

        if (currentRestaurant != null) {
            refused.add(currentRestaurant);
        }

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
            favorites.add(currentRestaurant);
            // Display button to go to current restaurant details
            detailButton.setVisibility(View.VISIBLE);
        }

        // Next restaurant
        currentRestaurant = getNextRestaurant();
        displayRestaurant();
    }

    public void detailRestaurant() {
        Toast.makeText(getContext(), "Go to detail of " + previousRestaurant.getName(), Toast.LENGTH_SHORT).show();
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

    private List<Restaurant> getMostMatchingRestaurants() {
        // TODO add logic with preferences
        return Mock.getRestaurantLaDoua();
    }

    private List<Restaurant> favorites = new ArrayList<Restaurant>();
    private boolean isFavorite(Restaurant r) {
        // TODO use user favorite list
        return favorites.contains(r);
    }

    private boolean isRefused(Restaurant r) {
        return refused.contains(r);
    }

    /**
     * Fills restaurant displays
     */
    private void displayRestaurant() {
        String name = "", distance = "", price = "", tempsAtt = "", grade = "";
        if (restaurantTitle.isCursorVisible() && currentRestaurant != null) {
            name = currentRestaurant.getName();
            distance = currentRestaurant.getDistance().toString();
            price = currentRestaurant.getPrice().toString();
            tempsAtt = currentRestaurant.getTempsAttenteMoy().toString();
            grade = currentRestaurant.getGrade().toString();
        }

        restaurantTitle.setText(name);
        restaurantDistance.setText(distance);
        restaurantPrice.setText(price);
        restaurantTempsAttente.setText(tempsAtt);
        restaurantGrade.setText(grade);
    }
}
