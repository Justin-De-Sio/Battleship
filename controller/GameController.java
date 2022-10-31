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
                view.askForMoveOrShoot();

            } else {
                view.displayBoard(board2);

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
    public boolean isContainShip(String[] result) {
        return whoIsPlaying.getBoard()[Integer.parseInt(result[0])][Integer.parseInt(result[1])].isContainShip();
    }



    private void Shoot() {
        String coords =  view.askSelectShip();
        int xAttacker = getNumberIndex(coords);
        int yAttacker = getLetterIndex(coords);
        //TODO check if the ship is already hit
        //TODO get x y victime

    }


    public int getLetterIndex(String coords) {
        return coords.charAt(0) - 'A';


    }
    public int getNumberIndex(String coords) {
        return Integer.parseInt(coords.substring(1));
    }




    public boolean isValideCoord(String coords) {


        boolean goodInput = false;
        String numbers = "";
        //cast String to String[]
        String[] coordsAvecNomnull = coords.split("");


        // On vérifie qu'il y a deux ou trois caractères
        if (coordsAvecNomnull.length >= 2 && coordsAvecNomnull.length <= 3) {

            // On vérifie que le premier caractère est une lettre entre A et O
            if (converter_A_to_0(coordsAvecNomnull[0].charAt(0)) != -1) {

                if (coordsAvecNomnull.length == 2) {
                    numbers = coordsAvecNomnull[1];

                    // On vérifie que le chiffre donné est compris entre 0 et 9 si on a 2 caractères
                    if (numbers.charAt(0) >= '0' && numbers.charAt(0) <= '9') {
                        goodInput = true;
                    }

                } else if (coordsAvecNomnull.length == 3) {
                    numbers = coordsAvecNomnull[1].concat(coordsAvecNomnull[2]);

                    // On vérifie si le premier chiffre est un 1 et le deuxième 0 jusqu'à 4 (pour permettre 0 à 14)
                    if ((numbers.charAt(0) == '0' || numbers.charAt(0) == '1') && (numbers.charAt(1) >= '0' && numbers.charAt(1) <= '4')) {
                        goodInput = true;
                    }
                }
            }
        }

        return goodInput;

    }
    public int converter_A_to_0(char lettre) {
        int accii_lettre = lettre;
        if ((97 <= accii_lettre) && (accii_lettre <= 111)) {//si on à des minuscule je le transforme en majuscule
            accii_lettre = accii_lettre - 32;
        }
        if ((65 <= accii_lettre) && (accii_lettre <= 79)) {//je vérifier que l'on m'envois une lettre entre A et O
            return accii_lettre - 65;
        } else {
            return -1; //-1 est la valeur qu'il retourne en cas d'erreur
        }
    }

    public void quit() {
        System.exit(0);
    }

    public void callMoveOrShoot(int choice) {
        switch (choice) {
            case 1:
                view.askForMove();
                break;
            case 2:
                Shoot();
                break;
            default:
                view.askForMoveOrShoot();
        }
    }



    public Board evaluateWinner() {
        return evaluator.evaluateWinner(board1, board2);
    }

    // methode move


}
