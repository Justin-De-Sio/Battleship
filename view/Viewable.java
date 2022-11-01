package view;

import controller.GameController;
import model.Board;

public interface Viewable {
    void displayBoard(Board board);

    void displayMenu();

    void askForMoveOrShoot();

    void displayWinner(Board board);

    void setController(GameController gameController);

    void displayHelp();

    void askForMove();

    String askSelectShipAttacker();

    String askSelectTarget();


}
