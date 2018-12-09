package fr.utt.if26.brainn_back.Game;

import android.speech.tts.TextToSpeech;

import java.util.Locale;
import java.util.Random;

public class PetitCarre {
    private int position;
    private String son;
    private boolean[] reponses;
    private String[]lettres;

    public String getSon() {
        return son;
    }

    public void setSon(String son) {
        this.son = son;
    }

    public PetitCarre(PetitCarre[] listeCarres, int niveau) {
        this.position  = randomPosition(listeCarres, niveau);
        this.son = randomSon();
        this.reponses = null;
    }

    public int getPosition() {
        return position;
    }

    public String randomSon(){
        lettres = new String[]{"a","b","c","d","e","f","g","h","i"};
        Random rand = new Random();
        int nombre = rand.nextInt(8) + 1;
        return lettres[nombre];
    }

    public int randomPosition (PetitCarre[] listeCarres, int niveau){
        Random rand = new Random();
        int positionCarre;

        int nbElements=0;
        for(int indice=0;indice<listeCarres.length;indice++){
            if(listeCarres[indice] instanceof PetitCarre){
                nbElements++;
            }
        }
        if(nbElements >= niveau){
            //chance représente la proba que le carré apparaisse à la meme position | proba = 1/ (chance)
            int chance = 4; // 1/3
            int proba = rand.nextInt(chance-1) + 1;
            switch (proba){
                case 1:
                    return listeCarres[nbElements-niveau].getPosition();
                default:
                    return rand.nextInt(8) + 1;
            }
        } else{
            positionCarre = rand.nextInt(8) + 1;
            return positionCarre;
        }
    }

    public void setReponses (boolean[] tableauReponses){
        this.reponses=tableauReponses;
    }

    public boolean[] getReponses (){
        return this.reponses;
    }
}
