package com.hexabinome.saladetomateoignon;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.GsonBuilder;
import com.hexabinome.saladetomateoignon.modele.Utilisateur;

/**
 * Created by haidara on 21/12/15.
 */
public class PrefUtils {

    public static final String UTILISATEUR = "__UTILISATEUR__" ;
    public static final String PREFS_FIRST_LAUNCH = "__FIRST__";

    public static void sauvegardeUtilisateur(Activity activity, Utilisateur value) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(activity.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String userInstring = new GsonBuilder().disableHtmlEscaping().create().toJson(value);

        editor.putString(UTILISATEUR,userInstring);
        editor.commit();
    }

    public static Utilisateur recupererUtilisateur(Activity activity){
        SharedPreferences preferences = activity.getSharedPreferences(activity.getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        String utilisateurString = preferences.getString(UTILISATEUR,null);
        if(utilisateurString == null){
            return null;
        } else {
            return new GsonBuilder().disableHtmlEscaping().create().fromJson(utilisateurString,Utilisateur.class);
        }
    }


    public static void saveBooleanToPrefs(Activity activity, String key, Boolean value) {
        SharedPreferences preferences = activity.getSharedPreferences(activity.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean(key, value);
        editor.commit();
    }

    public static String getStringFromPrefs(Activity activity, String key, String defaultValue) {
        SharedPreferences preferences = activity.getSharedPreferences(activity.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        return preferences.getString(key,defaultValue);
    }

    public static boolean getBooleanFromPrefs(Activity activity,String key, Boolean defaultValue){
        SharedPreferences preferences = activity.getSharedPreferences(activity.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        return preferences.getBoolean(key, defaultValue);
    }

    public static void resetPrefs(Activity activity){
        SharedPreferences preferences = activity.getSharedPreferences(activity.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear().commit();
    }
}
