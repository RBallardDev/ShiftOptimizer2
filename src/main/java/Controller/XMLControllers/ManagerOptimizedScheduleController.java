package Controller.XMLControllers;

import Controller.ShiftAssigning.ShiftAssigner;
import Model.Schedules.OptimizedSchedule.Hour;
import Model.Schedules.OptimizedSchedule.OptimizedDaySchedule;
import Model.Schedules.OptimizedSchedule.OptimizedSchedule;
import Model.Time.Week;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import java.time.LocalTime;

public class ManagerOptimizedScheduleController {
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
        System.out.println(OptimizedSchedule.buildOptimizedScheduleString());

        for (int i = 0; i < 7; i++) {
            OptimizedDaySchedule optimizedDaySchedule = OptimizedSchedule.optimizedDaySchedules[i];

            LocalTime startTime = null;
            LocalTime endTime = null;
            String username = null;

            for (Hour hour : optimizedDaySchedule.getHours()) {
                for (int halfHour = 0; halfHour < 2; halfHour++) {
                    String currentUsername = hour.halfHours[halfHour];
                    LocalTime currentTime = LocalTime.of(hour.hourOfDay, halfHour * 30);

                    if (username == null && currentUsername != null) {
                        // Start of a new shift
                        startTime = currentTime;
                        username = currentUsername;
                    } else if (username != null && (currentUsername == null || !currentUsername.equals(username))) {
                        // End of the current shift and start of a new one or no shift
                        endTime = currentTime;
                        ObservableList<String> items = getDayListView(optimizedDaySchedule.name).getItems();
                        items.add(username + ": " + startTime + " - " + endTime);

                        if (currentUsername != null) {
                            // Start of a new shift
                            startTime = currentTime;
                            username = currentUsername;
                        } else {
                            // No shift
                            username = null;
                        }
                    }
                }
            }

            // Add the last shift if it hasn't been added
            if (username != null) {
                endTime = LocalTime.of(23, 59); // Assuming the last possible time for a shift
                ObservableList<String> items = getDayListView(optimizedDaySchedule.name).getItems();
                items.add(username + ": " + startTime + " - " + endTime);
            }
        }
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

}
