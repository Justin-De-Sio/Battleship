package controller;

import model.Board;

public interface GameEvaluator {
    Board evaluateWinner(Board board1, Board board2);
    boolean isGameOver(Board board);
}
