package models;

public class CompteEpargne extends Compte {

    public static final double TAUX_INTERET = 6;

    public CompteEpargne() {
        super();
    }

    public CompteEpargne(double solde) {
        super(solde);
    }

    public void calculInteret(){
        solde += solde * (TAUX_INTERET / 100);
        ajouterDansHistorique("interêts de " + TAUX_INTERET + " % : nouveau solde " + this.solde);
    }

}
