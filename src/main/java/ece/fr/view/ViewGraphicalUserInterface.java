package ece.fr.view;

import ece.fr.controller.GameController;
import ece.fr.model.Board;
import ece.fr.model.ship.Direction;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * JavaFx graphical user interface
 */
public class ViewGraphicalUserInterface extends Application implements Viewable {


    @Override
    public void start(Stage stage) throws Exception {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label l = new Label("Salut !");
        Scene scene = new Scene(new StackPane(l), 640, 480);
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void displayBoard(Board board) {

    }

    @Override
    public void displayMenu() {


    }

    @Override
    public void askForMoveOrShoot() {

    }

    @Override
    public void displayWinner(Board board) {

    }

    @Override
    public void displayfuse(Board board, int x, int y) {

    }

    @Override
    public void displayBoardToucherOuPas(Board board) {

    }

    @Override
    public void setController(GameController gameController) {

    }

    @Override
    public void displayHelp() {

    }

    @Override
    public void askForMove() {

    }

    @Override
    public String askSelectShip() {
        return null;
    }

    @Override
    public String askSelectTarget() {
        return null;
    }

    @Override
    public Direction askDirection(Direction direction1, Direction direction2) {
        return null;
    }

    @Override
    public void displayError(String message) {

    }
}
