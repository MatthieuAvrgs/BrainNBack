package fr.utt.if26.brainn_back.Game;

import java.util.Random;

public class PetitCarre {
    private int position;
    private boolean[] reponses;

    public PetitCarre(int position) {
        Random rand = new Random();
        this.position  = rand.nextInt(8) + 1;
        this.reponses = null;
    }
}