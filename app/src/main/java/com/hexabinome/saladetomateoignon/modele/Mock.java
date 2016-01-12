package com.hexabinome.saladetomateoignon.modele;

import android.util.Log;

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

    public static boolean existsUser(String email)
    {
        return utilisateurs.containsKey(email);
    }

    private static List<PointDeRestauration> createRestaurant(){
        ArrayList<PointDeRestauration> preference = new ArrayList<PointDeRestauration>();

        PointDeRestauration jussieu = PointDeRestauration.builder()
                .name("Resto’U Jussieu")
                .prix(3.2)
                .longitude(4.876355)
                .latitude(45.780984)
                .idPhoto(R.drawable.jussieu)
                .note(3)
                .description(
                        "A Villeurbanne face au campus de la Doua de l’université Lyon 1 et de l’INSA, salle de 1 160 places.")
                .addTypePointDeRestauration(
                        PointDeRestauration.TypePointDeRestauration.RESTAURANT_UNIVERSITAIRE)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.SANS_PORC)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.VEGETALIEN)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.VEGETARIEN)
                .addAvis(new Avis(3, "J'ai beaucoup aimé ce restaurant gastronomique", "Robin Royer","12/12/2015"))
                .addAvis(new Avis(2, "C'etait vraiment pas bon", "Alexis Andra","05/01/2015"))
                .menuDujour("Brochette de dinde sauce mexicaine steak hache sauce.")
                .addTempsDattente(1,10)
                .addTempsDattente(2,15)
                .addTempsDattente(3,25)
                .addTempsDattente(4,15)
                .addTempsDattente(5,5)
                .build();

        PointDeRestauration grignote = PointDeRestauration.builder()
                .name("La Grignote")
                .prix(5.2)
                .longitude(4.874866)
                .latitude(45.784130)
                .note(2)
                .description(
                        "A Villeurbanne face au campus de la Doua de l’université Lyon 1 et de l’INSA, salle de 34 places.")
                .idPhoto(R.drawable.grignote)
                .addTypePointDeRestauration(
                        PointDeRestauration.TypePointDeRestauration.RESTAURANT_UNIVERSITAIRE)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.SANS_PORC)
                .addAvis(
                        new Avis(3, "J'ai beaucoup aimé ce restaurant gastronomique", "Robin Royer",
                                "02/15/2014"))
                .addAvis(new Avis(2, "C'etait vraiment pas bon", "Alexis Andra", "12/11/2013"))
                .menuDujour("Steak hache sauce poivre farfalles aricots verts.")
                .addTempsDattente(1, 10)
                .addTempsDattente(2, 15)
                .addTempsDattente(3, 25)
                .addTempsDattente(4, 15)
                .addTempsDattente(5, 5)
                .build();

        PointDeRestauration puvis = PointDeRestauration.builder()
                .name("Resto’U Puvis de Chavannes")
                .prix(3.2)
                .longitude(4.876355)
                .latitude(45.780984)
                .note(2)
                .idPhoto(PointDeRestauration.NO_PHOTO)
                .description(
                        "A Villeurbanne face au campus de la Doua de l’université Lyon 1 et CPE, salle de 813 places.")
                .addTypePointDeRestauration(
                        PointDeRestauration.TypePointDeRestauration.RESTAURANT_UNIVERSITAIRE)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.SANS_PORC)
                .addAvis(
                        new Avis(3, "J'ai beaucoup aimé ce restaurant gastronomique", "Robin Royer",
                                "17/04/2015"))
                .addAvis(new Avis(2, "C'etait vraiment pas bon", "Alexis Andra", "22/03/2015"))
                .menuDujour("Omelette paysanne.")
                .addTempsDattente(1, 10)
                .addTempsDattente(2, 15)
                .addTempsDattente(3, 25)
                .addTempsDattente(4, 15)
                .addTempsDattente(5, 5)
                .build();

        // Restaurant INSA
        PointDeRestauration olivier = PointDeRestauration.builder()
                .name("L'Olivier")
                .prix(3.2)
                .longitude(4.878014)
                .latitude(45.782281)
                .note(2)
                .description("Restauration italienne.")
                .idPhoto(PointDeRestauration.NO_PHOTO)
                .addTypePointDeRestauration(
                        PointDeRestauration.TypePointDeRestauration.RESTAURANT_UNIVERSITAIRE)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.SANS_PORC)
                .addAvis(
                        new Avis(3, "J'ai beaucoup aimé ce restaurant gastronomique", "Robin Royer",
                                "12/01/2014"))
                .addAvis(new Avis(2, "C'etait vraiment pas bon", "Alexis Andra", "09/03/2015"))
                .menuDujour("Gratin de choux fleur.")
                .addTempsDattente(1, 10)
                .addTempsDattente(2, 15)
                .addTempsDattente(3, 25)
                .addTempsDattente(4, 15)
                .addTempsDattente(5, 5)
                .build();

        PointDeRestauration grillon = PointDeRestauration.builder()
                .name("Grillon")
                .prix(4.2)
                .longitude(4.874866)
                .latitude(45.784130)
                .note(1)
                .description("Restaurant grill.")
                .idPhoto(PointDeRestauration.NO_PHOTO)
                .addTypePointDeRestauration(
                        PointDeRestauration.TypePointDeRestauration.RESTAURANT_UNIVERSITAIRE)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.SANS_PORC)
                .addAvis(
                        new Avis(3, "J'ai beaucoup aimé ce restaurant gastronomique", "Robin Royer",
                                "01/10/2015"))
                .addAvis(new Avis(2, "C'etait vraiment pas bon", "Alexis Andra", "10/12/2014"))
                .menuDujour("Blanquette de veau riz.")
                .addTempsDattente(1, 10)
                .addTempsDattente(2, 15)
                .addTempsDattente(3, 25)
                .addTempsDattente(4, 15)
                .addTempsDattente(5, 5)
                .build();

        PointDeRestauration castor = PointDeRestauration.builder()
                .name("Le Castor et Pollux")
                .prix(4.4)
                .longitude(4.869037)
                .latitude(45.783846)
                .note(3)
                .description("Restaurant self-service traditionnel.")
                .idPhoto(R.drawable.resto_u)
                .addTypePointDeRestauration(
                        PointDeRestauration.TypePointDeRestauration.RESTAURANT_UNIVERSITAIRE)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.SANS_PORC)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.VEGETARIEN)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.VEGETALIEN)
                .addAvis(
                        new Avis(3, "J'ai beaucoup aimé ce restaurant gastronomique", "Robin Royer",
                                "12/12/2015"))
                .addAvis(new Avis(2, "C'etait vraiment pas bon", "Alexis Andra", "01/10/2015"))
                .menuDujour("Epinards sauce brune.")
                .addTempsDattente(1, 10)
                .addTempsDattente(2, 15)
                .addTempsDattente(3, 25)
                .addTempsDattente(4, 15)
                .addTempsDattente(5, 5)
                .build();

        PointDeRestauration beurk = PointDeRestauration.builder()
                .name("Puit du Saule")
                .prix(4.4)
                .longitude(4.873614)
                .latitude(45.78108)
                .note(1)
                .description("Restaurant pour les professeurs de l'INSA.")
                .idPhoto(R.drawable.resto_u)
                .addTypePointDeRestauration(
                        PointDeRestauration.TypePointDeRestauration.RESTAURANT_UNIVERSITAIRE)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.SANS_PORC)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.VEGETALIEN)
                .addAvis(
                        new Avis(3, "J'ai beaucoup aimé ce restaurant gastronomique", "Robin Royer",
                                "12/03/2013"))
                .addAvis(new Avis(2, "C'etait vraiment pas bon", "Alexis Andra", "12/12/2012"))
                .menuDujour("Rôti de dinde.")
                .addTempsDattente(1, 15)
                .addTempsDattente(2, 25)
                .addTempsDattente(3, 30)
                .addTempsDattente(4, 20)
                .addTempsDattente(5, 10)
                .build();

        // Supermarché
        PointDeRestauration carrefour = PointDeRestauration.builder()
                .name("Supermarché : Carrefour Market")
                .prix(0.0)
                .longitude(4.875102)
                .latitude(45.776971)
                .note(3)
                .description(
                        "Carrefour Market, enseigne française de supermarchés appartenant au groupe Carrefour.")
                .idPhoto(R.drawable.carrefour)
                .addTypePointDeRestauration(PointDeRestauration.TypePointDeRestauration.SUPERMARCHE)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.SANS_PORC)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.VEGETARIEN)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.VEGETALIEN)
                .addAvis(new Avis(2, "Les caissières ne sont pas très sympa", "Alexis Andra",
                        "12/12/2013"))
                .addAvis(new Avis(2, "Il fait trop froid dans le magasin", "Alexis Papin",
                        "12/06/2013"))
                .addTempsDattente(1, 10)
                .addTempsDattente(2, 15)
                .addTempsDattente(3, 15)
                .addTempsDattente(4, 15)
                .addTempsDattente(5, 5)
                .build();

        // cafeteria
        PointDeRestauration cyber = PointDeRestauration.builder()
                .name("Cybercafé de la Doua")
                .prix(3.2)
                .longitude(4.876355)
                .latitude(45.780984)
                .note(3)
                .description(
                        "A Villeurbanne, situé sur le campus de la Doua de l’Université Lyon 1, à l’intérieur du déambulatoire 1er cycle, salle de 78 places.")
                .idPhoto(R.drawable.cybercafe_doua)
                .addTypePointDeRestauration(PointDeRestauration.TypePointDeRestauration.CAFETERIA)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.SANS_PORC)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.VEGETARIEN)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.VEGETALIEN)
                .addAvis(new Avis(3, "Cyber très sympa avec de bon sandwich", "David Wobrock",
                        "06/10/2015"))
                .addAvis(new Avis(2, "C'etait vraiment bon avec une bonne ambiance", "Alexis Andra",
                        "12/05/2015"))
                .addTempsDattente(1, 10)
                .addTempsDattente(2, 15)
                .addTempsDattente(3, 20)
                .addTempsDattente(4, 15)
                .addTempsDattente(5, 5)
                .build();

        // cafeteria
        PointDeRestauration cafetpuvis = PointDeRestauration.builder()
                .name("Cafet’U Puvis de Chavannes")
                .prix(3.2)
                .longitude(4.876355)
                .latitude(45.780984)
                .note(3)
                .description(
                        "Située à Villeurbanne face au campus de la Doua de l’Université Lyon 1 et CPE.")
                .idPhoto(R.drawable.resto_u)
                .addTypePointDeRestauration(PointDeRestauration.TypePointDeRestauration.CAFETERIA)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.SANS_PORC)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.VEGETARIEN)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.VEGETALIEN)
                .addAvis(
                        new Avis(3, "J'ai beaucoup aimé ce restaurant gastronomique", "Robin Royer",
                                "12/12/2015"))
                .addAvis(new Avis(2, "C'etait vraiment pas bon", "Alexis Andra", "12/06/2015"))
                .addTempsDattente(1, 5)
                .addTempsDattente(2, 10)
                .addTempsDattente(3, 10)
                .addTempsDattente(4, 10)
                .addTempsDattente(5, 5)
                .build();

        PointDeRestauration astree = PointDeRestauration.builder()
                .name("Cafet’U Astrée")
                .prix(3.2)
                .longitude(4.876355)
                .latitude(45.780984)
                .note(3)
                .description(
                        "A Villeurbanne, situé sur le campus de la Doua de l’Université Lyon 1, à l’intérieur du bâtiment Astrée, salle de 90 places.")
                .idPhoto(R.drawable.cafe_astree)
                .addTypePointDeRestauration(PointDeRestauration.TypePointDeRestauration.CAFETERIA)
                .addTypePointDeRestauration(
                        PointDeRestauration.TypePointDeRestauration.RESTAURANT_UNIVERSITAIRE)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.SANS_PORC)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.VEGETARIEN)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.VEGETALIEN)
                .addAvis(
                        new Avis(3, "J'ai beaucoup aimé ce restaurant gastronomique", "Robin Royer",
                                "12/10/2015"))
                .addAvis(new Avis(2, "C'etait vraiment pas bon", "Alexis Andra", "12/09/2015"))
                .menuDujour("Différents sandwichs : thon, poulet, viande hachée.")
                .addTempsDattente(1, 5)
                .addTempsDattente(2, 10)
                .addTempsDattente(3, 15)
                .addTempsDattente(4, 15)
                .addTempsDattente(5, 5)
                .build();

        PointDeRestauration cafetJussieu = PointDeRestauration.builder()
                .name("Cafet’U Jussieu")
                .prix(3.2)
                .longitude(4.876355)
                .latitude(45.780984)
                .note(5)
                .description(
                        "A Villeurbanne face au campus de la Doua de l’université Lyon 1 et de l’INSA.")
                .idPhoto(PointDeRestauration.NO_PHOTO)
                .addTypePointDeRestauration(PointDeRestauration.TypePointDeRestauration.CAFETERIA)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.SANS_PORC)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.VEGETARIEN)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.VEGETALIEN)
                .addAvis(new Avis(3, "J'ai beaucoup aimé ce restaurant gastronomique", "Robin Royer","12/12/2015"))
                .addAvis(new Avis(2, "C'etait vraiment pas bon", "Alexis Andra","06/12/2015"))
                .menuDujour("Différents sandwichs : thon, poulet, viande hachée.")
                .addTempsDattente(1,10)
                .addTempsDattente(2,15)
                .addTempsDattente(3,25)
                .addTempsDattente(4,15)
                .addTempsDattente(5,5)
                .build();

        PointDeRestauration tacos = PointDeRestauration.builder()
                .name("Snack du campus")
                .prix(6)
                .addTempsDattente(1, 15)
                .addTempsDattente(2, 20)
                .addTempsDattente(3, 20)
                .addTempsDattente(4, 15)
                .addTempsDattente(5, 10)
                .note(5)
                .longitude(4.874531)
                .latitude(45.777121)
                .description("Tacos hyper sympa avec snoop dogg")
                .idPhoto(R.drawable.snack_campus)
                .addTypePointDeRestauration(PointDeRestauration.TypePointDeRestauration.FASTFOOD)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.SANS_PORC)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.VEGETALIEN)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.VEGETARIEN)
                .menuDujour("Tacos tous les jours.")
                .addAvis(new Avis(5,
                        "Tout ce qu'on attend d'un bon tacos, à bon prix, et avec des gérants qui ont toujours le sourire et la pêche ! :D",
                        "Pouya Farrahi Far", "10/10/2015"))
                .addAvis(new Avis(5, "Très bon, pas cher, garant de la survie dans un monde hostile peuplé de placards vides, de la flemme du vendredi soir, ou pire encore ...", "Antoine Caron", "10/11/2015"))
                .build();

        PointDeRestauration kebabMosaic = PointDeRestauration.builder()
            .name("Sandwicherie Mosaic")
            .prix(5)
            .addTempsDattente(1, 10)
            .addTempsDattente(2, 16)
            .addTempsDattente(3, 18)
            .addTempsDattente(4, 10)
            .addTempsDattente(5, 12)
            .note(4)
            .longitude(4.873662)
            .latitude(45.778761)
            .description("Le crème du kebab autour du campus.")
            .idPhoto(R.drawable.mosaik_kebab)
            .addTypePointDeRestauration(PointDeRestauration.TypePointDeRestauration.FASTFOOD)
            .addTypeDeRegime(PointDeRestauration.TypeRegime.SANS_PORC)
            .addTypeDeRegime(PointDeRestauration.TypeRegime.VEGETARIEN)
            .menuDujour("Kebab frite.")
            .addAvis(new Avis(4,
                    "Halil vous reçoit toujours avec le sourire ses employés sont super sympas\n" +
                            "Au contraire de beaucoup de kebabs qui se le font livrer, le cone de viande est préparé sur place par halil\n" +
                            "Le restaurant est sympa et on ne peut plus propre\n" +
                            "et à l'arrivée le kebab est tres bon",
                    "Ahmid D.", "18/03/2008"))
            .build();

        PointDeRestauration ninkasi = PointDeRestauration.builder()
                .name("Ninkasi La Doua")
                .prix(5)
                .addTempsDattente(1, 26)
                .addTempsDattente(2, 28)
                .addTempsDattente(3, 32)
                .addTempsDattente(4, 23)
                .addTempsDattente(5, 56)
                .note(4)
                .longitude(4.872987)
                .latitude(45.778937)
                .description("Le tout nouvel Ninkasi près du campus de La Doua ! Les plaisirs lyonnais pour les étudiants.")
                .idPhoto(R.drawable.ninkasi)
                .addTypePointDeRestauration(PointDeRestauration.TypePointDeRestauration.FASTFOOD)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.SANS_PORC)
                .addTypeDeRegime(PointDeRestauration.TypeRegime.VEGETARIEN)
                .menuDujour("Burger et bières lyonnaises.")
                .addAvis(new Avis(3,
                        "Serveur très agréable, ambiance chaleureuse et très bon burger accompagné d'un succulent coleslow. Cependant, les frites sont presque immangeables et il n'y avait pas assez de monnaie en caisse pour payer en liquide !",
                        "Sacha Maurin", "18/12/2015"))
                .addAvis(new Avis(4,
                        "Dans un cadre agréable, déguster un burger composé soit-même et servi rapidement est un vrai bonheur. Les menus sont complétés par des sundaes aux divers coulis et autres \"toppings\", mais aussi et surtout par les bières brassées sur place, pour lesquelles la chaîne a été primées.\n" +
                                "Toutefois, le service peut encore progresser, et on sent un certain manque d'expérience du personnel (ouverture récente), qui espérons-le s'améliora avec le temps.\n" +
                                "Un conseil : pensez à bien préciser la cuisson des viandes aux serveurs !",
                        "Guillaume CHAU", "11/10/2015"))
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
        preference.add(tacos);
        preference.add(kebabMosaic);
        preference.add(ninkasi);


        return preference;
    }

    public static void addUtilisateur(Utilisateur user) {
        utilisateurs.put(user.getEmail(), user);
    }

    public static List<PointDeRestauration> getRestaurantLaDoua() {
        return pointDeRestaurations;
    }


}
