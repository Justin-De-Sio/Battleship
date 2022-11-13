package ece.fr;

import ece.fr.controller.GameController;
import ece.fr.controller.GameEvaluator;
import ece.fr.controller.manager.LastAliveEvaluator;
import ece.fr.view.ViewCommandLineInterface;
import ece.fr.view.ViewGraphicalUserInterface;
import ece.fr.view.Viewable;

import static javafx.application.Application.launch;

public class Main {
    public static void main(String[] args) {
        Viewable view = new ViewCommandLineInterface();

        GameEvaluator gameEvaluator = new LastAliveEvaluator();

        GameController gameController = new GameController(view, gameEvaluator);
        launch();

        gameController.run();

    }
}
