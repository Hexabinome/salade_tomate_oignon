package com.hexabinome.saladetomateoignon.fragment.favoris;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.hexabinome.saladetomateoignon.PrefUtils;
import com.hexabinome.saladetomateoignon.R;
import com.hexabinome.saladetomateoignon.modele.PointDeRestauration;
import com.hexabinome.saladetomateoignon.modele.Utilisateur;

import java.util.List;

/**
 * Cette class permet de remplir chaque item de la liste
 */
public class RestaurantAdapter extends ArrayAdapter<PointDeRestauration> {


    private List<PointDeRestauration> pointDeRestaurationList;

    private Context context;

    public RestaurantAdapter(List<PointDeRestauration> pointDeRestaurationList, Context ctx) {
        super(ctx, R.layout.row_layout, pointDeRestaurationList);
        this.pointDeRestaurationList = pointDeRestaurationList;
        this.context = ctx;
    }



    public View getView(int pos, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        // First let's verify the convertView is not null
        if (convertView == null) {

            // This a new view we inflate the new layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_layout, parent, false);

            viewHolder = new ViewHolder();
            // Now we can fill the layout with the right values
            viewHolder.nameTextView = (TextView) convertView.findViewById(R.id.name);
            viewHolder.priceTextView = (TextView) convertView.findViewById(R.id.price);
            viewHolder.ratingBar = (RatingBar) convertView.findViewById(R.id.rating);
            viewHolder.distanceTextView = (TextView) convertView.findViewById(R.id.dist);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }



        final PointDeRestauration p = pointDeRestaurationList.get(pos);
        final Utilisateur utilisateur = PrefUtils.recupererUtilisateur(context);

        viewHolder.nameTextView.setText(p.getName());
        double prix = p.getPrix();

        if(p.getTypePointDeRestauration().contains(PointDeRestauration.TypePointDeRestauration.SUPERMARCHE)){

            viewHolder.priceTextView.setText("-- €"); // pas de prix moyen pour les supermachés
        } else {
            if(utilisateur.getTypeUtilisateur() == Utilisateur.TypeUtilisateur.PROFESSEUR)
                prix += 2; // +2 € pour les profs
            viewHolder.priceTextView.setText(String.format(context.getString(R.string.prix_restaurant), prix));
        }


        viewHolder.ratingBar.setNumStars((int) p.getNote());
        viewHolder.distanceTextView.setText(String.format(context.getString(R.string.distance_restaurant), p.getDistance(utilisateur.getLongitude(), utilisateur.getLatitude())));


        return convertView;
    }


    @Override
    public int getCount() {
        return pointDeRestaurationList.size();
    }

    @Override
    public PointDeRestauration getItem(int position) {
        return pointDeRestaurationList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    /**
     * http://www.javacodegeeks.com/2013/09/android-viewholder-pattern-example.html
     */
    private static class ViewHolder {
        public TextView nameTextView;
        public TextView priceTextView;
        public RatingBar ratingBar;
        public TextView distanceTextView;
    }

    public void setPointDeRestaurationList(List<PointDeRestauration> pointDeRestaurationList) {
        this.pointDeRestaurationList = pointDeRestaurationList;
    }
}
