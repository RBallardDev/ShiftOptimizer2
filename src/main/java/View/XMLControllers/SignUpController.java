package View.XMLControllers;

import Controller.File.JacksonEditor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;


public class SignUpController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ComboBox<String> statusComboBox;

    @FXML
    private void handleSignUp() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String status = statusComboBox.getValue();



        // Implement the logic to add a new user
        try {
            JacksonEditor.addWorker(username, password, status);
            HelperMethods.showAlert("Sign-Up Successful", "Your credentials have been saved. You can now login.");
        } catch (IllegalArgumentException e) {
            // This block will execute if the username is already taken
            HelperMethods.showAlert("Sign-Up Failed", "This username has already been taken. Please choose another username.");
        } catch (Exception e) {
            // This block will execute for other types of exceptions
            HelperMethods.showAlert("Sign-Up Failed", "An error occurred. Please try again.");
        }
    }

    public void handleBack(ActionEvent event) throws IOException {
        Parent signUpScreenRoot = FXMLLoader.load(getClass().getResource("/views/start/start-view.fxml"));
        Stage stage = HelperMethods.getStageFromEvent(event);
        stage.setScene(new Scene(signUpScreenRoot));
        stage.show();
    }
}