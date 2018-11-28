package fr.utt.if26.brainn_back;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
/* test message */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bouton qui permet de commencer le jeu
        Button btn = (Button)findViewById(R.id.bouton_commencer_jeu);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GameActivity.class));
            }
        });

        //get the spinner
        Spinner dropdown = findViewById(R.id.spinner_niveau_jeu);
        String[] items = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        //creation de l'adaptateur qui permet de savoir comment les items vont etre display
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
        // set niveau settings avec cette valeur dropdown.getSelectedItem().toString();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.parametres: // correspond à la définition dans le fichier XML
                goToParametres(); // appel à une méthode annexe
                return true;
            //case R.id.a_propos: // correspond à la définition dans le fichier XML
            //    goToRegles(); // appel à une méthode annexe
            //    return true;
            default:
                return true;
        }
    }

    private void goToParametres() {
        Toast.makeText(this, "Paramètres", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

}
