package ece.fr.controller;

import ece.fr.controller.manager.ChoiceManager;
import ece.fr.model.BOT;
import ece.fr.model.Board;
import ece.fr.model.ship.*;
import ece.fr.view.Viewable;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Controlleur principal
 */
public class GameController implements Serializable {

    private final Viewable view;
    final private boolean isCheatOn;
    private Board board1;
    private Board board2;
    private BOT Bot;
    private Board attacker;
    private Board victim;
    private GameState gameState;
    private GameEvaluator evaluator;
    private ChoiceManagerable choiceManager;


    public GameController(Viewable view, GameEvaluator evaluator, boolean isCheatOn) {
        this.view = view;
        this.evaluator = evaluator;
        this.choiceManager = new ChoiceManager(view, this);//TODO le choixManager doit être en paramètre
        this.board1 = new Board();
        this.board2 = new Board();
        this.Bot = new BOT(this.board2);
        this.attacker = board1;
        this.victim = board2;
        this.isCheatOn = isCheatOn;

        view.setController(this);

        this.gameState = GameState.NOT_STARTED;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * Méthode permettant la création d'une partie en changeant le GameState
     * Appelle la méthode pour évaluer le winner en fonction des ship coulés
     * Appelle les fonctions pour que le bot fasse une action (tirer ou bouger)
     * Sauvegarde la partie automatiquement à la fin de chaque tour dans un fichier
     */
    public void startGame() {


        if (gameState == GameState.NOT_STARTED) {
            view.displayMenu();
        }

        while (gameState == GameState.IN_PROGRESS) {
            if (attacker == board1) {
                if (isCheatOn) {
                    view.displayBoard(board2);
                }
                view.displayBoard(board1);
                view.displayBoardToucherOuPas(board2);
                view.askForMoveOrShoot();

            } else {
                boolean i = Bot.hit_or_move();
                if (i) {
                    Bot.hitBot(board1);
                } else {
                    Bot.move();
                }

            }

            if (evaluator.evaluateWinner(board1, board2) != null) {
                evaluateWinner(board1, board2);

            }
            attacker = (attacker == board1) ? board2 : board1;
            victim = (victim == board1) ? board2 : board1;
            saveGame();
            clearScreen();
        }
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Termine la partie et donne le Winner
     * @param board1
     * @param board2
     */
    public void evaluateWinner(Board board1, Board board2) {
        Board winner = evaluator.evaluateWinner(board1, board2);

        if (winner == board1) {
            view.displayWinner("Joueur");
        } else {
            view.displayWinner("Bot");
        }
        gameState = GameState.FINISHED;
    }

    /**
     * Choix dans le menu
     * @param choice
     */
    public void selectMenuChoice(int choice) {
        choiceManager.selectMenuChoice(choice);
    }

    /**
     * Choix d'actions dans la partie
     * @param choice
     */
    public void selectMoveOrShoot(int choice) {
        choiceManager.selectMoveOrShoot(choice);
    }


    /**
     * Crée une nouvelle partie
     */
    public void startNewGame() {
        this.gameState = GameState.IN_PROGRESS;

    }

    /**
     * Chargement de la partie en récupérant le plateau, la liste de ship ainsi que le plateau adverse
     */
    public void startLastGame() {
        System.out.println("Chargement...");
        board1.setBoard((Ship[][]) Serializer.deSerialize(board1.getBoard(), "board1DataBoard"));
        board1.setShipsList((Ship[]) Serializer.deSerialize(board1.getShipsList(), "board1DataShipList"));
        board1.setSecondBoard((SecondBoard) Serializer.deSerialize(board1.getSecondBoard(), "board1DataSecondBoard"));
        board2.setBoard((Ship[][]) Serializer.deSerialize(board2.getBoard(), "board2DataBoard"));
        board2.setShipsList((Ship[]) Serializer.deSerialize(board2.getShipsList(), "board2DataShipList"));
        board2.setSecondBoard((SecondBoard) Serializer.deSerialize(board2.getSecondBoard(), "board2DataSecondBoard"));

    }

    /**
     *Sauvegarde la partie dans un fichier
     */
    public void saveGame() {
        Serializer.serialize(board1.getShipsList(), "board1DataShipList");
        Serializer.serialize(board1.getBoard(), "board1DataBoard");
        Serializer.serialize(board1.getSecondBoard(), "board1DataSecondBoard");
        Serializer.serialize(board2.getShipsList(), "board2DataShipList");
        Serializer.serialize(board2.getBoard(), "board2DataBoard");
        Serializer.serialize(board2.getSecondBoard(), "board2DataSecondBoard");

    }


    /**
     * Permet la selection des ship en utilisant les coordonnées
     * @return de type Ship
     */
    public Ship selectShip() {
        String coords = view.askSelectShip();
        final int xAttacker = getNumberIndex(coords);
        final int yAttacker = getLetterIndex(coords);
        Ship shipAttacker = this.attacker.getBoard()[xAttacker][yAttacker];
        if (shipAttacker == null) {
            view.displayError("Pas de bateau à cette position");
            shipAttacker = selectShip();
        }
        return shipAttacker;
    }


    /**
     * Permet de selectionner ou on veut tirer
     * Verifie aussi si la fusee eclairante est utilisée ou pas encore
     */
    public void AskForShoot() {
        Ship attackerShip = selectShip();
        String coords = view.askSelectTarget();
        final int xVictim = getNumberIndex(coords);
        final int yVictim = getLetterIndex(coords);
        if ((attackerShip.getPowershot() == ShootingPower.DESTROYER)) {
            Destroyer destroyer = (Destroyer) attackerShip;
            if ((destroyer.isFusee())) {
                view.displayfuse(board2, xVictim, yVictim);
                destroyer.setFusee(false);
            }

        } else {
            this.attacker.shoots(attackerShip, xVictim, yVictim, this.victim);
        }
    }

    /**
     * Demande pour bouger un ship en fonction de sa direction
     */
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
        } catch (IllegalArgumentException e) {
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


    /**
     * Verifie si les coordonnées entrée sont dans le bon format
     * @param coords
     * @return boolean
     */
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

    /**
     * Conversion des lettres pour la selection de l'utilisateur
     * @param accii_lettre
     * @return int
     */
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


    public void about() {
        view.displayHelp();
    }

    public String TextFromFile(String fileName) {
        String text = "";
        String path = "src/main/resources/" + fileName;
        try {
            text = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

}
