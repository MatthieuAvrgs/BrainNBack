package fr.utt.if26.brainn_back;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Locale;

import fr.utt.if26.brainn_back.Game.Settings;


public class MainActivity extends AppCompatActivity {
/* test message */
    private PersistancePartie bdd = new PersistancePartie(this);
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView meilleurScore = (TextView) findViewById(R.id.meilleur_score);
        TextView meilleurNiveau = (TextView) findViewById(R.id.meilleur_niveau);
        TextView nbParties = (TextView) findViewById(R.id.nb_parties);

        meilleurScore.setText(String.valueOf(bdd.getMeilleurScore())+"%");
        meilleurNiveau.setText(String.valueOf(bdd.getMeilleurNiveau()));
        nbParties.setText(String.valueOf(bdd.getNombrePartiesJouees()));


        //bouton qui permet de commencer le jeu
        Button btn_commencer_jeu = (Button)findViewById(R.id.bouton_commencer_jeu);

        btn_commencer_jeu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), GameActivity.class);
                startActivity(intent);
            }
        });

        //bouton pour afficher l'historique
        Button btn_historique = (Button)findViewById(R.id.bouton_afficher_historique);
        btn_historique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BDDScoreListe.class));
            }
        });

        TextView textNiveau = (TextView) findViewById(R.id.text_niveau);
        textNiveau.setText("Niveau : "+new Settings().getNiveau());
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
