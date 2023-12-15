package View.XMLControllers;

import Controller.File.JacksonEditor;
import Controller.File.JacksonGetter;
import Controller.UserAuth.Session;
import Controller.UserAuth.SessionAuth;
import Model.Schedules.ManagerSchedule.AvailableDaySchedule;
import Model.Schedules.ManagerSchedule.AvailableSchedule;
import Model.Schedules.ManagerSchedule.AvailableShift;
import Controller.Time.Week;
import Model.Schedules.WorkerSchedule.TimeUnavailable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalTime;


public class ManagerInputTimesController {

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
    @FXML
    TabPane dayTabPane;

    @FXML
    private void initialize() {
        JacksonGetter.loadAvailableSchedule();
        for (AvailableDaySchedule availableDaySchedule : AvailableSchedule.getAvailableDaySchedules()) {
            for (AvailableShift availableShift : availableDaySchedule.getAvailableShifts()) {
                updateListView(availableDaySchedule.name, availableShift.getStartTime(), availableShift.getEndTime());
            }
        }
    }

    public void submit(ActionEvent event) {
        if (hasNullInputs()) {
            HelperMethods.showAlert("Invalid Input", "Must fill out all fields of the input");

        } else if (invalidInput()) {
            HelperMethods.showAlert("Invalid Input", "End of shift must be in the future of the start of shift");

        } else if (shiftsOverlap()) {
            HelperMethods.showAlert("Invalid Input", "Shifts must not overlap");
        } else {
            AvailableShift availableShift = new AvailableShift(Week.DayNames.valueOf(dayComboBox.getValue()),
                    LocalTime.of(startHourComboBox.getValue(), startMinuteComboBox.getValue()),
                    LocalTime.of(endHourComboBox.getValue(), endMinuteComboBox.getValue()));
            JacksonEditor.addAvailableShift(Week.DayNames.valueOf(dayComboBox.getValue()), availableShift);
            updateListView(Week.DayNames.valueOf(dayComboBox.getValue()), LocalTime.of(startHourComboBox.getValue(),
                    startMinuteComboBox.getValue()), LocalTime.of(endHourComboBox.getValue(), endMinuteComboBox.getValue()));
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
                JacksonEditor.removeAvailableShift(selectedDay, new AvailableShift(selectedDay, startTime,endTime));
            }        }
    }

    private boolean hasNullInputs() {
        boolean day = dayComboBox.getValue() == null;
        boolean startHour = startHourComboBox.getValue() == null;
        boolean startMinute = startMinuteComboBox.getValue() == null;
        boolean endHour = endHourComboBox.getValue() == null;
        boolean endMinute = endMinuteComboBox.getValue() == null;

        return day || startHour || startMinute || endHour || endMinute;
    }

    private boolean invalidInput() {
        return LocalTime.of(startHourComboBox.getValue(), startMinuteComboBox.getValue()).isAfter(LocalTime.of(endHourComboBox.getValue(), endMinuteComboBox.getValue()));
    }

    private boolean shiftsOverlap() {
        JacksonGetter.loadAvailableSchedule();
        AvailableDaySchedule availableDaySchedule = AvailableSchedule.getAvailableDaySchedule(Week.DayNames.valueOf(dayComboBox.getValue()));
        int startHour = startHourComboBox.getValue();
        int startMinute = startMinuteComboBox.getValue();
        int endHour = endHourComboBox.getValue();
        int endMinute = endMinuteComboBox.getValue();

        LocalTime startTime = LocalTime.of(startHour, startMinute);
        LocalTime endTime = LocalTime.of(endHour, endMinute);

        for (AvailableShift availableShift : availableDaySchedule.getAvailableShifts()) {

            boolean shiftFirst = startTime.isAfter(availableShift.getStartTime()) && startTime.isBefore(availableShift.getEndTime());
            boolean addedShiftFirst = availableShift.getStartTime().isBefore(endTime) && availableShift.getStartTime().isAfter(startTime);
            boolean shiftInAddedShift = availableShift.getStartTime().isAfter(startTime) && availableShift.getEndTime().isBefore(endTime);
            boolean addedShiftInShift = startTime.isAfter(availableShift.getStartTime()) && endTime.isBefore(availableShift.getEndTime());

            if (shiftFirst || addedShiftFirst || shiftInAddedShift || addedShiftInShift) {
                return true;
            }
        }
        return false;
    }

    public void handleBack(ActionEvent event) throws IOException {
        Parent signUpScreenRoot = FXMLLoader.load(getClass().getResource("/views/manager/manager-main-view.fxml"));
        Stage stage = HelperMethods.getStageFromEvent(event);
        stage.setScene(new Scene(signUpScreenRoot));
        stage.show();
    }
}
