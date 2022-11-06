package ece.fr.controller;

import ece.fr.model.Board;

public interface GameEvaluator {
    Board evaluateWinner(Board board1, Board board2);

    boolean isGameOver(Board board);
}
