package Controller.XMLControllers;
import Controller.UserAuth.SessionAuth;
import Controller.UserAuth.SignInAuth;
import Model.Token;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
            //Next page with all the options
        } else {
            HelperMethods.showAlert("Invalid Credentials", "The username or password that you entered was incorrect. Please try again.");
            //Sign-in failed
        }
    }
}