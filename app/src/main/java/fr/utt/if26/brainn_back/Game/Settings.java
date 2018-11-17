package fr.utt.if26.brainn_back.Game;

public class Settings {
    private int niveau = 1;
    private float temps = 3000;

    public Settings(int niveau, float temps) {
        this.niveau = niveau;
        this.temps = temps;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public float getTemps() {
        return temps;
    }

    public void setTemps(float temps) {
        this.temps = temps;
    }
}
