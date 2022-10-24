import controller.GameController;
import view.ViewCommandLineInterface;

public class Main {
    public static void main(String[] args) {
        GameController gameController = new GameController(new ViewCommandLineInterface());
//        gameController.run();

    }
}
