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

            for(Hour hour :optimizedDaySchedule.getHours()){

                //Check for the first half of the hour -------------------------------------------
                if(username != null){

                    //username not null means that the last time segment was the same person
                    if(hour.halfHours[0] == null){
                        //current time segment means that shift ends here
                        endTime = LocalTime.of(hour.hourOfDay, 0);


                        //Put the result to the list
                        ObservableList<String> items = getDayListView(optimizedDaySchedule.name).getItems();
                        items.add(username + ": " + startTime + " - " + endTime);
                        //set the username to null

                        username = null;

                    }else if(hour.halfHours[0].equals(username)){
                        //shift continues further than this
                    }else if(!hour.halfHours[0].equals(username)){
                        //If another person continues a shift right after then set the end time
                        endTime = LocalTime.of(hour.hourOfDay, 0);

                        //Put the result to the list
                        ObservableList<String> items = getDayListView(optimizedDaySchedule.name).getItems();
                        items.add(username + ": " + startTime + " - " + endTime);

                        username = hour.halfHours[0];

                        items = getDayListView(optimizedDaySchedule.name).getItems();
                        items.add(username + ": " + startTime + " - " + endTime);
                        startTime = LocalTime.of(hour.hourOfDay, 0);
                        username = hour.halfHours[0];


                    }
                }
                //If the username is null, this is beginning a new shift
                if(hour.halfHours[0] != null){

                    startTime = LocalTime.of(hour.hourOfDay, 30);
                    username = hour.halfHours[0];
                }

                //Check for the second half of the hour -----------------------------------------------------
                if(username != null){
                    //username not null means that the last time segment was the same person
                    if(hour.halfHours[1] == null){
                        //current time segment means that shift ends here
                        endTime = LocalTime.of(hour.hourOfDay, 30);

                        //Put the result to the list
                        ObservableList<String> items = getDayListView(optimizedDaySchedule.name).getItems();
                        items.add(username + ": " + startTime + " - " + endTime);
                        //set the username to null

                        username = null;
                    }else if(hour.halfHours[1].equals(username)){
                        //shift continues further than this
                    }else if(!hour.halfHours[1].equals(username)){
                        //If another person continues a shift right after then set the end time
                        endTime = LocalTime.of(hour.hourOfDay, 30);

                        //Put the result to the list
                        ObservableList<String> items = getDayListView(optimizedDaySchedule.name).getItems();
                        items.add(username + ": " + startTime + " - " + endTime);

                        username = hour.halfHours[1];

                        items = getDayListView(optimizedDaySchedule.name).getItems();
                        items.add(username + ": " + startTime + " - " + endTime);
                        startTime = LocalTime.of(hour.hourOfDay, 30);
                        username = hour.halfHours[1];

                    }
                }
                //If the username is null, this is beginning a new shift
                if(hour.halfHours[1] != null){
                    System.out.println("HI");

                    startTime = LocalTime.of(hour.hourOfDay, 30);
                    username = hour.halfHours[1];
                }

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
