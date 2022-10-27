package controller;

import model.Board;
import model.ship.Ship;

public class GameEvaluator {

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
        for (Ship ship : board.getShips()) {
            if (!ship.isSunk()) {
                return false;
            }
        }
        return true;
    }
}

