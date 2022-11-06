package manager;

import controller.GameEvaluator;
import model.Board;
import model.ship.Ship;

public class LastAliveEvaluator implements GameEvaluator {

    //use isGameOver(
    public  Board evaluateWinner(Board board1, Board board2) {
        if (isGameOver(board1)) {
            return board2;
        } else if (isGameOver(board2)) {
            return board1;
        } else {
            return null;
        }

    }

    public  boolean isGameOver(Board board) {
        for (Ship ship : board.getShipsList()) {
            if (!ship.isSunk()) {
                return false;
            }
        }
        return true;
    }
}

