package fr.utt.if26.brainn_back.Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Partie {
    private int scorePoint;
    private Map <String,Statistique> statistiquesPartie;
    private ArrayList<int[]> statistiques;
    private PetitCarre[] listeCarres;
    private Settings settingPartie;

    public Partie(Settings settingPartie) {
        this.settingPartie = settingPartie;
        this.listeCarres = new PetitCarre[settingPartie.getNbreItems()];
        for(int i=0; i<settingPartie.getNbreItems(); i++){
            listeCarres[i]=new PetitCarre(this.listeCarres, settingPartie.getNiveau());
        }
        this.statistiquesPartie=new HashMap<String,Statistique>();
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

    public void calculerScore(){
        this.obtenirStatistiquesPartie();
        this.compterLesPoints();
    }

    private void compterLesPoints(){
        Iterator<Map.Entry<String,Statistique>> it = this.statistiquesPartie.entrySet().iterator();
        int totalBonnesReponses=0;
        int totalMauvaisesReponses=0;
        int totalOublies=0;
        while (it.hasNext()) {
            Map.Entry<String,Statistique> pair = it.next();
            totalBonnesReponses += pair.getValue().getBonnesReponses();
            totalMauvaisesReponses += pair.getValue().getMauvaisesReponses();
            totalOublies += pair.getValue().getOublies();
        }
        int denominateur=(totalBonnesReponses+totalOublies)*2;
        denominateur=denominateur==0?1:denominateur;
        this.scorePoint = (totalBonnesReponses*2-totalMauvaisesReponses)*100/(denominateur);
        if(this.scorePoint<0){
            this.scorePoint=0;
        }
    }

    private void obtenirStatistiquesPartie(){
        //initialisation de la hashmap pour les statistiques de la partie
        this.statistiquesPartie.put("position",new Statistique());
        this.statistiquesPartie.put("son",new Statistique());
        this.statistiquesPartie.put("couleur",new Statistique());

        int niveau = this.settingPartie.getNiveau();

        // initialisation des indices de reponse
        int reponsePosition = 0;
        //int reponseSon = 0;
        //int reponseCouleur = 0;

        //boucle qui permet d'itérer sur chaque PetitCarre
        for(int index = 0; index<(getSettingPartie().getNbreItems()); index++) {
            //si indew>=niveau signifie qu'une réponse de l'utilisateur est possible
            if(index>=niveau){
                //on donne en paramètre la position du carre actuel et celui N fois avant, on donne aussi la réponse du joueur
                reponsePosition = this.traiterReponseJoueur(this.listeCarres[index].getPosition(),this.listeCarres[index-niveau].getPosition(),this.listeCarres[index].getReponses()[0]);
                //on appelle une méthode de la classe Score qui permet de traiter la réponse obtenue par la méthode traiterReponseJoueur
                this.statistiquesPartie.get("position").traiterReponseStatistique(reponsePosition);
                //TODO add traitement reponse pour son et couleur
            }
        }
    }

    private int traiterReponseJoueur(int infoCarreEnCours, int infoNCarreAvant, boolean reponseJoueur){
        //bonne réponse on retourne 1
        if(infoCarreEnCours==infoNCarreAvant && reponseJoueur == true){
            return 1;
        }
        //il a oublié de cliquer on retourne 2
        else if(infoCarreEnCours==infoNCarreAvant && reponseJoueur == false){
            return 2;
        }
        //si joueur a appuyer et il ne le fallait pas on retourne 3
        else if (infoCarreEnCours!=infoNCarreAvant && reponseJoueur == true){
            return 3;
        }
        //cas par defaut
        return 0;
    }

    public Map <String,Statistique> getStatistiquesPartie() { return this.statistiquesPartie; }
    public int getScorePoint(){
        return this.scorePoint;
    }
}
