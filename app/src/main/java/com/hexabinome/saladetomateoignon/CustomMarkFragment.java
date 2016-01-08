package com.hexabinome.saladetomateoignon;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomMarkFragment extends DialogFragment {
    View rootView;
    TextView nameRestaurantView;
    RatingBar stars;
    EditText comment;

    String nomRestaurant;
    float note;
    boolean paramSet = false;

    public interface CustomMarkFragmentListener {
        void onMarkPositiveClick(String avis);
        void onMarkNegativeClick();
    }

    CustomMarkFragmentListener hostListener;

    public void setParam(String nomRestaurant, float note)
    {
        paramSet = true;
        this.nomRestaurant = nomRestaurant;
        this.note = note;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            hostListener = (CustomMarkFragmentListener)activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if(paramSet)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            // Get the layout inflater
            LayoutInflater inflater = getActivity().getLayoutInflater();
            rootView = inflater.inflate(R.layout.custom_mark, null);

            nameRestaurantView = (TextView) rootView.findViewById(R.id.customMarkNomRestaurant);
            nameRestaurantView.setText(nomRestaurant);

            stars = (RatingBar) rootView.findViewById(R.id.customMarkNotation);
            stars.setRating(note);

            builder.setView(rootView)
                    // Add action buttons
                    .setPositiveButton(R.string.valider, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            comment = (EditText) rootView.findViewById(R.id.custonMarkComment);
                            if(comment == null) {
                                hostListener.onMarkPositiveClick("");
                            }
                            else {
                                hostListener.onMarkPositiveClick(comment.getText().toString());
                            }
                        }
                    })
                    .setNegativeButton(R.string.annuler, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            hostListener.onMarkNegativeClick();
                        }
                    });
            return builder.create();
        }
        else
        {
            return null;
        }
    }


}