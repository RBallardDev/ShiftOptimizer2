package Model.Schedules;

import Model.Staff.Worker;

import java.time.LocalTime;

public class Shift {
    private LocalTime startTime;
    private LocalTime endTime;
    //@JsonProperty("username")
    //private String userName; // or any identifier for the worker

    private Worker worker;

    private DaySchedule daySchedule;

    public Shift(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;

    }

    // Getters and setters
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

    /*public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }*/

    public DaySchedule getDay() {
        return daySchedule;
    }

    public void setDay(DaySchedule daySchedule) {
        this.daySchedule = daySchedule;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    // Additional methods as needed, like checking if a shift overlaps with another, etc.
}
