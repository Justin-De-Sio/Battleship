package view;

import controller.GameController;
import model.Board;

public class ViewCommandLineInterface {

    private GameController controller;
    public void setController(GameController gameController) {
        this.controller = gameController;

    }

    public void displayBoard(Board board) {
    //TODO
    }

    public void displayMenu() {
       // TODO

//        System.out.println("1 : Jouer une partie");
//        System.out.println("2 : Charger une partie");
//        System.out.println("3 : Aide");
//        System.out.println("4 : Quitter\n");
//        controller.startNewGame();
//        controller.startLastGame();
//        displayHelp();
//        controller.quit();
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
