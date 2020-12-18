package models;

import java.util.ArrayList;
import java.util.LinkedList;

public class Compte {

    static int ctCode;
    protected int code;
    protected double solde;
    protected LinkedList<String> historique = new LinkedList<>();

    public Compte() {
        this.code = ctCode++;
    }

    public Compte(double solde) {
        this();
        this.solde = solde;
    }

    public void calculEtPaiementFrais(){
        if (this instanceof ComptePayant){
            solde -= ComptePayant.COUT_OPERATION;
            historique.set(historique.size() - 1, historique.getLast() + " : frais de " + ComptePayant.COUT_OPERATION);
        }
        else {
            historique.set(historique.size() - 1, historique.getLast() + " : aucun frais");
        }
    }

    public void depot(double montant){
        ajouterDansHistorique("dépôt de " + montant);
        this.solde += montant;
        calculEtPaiementFrais();
    }

    public void retrait(double montant){
        if (this.solde - montant >= 0) {
            this.solde -= montant;
            ajouterDansHistorique("retrait de " + montant);
            calculEtPaiementFrais();
        }
        else {
            ajouterDansHistorique("échec de la tentative de retrait de " + montant);
        }
    }

    public void virement(Compte cDestinataire, double montant) {
        if (this.solde - montant >= 0) {
            this.solde -= montant;
            cDestinataire.solde += montant;
            ajouterDansHistorique("virement de " + montant + " vers le compte " + cDestinataire.code);
            calculEtPaiementFrais();
        }
        else {
            ajouterDansHistorique("échec de la tentative de virement de " + montant + " vers le compte " + cDestinataire.code);
        }
    }

    public void visualiserHistorique(){
        for (String line: historique)
            System.out.println(line);
    }

    protected void ajouterDansHistorique(String message){
        historique.add("Compte " + code + " : " + message);
    }

    public int getCode() {
        return code;
    }

    public double getSolde() {
        return solde;
    }

    public LinkedList<String> getHistorique() {
        return historique;
    }

    @Override
    public String toString() {
        String typeCompte = "Compte classique";
        if (this instanceof CompteEpargne)
            typeCompte = "Compte épargne";
        else if (this instanceof ComptePayant)
            typeCompte = "Compte payant";
        return typeCompte + " : " +
                "code=" + code +
                ", solde=" + solde;
    }

}
