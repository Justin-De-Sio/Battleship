package view;

import controller.GameController;
import model.Board;
import model.Square;

import java.util.*;

public class ViewCommandLineInterface {

    private GameController controller;

    public void setController(GameController gameController) {
        this.controller = gameController;

    }


    public void displayBoard(Board board) {

      System.out.println("\tA   B   C   D   E   F   G   H   I   J   K   L   M   N   O");

      for (int i = 0; i<15; i++){
          System.out.print((char)(i+65) + "\t");
          for(int j = 0; j<15; j++){

              System.out.print(board.getSquare(i, j).getDesignator() + " | ");

          }
          System.out.println();
      }





    //TODO
    }

    public void displayMenu() {
       // TODO

        System.out.println("1 : Jouer une partie");
        System.out.println("2 : Charger une partie");
        System.out.println("3 : Aide");
        System.out.println("4 : Quitter\n");
        controller.startNewGame();
        controller.startLastGame();
        displayHelp();
//       controller.quit();
    }

    private void displayHelp() {
        // TODO
    }

    public void askForAction() {
        // TODO
    }

    public void displayGameOver(String winner) {

        //  afficher le gagnant et faire un switch case
//        controller.quit();
//        displayMenu();
    }
}
