package Model.Schedules.OptimizedSchedule;

import Controller.Time.Week;

import java.time.LocalTime;

public class OptimizedSchedule {
    public static OptimizedDaySchedule[] optimizedDaySchedules = new OptimizedDaySchedule[7];

    public static void initialize() {
        for (int i = 0; i < 7; i++) {
            optimizedDaySchedules[i] = new OptimizedDaySchedule(Week.DAY_NAMES[i]);
        }
    }

    public static void setNameToShift(String username, Week.DayNames day, LocalTime time) {
        int halfHourSegment = -1;
        if (time.getMinute() == 30) {
            halfHourSegment = 1;
        } else if (time.getMinute() == 0) {
            halfHourSegment = 0;
        }
        optimizedDaySchedules[Week.getIndexFromDay(day)].hours[time.getHour()].halfHours[halfHourSegment] = username;
    }

    public static String buildOptimizedScheduleString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Optimized Schedule:" + "\n");
        for(OptimizedDaySchedule optimizedDaySchedule : optimizedDaySchedules){
            stringBuilder.append(optimizedDaySchedule.name + ":" + "\n");
                for(Hour hour: optimizedDaySchedule.hours){
                    stringBuilder.append(hour.hourOfDay + ":00" + " is covered by " + hour.halfHours[0] + "\n");
                    stringBuilder.append(hour.hourOfDay + ":30" + " is covered by " + hour.halfHours[1] + "\n");

                }
        }
        return stringBuilder.toString();
    }
}
