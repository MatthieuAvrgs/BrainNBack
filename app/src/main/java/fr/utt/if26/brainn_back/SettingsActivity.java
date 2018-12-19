package fr.utt.if26.brainn_back;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;



public class SettingsActivity extends PreferenceActivity {
    public final static String NIVEAU_ENREGISTRE = "niveau";
    public final static String AVEC_SON = "son";
    public final static String AVEC_COULEUR = "couleur";
    public final static String NB_CARRES_ENREGISTRE = "nbCarres";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String niveauS = SP.getString("niveau","1");
        String tempsS = SP.getString("temps","2000");
        String nbCarres = SP.getString("nbCarres","10");
        boolean isSon = SP.getBoolean("son", false);
        boolean isCouleur = SP.getBoolean("couleur",false);
        //int niveau = Integer.parseInt(niveauS);
        //int temps = Integer.parseInt(tempsS);
        //int nbreItems = Integer.parseInt(nbCarres);

        /*SharedPreferences.Editor prefs = PreferenceManager.getDefaultSharedPreferences(this).edit();
        prefs.putBoolean("son",isSon);
        prefs.putBoolean("couleur",isCouleur);
        //prefs.commit();*/
    }

    public static class MyPreferenceFragment extends PreferenceFragment
    {
        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preference);


        }
    }

}

//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//        SharedPreferences.Editor editor = preferences.edit();
//
//        editor.putInt(NIVEAU_ENREGISTRE, 1);
//        editor.commit();
//
//
//        Spinner dropdown = findViewById(R.id.spinner_niveau_jeu_settings);
//        String[] items = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"};
//        //creation de l'adaptateur qui permet de savoir comment les items vont etre display
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//        //set the spinners adapter to the previously created one.
//        dropdown.setAdapter(adapter);
//        // set niveau settings la valeur ci dessous
//        // dropdown.getSelectedItem().toString();



