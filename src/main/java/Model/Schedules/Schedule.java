package Model.Schedules;

import Model.Time.Day;


import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private ArrayList<Day> days = new ArrayList<>();

    public Schedule() {
        Day.DayNames[] dayNames = {Day.DayNames.Sunday, Day.DayNames.Monday, Day.DayNames.Tuesday,
                Day.DayNames.Wednesday, Day.DayNames.Thursday, Day.DayNames.Friday,
                Day.DayNames.Saturday};
        for (int i = 0; i < 7; i++) {
            days.add(new Day(dayNames[i]));
        }
    }


    // Method to check for shift conflicts

    public String printSchedule() {
        StringBuilder scheduleString = new StringBuilder();
        for (Day day : days) {
            for(Shift shift: day.getShifts())
            scheduleString.append("Shift: ")
                    .append(shift.getStartTime())
                    .append(" to ")
                    .append(shift.getEndTime())
                    .append("\n");
        }
        return scheduleString.toString();
    }

    public Day getDay(Day.DayNames day){
        for(int i = 0;i< 7;i++){
            if(days.get(i).name.equals(day)){
                return days.get(i);
            }
        }
        throw new IllegalArgumentException();
    }

    // Additional methods as needed
}