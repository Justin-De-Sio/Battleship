package ece.fr;

import ece.fr.controller.GameController;
import ece.fr.controller.GameEvaluator;
import ece.fr.manager.LastAliveEvaluator;
import ece.fr.view.ViewCommandLineInterface;

public class Main {
    public static void main(String[] args) {
        ViewCommandLineInterface view = new ViewCommandLineInterface();
        GameEvaluator gameEvaluator = new LastAliveEvaluator();

        GameController gameController = new GameController(view, gameEvaluator);
        gameController.run();

    }
}
