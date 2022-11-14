package ece.fr.view;

import ece.fr.controller.GameController;
import ece.fr.model.Board;
import ece.fr.model.ship.Direction;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * JavaFx graphical user interface
 */
public class ViewGraphicalUserInterface extends Application implements Viewable {


    private GameController controller;
    private Stage stage;

    public ViewGraphicalUserInterface(Stage stage) {
        this.stage = stage;
        try {
            start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void start(Stage stage) throws Exception {
        try {
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root, 300, 250);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }


    }


    @Override
    public void displayMenu() {
        // draw menu  without fxml
        try {
            Label label = new Label("Battleship");
            label.setFont(new Font("Arial", 20));
            Button start = new Button("Start");
            Button load = new Button("Load");
            Button about = new Button("About");
            Button quit = new Button("Quit");

            start.setOnAction((ActionEvent e) -> {
                controller.startNewGame();
            });
            load.setOnAction((ActionEvent e) -> {
                controller.startLastGame();
            });
            about.setOnAction((ActionEvent e) -> {
                controller.about();
            });
            quit.setOnAction((ActionEvent e) -> {
                controller.quit();
            });


            BorderPane root = new BorderPane();
            // in vertical box
            VBox vBox = new VBox();
            vBox.getChildren().addAll(label, start, load, about, quit);
            root.setCenter(vBox);





            Scene scene = new Scene(root, 300, 250);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());


        }


    }

    @Override
    public void setController(GameController gameController) {
        this.controller = gameController;
    }


    @Override
    public void displayBoard(Board board) {


    }

    @Override
    public void askForMoveOrShoot() {

    }

    @Override
    public void displayWinner(String winner) {

    }

    @Override
    public void displayfuse(Board board, int x, int y) {

    }

    @Override
    public void displayBoardToucherOuPas(Board board) {

    }

    @Override
    public void displayHelp() {
        // load help.txt
          try {

                BorderPane root = new BorderPane();

                Text text = new Text("help");
                root.setCenter(text);
                Scene scene = new Scene(root, 300, 250);
                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }


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

    public void startGame(ActionEvent actionEvent) {

    }


}
