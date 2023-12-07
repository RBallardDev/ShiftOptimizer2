package Model.Schedules;

import Model.Staff.User;
import Model.Staff.Worker;
import Model.Time.Day;

import java.time.LocalTime;

public class Shift {
    private LocalTime startTime;
    private LocalTime endTime;
    //@JsonProperty("username")
    //private String userName; // or any identifier for the worker

    private User worker;

    private Day day;

    public Shift(LocalTime startTime, LocalTime endTime, User worker) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.worker = worker;

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

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public User getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    // Additional methods as needed, like checking if a shift overlaps with another, etc.
}
