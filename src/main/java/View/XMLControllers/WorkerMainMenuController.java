package View.XMLControllers;

import Controller.UserAuth.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WorkerMainMenuController {
    public void handleInputTimes(ActionEvent event) throws IOException {
        Parent signUpScreenRoot = FXMLLoader.load(getClass().getResource("/views/worker/worker-input-times-view.fxml"));
        Stage stage = HelperMethods.getStageFromEvent(event);
        stage.setScene(new Scene(signUpScreenRoot));
        stage.show();
    }

    public void handleViewShifts(ActionEvent event) {
    }

    public void handleViewSchedule(ActionEvent event) throws IOException {
        Parent scheduleScreenRoot = FXMLLoader.load(getClass().getResource("/views/worker/worker-schedule-view.fxml"));
        Stage stage = HelperMethods.getStageFromEvent(event);
        stage.setScene(new Scene(scheduleScreenRoot));
        stage.show();
    }

    public void handleLogOut(ActionEvent event) throws IOException {
        Session.setToken(null);
        Parent signUpScreenRoot = FXMLLoader.load(getClass().getResource("/views/start/start-view.fxml"));
        Stage stage = HelperMethods.getStageFromEvent(event);
        stage.setScene(new Scene(signUpScreenRoot));
        stage.show();
    }
}
