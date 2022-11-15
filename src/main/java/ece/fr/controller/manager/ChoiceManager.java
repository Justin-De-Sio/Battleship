package ece.fr.controller.manager;

import ece.fr.controller.ChoiceManagerable;
import ece.fr.controller.GameController;
import ece.fr.view.Viewable;

/**
 * Gère les choix de l'utilisateur en renvoyant aux méthodes respectives
 */
public class ChoiceManager implements ChoiceManagerable {

    private final Viewable view;
    private final GameController gameController;

    public ChoiceManager(Viewable view, GameController controller) {
        this.view = view;
        this.gameController = controller;

    }

    /**
     * Appelle les méthodes du gamecontroller pour les 5 choix que l'utilisateur a au lancement du jeu
     * @param choice
     */
    public void selectMenuChoice(int choice) {
        switch (choice) {

            case 1:
                this.gameController.startNewGame();
                break;
            case 2:
                gameController.startLastGame();
                this.gameController.startNewGame();

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

    /**
     * Appelle les méthodes dugameController pour choisir l'action une fois la partie lancée
     * @param choice
     */
    public void selectMoveOrShoot(int choice) {
        switch (choice) {
            case 1:
                gameController.moveShip();
                break;
            case 2:
                gameController.AskForShoot();
                break;
            default:
                view.askForMoveOrShoot();
        }
    }
}
