package ece.fr;

import ece.fr.controller.GameController;
import ece.fr.controller.GameEvaluator;
import ece.fr.controller.manager.LastAliveEvaluator;
import ece.fr.view.ViewCommandLineInterface;
import ece.fr.view.ViewGraphicalUserInterface;
import ece.fr.view.Viewable;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {

        if (args.length == 0) {
            Viewable view = new ViewCommandLineInterface();
            GameEvaluator gameEvaluator = new LastAliveEvaluator();
            GameController gameController = new GameController(view, gameEvaluator, false);
            System.out.println("Pour lancer l'interface graphique du jeu veuillez entrer l'argument <gui> avant de lancer le programme");
            System.out.println("Pour pouvoir voir la board adverse(cheat) veuillez entrer l'argument <cheat>\n");
            gameController.startGame();
        } else {
            switch (args[0]) {
                case "gui":
                    launch(args);
                    break;
                case "cheat":

                    Viewable view = new ViewCommandLineInterface();
                    GameEvaluator gameEvaluator = new LastAliveEvaluator();
                    GameController gameController = new GameController(view, gameEvaluator, true);
                    gameController.startGame();
                    break;
                default:
                    System.out.println("Wrong argument, should be gui or cheat");
                    break;
            }
        }

    }


    @Override
    public void start(Stage stage) {
        Viewable view = new ViewGraphicalUserInterface(stage);
        GameEvaluator gameEvaluator = new LastAliveEvaluator();
        GameController gameController = new GameController(view, gameEvaluator, false);
        gameController.startGame();
    }
}
