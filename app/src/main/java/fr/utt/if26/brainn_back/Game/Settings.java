package fr.utt.if26.brainn_back.Game;

public class Settings {
    private int niveau = 1;
    private long temps = 3000;
    private int nbreItems = 10;

    public Settings(int niveau, long temps, int nbreItems) {
        this.niveau = niveau;
        this.temps = temps;
        this.nbreItems = nbreItems;
    }

    public Settings() {
    }

    public int getNbreItems() {
        return nbreItems;
    }



    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public long getTemps() {
        return temps;
    }

    public void setTemps(long temps) {
        this.temps = temps;
    }
}
