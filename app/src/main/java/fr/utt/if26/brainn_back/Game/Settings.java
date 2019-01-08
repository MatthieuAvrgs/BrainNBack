package fr.utt.if26.brainn_back.Game;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Settings {
    private int niveau = 1;
    private long temps = 3000;
    private int nbreItems = 10;
    private boolean couleur = false;
    private boolean son = false;

    public Settings(Context c) {
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(c);
        boolean isSon = SP.getBoolean("son", false);
        boolean isCouleur = SP.getBoolean("couleur",false);
        String niveauS = SP.getString("niveau","1");
        String tempsS = SP.getString("temps","3000");
        String nbCarres = SP.getString("nbCarres","10");
        this.niveau = Integer.parseInt(niveauS);
        this.temps = Integer.parseInt(tempsS);
        this.nbreItems = Integer.parseInt(nbCarres);
        this.couleur = isCouleur;
        this.son = isSon;
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

    public boolean isCouleur() {
        return couleur;
    }

    public void setCouleur(boolean couleur) {
        this.couleur = couleur;
    }

    public boolean isSon() {
        return son;
    }

    public void setSon(boolean son) {
        this.son = son;
    }
}
