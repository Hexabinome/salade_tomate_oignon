package com.hexabinome.saladetomateoignon.fragment.preferences;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
 * Use the {@link PreferencesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PreferencesFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnPreferencesFragmentInteractionListener mListener;

    private Button btnDisconnect;
    public PreferencesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PreferencesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PreferencesFragment newInstance(String param1, String param2) {
        PreferencesFragment fragment = new PreferencesFragment();
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
        Utilisateur user = PrefUtils.recupererUtilisateur(getActivity());
        Preferences preferences = user.getPreferences();
        DiscreteSeekBar distance = (DiscreteSeekBar) inflatedView.findViewById(R.id.prefDistanceRestaurant);
        DiscreteSeekBar attente = (DiscreteSeekBar) inflatedView.findViewById(R.id.prefTempsAttente);
        DiscreteSeekBar prix = (DiscreteSeekBar) inflatedView.findViewById(R.id.prefPrixRestaurant);
        RatingBar note = (RatingBar) inflatedView.findViewById(R.id.prefNote);
        Spinner regime = (Spinner) inflatedView.findViewById(R.id.regime);
        distance.setProgress(preferences.getDistance());
        attente.setProgress(preferences.getTempsDattente());
        prix.setProgress(preferences.getPrix());
        note.setRating((float) preferences.getNote());

        Restaurant.TypeRegime prefRegime = preferences.getTypeRegime();
        regime.setSelection(prefRegime.ordinal());
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
        // TODO: Update argument type and name
        void onPreferencesFragmentInteraction(Uri uri);
    }
}
