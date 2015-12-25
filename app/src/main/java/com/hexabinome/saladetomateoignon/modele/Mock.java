package com.hexabinome.saladetomateoignon.modele;

import com.hexabinome.saladetomateoignon.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Cette classe permettra de générer des données pour l'interface graphique.
 * Elle contiendra des méthodes statiques etc...
 *
 * @author Mohamed El Mouctar HAIDARA
 */
public final class Mock {
    private static Map<String, Utilisateur> utilisateurs;



    private Mock() {

    }

    public static void generateUsers() {
        utilisateurs = new HashMap<String, Utilisateur>();
        utilisateurs.put("DWobrock@insa-lyon.fr", new Utilisateur("Wobrock", "David", "DWobrock@insa-lyon.fr", "1234"));
        utilisateurs.put("JCornevin@insa-lyon.fr", new Utilisateur("Cornevin", "Jolan", "JCornevin@insa-lyon.fr", "1234"));
        utilisateurs.put("RRoyer@insa-lyon.fr", new Utilisateur("Royer", "Robin", "RRoyer@insa-lyon.fr", "1234"));
        utilisateurs.put("elmhaidara@gmail.com", new Utilisateur("Haidara", "Mohamed El Mouctar", "elmhaidara@gmail.com", "1234"));

    }

    public static Utilisateur getUtilisateur(String email, String motDePasse) {
        Utilisateur user = utilisateurs.get(email);
        if (user == null || !user.getMotDePasse().equals(motDePasse)) {
            return null;
        } else {
            return user;
        }
    }

    public static void addUtilisateur(Utilisateur user) {
        utilisateurs.put(user.getEmail(), user);
    }

    public static ArrayList<Restaurant> getRestaurantLaDoua() {
        ArrayList<Restaurant> preference = new ArrayList<Restaurant>();
        //TODO : create a real list of the restaurants around INSA
        preference.add(new Restaurant("Resto’U Jussieu", 3.20, 15, 3,
                Restaurant.TypePointDeRestauration.RESTAURANT_UNIVERSITAIRE, null, 4.876355, 45.780984,"A Villeurbanne face au campus de la Doua de l’université Lyon 1 et de l’INSA, salle de 1 160 places.",Restaurant.NO_PHOTO));
        preference.add(new Restaurant("L'Olivier", 4.40, 25, 3,
                Restaurant.TypePointDeRestauration.RESTAURANT_UNIVERSITAIRE, null, 4.878014, 45.782281, "Restauration italienne",Restaurant.NO_PHOTO));
        preference.add(new Restaurant("Grillon", 4.40, 20, 4,
                Restaurant.TypePointDeRestauration.RESTAURANT_UNIVERSITAIRE, null, 4.874866, 45.784130,"Restaurant grill",Restaurant.NO_PHOTO));
        preference.add(new Restaurant("La Grignote", 5.30, 5, 3,
                Restaurant.TypePointDeRestauration.CAFETERIA, null, 4.874866, 45.784130,"A Villeurbanne face au campus de la Doua de l’université Lyon 1 et de l’INSA, salle de 34 places.",Restaurant.NO_PHOTO));
        preference.add(new Restaurant("Le Castor et Pollux", 4.40, 10, 1,
                Restaurant.TypePointDeRestauration.RESTAURANT_UNIVERSITAIRE, null, 4.869037, 45.783846, "Restaurant self-service traditionnel",Restaurant.NO_PHOTO));
        preference.add(new Restaurant("Beurk", 4.40, 10, 1,
                Restaurant.TypePointDeRestauration.RESTAURANT_UNIVERSITAIRE, null, 4.873614, 45.78108,"Comme son nom l'indique, c'est Beurk",Restaurant.NO_PHOTO));
        preference.add(new Restaurant("Supermarché : Carrefour Market", 5.60, 10, 1,
                Restaurant.TypePointDeRestauration.SUPERMARCHE, null, 4.875102, 45.776971, "Carrefour Market, enseigne française de supermarchés appartenant au groupe Carrefour",Restaurant.NO_PHOTO));
        preference.add(new Restaurant("Resto’U Puvis de Chavannes", 3.20, 15, 3,
                Restaurant.TypePointDeRestauration.RESTAURANT_UNIVERSITAIRE, null, 4.876355, 45.780984,"A Villeurbanne face au campus de la Doua de l’université Lyon 1 et CPE, salle de 813 places.",Restaurant.NO_PHOTO));

        // cafeteria
        preference.add(new Restaurant("Cybercafé de la Doua", 3.20, 15, 3,
                Restaurant.TypePointDeRestauration.CAFETERIA, null, 4.876355, 45.780984,"A Villeurbanne, situé sur le campus de la Doua de l’Université Lyon 1, à l’intérieur du déambulatoire 1er cycle, salle de 78 places.", R.drawable.cybercafe_doua));
        preference.add(new Restaurant("Cafet’U Puvis de Chavannes", 3.20, 15, 3,
                Restaurant.TypePointDeRestauration.CAFETERIA, null, 4.876355, 45.780984,"Située à Villeurbanne face au campus de la Doua de l’Université Lyon 1 et CPE.",Restaurant.NO_PHOTO));
        preference.add(new Restaurant("Cafet’U Astrée", 3.20, 15, 1,
                Restaurant.TypePointDeRestauration.CAFETERIA, null, 4.876355, 45.780984,"A Villeurbanne, situé sur le campus de la Doua de l’Université Lyon 1, à l’intérieur du bâtiment Astrée, salle de 90 places.",R.drawable.cafe_astree));
        preference.add(new Restaurant("Cafet’U Jussieu", 3.20, 15, 2,
                Restaurant.TypePointDeRestauration.CAFETERIA, null, 4.876355, 45.780984,"A Villeurbanne face au campus de la Doua de l’université Lyon 1 et de l’INSA.",Restaurant.NO_PHOTO));



        return preference;
    }


}
