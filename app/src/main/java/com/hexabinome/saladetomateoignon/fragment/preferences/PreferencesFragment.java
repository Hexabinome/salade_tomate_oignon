package com.hexabinome.saladetomateoignon.fragment.preferences;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.hexabinome.saladetomateoignon.PrefUtils;
import com.hexabinome.saladetomateoignon.R;
import com.hexabinome.saladetomateoignon.modele.PointDeRestauration;
import com.hexabinome.saladetomateoignon.modele.Preferences;
import com.hexabinome.saladetomateoignon.modele.Utilisateur;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import java.util.HashMap;
import java.util.Map;

import io.techery.properratingbar.ProperRatingBar;
import io.techery.properratingbar.RatingListener;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnPreferencesFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class PreferencesFragment extends Fragment implements View.OnClickListener {

    /**
     * Boolean to know if the Fragment has been attached to the Activity
     */
    boolean isReady = false;

    /**
     * Boolean to know if user clicked on the disconnect button
     */
    boolean disconnectClicked = false;

    private OnPreferencesFragmentInteractionListener mListener;

    private PointDeRestauration.TypeRegime regimeSelectionne;
    private Spinner regimeSpinner;

    private DiscreteSeekBar distanceSeekBar, attenteSeekBar, prixSeekBar;
    private ProperRatingBar noteRatingBar;

    private Preferences preferences, oldPreferences;
    private Utilisateur user;

    private TextView distanceTextView, tempsTextView, prixTextView, connecteEnTantqueTextView;


    /**
     * Relative layout for checkboxes
     */
    private LinearLayout checkBoxesLayout;

    Map<PointDeRestauration.TypePointDeRestauration, CheckBox> checkBoxMap = new HashMap<>();


    private static final String TAG = "PreferencesFragment";

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
        //load user
        user = PrefUtils.recupererUtilisateur(getActivity());

        if (user != null) {
            preferences = user.getPreferences();
        } else {
            preferences = Preferences.getDefaultPreferences();
        }

        oldPreferences = new Preferences(preferences);

        distanceSeekBar = (DiscreteSeekBar) inflatedView.findViewById(R.id.prefDistanceRestaurant);
        attenteSeekBar = (DiscreteSeekBar) inflatedView.findViewById(R.id.prefTempsAttente);
        prixSeekBar = (DiscreteSeekBar) inflatedView.findViewById(R.id.prefPrixRestaurant);
        noteRatingBar = (ProperRatingBar) inflatedView.findViewById(R.id.prefNote);
        distanceTextView = (TextView) inflatedView.findViewById(R.id.distanceChoisie);
        prixTextView = (TextView) inflatedView.findViewById(R.id.prixChoisi);
        tempsTextView = (TextView) inflatedView.findViewById(R.id.tempsChoisi);
        connecteEnTantqueTextView = (TextView) inflatedView.findViewById(R.id.connecte_en_tant_que);
        regimeSpinner = (Spinner) inflatedView.findViewById(R.id.regime);
        configureSpinner();

        checkBoxesLayout = (LinearLayout) inflatedView.findViewById(R.id.checkboxLayout);

        distanceSeekBar.setProgress(preferences.getDistance());
        distanceTextView.setText(
                String.format(getString(R.string.distance_restaurant_entier),
                        preferences.getDistance()));

        distanceSeekBar.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                distanceTextView.setText(
                        String.format(getString(R.string.distance_restaurant_entier),
                                value));
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });

        attenteSeekBar.setProgress(preferences.getTempsDattente());
        tempsTextView.setText(
                String.format(getString(R.string.temps_attente_2), preferences.getTempsDattente()));
        attenteSeekBar.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                tempsTextView.setText(
                        String.format(getString(R.string.temps_attente_2), value));
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });

        prixSeekBar.setProgress(preferences.getPrix());
        prixTextView.setText(
                String.format(getString(R.string.prix_restaurant_entier), preferences.getPrix()));
        prixSeekBar.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                prixTextView.setText(
                        String.format(getString(R.string.prix_restaurant_entier), value));
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });
        noteRatingBar.setRating((int) preferences.getNote());

        configureCheckBoxes();




        connecteEnTantqueTextView.setText(String.format(getString(R.string.connecte_en_tant_que),user.getTypeUtilisateur()));

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

        isReady = true;
    }


    private void configureCheckBoxes() {

        // un offset pour ne pas commencer l'id des checkbox inseré à 0. Bug sinon
        int offsetId = 1994;

        // checkboxes creation
        for (PointDeRestauration.TypePointDeRestauration typePointDeRestauration : PointDeRestauration.TypePointDeRestauration
                .values()) {
            CheckBox checkBox = new CheckBox(getContext());
            checkBox.setId(typePointDeRestauration.ordinal() + offsetId);
            checkBox.setText(typePointDeRestauration.toString());


            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            checkBox.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
            checkBoxesLayout.addView(checkBox, layoutParams);

            checkBox.setTag(typePointDeRestauration);
            checkBoxMap.put(typePointDeRestauration, checkBox);

        }

        // checkboxes enabling
        for (PointDeRestauration.TypePointDeRestauration typePointDeRestauration : preferences.getTypePointDeRestaurations()) {
            checkBoxMap.get(typePointDeRestauration).setChecked(true);
        }


    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        isReady = false;
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * Configure spinner interaction
     */
    private void configureSpinner() {

        ArrayAdapter<PointDeRestauration.TypeRegime> regimeArrayAdapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_spinner_item);
        regimeArrayAdapter.addAll(PointDeRestauration.TypeRegime.values());
        regimeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        regimeSpinner.setAdapter(regimeArrayAdapter);

        regimeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                regimeSelectionne = (PointDeRestauration.TypeRegime) parent.getItemAtPosition(
                        position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        regimeSpinner.setSelection(preferences.getTypeRegime().ordinal());
    }

    private void updatePreferences() {
        preferences.setDistance(distanceSeekBar.getProgress());
        preferences.setTempsDattente(attenteSeekBar.getProgress());
        preferences.setNote(noteRatingBar.getRating());
        preferences.setPrix(prixSeekBar.getProgress());
        preferences.setTypeRegime(regimeSelectionne);

        preferences.getTypePointDeRestaurations().clear();

        for (CheckBox checkBox : checkBoxMap.values()) {
            if (checkBox.isChecked()) {
                preferences.getTypePointDeRestaurations()
                        .add((PointDeRestauration.TypePointDeRestauration) checkBox.getTag());
            }
        }


    }

    private void saveUserPreferences() {
        user = PrefUtils.recupererUtilisateur(getActivity());
        user.setPreferences(preferences);
        PrefUtils.sauvegardeUtilisateur(getContext(), user);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (! isVisibleToUser && isReady) {
            updatePreferences();
            saveUserPreferences();

            if (! preferences.equals(oldPreferences)) {
                mListener.onPreferencesChanged();
            }

            oldPreferences = new Preferences(preferences);
        }
    }

    @Override
    public void onPause() {
        if (! disconnectClicked) {
            updatePreferences();
            saveUserPreferences();
        }
        super.onPause();
    }

    public void setDisconnectClicked(boolean disconnectClicked) {
        this.disconnectClicked = disconnectClicked;
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
        void onPreferencesChanged();

    }


}
