package Model.Schedules.OptimizedSchedule;

import Model.Time.Week;

public class OptimizedDaySchedule {
    protected Week.DayNames name;
    Hour[] hours = new Hour[24];

    public OptimizedDaySchedule(Week.DayNames name) {
        this.name = name;
        for (int i = 0; i < hours.length; i++) {
            hours[i] = new Hour(i);
        }
    }
}
