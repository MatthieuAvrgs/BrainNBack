package fr.utt.if26.brainn_back;

import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class BDDScoreListe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bddscore_liste);

        ListView listeParties = (ListView) findViewById(R.id.liste_parties);
        PersistancePartie bdd = new PersistancePartie(this);

        //test avec des valeurs prédéfinies pour une partie
        bdd.initData();
        Cursor cursorListe = bdd.getListeParties();
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.layout_une_partie,
                cursorListe, new String[]{"date", "niveau", "couleur","son",
                "score"}, new int[]{R.id.date, R.id.niveau,R.id.couleur,R.id.son, R.id.score},0);
        listeParties.setAdapter(adapter);
    }
}
