import models.Compte;
import models.CompteEpargne;
import models.ComptePayant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Compte> comptes = new ArrayList<>();
    static Compte compteSelection;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        programme: while (true){
            aff("(1) Création d'un compte, (2) Listage des comptes, (3) Sélection d'un compte, (4) Quitter");
            switch (scanner.nextInt()){
                case 1 : creationCompte(); break;
                case 2 : listerCompte(); break;
                case 3 : selectionCompte(); break;
                case 4 : break programme;
            }
        }
    }

    private static void selectionCompte() {
        aff("Entrez le numéro de compte que vous souhaitez séléctionner :");
        listerCompte();
        compteSelection = getCompteByCode(scanner.nextInt());
        if (compteSelection != null)
            operationsSurCompte();
        else
            aff("Désolé le compte n'existe pas");
    }

    private static void operationsSurCompte() {
        operationsCompte: while (true){
            aff("(1) Dépôt, (2) Retrait, (3) Virement, (4) Historique, (5) Voir solde et infos, (6) Sortir du compte");
            switch (scanner.nextInt()){
                case 1 : depot(); break;
                case 2 : retrait(); break;
                case 3 : virement(); break;
                case 4 : compteSelection.visualiserHistorique(); break;
                case 5 : aff(compteSelection.toString()); break;
                case 6 : break operationsCompte;
            }
        }
    }

    private static void depot() {
        aff("Montant du dépôt :");
        compteSelection.depot(scanner.nextDouble());
        aff(compteSelection.getHistorique().getLast());
    }

    private static void retrait() {
        aff("Montant du retrait :");
        compteSelection.retrait(scanner.nextDouble());
        aff(compteSelection.getHistorique().getLast());
    }

    private static void virement() {
        while (true){
            aff("Sur quel compte souhaitez-vous réaliser un virement ? Entrez le numéro de compte :");
            listerCompte();
            Compte compteDestinataire = getCompteByCode(scanner.nextInt());
            if (compteDestinataire != null){
                aff("Montant du virement :");
                compteSelection.virement(compteDestinataire, scanner.nextDouble());
                aff(compteSelection.getHistorique().getLast());
                break;
            }
            else
                aff("Désolé le compte destinataire n'existe pas");
        }
    }

    public static Compte getCompteByCode(int code){
        for (Compte compte: comptes) {
            if (compte.getCode() == code)
                return compte;
        }
        return null;
    }

    private static void listerCompte() {
        for (Compte compte: comptes)
            aff(compte.toString());
    }

    private static void creationCompte() {
        aff("(1) Compte classique, (2) Compte épargne, (3) Compte payant");
        Compte compte;
        switch (scanner.nextInt()){
            case 2 : compte = new CompteEpargne(); break;
            case 3 : compte = new ComptePayant(); break;
            default : compte = new Compte(); break;
        }
        comptes.add(compte);
        aff("Le compte " + compte.getCode() + " a bien été créé");
    }

    public static void aff(String message){
        System.out.println(message);
    }

}
