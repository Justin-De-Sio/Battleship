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
        launch(args);


    }


    @Override
    public void start(Stage stage)  {
        Viewable viewable = new ViewGraphicalUserInterface(stage);
        GameEvaluator gameEvaluator = new LastAliveEvaluator();
        GameController gameController = new GameController(viewable,gameEvaluator);
        gameController.run();
    }
}
