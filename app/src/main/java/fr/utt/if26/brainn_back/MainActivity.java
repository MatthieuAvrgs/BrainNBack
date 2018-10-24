package fr.utt.if26.brainn_back;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
/* test message */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent secondeActivite = new Intent(MainActivity.this, GameActivity.class);
        startActivity(secondeActivite);
    }
}
