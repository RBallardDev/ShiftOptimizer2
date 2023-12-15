package View.XMLControllers;

import Controller.File.JacksonGetter;
import Controller.ShiftAssignment.ShiftAssigner;
import Controller.Time.Week;
import Controller.UserAuth.Session;
import Controller.UserAuth.SessionAuth;
import Model.Schedules.OptimizedSchedule.Hour;
import Model.Schedules.OptimizedSchedule.OptimizedDaySchedule;
import Model.Schedules.OptimizedSchedule.OptimizedSchedule;
import Model.Staff.Worker;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;



import java.io.IOException;
import java.time.LocalTime;

public class WorkerScheduleViewController {

    @FXML
    private ListView<String> sundayListView;

    @FXML
    private ListView<String> mondayListView;

    @FXML
    private ListView<String> tuesdayListView;

    @FXML
    private ListView<String> wednesdayListView;

    @FXML
    private ListView<String> thursdayListView;

    @FXML
    private ListView<String> fridayListView;

    @FXML
    private ListView<String> saturdayListView;



    @FXML
    private void initialize() {
        ShiftAssigner.optimizeWeek();
        Worker currentWorker = JacksonGetter.getWorkerByUsername(SessionAuth.authenticateToken(Session.getToken()));

        for (int i = 0; i < 7; i++) {
            OptimizedDaySchedule optimizedDaySchedule = OptimizedSchedule.optimizedDaySchedules[i];

            LocalTime startTime = null;
            LocalTime endTime = null;
            String username = null;

            for (Hour hour : optimizedDaySchedule.getHours()) {
                for (int halfHour = 0; halfHour < 2; halfHour++) {
                    String currentUsername = hour.halfHours[halfHour];
                    LocalTime currentTime = LocalTime.of(hour.hourOfDay, halfHour * 30);

                    if (currentUsername != null && currentUsername.equals(currentWorker.getUserName())) {
                        if (username == null) {
                            // Start of a new shift
                            startTime = currentTime;
                            username = currentUsername;
                        } else if (!currentUsername.equals(username)) {
                            // End of the current shift
                            endTime = currentTime;
                            addShiftToDayListView(optimizedDaySchedule.name, startTime, endTime);
                            startTime = null; // Reset for the next shift
                        }
                    } else if (username != null) {
                        // End of the current shift
                        endTime = currentTime;
                        addShiftToDayListView(optimizedDaySchedule.name, startTime, endTime);
                        startTime = null; // Reset for the next shift
                        username = null;
                    }
                }
            }

            // Add the last shift if it hasn't been added
            if (username != null) {
                endTime = LocalTime.of(23, 59); // Assuming the last possible time for a shift
                addShiftToDayListView(optimizedDaySchedule.name, startTime, endTime);
            }
        }
    }

    private void addShiftToDayListView(Week.DayNames dayName, LocalTime startTime, LocalTime endTime) {
        ObservableList<String> items = getDayListView(dayName).getItems();
        items.add(startTime + " - " + endTime);
    }


    private ListView<String> getDayListView(Week.DayNames dayName) {
        switch (dayName) {
            case Sunday:
                return sundayListView;
            case Monday:
                return mondayListView;
            case Tuesday:
                return tuesdayListView;
            case Wednesday:
                return wednesdayListView;
            case Thursday:
                return thursdayListView;
            case Friday:
                return fridayListView;
            case Saturday:
                return saturdayListView;
            default:
                return null;
        }
    }

    public void handleBack(ActionEvent event) throws IOException {
        Parent signUpScreenRoot = FXMLLoader.load(getClass().getResource("/views/worker/worker-main-view.fxml"));
        Stage stage = HelperMethods.getStageFromEvent(event);
        stage.setScene(new Scene(signUpScreenRoot));
        stage.show();
    }
}

