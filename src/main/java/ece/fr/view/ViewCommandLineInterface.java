package ece.fr.view;

import ece.fr.controller.GameController;
import ece.fr.model.Board;
import ece.fr.model.ship.Direction;
import ece.fr.model.ship.Length;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * toute les méthode de l'affichage sont dans cette classe
 */
public class ViewCommandLineInterface implements Viewable {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    private GameController controller;


    public void setController(GameController gameController) {
        this.controller = gameController;

    }

    /**affiche la board
     * @param board affhiche las board choisis
     */
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

    /**
     * affiche le menus
     */
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

    /**
     * affiche help
     */
    public void displayHelp() {
        String text = controller.TextFromFile("about.txt");

        System.out.println("                                                    **********  Aide  **********\n");
        System.out.println(text);
        System.out.println();
        System.out.println("                 Appuyez sur Entrée pour confirmer.");

        Scanner readinput = new Scanner(System.in);

        String enterkey = "x";

        while (!enterkey.equals("")) {
            enterkey = readinput.nextLine();
        }

    }

    /**sélectionne le bateaux
     * @return demande sur quelle case est efficher le bateau
     */
    public String askSelectShip() {
        System.out.println("Quel navire voulez utiliser ?");
        Scanner scanner = new Scanner(System.in);
        String result = scanner.nextLine().toUpperCase();
        try {
            if (!controller.isValideCoord(result)) {

                throw new IOException();
            }
        } catch (Exception e) {
            displayError("Mauvais input ! Vous devez écrire un input au format 'A0' ou 'C13' ! ");
            return askSelectShip();
        }
        return result;
    }

    /**permet de demander quelle case est la cible
     * @return renvois la casse ou l'on tire
     */
    public String askSelectTarget() {
        System.out.println("Où souhaitez-vous Tirer ?");
        Scanner scanner = new Scanner(System.in);
        String result = scanner.nextLine().toUpperCase();
        try {
            if (!controller.isValideCoord(result)) {

                throw new IOException();
            }
        } catch (Exception e) {
            displayError("Mauvais input ! Vous devez écrire un input au format 'A0' ou 'C13' ! ");
            return askSelectTarget();
        }
        return result;
    }

    /**
     * @param direction1 affiche direction ou il peut bouger
     * @param direction2 affiche la deuxième direction de ou il peut bouger
     * @return la direction choisis
     */
    @Override
    public Direction askDirection(Direction direction1, Direction direction2) {
        System.out.println("Quelle direction voulez-vous utiliser ?");
        System.out.println("1 : " + direction1);
        System.out.println("2 : " + direction2);

        Scanner scanner = new Scanner(System.in);
        int result = scanner.nextInt();
        try {
            if (result != 1 && result != 2) {

                throw new IOException();
            }
        } catch (Exception e) {
            scanner.nextLine();
            displayError("Mauvais input ! Vous devez écrire un input au format '1' ou '2' ! ");
            return askDirection(direction1, direction2);
        }
        return result == 1 ? direction1 : direction2;

    }

    @Override
    public void displayError(String message) {
        System.out.println("\033[31m" + message + "\033[0m");
    }


    /**
     * demande à l'utilisateur si il veut  tirer ou bouger
     */
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

    /**affiche la fin du jeux
     * @param winner nom du gagnant
     */
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


    /**
     * @param board le board que l'on veut afficher
     * @param debutx à partir d'ou commencer le tir de fussée éclairante en x
     * @param debuy  à partir d'ou commencer le tir de fussée éclairante en y
     */
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
                            System.out.print("~" + " | " );
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
        delay3s();
    }


    /**permet à l'utilisateur de voir les case déja touchée
     * @param board board que l'on veut afficher seulement les casse toucher
     */
    public void displayBoardToucherOuPas(Board board) {
        System.out.println();
        System.out.println("\033[34m" + "Zone de tir" + "\033[0m");

        System.out.println("\tA   B   C   D   E   F   G   H   I   J   K   L   M   N   O");

        for (int i = 0; i < 15; i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < 15; j++) {
                if (board.getBoard()[i][j] == null)
                    System.out.print(ANSI_BLUE+"~" + " | "+ANSI_RESET);
                else {
                    if (board.getSecondBoard().isStrike(i, j)) {
                        System.out.print(ANSI_RED + "x" + ANSI_RESET + ANSI_BLUE + " | " + ANSI_RESET);
                    } else {
                        System.out.print(ANSI_BLUE + "~" + " | " + ANSI_RESET);
                    }
                }

            }
            System.out.println("");

        }

    }

    /**
     * crée un délay de 3s
     */
    public static void delay3s(){
        try {
            TimeUnit.SECONDS.sleep(3);
            String ESC = "3[";

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
