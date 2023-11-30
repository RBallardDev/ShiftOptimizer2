package View;

import Controller.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewMaster {

    public static void start(Stage primaryStage) throws IOException {
        showStartScreen(primaryStage);
    }
    public static void configStage(Stage stage, String title, Scene scene){
        stage.setTitle(title);
        stage.setScene(scene);
    }

    public static void setAndShowStage(Stage stage, Scene scene){
        stage.setScene(scene);
        stage.show();
    }
    public static void showStartScreen(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/start-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        setAndShowStage(stage, scene);
    }
}
