package View;

import Controller.ClickHandler;
import Utilities.Resources;
import View.Display.GameWindow;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class MainWindow extends Application {

    public static void main(String[] args) {
        Resources.initialiseMaps();
        Application.launch(MainWindow.class, args);
    }

    @Override
    public void start(Stage primaryStage) {//TODO add update timer
        primaryStage.setTitle("The Legend of Sonic");

        //Initialise game display
        GameWindow gameWindow = new GameWindow(new Player("Test Player 1"));
        gameWindow.setOnMouseClicked(new ClickHandler());
        ScrollPane scrollPane = new ScrollPane(gameWindow);
        scrollPane.setPrefSize(1400, 700);
        Group root = new Group();
        root.getChildren().add(scrollPane);

        //Set scene and display it
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}
