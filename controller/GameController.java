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
            } else {
                view.displayBoard(board2);
                view.askForAction();
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




    public void hits(int xAttacker, int yAttacker, int xTarget, int yTarget) {
        Ship shipAttacker = attacker.getBoard()[xAttacker][yAttacker].getShip();

        switch (shipAttacker.getPowershot().value()) {
            case 1: {
                shipAttacker.hit(victim.getBoard()[xTarget][yTarget]);

            }
            case 4: {
                for (int y = 0; y < 2; y++) {
                    for (int x = 0; x < 2; x++) {
                        shipAttacker.hit(victim.getBoard()[xTarget + x][yTarget + y]);
                    }
                }

            }
            case 9: {
                xTarget = xTarget - 1;//je me positione en haut à droite
                yTarget = yTarget - 1;
                for (int y = 0; y < 3; y++) {
                    for (int x = 0; x < 3; x++) {
                        shipAttacker.hit(victim.getBoard()[xTarget + x][yTarget + y]);
                    }
                }
            }
            default: {
                System.out.println("erreur!!!");
            }

        }
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
        return new GameEvaluator().evaluateWinner(board1, board2);
    }

    // methode move


}
