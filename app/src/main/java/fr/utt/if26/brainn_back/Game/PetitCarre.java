package fr.utt.if26.brainn_back.Game;

import android.speech.tts.TextToSpeech;

import java.util.Locale;
import java.util.Random;

public class PetitCarre {
    private int position;
    private int son;
    private int couleur;
    private boolean[] reponses;
    private String[]lettres;

    public PetitCarre(PetitCarre[] listeCarres, int niveau) {
        this.position  = randomIndex(listeCarres, niveau, "position");
        this.son = randomIndex(listeCarres, niveau, "son");
        this.couleur = randomIndex(listeCarres, niveau, "couleur");
        this.reponses = null;
    }

    public int getPosition() {
        return position;
    }


    public int randomIndex (PetitCarre[] listeCarres, int niveau, String caseToGet){
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
                    switch (caseToGet){
                        case "position":
                            return getNPosition(listeCarres, nbElements, niveau);
                        case "son":
                            return getNSon(listeCarres, nbElements, niveau);
                        case "couleur":
                            return getNCouleur(listeCarres, nbElements, niveau);
                        default:
                            return getNPosition(listeCarres, nbElements, niveau);
                    }
                default:
                    return rand.nextInt(8) + 1;
            }
        } else{
            positionCarre = rand.nextInt(8) + 1;
            return positionCarre;
        }
    }

    public int getNPosition(PetitCarre[] listeCarres, int nbElements, int niveau){
        return listeCarres[nbElements-niveau].getPosition();
    }
    public int getNSon(PetitCarre[] listeCarres, int nbElements, int niveau){
        return listeCarres[nbElements-niveau].getSon();
    }
    public int getNCouleur(PetitCarre[] listeCarres, int nbElements, int niveau){
        return listeCarres[nbElements-niveau].getCouleur();
    }

    public int getSon() {
        return son;
    }

    public void setSon(int son) {
        this.son = son;
    }

    public int getCouleur() {
        return couleur;
    }

    public void setCouleur(int couleur) {
        this.couleur = son;
    }

    public void setReponses (boolean[] tableauReponses){
        this.reponses=tableauReponses;
    }

    public boolean[] getReponses (){
        return this.reponses;
    }
}
