package com.hexabinome.saladetomateoignon.fragment.preferences;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Spinner;

import com.hexabinome.saladetomateoignon.LoginActivity;
import com.hexabinome.saladetomateoignon.PrefUtils;
import com.hexabinome.saladetomateoignon.R;
import com.hexabinome.saladetomateoignon.modele.Preferences;
import com.hexabinome.saladetomateoignon.modele.Restaurant;
import com.hexabinome.saladetomateoignon.modele.Utilisateur;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnPreferencesFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class PreferencesFragment extends Fragment implements View.OnClickListener {



    private OnPreferencesFragmentInteractionListener mListener;

    private Utilisateur user;

    private Button btnDisconnect;
    private Restaurant.TypeRegime regimeSelectionne;
    private Spinner regimeSpinner;

    DiscreteSeekBar distanceSeekBar, attenteSeekBar,prixSeekBar;
    RatingBar noteRatingBar;



    public PreferencesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflatedView = inflater.inflate(R.layout.fragment_preferences, container, false);
        btnDisconnect = (Button) inflatedView.findViewById(R.id.disconnect);
        btnDisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                PrefUtils.resetPrefs(getActivity());
                startActivity(intent);
            }
        });
        //load user
        user = PrefUtils.recupererUtilisateur(getActivity());
        Preferences preferences;
        if (user != null) {
            preferences = user.getPreferences();
        } else {
            preferences = Preferences.getDefaultPreferences();
        }
        distanceSeekBar = (DiscreteSeekBar) inflatedView.findViewById(R.id.prefDistanceRestaurant);
        attenteSeekBar = (DiscreteSeekBar) inflatedView.findViewById(R.id.prefTempsAttente);
        prixSeekBar = (DiscreteSeekBar) inflatedView.findViewById(R.id.prefPrixRestaurant);
        noteRatingBar = (RatingBar) inflatedView.findViewById(R.id.prefNote);

        regimeSpinner = (Spinner) inflatedView.findViewById(R.id.regime);
        configureSpinner();

        distanceSeekBar.setProgress(preferences.getDistance());
        attenteSeekBar.setProgress(preferences.getTempsDattente());
        prixSeekBar.setProgress(preferences.getPrix());
        noteRatingBar.setRating((float) preferences.getNote());

        Restaurant.TypeRegime prefRegime = preferences.getTypeRegime();
        regimeSpinner.setSelection(prefRegime.ordinal());
        return inflatedView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPreferencesFragmentInteractionListener) {
            mListener = (OnPreferencesFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnPreferencesFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {

    }

    private void configureSpinner(){

        ArrayAdapter<Restaurant.TypeRegime> regimeArrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item);
        regimeArrayAdapter.addAll(Restaurant.TypeRegime.values());
        regimeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        regimeSpinner.setAdapter(regimeArrayAdapter);

        regimeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                regimeSelectionne = (Restaurant.TypeRegime) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(!isVisibleToUser){
            // TODO : save preferences
            if(mListener != null)
                mListener.onPreferencesFragmentInteraction();
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
    public interface OnPreferencesFragmentInteractionListener {
        void onPreferencesFragmentInteraction();
    }
}
