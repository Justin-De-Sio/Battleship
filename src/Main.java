package src;

import src.controller.GameController;
import src.controller.GameEvaluator;
import src.manager.LastAliveEvaluator;
import src.view.ViewCommandLineInterface;

public class Main {
    public static void main(String[] args) {
        ViewCommandLineInterface view = new ViewCommandLineInterface();
        GameEvaluator gameEvaluator = new LastAliveEvaluator();

        GameController gameController = new GameController(view, gameEvaluator);
        gameController.run();

    }
}
