package controller;

import model.Board;
import model.ship.GameState;
import view.ViewCommandLineInterface;

public class GameController {

    private final ViewCommandLineInterface view;
    private final Board board1;
    private final Board board2;
    private Board whoIsPlaying;
    private GameState gameState;

    public GameController(ViewCommandLineInterface view) {
        this.view = view;
        this.board1 = new Board();
        this.board2 = new Board();
        this.whoIsPlaying = board1;

        view.setController(this);

        this.gameState = GameState.NOT_STARTED;
    }

    public void run() {
        if (gameState == GameState.NOT_STARTED) {
            view.displayMenu();
        }

        while (gameState == GameState.IN_PROGRESS) {
            if (whoIsPlaying == board1) {
                view.displayBoard(board1);
                view.askForAction();
            } else {
                view.displayBoard(board2);
                view.askForAction();
            }
            // winner
            if (board1.isGameOver() || board2.isGameOver()) {
                gameState = GameState.FINISHED;
                view.displayGameOver(whoIsWinner());
            }

            whoIsPlaying = whoIsPlaying == board1 ? board2 : board1;
        }
    }

    public String whoIsWinner() {
        return board1.isGameOver() ? "Player 2" : "Player 1";
    }


    public void startNewGame() {
        this.gameState = GameState.IN_PROGRESS;

    }

    public void startLastGame() {
        //TODO load last game
    }


    public void quit() {
        System.exit(0);
    }


    // methode move


}
