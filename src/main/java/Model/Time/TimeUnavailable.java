package Model.Time;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class TimeUnavailable {
    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;

    public TimeUnavailable(DayOfWeek day, LocalTime startTime, LocalTime endTime) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters and Setters
    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    // Additional methods for overlap checks, etc., can be added here
}
