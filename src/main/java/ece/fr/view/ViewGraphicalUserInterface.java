package ece.fr.view;

import ece.fr.controller.GameController;
import ece.fr.model.Board;
import ece.fr.model.ship.Direction;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * JavaFx graphical user interface
 */
public class ViewGraphicalUserInterface extends Application implements Viewable {


    Group group;
    Scene scene;
    int height = 720;
    int width = 1080;
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
            //creating a Group object
            group = new Group();

            //Creating a Scene by passing the group object, height and width

            scene = new Scene(group, width, height);

            //setting color to the scene
            scene.setFill(Color.BEIGE);

            //Setting the title to Stage.
            stage.setTitle("Bataille Navale");

            //Adding the scene to Stage
            stage.setScene(scene);

            //Displaying the contents of the stage
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }


    }


    @Override
    public void displayMenu() {

        try {
            Label label = new Label("Battleship");
            label.setFont(new Font("Arial", 40));
            Button start = new Button("Start");
            Button load = new Button("Load");
            Button about = new Button("About");
            Button quit = new Button("Quit");
            VBox vbox = new VBox(label, start, load, about, quit);
            vbox.setSpacing(10);
            vbox.setLayoutX(250);
            vbox.setLayoutY(250);
            group.getChildren().add(vbox);
            start.setOnAction(this::startGame);
            load.setOnAction(this::loadGame);
            about.setOnAction(this::about);
            quit.setOnAction(this::quit);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());

        }
    }

    private void about(ActionEvent actionEvent) {
        group.getChildren().clear();
        try {

            String text = controller.TextFromFile("about.txt");

            Text text1 = new Text(text);
            text1.setFont(new Font("Arial", 20));
            text1.setLayoutX(250);
            text1.setLayoutY(250);
            Button back = new Button("Back");
            back.setLayoutX(250);
            back.setLayoutY(600);
            group.getChildren().addAll(text1, back);
            back.setOnAction(this::back);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayMenu(ActionEvent actionEvent) {
        group.getChildren().clear();
        displayMenu();
    }

    private void back(ActionEvent actionEvent) {
        group.getChildren().clear();
        displayMenu();
    }

    private void loadGame(ActionEvent actionEvent) {
        controller.startLastGame();
    }

    private void quit(ActionEvent actionEvent) {
        controller.quit();
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
        controller.startGame();
    }


}
