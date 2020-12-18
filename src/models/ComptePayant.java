package models;

public class ComptePayant extends Compte {

    public static final double COUT_OPERATION = 0.10;

    public ComptePayant() {
    }

    public ComptePayant(double solde) {
        super(solde);
    }

}
