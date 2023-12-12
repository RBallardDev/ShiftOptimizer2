package Model.Schedules;

import Controller.File.Jackson;
import Model.Staff.Worker;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.time.LocalTime;

public class Shift {
    private LocalTime startTime;
    private LocalTime endTime;
    //@JsonProperty("username")
    //private String userName; // or any identifier for the worker

    private Worker worker;

    private DayWorkerSchedule dayWorkerSchedule;

    public Shift(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;

    }

    // Getters and setters
    public LocalTime getStartTime() {
        return startTime;
    }

    public ArrayNode getTimesArrayAsJsonNode(){
        ObjectMapper objectMapper = Jackson.getObjectMapper();
        ArrayNode timesArray = objectMapper.createArrayNode();
        timesArray.add(startTime.getHour());
        timesArray.add(startTime.getMinute());
        timesArray.add(endTime.getHour());
        timesArray.add(endTime.getMinute());
        return timesArray;
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

    public DayWorkerSchedule getDay() {
        return dayWorkerSchedule;
    }

    public void setDay(DayWorkerSchedule dayWorkerSchedule) {
        this.dayWorkerSchedule = dayWorkerSchedule;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    // Additional methods as needed, like checking if a shift overlaps with another, etc.
}
