package view;

import controller.GameController;
import model.Board;
import model.Square;

import javax.xml.bind.annotation.XmlType;
import java.awt.*;
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
            System.out.print((char) (i + 65) + "\t");
            for (int j = 0; j < 15; j++) {
                if (!board.getBoard()[i][j].isContainShip())
                System.out.print("~" + " | ");
                else {
                    System.out.print("■" + " | ");
                }

            }
            System.out.println();
        }


        //TODO
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
                break;
            case 4:
                System.out.println("mmm");
                break;
            default:
                controller.quit();
        }

//       controller.quit();
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
        System.out.println("                 |      L'objectif est de faire couler tous les bateaux ennemi     |");

        // TODO
    }

    public void askForAction() {


    }


    public void displayGameOver(String winner) {

        System.out.println("                 **********  Quitter **********");
        System.out.println("                 |       La partie est finie      |");

        //  afficher le gagnant et faire un switch case
//        controller.quit();
//        displayMenu();
    }
}
