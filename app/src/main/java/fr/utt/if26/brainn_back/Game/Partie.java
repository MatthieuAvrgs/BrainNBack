package fr.utt.if26.brainn_back.Game;

public class Partie {
    private int score;
    private PetitCarre[] listeCarres;
    private int[] stat;
    private Settings settingPartie;

    public Partie(int score, PetitCarre[] listeCarres, int[] stat, Settings settingPartie) {
        this.score = score;
        this.listeCarres = listeCarres;
        this.stat = stat;
        this.settingPartie = settingPartie;
    }

    public void startGame(){

    }
}
