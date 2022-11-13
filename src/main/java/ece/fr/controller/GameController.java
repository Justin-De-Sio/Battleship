package ece.fr.controller;

import ece.fr.controller.manager.ChoiceManager;
import ece.fr.model.BOT;
import ece.fr.model.Board;
import ece.fr.model.ship.*;
import ece.fr.view.Viewable;

import java.io.Serializable;

public class GameController implements Serializable {

    private final Viewable view;
    private Board board1;
    private Board board2;

    private BOT Bot;
    private Board attacker;
    private Board victim;
    private GameState gameState;
    private GameEvaluator evaluator;
    private ChoiceManagerable choiceManager;


    public GameController(Viewable view, GameEvaluator evaluator) {
        this.view = view;
        this.evaluator = evaluator;
        this.choiceManager = new ChoiceManager(view, this);//TODO le choixManager doit être créé dans le main
        this.board1 = new Board();
        this.board2 = new Board();
        this.Bot=new BOT(this.board2);
        this.attacker = board1;
        this.victim = board2;

        view.setController(this);

        this.gameState = GameState.NOT_STARTED;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void run() {


        if (gameState == GameState.NOT_STARTED) {
            view.displayMenu();
        }

        while (gameState == GameState.IN_PROGRESS) {
            if (attacker == board1) {
                System.out.println("AFFICHAGE du board du joueur");
               view.displayBoard(board1);
                view.askForMoveOrShoot();
                System.out.println("AFFICHAGE du board du bot");
                view.displayBoard(board2);
            } else
            {
                Bot.set_BoardBot(board2);
                boolean i=Bot.hit_or_move();
                if (i){
                    System.out.println("le bot tir:");
                    Bot.hitBot(board1);
                }
                else{
                    System.out.println("bouge:");
                    Bot.move();
                }

            }
            // winner
            if (evaluateWinner() != null) {
                view.displayWinner(evaluateWinner());
                gameState = GameState.FINISHED;
            }
            saveGame();
            attacker = (attacker == board1) ? board2 : board1;
            victim = (victim == board1) ? board2 : board1;
        }
    }

    public void selectMenuChoice(int choice) {
        choiceManager.selectMenuChoice(choice);
    }

    public void selectMoveOrShoot(int choice) {
        choiceManager.selectMoveOrShoot(choice);
    }


    public void startNewGame() {
        this.gameState = GameState.IN_PROGRESS;

    }

    public void startLastGame() {
        board1.setBoard((Ship[][]) Serializer.deSerialize(board1.getBoard(), "src/main/resources/board1DataBoard"));
        board1.setShipsList((Ship[])Serializer.deSerialize(board1.getShipsList(), "src/main/resources/board1DataShipList"));
        board1.setSecondBoard((SecondBoard) Serializer.deSerialize(board1.getSecondBoard(), "src/main/resources/board1DataSecondBoard"));
        board2.setBoard((Ship[][]) Serializer.deSerialize(board2.getBoard(), "src/main/resources/board2DataBoard"));
        board2.setShipsList((Ship[])Serializer.deSerialize(board2.getShipsList(), "src/main/resources/board2DataShipList"));
        board2.setSecondBoard((SecondBoard) Serializer.deSerialize(board2.getSecondBoard(), "src/main/resources/board2DataSecondBoard"));

    }

    public void saveGame() {
        Serializer.serialize(board1.getShipsList(), "src/main/resources/board1DataShipList");
        Serializer.serialize(board1.getBoard(), "src/main/resources/board1DataBoard");
        Serializer.serialize(board1.getSecondBoard(), "src/main/resources/board1DataSecondBoard");
        Serializer.serialize(board2.getShipsList(), "src/main/resources/board2DataShipList");
        Serializer.serialize(board2.getBoard(), "src/main/resources/board2DataBoard");
        Serializer.serialize(board2.getSecondBoard(), "src/main/resources/board2DataSecondBoard");

    }


    public Ship selectShip() {
        String coords = view.askSelectShip();
        final int xAttacker = getNumberIndex(coords);
        final int yAttacker = getLetterIndex(coords);
        Ship shipAttacker = this.attacker.getBoard()[xAttacker][yAttacker];
        if (shipAttacker == null) {
            // TODO throw exception
            System.out.println("No ship at this position");
            shipAttacker = selectShip();
        }
        return shipAttacker;
    }


    public void AskForShoot() {
        Ship attackerShip = selectShip();
        String coords = view.askSelectTarget();
        final int xVictim = getNumberIndex(coords);
        final int yVictim = getLetterIndex(coords);
        if((attackerShip.getPowershot()== ShootingPower.DESTROYER)&&(attackerShip.isFusee())){
            view.displayfuse(board2,xVictim,yVictim);
            attackerShip.setFusee();
        }
        else {
            this.attacker.shoots(attackerShip, xVictim, yVictim, this.victim);
        }
    }

    public void moveShip() {
        try {
            Ship ship = selectShip();
            Direction direction;
            if (ship.isVertical()) {
                direction = view.askDirection(Direction.NORTH, Direction.SOUTH);
            } else {
                direction = view.askDirection(Direction.EAST, Direction.WEST);
            }
            this.attacker.moveShip(ship, direction);
        }
        catch (IllegalArgumentException e) {
            view.displayError(e.getMessage());
            moveShip();
        }
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

                } else {
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

    public int converter_A_to_0(int accii_lettre) {
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


    public Board evaluateWinner() {
        return evaluator.evaluateWinner(board1, board2);
    }



}
