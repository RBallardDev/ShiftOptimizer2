package Model.Schedules.ManagerSchedule;

import Controller.Time.Week;

import java.util.ArrayList;

public class AvailableDaySchedule {
    public Week.DayNames name;
    private ArrayList<AvailableShift> availableShifts = new ArrayList<>();

    public AvailableDaySchedule(Week.DayNames name){
        this.name = name;
    }

    public void addShiftToDay(AvailableShift availableShift) {
        availableShifts.add(availableShift);
    }

    public ArrayList<AvailableShift> getAvailableShifts(){
        return availableShifts;
    }
}
