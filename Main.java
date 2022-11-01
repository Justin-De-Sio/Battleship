import controller.GameController;
import controller.GameEvaluator;
import manager.ChoiceManager;
import manager.LastAliveEvaluator;
import view.ViewCommandLineInterface;

public class Main {
    public static void main(String[] args) {
        ViewCommandLineInterface view = new ViewCommandLineInterface();
        GameEvaluator gameEvaluator = new LastAliveEvaluator();

        GameController gameController = new GameController(view, gameEvaluator);
        gameController.run();

    }
}
