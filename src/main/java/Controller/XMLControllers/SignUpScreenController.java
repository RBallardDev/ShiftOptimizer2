package Controller.XMLControllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SignUpScreenController {

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

        // Implement the logic to add a new worker
        // Use JacksonEditor.addWorker(username, password, status);
    }
}