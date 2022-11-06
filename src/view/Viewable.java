package src.view;

import src.controller.GameController;
import src.model.Board;
import src.model.ship.Direction;

public interface Viewable {
    void displayBoard(Board board);

    void displayMenu();

    void askForMoveOrShoot();

    void displayWinner(Board board);

    void setController(GameController gameController);

    void displayHelp();

    void askForMove();

    String askSelectShip();

    String askSelectTarget();


    Direction askDirection(Direction direction1, Direction direction2);
}
