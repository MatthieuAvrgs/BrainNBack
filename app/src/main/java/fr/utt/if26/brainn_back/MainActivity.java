package fr.utt.if26.brainn_back;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


public class MainActivity extends AppCompatActivity {
/* test message */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent secondeActivite = new Intent(MainActivity.this, GameActivity.class);
        startActivity(secondeActivite);

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
        String[] items = new String[]{"1", "2", "3"};
        //creation de l'adaptateur qui permet de savoir comment les items vont etre display
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
    }
}
