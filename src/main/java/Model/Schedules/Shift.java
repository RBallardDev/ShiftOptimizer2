package Model.Schedules;

import main.java.Model.Staff.Worker;
import main.java.Model.Time.Day;

import java.time.LocalTime;

public class Shift {
    private LocalTime startTime;
    private LocalTime endTime;
    //@JsonProperty("username")
    //private String userName; // or any identifier for the worker

    private Worker worker;

    private Day day;

    public Shift(LocalTime startTime, LocalTime endTime, Worker worker, Day day) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.worker = worker;
        this.day = day;
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

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    // Additional methods as needed, like checking if a shift overlaps with another, etc.
}
