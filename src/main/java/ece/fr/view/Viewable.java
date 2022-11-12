package ece.fr.view;

import ece.fr.controller.GameController;
import ece.fr.model.Board;
import ece.fr.model.ship.Direction;

public interface Viewable {
    void displayBoard(Board board);

    void displayMenu();

    void askForMoveOrShoot();

    void displayWinner(Board board);
    void displayfuse(Board board,int x, int y);

    void setController(GameController gameController);

    void displayHelp();

    void askForMove();

    String askSelectShip();

    String askSelectTarget();



    Direction askDirection(Direction direction1, Direction direction2);
}
