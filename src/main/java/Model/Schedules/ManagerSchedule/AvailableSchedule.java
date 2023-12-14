package Model.Schedules.ManagerSchedule;

import Model.Time.Week;

import java.util.ArrayList;


public class AvailableSchedule
{
private static AvailableDaySchedule[] availableDaySchedules = new AvailableDaySchedule[7];

    public static void setDay(AvailableDaySchedule newAvailableDaySchedule, int i) {
        availableDaySchedules[i] = newAvailableDaySchedule;
    }

    public static AvailableDaySchedule getAvailableDaySchedule(Week.DayNames day){
        return availableDaySchedules[Week.getIndexFromDay(day)];
    }

    public static AvailableDaySchedule[] getAvailableDaySchedules(){
        return availableDaySchedules;
    }
}
