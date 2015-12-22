package com.hexabinome.saladetomateoignon.fragment.favoris;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.hexabinome.saladetomateoignon.PrefUtils;
import com.hexabinome.saladetomateoignon.R;
import com.hexabinome.saladetomateoignon.modele.Restaurant;
import com.hexabinome.saladetomateoignon.modele.Utilisateur;

import java.util.List;

/**
 * Cette class permet de remplir chaque item de la liste
 */
public class RestaurantAdapter extends ArrayAdapter<Restaurant> {


    private List<Restaurant> restaurantList;

    private LayoutInflater layoutInflater;
    private Context context;

    public RestaurantAdapter(List <Restaurant> restaurantList, Context ctx) {
        super(ctx, R.layout.row_layout, restaurantList);
        this.restaurantList = restaurantList;
        this.context = ctx;
    }


     public View getView(int pos, View convertView, ViewGroup parent)
     {
         // First let's verify the convertView is not null
         if (convertView == null) {
            // This a new view we inflate the new layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_layout, parent, false);
         }
         // Now we can fill the layout with the right values
         TextView tv = (TextView) convertView.findViewById(R.id.name);
         TextView priceView = (TextView) convertView.findViewById(R.id.price);
         RatingBar rateBar = (RatingBar) convertView.findViewById(R.id.rating);
         TextView distanceView = (TextView) convertView.findViewById(R.id.dist);


         final Restaurant p = restaurantList.get(pos);
         Utilisateur utilisateur = PrefUtils.recupererUtilisateur(context);

         tv.setText(p.getName());
         priceView.setText(String.format(context.getString(R.string.prix_restaurant),p.getPrix()));

         rateBar.setNumStars((int)p.getNote());
         distanceView.setText(String.format(context.getString(R.string.distance_restaurant),p.getDistance(utilisateur.getLongitude(),utilisateur.getLatitude())));



       /*  convertView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Animation anim = AnimationUtils.loadAnimation(
                                                        getContext(), android.R.anim.fade_out
                                                );
                                                anim.setDuration(500);
                                                v.startAnimation(anim );

                                                Log.d("on click"," you have click on : " + p);
                                            }
                                        }
         );*/

         return convertView;
     }



    @Override
    public int getCount() {
        return restaurantList.size();
    }

    @Override
    public Restaurant getItem(int position) {
        return restaurantList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    private static class ViewHolder{
        // TODO : put the fields for each item in the list
    }

    public void setRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }
}
