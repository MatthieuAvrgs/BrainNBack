package fr.utt.if26.brainn_back;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import fr.utt.if26.brainn_back.Game.Partie;
import fr.utt.if26.brainn_back.Game.PetitCarre;
import fr.utt.if26.brainn_back.Game.Settings;

import static java.lang.Thread.sleep;

public class GameActivity extends AppCompatActivity {

    View carre1;
    View carre2;
    View carre3;
    View carre4;
    View carre5;
    View carre6;
    View carre7;
    View carre8;
    View carre9;
    private Thread waiting = null;

    Settings settingsPartie;
    Partie partie;
    List<View> listeVuesCarre;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Déclaration des composants

        carre1 = (View) findViewById(R.id.carre1);
        carre2 = (View) findViewById(R.id.carre2);
        carre3 = (View) findViewById(R.id.carre3);
        carre4 = (View) findViewById(R.id.carre4);
        carre5 = (View) findViewById(R.id.carre5);
        carre6 = (View) findViewById(R.id.carre6);
        carre7 = (View) findViewById(R.id.carre7);
        carre8 = (View) findViewById(R.id.carre8);
        carre9 = (View) findViewById(R.id.carre9);

        listeVuesCarre = new ArrayList<View>();
        listeVuesCarre.add(carre1);
        listeVuesCarre.add(carre2);
        listeVuesCarre.add(carre3);
        listeVuesCarre.add(carre4);
        listeVuesCarre.add(carre5);
        listeVuesCarre.add(carre6);
        listeVuesCarre.add(carre7);
        listeVuesCarre.add(carre8);
        listeVuesCarre.add(carre9);
        final Button boutonSon;
        final Button boutonPosition;


        // int niveau = get niveau dans la bbd
        // long temps = get temps dans la bdd
        // int nbreItems = get nbrItems dans la bdd
        // settingsPartie = Settings(niveau, temps, nbreItems)
        settingsPartie = new Settings();
        partie = new Partie(new Settings());

        //timing
        final  Handler mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //mettre à jour affichage
                updateEcran(msg);
            }
        };

        boutonSon = findViewById(R.id.boutonSon);
        boutonPosition = findViewById(R.id.boutonPosition);

        final boolean reponses[] = new boolean[] {false, false, false};
        //reponses[0] : position
        //reponses[1] : son
        //reponses[2] : couleur
        boutonPosition.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                reponses[0]=true;
            }
        });
        boutonSon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                reponses[1]=true;
            }
        });


        new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(settingsPartie.getTemps());
                        //on parcourt les petitcarres du tableau
                        for(index = 0; index<(partie.getSettingPartie().getNbreItems()); index++) {
                            reponses[0] = false;
                            reponses[1] = false;
                            reponses[2] = false;
                            Message afficherCarre = mHandler.obtainMessage(1,
                                    partie.getListeCarres()[index].getPosition(), 0);

                            //envoie message d'afficher le carre dans le Thread UI
                            mHandler.sendMessage(afficherCarre);

                            Message desafficherCarre = mHandler.obtainMessage(2,
                                    partie.getListeCarres()[index].getPosition(), 0);
                            //envoie message de désafficher ce même carre dans le thread UI après 1 seconde
                            mHandler.sendMessageDelayed(desafficherCarre, 1000);

                            //Temps entre 2 carrés
                            Thread.sleep(settingsPartie.getTemps());

                            //creation d'une nouvelle variable pour sauvegarder les reponses du joueur
                            partie.getListeCarres()[index].setReponses(new boolean[]{reponses[0], reponses[1], reponses[2]});
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally{
                        partie.calculerScore();
                    }
                }
            }).start();

        }





    public void updateEcran(Message msg) {
        //cas 1 : affichage du carre
        if(msg.what==1){
            listeVuesCarre.get(msg.arg1).setBackgroundColor(getResources().getColor(R.color.marron));
        }
        //cas 2 : desaffichage
        else {
            listeVuesCarre.get(msg.arg1).setBackgroundColor(getResources().getColor(R.color.white));
        }

    }
}
