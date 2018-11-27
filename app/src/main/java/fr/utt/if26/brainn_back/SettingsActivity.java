package fr.utt.if26.brainn_back;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        Spinner dropdown = findViewById(R.id.spinner_niveau_jeu_settings);
        String[] items = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        //creation de l'adaptateur qui permet de savoir comment les items vont etre display
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
        // set niveau settings la valeur ci dessous
        // dropdown.getSelectedItem().toString();

    }
}
