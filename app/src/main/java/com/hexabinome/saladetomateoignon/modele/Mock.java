package com.hexabinome.saladetomateoignon.modele;

import com.hexabinome.saladetomateoignon.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Cette classe permettra de générer des données pour l'interface graphique.
 * Elle contiendra des méthodes statiques etc...
 *
 * @author Mohamed El Mouctar HAIDARA
 */
public final class Mock {

    private static Map<String, Utilisateur> utilisateurs;

    private static List<PointDeRestauration> pointDeRestaurations = createRestaurant();

    private Mock() {

    }

    public static void generateUsers() {
        utilisateurs = new HashMap<String, Utilisateur>();
        utilisateurs.put("DWobrock@insa-lyon.fr", new Utilisateur("Wobrock", "David", "DWobrock@insa-lyon.fr", "1234", Utilisateur.TypeUtilisateur.ETUDIANT));
        utilisateurs.put("JCornevin@insa-lyon.fr", new Utilisateur("Cornevin", "Jolan", "JCornevin@insa-lyon.fr", "1234", Utilisateur.TypeUtilisateur.ETUDIANT));
        utilisateurs.put("RRoyer@insa-lyon.fr", new Utilisateur("Royer", "Robin", "RRoyer@insa-lyon.fr", "1234", Utilisateur.TypeUtilisateur.ETUDIANT));
        utilisateurs.put("elmhaidara@gmail.com", new Utilisateur("Haidara", "Mohamed El Mouctar", "elmhaidara@gmail.com", "1234", Utilisateur.TypeUtilisateur.PROFESSEUR));
        utilisateurs.put("dumblecore@poudlard.fr", new Utilisateur("Dumblecore", "Albus", "jadore@crepes.fr", "42", Utilisateur.TypeUtilisateur.PROFESSEUR));
    }

    public static Utilisateur getUtilisateur(String email, String motDePasse) {
        Utilisateur user = utilisateurs.get(email);
        if (user == null || !user.getMotDePasse().equals(motDePasse)) {
            return null;
        } else {
            return user;
        }
    }

