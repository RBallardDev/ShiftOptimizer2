package View.XMLControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {

    @FXML
    private void handleSignUp(ActionEvent event) {
        loadScene("/views/signUp/signUp-view.fxml", event);
    }

    @FXML
    private void handleSignIn(ActionEvent event) {
        loadScene("/views/signIn/signIn-view.fxml", event);
    }

    /**
     * Loads the given FXML scene and sets it on the current stage.
     *
     * @param fxmlPath the path to the FXML file
     * @param event the action event triggering the scene change
     */
    private void loadScene(String fxmlPath, ActionEvent event) {
        try {
            Parent sceneRoot = FXMLLoader.load(getClass().getResource(fxmlPath));
            Stage stage = HelperMethods.getStageFromEvent(event);
            stage.setScene(new Scene(sceneRoot));
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading scene: " + fxmlPath);
            e.printStackTrace();
            // Optionally, display an alert to the user if needed.
        }
    }
}