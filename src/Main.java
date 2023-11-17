import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Main extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        GameScene gameScene = new GameScene(root, 800,400);
        stage.setScene(gameScene);
        stage.show();

        Button buttonPlus = new Button("Reset velocity");
        Button buttonMoins = new Button("Velocity+");


        root.getChildren().addAll(buttonPlus,buttonMoins);
        buttonPlus.setOnAction((e)->{
            gameScene.testPlus();
        });
        buttonMoins.setOnAction((e)->{
            gameScene.testMoins();
        });
        buttonMoins.setLayoutY(70);


    }

    public static void main(String[] args) {
        launch(args);
        }

}