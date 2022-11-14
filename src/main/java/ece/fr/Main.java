package ece.fr;

import ece.fr.controller.GameController;
import ece.fr.controller.GameEvaluator;
import ece.fr.controller.manager.LastAliveEvaluator;
import ece.fr.view.ViewCommandLineInterface;
import ece.fr.view.ViewGraphicalUserInterface;
import ece.fr.view.Viewable;
import javafx.application.Application;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class Main extends Application {
    public static void main(String[] args)  {
        switch (args[0]) {
            case "cli":
                Viewable view = new ViewCommandLineInterface();
                GameEvaluator gameEvaluator = new LastAliveEvaluator();
                GameController gameController = new GameController(view,gameEvaluator);
                gameController.startGame();
                break;
            case "gui":
                launch(args);
                break;
            default:
                System.out.println("Wrong argument, should be cli or gui");
                break;
        }



    }


    @Override
    public void start(Stage stage)  {

        Viewable view = new ViewGraphicalUserInterface(stage);
        GameEvaluator gameEvaluator = new LastAliveEvaluator();
        GameController gameController = new GameController(view,gameEvaluator);
        gameController.startGame();
    }
}
