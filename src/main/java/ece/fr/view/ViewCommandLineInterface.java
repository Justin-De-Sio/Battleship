package ece.fr.view;

import ece.fr.controller.GameController;
import ece.fr.model.Board;
import ece.fr.model.ship.Direction;
import ece.fr.model.ship.Length;
import ece.fr.model.ship.Ship;

import java.io.IOException;
import java.util.Scanner;

public class ViewCommandLineInterface implements Viewable {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    private GameController controller;


    public void setController(GameController gameController) {
        this.controller = gameController;

    }

    public void displayBoard(Board board) {
        System.out.println("\033[34m" + "La flotte" + "\033[0m");

        System.out.println("\tA   B   C   D   E   F   G   H   I   J   K   L   M   N   O");
        for (int i = 0; i < 15; i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < 15; j++) {
                if (board.getBoard()[i][j] == null)
                    System.out.print(ANSI_BLUE+"~"+ " | "+ANSI_RESET);
                else {
                    if(board.getSecondBoard().isStrike(i, j)){
                        System.out.print(ANSI_RED+"x" +ANSI_RESET+ANSI_BLUE+" | "+ANSI_RESET);
                    }
                    else{
                        if (board.getBoard()[i][j].getLength()==Length.BATTLESHIP){
                            System.out.print(ANSI_CYAN+"B"+ANSI_RESET+ANSI_BLUE+" | "+ANSI_RESET);
                        }
                        else if (board.getBoard()[i][j].getLength()==Length.CRUISER){
                            System.out.print(ANSI_GREEN+"C" +ANSI_RESET+ANSI_BLUE+" | "+ANSI_RESET);
                        }
                        else if (board.getBoard()[i][j].getLength()==Length.DESTROYER){
                            System.out.print(ANSI_YELLOW+"D"+ANSI_RESET+ANSI_BLUE+" | "+ANSI_RESET);
                        }
                        else if (board.getBoard()[i][j].getLength()==Length.SUBMARINE){
                            System.out.print(ANSI_PURPLE+"S"+ANSI_RESET+ANSI_BLUE+" | "+ANSI_RESET);
                        }
                    }
                }

            }
            System.out.println();
        }

    }

    public void displayMenu() {
        System.out.println("\t\t\t  **********************************");
        System.out.println("\t\t\t  **********************************");
        System.out.println("*************  Bienvenue dans la bataille Navale  ************* ");
        System.out.println("\t\t\t   *********************************");
        System.out.println("\t\t\t\t\t  ********************");
        System.out.println("\t\t\t\t\t\t****************");
        System.out.println("\t\t\t\t\t\t   **********");


        System.out.println("1 : Jouer une partie");
        System.out.println("2 : Charger une partie");
        System.out.println("3 : Aide");
        System.out.println("4 : Quitter\n");

        Scanner scanner = new Scanner(System.in);

        int choice = 0;
        while (choice != 1 && choice != 2 && choice!=3 && choice!=4 ) {
            try {
                choice = scanner.nextInt();

            } catch (java.util.InputMismatchException e) {
                scanner.nextLine();
                displayError("Veuillez entrer un nombre");
            } catch (Exception e) {
                displayError("une erreur est survenue: "+ e.getMessage());
            }

            if (choice != 1 && choice != 2 && choice!=3 && choice!=4)
                System.out.println("Mauvais input ! Vous devez choisir entre Jouer une partie (1) ou de charger une partie (2) ou d'afficher les aides (3) et encore de quitter !");

            System.out.println();
        }
        controller.selectMenuChoice(choice);


    }

    public void displayHelp() {

        System.out.println("                                                    **********  Aide  **********\n");
        System.out.println("|     L'objectif est de faire couler tous les bateaux ennemis                                                               |");
        System.out.println("|     Chacun des joueurs possède une grille afin de positionner les navires, l'autre grille sert à visualiser les dégâts    |");
        System.out.println("|     Chaque joueur possède 10 navires : 1 cuirassé, 2 croiseurs, 3 destroyers et 4 sous-marins                             |\n");
        System.out.println("|     Voici les Tailles :                                                                                                   |");
        System.out.println("|     Cuirassé : 7 cases                                                                                                    |");
        System.out.println("|     Croiseur : 5 cases                                                                                                    |");
        System.out.println("|     Destroyer : 3 cases                                                                                                   |");
        System.out.println("|     Sous marin : 1 case                                                                                                   |\n");
        System.out.println("|     Puissance de tir :                                                                                                    |");
        System.out.println("|     Cuirassé : 9 cases                                                                                                    |");
        System.out.println("|     Croiseur : 4 cases                                                                                                    |");
        System.out.println("|     Destroyer : 1 case                                                                                                    |");
        System.out.println("|     Sous marin : 1 case                                                                                                   |");
        System.out.println("|     L'objectif est de faire couler tous les bateaux ennemis                                                               |");

        System.out.println();
        System.out.println("                 Appuyez sur Entrée pour confirmer.");

        Scanner readinput = new Scanner(System.in);

        String enterkey = "x";

        while (!enterkey.equals("")) {
            enterkey = readinput.nextLine();
        }

    }

    public String askSelectShip() {
        System.out.println("Quel navire voulez utiliser ?");
        Scanner scanner = new Scanner(System.in);
        String result = scanner.nextLine().toUpperCase();
        try {
            if (!controller.isValideCoord(result)) {

                throw new IOException("Mauvais input ! Vous devez écrire un input au format 'A0' ou 'C13' ! ");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return askSelectShip();
        }
        return result;
    }

    public String askSelectTarget() {
        System.out.println("Où souhaitez-vous Tirer ?");
        Scanner scanner = new Scanner(System.in);
        String result = scanner.nextLine().toUpperCase();
        try {
            if (!controller.isValideCoord(result)) {

                throw new IOException("Mauvais input ! Vous devez écrire un input au format 'A0' ou 'C13' ! ");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return askSelectTarget();
        }
        return result;
    }

    @Override
    public Direction askDirection(Direction direction1, Direction direction2) {
        System.out.println("Quelle direction voulez-vous utiliser ?");
        System.out.println("1 : " + direction1);
        System.out.println("2 : " + direction2);
          Scanner scanner = new Scanner(System.in);
        int result = scanner.nextInt();
        try {
            if (result != 1 && result != 2) {

                throw new IOException("Mauvais input ! Vous devez écrire un input au format '1' ou '2' ! ");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return askDirection(direction1, direction2);
        }
        return result == 1 ? direction1 : direction2;

    }

    @Override
    public void displayError(String message) {
        System.out.println("\033[31m" + message + "\033[0m");
    }


    // Retourne 1 si déplacement
    // Retourne 2 si tir
    public void askForMoveOrShoot() {
        System.out.println();
        System.out.println("Choisissez une option :");
        System.out.println("1 - Se déplaceṙ");
        System.out.println("2 - Tirer");
        System.out.println();

        Scanner scanner = new Scanner(System.in);

        int choice = 0;
        while (choice != 1 && choice != 2) {
            try {
                choice = scanner.nextInt();

            } catch (java.util.InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Vous devez entrer un chiffre !");
            } catch (Exception e) {
                System.out.println("Une erreur est survenue !");
            }

            if (choice != 1 && choice != 2)
                System.out.println("Mauvais input ! Vous devez choisir de vous déplacer (1) ou de tirer (2) !");

            System.out.println();
        }
        controller.selectMoveOrShoot(choice);

    }

    public void askForMove() {


    }

    public void displayWinner(String winner) {
        System.out.println("******************  Quitter ********************");
        System.out.println("\t\t La partie est finie\t\t\t");
        System.out.println("\t\tLe gagnant est : Joueur\t\t");
        System.out.println("\tAppuyez sur Entrée pour revenir au menu\t");
        Scanner readinput = new Scanner(System.in);

        String enterkey = "x";

        while (!enterkey.equals("")) {
            enterkey = readinput.nextLine();
        }
        displayMenu();
    }


    public void displayfuse(Board board,int debutx, int debuy) {

        if(debutx>11){
            int r=14-debutx;
            debutx=debutx-r;
        }
        if (debuy>11){
            int r=14-debuy;
            debuy=debuy-r;
        }
            int finx=debutx+4;
        int finy=debuy+4;
        System.out.println("\tA   B   C   D   E   F   G   H   I   J   K   L   M   N   O");

        for (int i = 0; i < 15; i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < 15; j++) {
                if((i>=debutx)&&(j>=debuy)){
                    if((i<finx)&&(j<finy)){

                        if (board.getBoard()[i][j] == null)
                            System.out.print("~" + " | ");
                        else {
                            if(board.getSecondBoard().isStrike(i, j)){
                                System.out.print(ANSI_RED+"x" +ANSI_RESET+" | ");
                            }
                            else {
                                System.out.print("■" + " | ");
                            }
                        }
            }
                    else{
                        System.out.print(ANSI_WHITE_BACKGROUND+" " + " | "+ANSI_RESET);
                    }
                }
                else{
                    System.out.print(ANSI_WHITE_BACKGROUND+" " + " | "+ANSI_RESET);
                }
            }
            System.out.println();
        }
        System.out.println("                 Appuyez sur Entrée pour confirmer.");
        Scanner readinput = new Scanner(System.in);

        String enterkey = "x";

        while (!enterkey.equals("")) {
            enterkey = readinput.nextLine();
        }

    }


    public void displayBoardToucherOuPas(Board board) {
        System.out.println("\033[34m" + "Zone de tire" + "\033[0m");

        System.out.println("\tA   B   C   D   E   F   G   H   I   J   K   L   M   N   O");

        for (int i = 0; i < 15; i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < 15; j++) {
                if (board.getBoard()[i][j] == null)
                    System.out.print(ANSI_BLUE+"~" + " | "+ANSI_RESET);
                else {
                    if(board.getSecondBoard().isStrike(i, j)){
                        System.out.print(ANSI_RED+"x" +ANSI_RESET+ANSI_BLUE+" | "+ANSI_RESET);
                    }
                    else {
                        System.out.print(ANSI_BLUE+"~" + " | "+ANSI_RESET);
                    }
                }

            }
            System.out.println();
        }

    }
}
