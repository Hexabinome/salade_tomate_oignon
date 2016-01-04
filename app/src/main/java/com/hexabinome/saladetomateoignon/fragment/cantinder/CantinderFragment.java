package com.hexabinome.saladetomateoignon.fragment.cantinder;

import android.content.Context;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hexabinome.saladetomateoignon.PrefUtils;
import com.hexabinome.saladetomateoignon.R;
import com.hexabinome.saladetomateoignon.modele.Mock;
import com.hexabinome.saladetomateoignon.modele.PointDeRestauration;
import com.hexabinome.saladetomateoignon.modele.Preferences;
import com.hexabinome.saladetomateoignon.modele.Utilisateur;
import com.hexabinome.saladetomateoignon.modele.filtres.Filtre;
import com.hexabinome.saladetomateoignon.modele.filtres.FiltreDistance;
import com.hexabinome.saladetomateoignon.modele.filtres.FiltreNote;
import com.hexabinome.saladetomateoignon.modele.filtres.FiltrePrix;
import com.hexabinome.saladetomateoignon.modele.filtres.FiltreTempsDAttente;
import com.hexabinome.saladetomateoignon.modele.filtres.FiltreTypePointDeRestauration;
import com.hexabinome.saladetomateoignon.modele.filtres.FiltreTypeRegime;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import info.hoang8f.widget.FButton;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnCantinderFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class CantinderFragment extends Fragment implements View.OnClickListener {

    private OnCantinderFragmentInteractionListener mListener;
    public static final String TAG = "CantinderFragment";

    private Utilisateur currentUser;

    private PointDeRestauration currentPointDeRestauration;
    private PointDeRestauration previousPointDeRestauration;
    private List<PointDeRestauration> refused = new ArrayList<>(); // TODO reinit when come back to this fragment

    private TextView restaurantTitle;
    private TextView restaurantPrix;
    private TextView restaurantDistance;
    private TextView restaurantTempsAttente;
    private TextView emptyCantinderTextview;
    private RatingBar rateBar;

    private ImageButton declineButton;
    private ImageButton acceptButton;
    private ImageView restaurantImageView;

    private LinearLayout cantinder_layout;
    private LinearLayout cantinder_like_dislike_layout;
    private LinearLayout cantinder_empty_cardBoard_layout;

    private List<PointDeRestauration> restaurationList;

    boolean isReady = false, isPreferencesChanged = false;


    public CantinderFragment() {
        // Required empty public constructor
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
        emptyCantinderTextview = (TextView) inflatedView.findViewById(R.id.empty_cardBoard_textview);
        restaurantImageView = (ImageView) inflatedView.findViewById(R.id.restaurantImageView);

        cantinder_layout = (LinearLayout) inflatedView.findViewById(R.id.cantinder_board);
        cantinder_like_dislike_layout = (LinearLayout) inflatedView.findViewById(
                R.id.cantinder_like_dislike_layout);
        cantinder_empty_cardBoard_layout = (LinearLayout) inflatedView.findViewById(
                R.id.empty_cardBoard_layout);

        declineButton.setOnClickListener(this);
        acceptButton.setOnClickListener(this);

        restaurantTitle = (TextView) inflatedView.findViewById(R.id.restaurantTitle);
        restaurantTempsAttente = (TextView) inflatedView.findViewById(R.id.restaurantTempsAttente);
        restaurantDistance = (TextView) inflatedView.findViewById(R.id.restaurantDistance);
        restaurantPrix = (TextView) inflatedView.findViewById(R.id.restaurantPrice);
//        restaurantGrade = (TextView) inflatedView.findViewById(R.id.restaurantGrade);
        rateBar = (RatingBar) inflatedView.findViewById(R.id.restaurantGrade);


        majRestaurationList();
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

        isReady = true;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        isReady = false;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == acceptButton.getId()) {
            acceptRestaurant();
        } else if (v.getId() == declineButton.getId()) {
            declineRestaurant();
        } //else if (v.getId() == emptyCantinderButton.getId()) {
          //  if (mListener != null)
          //      mListener.onCantinderFragmentInteraction(0);
        //}
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
        void onCantinderFragmentInteraction(int tabNumber);

        void onNewFavorisAdded();
    }

    /**
     * Refuse le restaurant actuellement affiché
     * Passe au restaurant suivant
     */
    public void declineRestaurant() {
        if (currentPointDeRestauration != null) {
            refused.add(currentPointDeRestauration);
        }


        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this.getContext(),
                R.anim.dislike);
