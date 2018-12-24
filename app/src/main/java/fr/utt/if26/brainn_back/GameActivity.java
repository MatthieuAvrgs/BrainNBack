package fr.utt.if26.brainn_back;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import fr.utt.if26.brainn_back.Game.Partie;
import fr.utt.if26.brainn_back.Game.PetitCarre;
import fr.utt.if26.brainn_back.Game.Settings;
import fr.utt.if26.brainn_back.Game.Statistique;

import static java.lang.Thread.sleep;

public class GameActivity extends AppCompatActivity implements
        TextToSpeech.OnInitListener
{
    AlertDialog.Builder builder;

    private PersistancePartie bdd = new PersistancePartie(this);

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
    private Map <Integer,String> sonMap;
    private Map <Integer,Integer> couleurMap;

    private TextToSpeech tts;

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
        final Button boutonCouleur;

        settingsPartie = new Settings(this);
        partie = new Partie(settingsPartie);

        boutonSon = findViewById(R.id.boutonSon);
        boutonPosition = findViewById(R.id.boutonPosition);
        boutonCouleur = findViewById(R.id.boutonCouleur);

        sonMap = new HashMap <Integer,String> ();
        sonMap.put(1,"a");
        sonMap.put(2,"b");
        sonMap.put(3,"c");
        sonMap.put(4,"d");
        sonMap.put(5,"e");
        sonMap.put(6,"f");
        sonMap.put(7,"g");
        sonMap.put(8,"h");
        sonMap.put(9,"i");

        couleurMap = new HashMap <Integer,Integer> ();
        couleurMap.put(1,R.color.marron); //marron
        couleurMap.put(2,R.color.beigeFonce); //beige foncé
        couleurMap.put(3,R.color.bleuFonce); //bleu foncé
        couleurMap.put(4,R.color.rouge); //rouge
        couleurMap.put(5,R.color.fushia); //fushia
        couleurMap.put(6,R.color.bleuCiel); //bleu ciel
        couleurMap.put(7,R.color.vertPomme); //vert pomme
        couleurMap.put(8,R.color.orange); //orange
        couleurMap.put(9,R.color.jaune); //jaune

        //timing
        final  Handler mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //mettre à jour affichage
                updateEcran(msg);
            }
        };

        TextToSpeech.OnInitListener ttsListener =
                new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status==TextToSpeech.SUCCESS){
                            tts.setLanguage(Locale.getDefault());
                        }

                    }
                };
         tts = new TextToSpeech(this, ttsListener);


        final boolean reponses[] = new boolean[] {false, false, false};


        boutonPosition.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                reponses[0]=true;
                }
        });

        if (settingsPartie.isSon()==true){
            boutonSon.setVisibility(View.VISIBLE);
            boutonSon.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Code here executes on main thread after user presses button
                    reponses[1]=true;
                }
            });
        }else{
            boutonSon.setVisibility(View.GONE);
        }

        if (settingsPartie.isCouleur()==true){
            boutonCouleur.setVisibility(View.VISIBLE);
            boutonCouleur.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Code here executes on main thread after user presses button
                    reponses[2]=true;
                }
            });
        }else{
            boutonCouleur.setVisibility(View.GONE);
        }



        Thread moteurJeu = new Thread(new Runnable() {
            public void run(){
                int couleurCarre = R.color.marron;
                try {
                    Thread.sleep(2000);
                    //on parcourt les petitcarres du tableau
                    for(int index = 0; index<(partie.getSettingPartie().getNbreItems()); index++) {
                        reponses[0] = false;
                        reponses[1] = false;
                        reponses[2] = false;

                        if (partie.getSettingPartie().isCouleur()==true){
                            couleurCarre = couleurMap.get(partie.getListeCarres()[index].getCouleur());
                        }
                        Message afficherCarre = mHandler.obtainMessage(1,
                                partie.getListeCarres()[index].getPosition(),couleurCarre);
                        //envoie message d'afficher le carre dans le Thread UI
                        mHandler.sendMessage(afficherCarre);
                        tts.speak(sonMap.get(partie.getListeCarres()[index].getSon()), TextToSpeech.QUEUE_FLUSH, null);


                        Message desafficherCarre = mHandler.obtainMessage(2,
                                partie.getListeCarres()[index].getPosition(), 0);
                        //envoie message de désafficher ce même carre dans le thread UI après 1 seconde
                        mHandler.sendMessageDelayed(desafficherCarre, 1000);

                        //Temps entre 2 carrés
                        Thread.sleep(settingsPartie.getTemps());

                        //creation d'une nouvelle variable pour sauvegarder les reponses du joueur
                        partie.getListeCarres()[index].setReponses(new boolean[]{reponses[0], reponses[1], reponses[2]});
                    }
                    //fin de la partie
                    partie.calculerScore();
                    //save in bdd
                    bdd.addPartie(partie);
                    //quand les carrés ont été affichés, on affiche la pop up de fin de partie
                    Message openDialog = mHandler.obtainMessage(3,
                           partie.getScorePoint(), 0);
                    //envoie message d'afficher le carre dans le Thread UI
                    mHandler.sendMessage(openDialog);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        moteurJeu.start();
        TextView textNiveau = (TextView) findViewById(R.id.text_niveau);
        textNiveau.setText("Niveau : "+new Settings().getNiveau());
    }

//Map<String,Statistique> statistiques
    private void afficherDialogue(int score){
        List <Statistique> productList = this.partie.getStatistiquesPartie();
        LayoutInflater dialogInflater = LayoutInflater.from(this);
        final View allListsView = dialogInflater.inflate(R.layout.dialog_score, null);
        //changement du titre
        TextView titreDialog = (TextView) allListsView.findViewById(R.id.dialog_titre);
        String sourceString = "<b>Score : " + score +"%</b> ";
        titreDialog.setText(Html.fromHtml(sourceString));

        //listView
        ListView lview = (ListView) allListsView.findViewById(R.id.stats_list_view);
        StatistiquesAdapter adapter = new StatistiquesAdapter(this, productList);
        lview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        AlertDialog.Builder listsDialog = new AlertDialog.Builder(this);
        listsDialog.setView(allListsView);
        listsDialog.setCancelable(false)
                .setNegativeButton("Menu", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent mainActivity = new Intent (GameActivity.this, MainActivity.class);
                        startActivity(mainActivity);
                        dialog.dismiss();
                    }
                }).setPositiveButton("Rejouer", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                startActivity(new Intent(GameActivity.this, GameActivity.class));
                dialog.cancel();
            }
        });
        AlertDialog alertD = listsDialog.create();
        alertD.show();
    }


    public void updateEcran(Message msg) {
        //cas 1 : affichage du carre
        if(msg.what==1){
             listeVuesCarre.get(msg.arg1).setBackgroundColor(getResources().getColor(msg.arg2));
        }
        //cas 2 : desaffichage
        else  if(msg.what==2){
            listeVuesCarre.get(msg.arg1).setBackgroundColor(getResources().getColor(R.color.white));
        }
        else  if(msg.what==3){
            afficherDialogue(msg.arg1);
        }
    }

    @Override
    public void onInit(int status) {

    }
}
