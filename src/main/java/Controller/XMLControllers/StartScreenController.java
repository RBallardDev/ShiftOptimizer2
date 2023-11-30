package Controller.XMLControllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;

import java.io.IOException;

public class StartScreenController {

    @FXML
    private Button signUpButton;

    @FXML
    private Button signInButton;

    @FXML
    private void handleSignUp(ActionEvent event) {
        try {
            // Load the sign-up screen FXML file
            Parent signUpScreenRoot = FXMLLoader.load(getClass().getResource("/signUpScreen-view.fxml"));

            // Get the current stage using the button from the current scene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the sign-up screen scene to the stage
            stage.setScene(new Scene(signUpScreenRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSignIn() {
        // Implement sign-in logic
    }
}