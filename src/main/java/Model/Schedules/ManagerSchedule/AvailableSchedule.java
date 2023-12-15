package Model.Schedules.ManagerSchedule;

import Controller.Time.Week;


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