//        getView().startAnimation(hyperspaceJumpAnimation);
        cantinder_layout.startAnimation(hyperspaceJumpAnimation);


        currentPointDeRestauration = getNextRestaurant();
        displayRestaurant();

    }

    /**
     * Accepte le restaurant actuellement affiché.
     * Ajoute le restaurant actuel, affiche le bouton détail du restaurant, et passe au restaurant
     * suivant
     */
    public void acceptRestaurant() {

        if (currentPointDeRestauration != null) {
            // Add current restaurant to favorties
            currentUser.addToFavorites(currentPointDeRestauration);
            PrefUtils.sauvegardeUtilisateur(getActivity(), currentUser);
            mListener.onNewFavorisAdded();
        }
        // Next restaurant


        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this.getContext(),
                R.anim.like);
        //getView().startAnimation(hyperspaceJumpAnimation);
        cantinder_layout.startAnimation(hyperspaceJumpAnimation);
        restaurationList.remove(currentPointDeRestauration);
        currentPointDeRestauration = getNextRestaurant();
        displayRestaurant();
    }


    /**
     * Gets the next restaurant, corresponding to the preferences and not already in the favorites
     *
     * @return The next restaurant that might match. Null if no restaurant left (all in favorites)
     */
    private PointDeRestauration getNextRestaurant() {
        previousPointDeRestauration = currentPointDeRestauration;
        for (PointDeRestauration r : restaurationList) {
            if (! isFavorite(r) && ! isRefused(r)) {
                return r;
            }
        }

        Toast.makeText(getContext(), "No restaurant found", Toast.LENGTH_SHORT).show();
        return null;
    }

    private SortedSet<PointDeRestauration> getMostMatchingRestaurants() {
        SortedSet<PointDeRestauration> mostMatchingPointDeRestaurations = new TreeSet<>(
                new Comparator<PointDeRestauration>() {
                    private Preferences pref;

                    /**
                     * Tends to negative if good (because the biggest score is the geatest and will be at the end, we want the best to be at the beginning)
                     * @param r
                     * @return
                     */
                    private double getScore(PointDeRestauration r) {
                        double score = 0;
                        if (r.getDistance(currentUser.getLongitude(),
                                currentUser.getLatitude()) > pref.getDistance()) {
                            score--;
                        } else {
                            score++;
                        }

                        if (r.getNote() > pref.getNote()) {
                            score--;
                        } else {
                            score++;
                        }

                        if (r.getPrix() > pref.getPrix()) {
                            score--;
                        } else {
                            score++;
                        }

                        if (r.getTempsAttenteMoy() > pref.getTempsDattente()) {
                            score--;
                        } else {
                            score++;
                        }
                        return score;
                    }

                    @Override
                    public int compare(PointDeRestauration lhs, PointDeRestauration rhs) {
                        pref = currentUser.getPreferences();
                        double scoreLeft = getScore(lhs);
                        double scoreRight = getScore(rhs);
                        return (int) (scoreRight - scoreLeft);
                    }
                });

        List<PointDeRestauration> filteredList = filtrePointDeRestauration(
                Mock.getRestaurantLaDoua(), currentUser);


        //  mostMatchingPointDeRestaurations.addAll(Mock.getRestaurantLaDoua());

        return new TreeSet<>(filteredList);
    }

    private static List<PointDeRestauration> filtrePointDeRestauration(
            List<PointDeRestauration> pointDeRestaurationList, Utilisateur utilisateur) {
        FiltrePrix filtrePrix = new FiltrePrix(utilisateur.getPreferences().getPrix());
        FiltreDistance filtreDistance = new FiltreDistance(utilisateur.getLongitude(),
                utilisateur.getLatitude(), utilisateur.getPreferences().getDistance());
        FiltreNote filtreNote = new FiltreNote(utilisateur.getPreferences().getNote());
        FiltreTempsDAttente filtreTempsDAttente = new FiltreTempsDAttente(
                utilisateur.getPreferences().getTempsDattente());
        FiltreTypeRegime filtreTypeRegime = new FiltreTypeRegime(
                utilisateur.getPreferences().getTypeRegime());
        FiltreTypePointDeRestauration filtreTypePointDeRestauration = new FiltreTypePointDeRestauration(
                utilisateur.getPreferences().getTypePointDeRestaurations());

        Set<Filtre> filtreHashSet = new HashSet<>();

        filtreHashSet.add(filtreDistance);
        filtreHashSet.add(filtreNote);
        filtreHashSet.add(filtreTypeRegime);
        filtreHashSet.add(filtrePrix);
        filtreHashSet.add(filtreTempsDAttente);
        filtreHashSet.add(filtreTypePointDeRestauration);

        List<PointDeRestauration> listFiltered = new ArrayList<>(pointDeRestaurationList);

        for (Filtre filtre : filtreHashSet) {
            listFiltered = filtre.appliqueFiltre(listFiltered);
        }


        return listFiltered;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (! isVisibleToUser && isReady) {
            // the fragment is not visible
            PrefUtils.sauvegardeUtilisateur(getActivity(), currentUser);
        } else {
            if (isPreferencesChanged) {
                majRestaurationList();
                Snackbar snackbar = Snackbar.make(getView(), "Points de restauration mis à jour",
                        Snackbar.LENGTH_SHORT);
                snackbar.show();
                isPreferencesChanged = false;
            }
        }
    }

    private boolean isFavorite(PointDeRestauration r) {
        return currentUser.isFavorite(r);
    }

    private boolean isRefused(PointDeRestauration r) {
        return refused.contains(r);
    }

    /**
     * Fills restaurant displays
     */
    private void displayRestaurant() {
        double distance;
        if (currentPointDeRestauration != null) {
            cantinder_layout.setVisibility(View.VISIBLE);
            cantinder_like_dislike_layout.setVisibility(View.VISIBLE);
            cantinder_empty_cardBoard_layout.setVisibility(View.GONE);
            distance = currentPointDeRestauration.getDistance(currentUser.getLongitude(),
                    currentUser.getLatitude());

            restaurantTitle.setText(currentPointDeRestauration.getName());
            restaurantDistance.setText(
                    String.format(getString(R.string.distance_restaurant), distance));
            restaurantPrix.setText(String.format(getString(R.string.prix_restaurant),
                    currentPointDeRestauration.getPrix()));
            restaurantTempsAttente.setText(String.format(getString(R.string.temps),
                    currentPointDeRestauration.getTempsAttenteMoy()));
            rateBar.setNumStars((int) currentPointDeRestauration.getNote());
            if (currentPointDeRestauration.getIdPhoto() != PointDeRestauration.NO_PHOTO)
                restaurantImageView.setImageDrawable(ContextCompat.getDrawable(getContext(),
                        currentPointDeRestauration.getIdPhoto()));
            else {
                restaurantImageView.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.image_restaurant));
            }

        } else {
            // TODO : faire autre choses
            cantinder_layout.setVisibility(View.GONE);
            cantinder_like_dislike_layout.setVisibility(View.GONE);
            cantinder_empty_cardBoard_layout.setVisibility(View.VISIBLE);
        }
    }

    public void setIsPreferencesChanged(boolean preferencesChanged) {
        isPreferencesChanged = preferencesChanged;
    }

    private void majRestaurationList() {
        currentUser = PrefUtils.recupererUtilisateur(getActivity());

        restaurationList = filtrePointDeRestauration(Mock.getRestaurantLaDoua(), currentUser);

        currentPointDeRestauration = getNextRestaurant();
        displayRestaurant();

    }
}
