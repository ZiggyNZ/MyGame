package View;

import Utilities.Resources;
import View.Display.GameWindow;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class MainWindow extends Application {

    public static void main(String[] args) {
        Resources.initialiseMaps();
        Application.launch(MainWindow.class, args);
    }

    @Override
    public void start(Stage primaryStage) {//TODO add update timer
        primaryStage.setTitle("My JS game");

        ScrollPane scrollPane = new ScrollPane(new GameWindow(new Player("Test Player 1")));
        scrollPane.setPrefSize(1400, 700);

        Group root = new Group();
        root.getChildren().add(scrollPane);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}