    private static List<PointDeRestauration> createRestaurant(){
        ArrayList<PointDeRestauration> preference = new ArrayList<PointDeRestauration>();

        PointDeRestauration jussieu = PointDeRestauration.builder()
                .name("Resto’U Jussieu")
                .prix(3.2)
                .longitude(4.876355)
                .latitude(45.780984)
                .tempsAttenteMoy(15)
                .idPhoto(R.drawable.jussieu)
                .note(3)
                .description(
                        "A Villeurbanne face au campus de la Doua de l’université Lyon 1 et de l’INSA, salle de 1 160 places.")
                .addTypePointDeRestauration(
                        PointDeRestauration.TypePointDeRestauration.RESTAURANT_UNIVERSITAIRE)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.SANS_PORC)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.VEGETALIEN)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.VEGETARIEN)
                .addAvis(new Avis(3, "J'ai beaucoup aimé ce restaurant gastronomique", "Robin Royer"))
                .addAvis(new Avis(2, "C'etait vraiment pas bon", "Alexis Andra"))
                .build();

        PointDeRestauration grignote = PointDeRestauration.builder()
                .name("La Grignote")
                .prix(5.2)
                .longitude(4.874866)
                .latitude(45.784130)
                .tempsAttenteMoy(10)
                .note(2)
                .description("A Villeurbanne face au campus de la Doua de l’université Lyon 1 et de l’INSA, salle de 34 places.")
                .idPhoto(R.drawable.grignote)
                .addTypePointDeRestauration(PointDeRestauration.TypePointDeRestauration.RESTAURANT_UNIVERSITAIRE)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.SANS_PORC)
                .addAvis(new Avis(3, "J'ai beaucoup aimé ce restaurant gastronomique", "Robin Royer"))
                .addAvis(new Avis(2, "C'etait vraiment pas bon", "Alexis Andra"))
                .build();

        PointDeRestauration puvis = PointDeRestauration.builder()
                .name("Resto’U Puvis de Chavannes")
                .prix(3.2)
                .longitude(4.876355)
                .latitude(45.780984)
                .tempsAttenteMoy(15)
                .note(2)
                .idPhoto(PointDeRestauration.NO_PHOTO)
                .description("A Villeurbanne face au campus de la Doua de l’université Lyon 1 et CPE, salle de 813 places.")
                .addTypePointDeRestauration(PointDeRestauration.TypePointDeRestauration.RESTAURANT_UNIVERSITAIRE)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.SANS_PORC)
                .addAvis(new Avis(3, "J'ai beaucoup aimé ce restaurant gastronomique", "Robin Royer"))
                .addAvis(new Avis(2, "C'etait vraiment pas bon", "Alexis Andra"))
                .build();

        // Restaurant INSA
        PointDeRestauration olivier = PointDeRestauration.builder()
                .name("L'Olivier")
                .prix(3.2)
                .longitude(4.878014)
                .latitude(45.782281)
                .tempsAttenteMoy(15)
                .note(2)
                .description("Restauration italienne.")
                .idPhoto(PointDeRestauration.NO_PHOTO)
                .addTypePointDeRestauration(PointDeRestauration.TypePointDeRestauration.RESTAURANT_UNIVERSITAIRE)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.SANS_PORC)
                .addAvis(new Avis(3, "J'ai beaucoup aimé ce restaurant gastronomique", "Robin Royer"))
                .addAvis(new Avis(2, "C'etait vraiment pas bon", "Alexis Andra"))
                .build();

        PointDeRestauration grillon = PointDeRestauration.builder()
                .name("Grillon")
                .prix(4.2)
                .longitude(4.874866)
                .latitude(45.784130)
                .tempsAttenteMoy(5)
                .note(1)
                .description("Restaurant grill.")
                .idPhoto(PointDeRestauration.NO_PHOTO)
                .addTypePointDeRestauration(PointDeRestauration.TypePointDeRestauration.RESTAURANT_UNIVERSITAIRE)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.SANS_PORC)
                .addAvis(new Avis(3, "J'ai beaucoup aimé ce restaurant gastronomique", "Robin Royer"))
                .addAvis(new Avis(2, "C'etait vraiment pas bon", "Alexis Andra"))
                .build();

        PointDeRestauration castor = PointDeRestauration.builder()
                .name("Le Castor et Pollux")
                .prix(4.4)
                .longitude(4.869037)
                .latitude(45.783846)
                .tempsAttenteMoy(10)
                .note(3)
                .description("Restaurant self-service traditionnel.")
                .idPhoto(PointDeRestauration.NO_PHOTO)
                .addTypePointDeRestauration(PointDeRestauration.TypePointDeRestauration.RESTAURANT_UNIVERSITAIRE)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.SANS_PORC)
                .addAvis(new Avis(3, "J'ai beaucoup aimé ce restaurant gastronomique", "Robin Royer"))
                .addAvis(new Avis(2, "C'etait vraiment pas bon", "Alexis Andra"))
                .build();

        PointDeRestauration beurk = PointDeRestauration.builder()
                .name("Puit du Saule")
                .prix(4.4)
                .longitude(4.873614)
                .latitude(45.78108)
                .tempsAttenteMoy(10)
                .note(1)
                .description("Restaurant pour les professeurs de l'INSA.")
                .idPhoto(PointDeRestauration.NO_PHOTO)
                .addTypePointDeRestauration(PointDeRestauration.TypePointDeRestauration.RESTAURANT_UNIVERSITAIRE)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.SANS_PORC)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.VEGETALIEN)
                .addAvis(new Avis(3, "J'ai beaucoup aimé ce restaurant gastronomique", "Robin Royer"))
                .addAvis(new Avis(2, "C'etait vraiment pas bon", "Alexis Andra"))
                .build();

        // Supermarché
        PointDeRestauration carrefour = PointDeRestauration.builder()
                .name("Supermarché : Carrefour Market")
                .prix(0.0)
                .longitude(4.875102)
                .latitude(45.776971)
                .tempsAttenteMoy(10)
                .note(3)
                .description("Carrefour Market, enseigne française de supermarchés appartenant au groupe Carrefour.")
                .idPhoto(R.drawable.carrefour)
                .addTypePointDeRestauration(PointDeRestauration.TypePointDeRestauration.SUPERMARCHE)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.SANS_PORC)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.VEGETARIEN)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.VEGETALIEN)
                .addAvis(new Avis(3, "J'ai beaucoup aimé ce restaurant gastronomique", "Robin Royer"))
                .addAvis(new Avis(2, "C'etait vraiment pas bon", "Alexis Andra"))
                .build();

        // cafeteria
        PointDeRestauration cyber = PointDeRestauration.builder()
                .name("Cybercafé de la Doua")
                .prix(3.2)
                .longitude(4.876355)
                .latitude(45.780984)
                .tempsAttenteMoy(15)
                .note(3)
                .description("A Villeurbanne, situé sur le campus de la Doua de l’Université Lyon 1, à l’intérieur du déambulatoire 1er cycle, salle de 78 places.")
                .idPhoto(R.drawable.cybercafe_doua)
                .addTypePointDeRestauration(PointDeRestauration.TypePointDeRestauration.CAFETERIA)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.SANS_PORC)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.VEGETARIEN)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.VEGETALIEN)
                .addAvis(new Avis(3, "J'ai beaucoup aimé ce restaurant gastronomique", "Robin Royer"))
                .addAvis(new Avis(2, "C'etait vraiment pas bon", "Alexis Andra"))
                .build();

        // cafeteria
        PointDeRestauration cafetpuvis = PointDeRestauration.builder()
                .name("Cafet’U Puvis de Chavannes")
                .prix(3.2)
                .longitude(4.876355)
                .latitude(45.780984)
                .tempsAttenteMoy(10)
                .note(3)
                .description("Située à Villeurbanne face au campus de la Doua de l’Université Lyon 1 et CPE.")
                .idPhoto(PointDeRestauration.NO_PHOTO)
                .addTypePointDeRestauration(PointDeRestauration.TypePointDeRestauration.CAFETERIA)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.SANS_PORC)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.VEGETARIEN)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.VEGETALIEN)
                .addAvis(new Avis(3, "J'ai beaucoup aimé ce restaurant gastronomique", "Robin Royer"))
                .addAvis(new Avis(2, "C'etait vraiment pas bon", "Alexis Andra"))
                .build();

        PointDeRestauration astree = PointDeRestauration.builder()
                .name("Cafet’U Astrée")
                .prix(3.2)
                .longitude(4.876355)
                .latitude(45.780984)
                .tempsAttenteMoy(10)
                .note(3)
                .description("A Villeurbanne, situé sur le campus de la Doua de l’Université Lyon 1, à l’intérieur du bâtiment Astrée, salle de 90 places.")
                .idPhoto(R.drawable.cafe_astree)
                .addTypePointDeRestauration(PointDeRestauration.TypePointDeRestauration.CAFETERIA)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.SANS_PORC)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.VEGETARIEN)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.VEGETALIEN)
                .addAvis(new Avis(3, "J'ai beaucoup aimé ce restaurant gastronomique", "Robin Royer"))
                .addAvis(new Avis(2, "C'etait vraiment pas bon", "Alexis Andra"))
                .build();

        PointDeRestauration cafetJussieu = PointDeRestauration.builder()
                .name("Cafet’U Jussieu")
                .prix(3.2)
                .longitude(4.876355)
                .latitude(45.780984)
                .tempsAttenteMoy(10)
                .note(5)
                .description("A Villeurbanne face au campus de la Doua de l’université Lyon 1 et de l’INSA.")
                .idPhoto(PointDeRestauration.NO_PHOTO)
                .addTypePointDeRestauration(PointDeRestauration.TypePointDeRestauration.CAFETERIA)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.SANS_PORC)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.VEGETARIEN)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.VEGETALIEN)
                .addAvis(new Avis(3, "J'ai beaucoup aimé ce restaurant gastronomique", "Robin Royer"))
                .addAvis(new Avis(2, "C'etait vraiment pas bon", "Alexis Andra"))
                .build();


        preference.add(jussieu);
        preference.add(grignote);
        preference.add(puvis);
        preference.add(beurk);
        preference.add(castor);
        preference.add(grillon);
        preference.add(olivier);
        preference.add(astree);
        preference.add(cafetJussieu);
        preference.add(cafetpuvis);
        preference.add(cyber);
        preference.add(carrefour);


        return preference;
    }

    public static void addUtilisateur(Utilisateur user) {
        utilisateurs.put(user.getEmail(), user);
    }

    public static List<PointDeRestauration> getRestaurantLaDoua() {
        return pointDeRestaurations;
    }


}
