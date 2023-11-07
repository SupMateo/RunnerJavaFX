import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class Main extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        GameScene gameScene = new GameScene(root, 600,400);
        stage.setScene(gameScene);
        stage.show();

        Button button = new Button("OK");
        root.getChildren().add(button);
        button.setOnAction((e)->{
            gameScene.test();
        });
    }

    public static void main(String[] args) {
        launch(args);
        }

}