package controller;

import model.Board;
import model.ship.Direction;
import model.ship.GameState;
import view.ViewCommandLineInterface;

public class GameController {

    private final ViewCommandLineInterface view;
    private Board board1;
    private Board board2;
    private Board nextPlayerBoard;

    private GameState gameState;

    public GameController(ViewCommandLineInterface view) {
        this.view = view;
        this.board1 = new Board();
        this.board2 = new Board();
         this.nextPlayerBoard = board1;

        view.setController(this);

        this.gameState = GameState.NOT_STARTED;
    }

    public void run() {
        if (gameState == GameState.NOT_STARTED) {
            this.gameState = GameState.IN_PROGRESS;
        }
        while (gameState == GameState.IN_PROGRESS) {
            view.displayBoard(nextPlayerBoard);
            if (nextPlayerBoard.isGameOver()) {
                this.gameState = GameState.FINISHED;
            }
            view.askForAction();
            nextPlayerBoard = nextPlayerBoard == board1 ? board2 : board1;

        }
    }


    public void hit(int x, int y) {
        nextPlayerBoard.hit(x, y);
    }


   // methode move



}
