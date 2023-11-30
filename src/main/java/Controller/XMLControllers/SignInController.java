package Controller.XMLControllers;
import Controller.UserAuth.SignInAuth;
import Model.Token;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignInController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    public void handleSignIn(ActionEvent actionEvent) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        Token token = SignInAuth.signIn(username, password);
        if (token != null) {
            //Sign-in successful
            Session.setToken(token);
            HelperMethods.showAlert("Login Successful", "This is a test. Don't display this really.");

            //Next page with all the options
        } else {
            HelperMethods.showAlert("Invalid Credentials", "The username or password that you entered was incorrect. Please try again.");
            //Sign-in failed
        }
    }

    public void handleBack(ActionEvent event) throws IOException {
        Parent signUpScreenRoot = FXMLLoader.load(getClass().getResource("/start-view.fxml"));
        Stage stage = HelperMethods.getStageFromEvent(event);
        stage.setScene(new Scene(signUpScreenRoot));
        stage.show();
    }
}