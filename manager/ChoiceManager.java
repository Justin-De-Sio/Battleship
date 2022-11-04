package manager;

import controller.ChoiceManagerable;
import controller.GameController;
import view.Viewable;

public class ChoiceManager implements ChoiceManagerable {

    private final Viewable view;
    private final GameController gameController;

    public ChoiceManager(Viewable view, GameController controller) {
        this.view = view;
        this.gameController = controller;

    }

    public void selectMenuChoice(int choice) {
        switch (choice) {

            case 1:
                this.gameController.startNewGame();
                break;
            case 2:
                gameController.startLastGame();
                break;
            case 3:
                view.displayHelp();
                view.displayMenu();
                break;
            case 4:
                gameController.quit();
                break;
            default:
                view.displayMenu();

        }
    }

    public void selectMoveOrShoot(int choice) {
        switch (choice) {
            case 1:
                gameController.moveShip();
                break;
            case 2:
                gameController.Shoot();
                break;
            default:
                view.askForMoveOrShoot();
        }
    }
}
