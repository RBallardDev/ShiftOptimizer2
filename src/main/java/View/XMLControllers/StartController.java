package View.XMLControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;

import java.io.IOException;

public class StartController {

    @FXML
    private Button signUpButton;

    @FXML
    private Button signInButton;

    @FXML
    private void handleSignUp(ActionEvent event) {
        try {
            // Load the sign-up screen FXML file
            Parent signUpScreenRoot = FXMLLoader.load(getClass().getResource("/signUp-view.fxml"));

            // Get the current stage using the button from the current scene
            Stage stage = HelperMethods.getStageFromEvent(event);
            // Set the sign-up screen scene to the stage
            stage.setScene(new Scene(signUpScreenRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSignIn(ActionEvent event) throws IOException {
        Parent signUpScreenRoot = FXMLLoader.load(getClass().getResource("/signIn-view.fxml"));
        Stage stage = HelperMethods.getStageFromEvent(event);
        stage.setScene(new Scene(signUpScreenRoot));
        stage.show();

    }
}