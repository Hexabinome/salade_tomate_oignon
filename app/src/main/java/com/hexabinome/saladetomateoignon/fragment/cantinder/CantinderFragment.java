package com.hexabinome.saladetomateoignon.fragment.cantinder;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.hexabinome.saladetomateoignon.R;
import com.hexabinome.saladetomateoignon.modele.Mock;
import com.hexabinome.saladetomateoignon.modele.Restaurant;

import org.w3c.dom.Text;

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

    private TextView restaurantTitle;
    private TextView restaurantPrice;
    private TextView restaurantDistance;
    private TextView restaurantTempsAttente;
    private TextView restaurantGrade;

    private ImageButton declineButton;
    private ImageButton acceptButton;

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

        declineButton.setOnClickListener(this);
        acceptButton.setOnClickListener(this);

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
        if(v.getId() == acceptButton.getId()){
            acceptRestaurant();
        } else if(v.getId() == declineButton.getId()){
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
        Toast.makeText(getContext(), "Decline", Toast.LENGTH_SHORT).show();
    }

    /**
     * Accepte le restaurant actuellement affiché.
     * Ajoute le restaurant actuel, affiche le bouton détail du restaurant, et passe au restaurant
     * suivant
     */
    public void acceptRestaurant() {
        Toast.makeText(getContext(), "Accepter", Toast.LENGTH_SHORT).show();
    }

    /**
     * Gets the next restaurant, corresponding to the preferences and not already in the favorites
     * @return The next restaurant that might match
     */
    private Restaurant getNextRestaurant() {
        return Mock.getRestaurantLaDoua().get(0);
    }

    private void displayRestaurant() {
        if (restaurantTitle.isCursorVisible()) {
            restaurantTitle.setText(currentRestaurant.getName());
            restaurantDistance.setText(currentRestaurant.getDistance().toString());
            restaurantPrice.setText(currentRestaurant.getPrice().toString());
            restaurantTempsAttente.setText(currentRestaurant.getTempsAttenteMoy().toString());
            restaurantGrade.setText(currentRestaurant.getGrade().toString());
        }
    }
}
