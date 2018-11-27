package fr.utt.if26.brainn_back.Game;

public class Partie {
    private int score;
    private PetitCarre[] listeCarres;
    private int[] stat;
    private Settings settingPartie;

    public Partie(Settings settingPartie) {
        this.settingPartie = settingPartie;
        this.listeCarres = new PetitCarre[settingPartie.getNbreItems()];
        for(int i=0; i<settingPartie.getNbreItems(); i++){
            listeCarres[i]=new PetitCarre(this.listeCarres, settingPartie.getNiveau());
        }
    }

    public PetitCarre[] getListeCarres() {
        return listeCarres;
    }

    public void setListeCarres(PetitCarre[] listeCarres) {
        this.listeCarres = listeCarres;
    }

    public Settings getSettingPartie() {
        return settingPartie;
    }

    public void startGame(){

    }
}
