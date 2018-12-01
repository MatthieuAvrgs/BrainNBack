package fr.utt.if26.brainn_back.Game;

public class Statistique {
    private int bonnesReponses;
    private int mauvaisesReponses;
    private int oublies;

    //constructeur, initialisation des attributs
    public Statistique(){
        this.bonnesReponses=0;
        this.mauvaisesReponses=0;
        this.oublies=0;
    }

    //compteur de réponses
    public void traiterReponseStatistique(int reponse){
        switch (reponse){
            //bonne réponse
            case 1:
                this.bonnesReponses++;
                break;
            //oublie d'appuyer de la part de l'utilisateur
            case 2:
                this.oublies++;
                break;
            //mauvaise réponse de l'utilisateur (il ne fallait pas appuyer, il l'a fait
            case 3:
                this.mauvaisesReponses++;
                break;
            default:
                break;
        }
    }

    public int getBonnesReponses(){
        return this.bonnesReponses;
    }

    public int getMauvaisesReponses(){
        return this.mauvaisesReponses;
    }

    public int getOublies(){
        return this.oublies;
    }

}
