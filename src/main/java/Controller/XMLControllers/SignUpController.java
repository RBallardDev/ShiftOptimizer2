package Controller.XMLControllers;

import Controller.File.JacksonEditor;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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

        JacksonEditor.addWorker(username, password, status);
    }
}