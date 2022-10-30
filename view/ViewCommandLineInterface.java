package view;

import controller.GameController;
import model.Board;
import model.Square;

import javax.xml.bind.annotation.XmlType;
import java.awt.*;
import java.io.IOException;
import java.util.*;

public class ViewCommandLineInterface {

    private GameController controller;
    private int ChoiceJ;

    public void setController(GameController gameController) {
        this.controller = gameController;

    }


    public void displayBoard(Board board) {

        System.out.println("\tA   B   C   D   E   F   G   H   I   J   K   L   M   N   O");

        for (int i = 0; i < 15; i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < 15; j++) {
                if (!board.getBoard()[i][j].isContainShip())
                System.out.print("~" + " | ");
                else {
                    System.out.print("■" + " | ");
                }

            }
            System.out.println();
        }

    }

    public void displayMenu() {

        // TODO

        System.out.println("               ************************************                ");
        System.out.println("               ************************************                ");
        System.out.println("*************  Bienvenue dans la bataille Navale  ************* ");
        System.out.println("               ************************************                ");
        System.out.println("                        ******************                         ");
        System.out.println("                        ******************                         ");
        System.out.println("                             *********                             ");


        System.out.println("1 : Jouer une partie");
        System.out.println("2 : Charger une partie");
        System.out.println("3 : Aide");
        System.out.println("4 : Quitter\n");

        Scanner scanner = new Scanner(System.in);
        this.ChoiceJ = scanner.nextInt();

        switch (ChoiceJ) {

            case 1:
                controller.startNewGame();
                break;
            case 2:
                controller.startLastGame();
                break;
            case 3:
                displayHelp();
                displayMenu();
                break;
            case 4:
                controller.quit();
                break;
            default:
                displayMenu();

        }

//       controller.quit();
    }

    private int converter_A_to_0(char lettre){
        int accii_lettre=lettre;
        if ((97<=accii_lettre)&&(accii_lettre<=111)){//si on à des minuscule je le transforme en majuscule
            accii_lettre=accii_lettre-32;
        }
        if ((65<=accii_lettre)&&(accii_lettre<=79)){//je vérifier que l'on m'envois une lettre entre A et O
        return accii_lettre-65;
        }
        else {
            return -1; //-1 est la valeur qu'il retourne en cas d'erreur
        }
    }

    private void displayHelp() {

        System.out.println("                 **********  Aide  **********");
        System.out.println("                 |      L'objectif est de faire couler tous les bateaux ennemi     |");
        System.out.println("                 |     Chacun des joueurs posséde une grille afin de positionner les navires, l'autre grille sert à visualisé les dégats    |");
        System.out.println("                 |     Chaque joueur possede 10 navires : 1 cuirassé, 2 croiseurs, 3 destroyers et 4 sous marins     |");
        System.out.println("                 |     Taille : ");
        System.out.println("                 |     Cuirassé : 7 cases");
        System.out.println("                 |     Croiseur : 5 cases");
        System.out.println("                 |     Destroyer : 3 cases");
        System.out.println("                 |     Sous marin : 1 case");
        System.out.println("                 |     Puissance de tir : ");
        System.out.println("                 |     Cuirassé : 9 cases");
        System.out.println("                 |     Croiseur : 4 cases");
        System.out.println("                 |     Destroyer : 1 cases");
        System.out.println("                 |     Sous marin : 1 case");
        System.out.println("                 |      L'objectif est de faire couler tous les bateaux ennemis     |");

        System.out.println();
        System.out.println("                 Appuyez sur Entrée pour confirmer.");

        Scanner readinput = new Scanner(System.in);

        String enterkey = "x";

        while (!enterkey.equals("")) {
            enterkey = readinput.nextLine();
        }

    }

    // TODO : Nom a changer : askForCoord()
    public void askForAction() {
        // Demande au joueur où il tire
        // Récupérer coordonnées du bateau qui tire et de la case touchée
        // TODO : Décider de renvoyer le result sous forme de String[] ou String normal, ou bien String pour la lettre et String pour numbers ? (préférence pour String[], qui permet ensuite de parser en nombre)

        // TODO : Tirer avec hit dans board ? Pour moi, ca c'est dans le controller, le view s'occupe de récupérer l'input de l'utilisateur et le donne au controller, qui parse l'input et qui modifie les données du model


        System.out.println("Où souhaitez-vous effectuer votre action ?");

        // Récupérer le x et le y

        Scanner sin = new Scanner(System.in);
        boolean goodInput = false;
        String numbers = "";

        while (!goodInput) {
            try {
                String[] result = sin.nextLine().toLowerCase().split("");

                // On vérifie qu'il y a deux ou trois caractères
                if (result.length >= 2 && result.length <= 3) {

                    // On vérifie que le premier caractère est une lettre entre A et O
                    if (converter_A_to_0(result[0].charAt(0)) != -1) {

                        if (result.length == 2) {
                            numbers = result[1];

                            // On vérifie que le chiffre donné est compris entre 0 et 9 si on a 2 caractères
                            if (numbers.charAt(0) >= '0' && numbers.charAt(0) <= '9') {
                                goodInput = true;
                            }

                        } else if (result.length == 3) {
                            numbers = result[1].concat(result[2]);

                            // On vérifie si le premier chiffre est un 1 et le deuxième 0 jusqu'à 4 (pour permettre 0 à 14)
                            if ((numbers.charAt(0) == '0' || numbers.charAt(0) == '1') && (numbers.charAt(1) >= '0' && numbers.charAt(1) <= '4')) {
                                goodInput = true;
                            }
                        }
                    }
                }

                if (!goodInput) throw new IOException("Mauvais input ! Vous devez écrire un input au format 'A0' ou 'C13' ! ");

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    // TODO : Nom à changer : askForAction()
    // Retourne 1 si déplacement
    // Retourne 2 si tir
    public int askForShoot() {
        System.out.println();
        System.out.println("Choisissez une option :");
        System.out.println("1 - Se déplaceṙ");
        System.out.println("2 - Tirer");
        System.out.println();

        int choice = 0;

        Scanner scanner = new Scanner(System.in);


        while (choice != 1 && choice != 2) {
            try {
                choice = scanner.nextInt();

            } catch (java.util.InputMismatchException e) {
                scanner.nextLine();
            }

            if (choice != 1 && choice != 2) System.out.println("Mauvais input ! Vous devez choisir de vous déplacer (1) ou de tirer (2) !");

            System.out.println();
        }


        return choice;

    }



    public void displayGameOver(String winner) {

        System.out.println("                 **********  Quitter **********");
        System.out.println("                 |       La partie est finie      |");

        //  afficher le gagnant et faire un switch case
//        controller.quit();
//        displayMenu();
    }
}
