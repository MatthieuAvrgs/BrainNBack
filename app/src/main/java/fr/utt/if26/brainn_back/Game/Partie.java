package fr.utt.if26.brainn_back.Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Partie {
    private int scorePoint;
    private ArrayList <Statistique> statistiquesPartie;
    private ArrayList<int[]> statistiques;
    private PetitCarre[] listeCarres;
    private Settings settingPartie;

    public Partie(Settings settingPartie) {
        this.settingPartie = settingPartie;
        this.listeCarres = new PetitCarre[settingPartie.getNbreItems()];
        for(int i=0; i<settingPartie.getNbreItems(); i++){
            listeCarres[i]=new PetitCarre(this.listeCarres, settingPartie.getNiveau());
        }
        this.statistiquesPartie=new ArrayList <Statistique>();
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
        Iterator<Statistique> it = this.statistiquesPartie.iterator();
        int totalBonnesReponses=0;
        int totalMauvaisesReponses=0;
        int totalOublies=0;
        while (it.hasNext()) {
            Statistique stat = it.next();
            totalBonnesReponses += stat.getBonnesReponses();
            totalMauvaisesReponses += stat.getMauvaisesReponses();
            totalOublies += stat.getOublies();
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
        Map <String, Statistique> localStats = new HashMap<String, Statistique>();
        localStats.put("position",new Statistique());
        if(settingPartie.isSon()==true){
            localStats.put("son",new Statistique());
        }
        if(settingPartie.isCouleur()==true){
            localStats.put("couleur",new Statistique());
        }

        int niveau = this.settingPartie.getNiveau();

        // initialisation des indices de reponse
        int reponsePosition = 0;
        int reponseSon = 0;
        int reponseCouleur = 0;

        //boucle qui permet d'itérer sur chaque PetitCarre
        for(int index = 0; index<(getSettingPartie().getNbreItems()); index++) {
            //si indew>=niveau signifie qu'une réponse de l'utilisateur est possible
            if(index>=niveau){
                //position
                //on donne en paramètre la position du carre actuel et celui N fois avant, on donne aussi la réponse du joueur
                reponsePosition = this.traiterReponseJoueur(this.listeCarres[index].getPosition(),this.listeCarres[index-niveau].getPosition(),this.listeCarres[index].getReponses()[0]);
                //on appelle une méthode de la classe Score qui permet de traiter la réponse obtenue par la méthode traiterReponseJoueur
                localStats.get("position").traiterReponseStatistique(reponsePosition);

                //son
                if(settingPartie.isSon()==true){
                    reponseSon = this.traiterReponseJoueur(this.listeCarres[index].getSon(),this.listeCarres[index-niveau].getSon(),this.listeCarres[index].getReponses()[1]);
                    localStats.get("son").traiterReponseStatistique(reponseSon);
                }
                //couleur
                if(settingPartie.isCouleur()==true){
                    reponseCouleur = this.traiterReponseJoueur(this.listeCarres[index].getCouleur(),this.listeCarres[index-niveau].getCouleur(),this.listeCarres[index].getReponses()[2]);
                    localStats.get("couleur").traiterReponseStatistique(reponseCouleur);
                }
            }
        }
        Iterator<Map.Entry<String, Statistique>> it3 = localStats.entrySet().iterator();
        while (it3.hasNext()) {
            Map.Entry<String, Statistique> pair = it3.next();
            pair.getValue().setTitre(pair.getKey());
            this.statistiquesPartie.add(pair.getValue());
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

    public List <Statistique> getStatistiquesPartie() { return this.statistiquesPartie; }
    public int getScorePoint(){
        return this.scorePoint;
    }
}
