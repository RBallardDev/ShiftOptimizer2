package Model.Schedules.ManagerSchedule;

import Controller.File.Jackson;
import Model.Schedules.WorkerSchedule.DayWorkerSchedule;
import Model.Staff.Worker;
import Model.Time.Week;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.time.LocalTime;

public class AvailableShift {
    private LocalTime startTime;
    private LocalTime endTime;

    private Week.DayNames day;
    //@JsonProperty("username")
    //private String userName; // or any identifier for the worker

    public AvailableShift(Week.DayNames day, LocalTime startTime, LocalTime endTime) {
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



    // Additional methods as needed, like checking if a shift overlaps with another, etc.
}
