package Controller.XMLControllers;


import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class HelperMethods {
    public static Stage getStageFromEvent(Event event) {
        return (Stage) ((Node) event.getSource()).getScene().getWindow();

    }

    protected static Scene createScene(Parent root) {
        return new Scene(root);
    }

    protected static void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }



}