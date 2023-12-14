package Controller.XMLControllers;

import Controller.File.JacksonEditor;
import Controller.File.JacksonGetter;
import Controller.UserAuth.SessionAuth;
import Model.Schedules.WorkerSchedule.DayWorkerSchedule;
import Model.Schedules.WorkerSchedule.TimeUnavailable;
import Model.Schedules.WorkerSchedule.WorkerSchedule;
import Model.Staff.Worker;
import Model.Time.Week;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalTime;

public class WorkerInputTimesController {

    @FXML
    private ComboBox<String> dayComboBox;

    @FXML
    private ComboBox<Integer> startHourComboBox;

    @FXML
    private ComboBox<Integer> startMinuteComboBox;

    @FXML
    private ComboBox<Integer> endHourComboBox;

    @FXML
    private ComboBox<Integer> endMinuteComboBox;
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

    @FXML TabPane dayTabPane;

    @FXML
    private void initialize(){
        Worker worker = JacksonGetter.getWorkerByUsername(SessionAuth.authenticateToken(Session.getToken()));
        WorkerSchedule workerSchedule = worker.getSchedule();
        for(DayWorkerSchedule dayWorkerSchedule :workerSchedule.dayWorkerSchedules){
            for(TimeUnavailable timeUnavailable: dayWorkerSchedule.getTimesUnavailable()){
                updateListView(dayWorkerSchedule.name, timeUnavailable.getStartTime(), timeUnavailable.getEndTime());
            }
        }

    }
    private void updateListView(Week.DayNames dayName, LocalTime startTime, LocalTime endTime) {
        String entry = String.format("%02d:%02d - %02d:%02d",
                startTime.getHour(), startTime.getMinute(),
                endTime.getHour(), endTime.getMinute());

        // Determine the appropriate ListView based on the day
        ListView<String> dayListView = getDayListView(dayName);
        if (dayListView != null) {
            dayListView.getItems().add(entry);
            clearInputFields();
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
    private void clearInputFields() {
        // Clear input fields after submission
        dayComboBox.getSelectionModel().clearSelection();
        startHourComboBox.getSelectionModel().clearSelection();
        startMinuteComboBox.getSelectionModel().clearSelection();
        endHourComboBox.getSelectionModel().clearSelection();
        endMinuteComboBox.getSelectionModel().clearSelection();
    }




    public void submit(ActionEvent event) {


        if (hasNullInputs()) {
            HelperMethods.showAlert("Invalid Input", "Must fill out all fields of the input");

        } else if(invalidInput()){
            HelperMethods.showAlert("Invalid Input", "End of shift must be in the future of the start of shift");

        }else {
            TimeUnavailable timeUnavailable = new TimeUnavailable(Week.DayNames.valueOf(dayComboBox.getValue()),
                    LocalTime.of(startHourComboBox.getValue(), startMinuteComboBox.getValue()),
                    LocalTime.of(endHourComboBox.getValue(), endMinuteComboBox.getValue()));
            JacksonEditor.addTimeUnavailable(SessionAuth.authenticateToken(Session.getToken()),
                    Week.DayNames.valueOf(dayComboBox.getValue()), timeUnavailable);

            updateListView(Week.DayNames.valueOf(dayComboBox.getValue()),LocalTime.of(startHourComboBox.getValue(),
                    startMinuteComboBox.getValue()),LocalTime.of(endHourComboBox.getValue(), endMinuteComboBox.getValue()));
        }
    }

    private boolean hasNullInputs(){
        boolean day = dayComboBox.getValue() == null;
        boolean startHour = startHourComboBox.getValue() == null;
        boolean startMinute = startMinuteComboBox.getValue() == null;
        boolean endHour = endHourComboBox.getValue() == null;
        boolean endMinute = endMinuteComboBox.getValue() == null;

        return day||startHour||startMinute||endHour||endMinute;
    }

    private boolean invalidInput(){
       return LocalTime.of(startHourComboBox.getValue(), startMinuteComboBox.getValue()).isAfter(LocalTime.of(endHourComboBox.getValue(), endMinuteComboBox.getValue()));
    }

    public void removeSelected(ActionEvent event) throws IOException {

        Tab selectedTab = dayTabPane.getSelectionModel().getSelectedItem();



        // Remove the selected item from the currently selected day's ListView
        Week.DayNames selectedDay = Week.DayNames.valueOf(selectedTab.getText());
        ListView<String> selectedDayListView = getDayListView(selectedDay);
        String selectedItem = selectedDayListView.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            String[] timeComponents = selectedItem.split(" - ");
            if (timeComponents.length == 2) {
                String startTimeStr = timeComponents[0];
                String endTimeStr = timeComponents[1];

                // Parse start and end times
                LocalTime startTime = LocalTime.parse(startTimeStr);
                LocalTime endTime = LocalTime.parse(endTimeStr);

                // Now you have startTime and endTime as LocalTime objects
                // You can use them as needed

                // Remove the selected item from the ListView
                selectedDayListView.getItems().remove(selectedItem);
                JacksonEditor.removeTimeAvailable(SessionAuth.authenticateToken(Session.getToken()),selectedDay, new TimeUnavailable(selectedDay, startTime,endTime));
            }        }

    }
}
