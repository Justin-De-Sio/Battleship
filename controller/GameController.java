package controller;

import model.Board;
import model.ship.GameState;
import model.ship.Ship;
import view.ViewCommandLineInterface;

public class GameController {

    private final ViewCommandLineInterface view;
    private final Board board1;
    private final Board board2;
    private Board attacker;
    private Board victim;
    private GameState gameState;
    private GameEvaluator evaluator;

    public GameController(ViewCommandLineInterface view, GameEvaluator evaluator) {
        this.view = view;
        this.evaluator = evaluator;
        this.board1 = new Board();
        this.board2 = new Board();
        this.attacker = board1;
        this.victim = board2;

        view.setController(this);

        this.gameState = GameState.NOT_STARTED;
    }

    public void run() {
        if (gameState == GameState.NOT_STARTED) {
            view.displayMenu();
        }

        while (gameState == GameState.IN_PROGRESS) {
            if (attacker == board1) {
                view.displayBoard(board1);
                view.askForAction();
                view.askForCoord();
            } else {
                view.displayBoard(board2);
                view.askForCoord();
            }
            // winner
            if (evaluateWinner() != null) {
                view.displayWinner(evaluateWinner());
                gameState = GameState.FINISHED;
            }


            attacker = (attacker == board1) ? board2 : board1;
            victim = (victim == board1) ? board2 : board1;
        }
    }

    public void MenuChoice(int choice) {
        switch (choice) {

            case 1:
                this.startNewGame();
                break;
            case 2:
                this.startLastGame();
                break;
            case 3:
                view.displayHelp();
                view.displayMenu();
                break;
            case 4:
                this.quit();
                break;
            default:
                view.displayMenu();

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

    public Board evaluateWinner() {
        return evaluator.evaluateWinner(board1, board2);
    }

    // methode move


}
