package com.hexabinome.saladetomateoignon.modele;

import com.hexabinome.saladetomateoignon.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
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

    public static ArrayList<PointDeRestauration> getRestaurantLaDoua() {
        ArrayList<PointDeRestauration> preference = new ArrayList<PointDeRestauration>();
        //TODO : create a real list of the restaurants around INSA

        // Restaurant RU crous
        preference.add(new PointDeRestauration("Resto’U Jussieu", 3.20, 15, 3,
                new HashSet<>(Arrays.asList(PointDeRestauration.TypePointDeRestauration.RESTAURANT_UNIVERSITAIRE)), null, 4.876355, 45.780984,"A Villeurbanne face au campus de la Doua de l’université Lyon 1 et de l’INSA, salle de 1 160 places.", PointDeRestauration.NO_PHOTO));
        preference.add(new PointDeRestauration("La Grignote", 5.30, 5, 3,
                new HashSet<>(Arrays.asList(PointDeRestauration.TypePointDeRestauration.RESTAURANT_UNIVERSITAIRE)), null, 4.874866, 45.784130,"A Villeurbanne face au campus de la Doua de l’université Lyon 1 et de l’INSA, salle de 34 places.", PointDeRestauration.NO_PHOTO));
        preference.add(new PointDeRestauration("Resto’U Puvis de Chavannes", 3.20, 15, 3,
                new HashSet<>(Arrays.asList(PointDeRestauration.TypePointDeRestauration.RESTAURANT_UNIVERSITAIRE)), null, 4.876355, 45.780984,"A Villeurbanne face au campus de la Doua de l’université Lyon 1 et CPE, salle de 813 places.", PointDeRestauration.NO_PHOTO));

        // Restaurant INSA
        preference.add(new PointDeRestauration("L'Olivier", 4.40, 25, 3,
                new HashSet<>( Arrays.asList(PointDeRestauration.TypePointDeRestauration.RESTAURANT_UNIVERSITAIRE)), null, 4.878014, 45.782281, "Restauration italienne", PointDeRestauration.NO_PHOTO));
        preference.add(new PointDeRestauration("Grillon", 4.40, 20, 4,
                new HashSet<>(Arrays.asList(PointDeRestauration.TypePointDeRestauration.RESTAURANT_UNIVERSITAIRE)), null, 4.874866, 45.784130,"Restaurant grill", PointDeRestauration.NO_PHOTO));
        preference.add(new PointDeRestauration("Le Castor et Pollux", 4.40, 10, 1,
                new HashSet<>(Arrays.asList(PointDeRestauration.TypePointDeRestauration.RESTAURANT_UNIVERSITAIRE)), null, 4.869037, 45.783846, "Restaurant self-service traditionnel", PointDeRestauration.NO_PHOTO));
        preference.add(new PointDeRestauration("Beurk", 4.40, 10, 1,
                new HashSet<>( Arrays.asList(PointDeRestauration.TypePointDeRestauration.RESTAURANT_UNIVERSITAIRE)), null, 4.873614, 45.78108,"Comme son nom l'indique, c'est Beurk", PointDeRestauration.NO_PHOTO));

        // Supermarché
        preference.add(new PointDeRestauration("Supermarché : Carrefour Market", 5.60, 10, 1,
                new HashSet<>(Arrays.asList(PointDeRestauration.TypePointDeRestauration.SUPERMARCHE)), null, 4.875102, 45.776971, "Carrefour Market, enseigne française de supermarchés appartenant au groupe Carrefour", PointDeRestauration.NO_PHOTO));

        // cafeteria
        preference.add(new PointDeRestauration("Cybercafé de la Doua", 3.20, 15, 3,
                new HashSet<>(Arrays.asList(PointDeRestauration.TypePointDeRestauration.CAFETERIA)), null, 4.876355, 45.780984,"A Villeurbanne, situé sur le campus de la Doua de l’Université Lyon 1, à l’intérieur du déambulatoire 1er cycle, salle de 78 places.", R.drawable.cybercafe_doua));
        preference.add(new PointDeRestauration("Cafet’U Puvis de Chavannes", 3.20, 15, 3,
                new HashSet<>(Arrays.asList(PointDeRestauration.TypePointDeRestauration.CAFETERIA)), null, 4.876355, 45.780984,"Située à Villeurbanne face au campus de la Doua de l’Université Lyon 1 et CPE.", PointDeRestauration.NO_PHOTO));
        preference.add(new PointDeRestauration("Cafet’U Astrée", 3.20, 15, 1,
                new HashSet<>(Arrays.asList(PointDeRestauration.TypePointDeRestauration.CAFETERIA)), null, 4.876355, 45.780984,"A Villeurbanne, situé sur le campus de la Doua de l’Université Lyon 1, à l’intérieur du bâtiment Astrée, salle de 90 places.",R.drawable.cafe_astree));
        preference.add(new PointDeRestauration("Cafet’U Jussieu", 3.20, 15, 2,
                new HashSet<>(Arrays.asList(PointDeRestauration.TypePointDeRestauration.CAFETERIA)), null, 4.876355, 45.780984,"A Villeurbanne face au campus de la Doua de l’université Lyon 1 et de l’INSA.", PointDeRestauration.NO_PHOTO));



        return preference;
    }


}
