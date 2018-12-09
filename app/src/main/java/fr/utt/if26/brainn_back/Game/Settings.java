package fr.utt.if26.brainn_back.Game;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Settings {
    private int niveau = 1;
    private long temps = 2500;
    private int nbreItems = 4;
    private boolean couleur = false;
    private boolean son = true;

    public Settings(Context c) {
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(c);
        boolean isSon = SP.getBoolean("son", false);
        boolean isCouleur = SP.getBoolean("couleur",false);
        String niveauS = SP.getString("niveau","null");
        String tempsS = SP.getString("temps","null");
        this.niveau = Integer.parseInt(niveauS);
        this.temps = Integer.parseInt(tempsS);
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
