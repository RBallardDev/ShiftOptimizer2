package Model.Staff;

import com.fasterxml.jackson.annotation.JsonProperty;
import Model.Schedules.Schedule;
import Model.Time.TimeUnavailable;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;

public class Worker {
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("status")
    private String status;
    @JsonProperty("schedule")
    private Schedule schedule;

    private List<TimeUnavailable> unavailableTimes;

    //int workerID;


    public Worker() {
        this.schedule = new Schedule();
    }

    public Worker(String username, String password, String status, Schedule schedule) {
        this.username = username;
        this.password = password;
        this.status = status;

        this.schedule = schedule;

        this.schedule = new Schedule();
        this.unavailableTimes = new ArrayList<>();

    }


    public String printSchedule() {
        return schedule.printSchedule();
    }

    @Override
    public String toString() {
        return "Worker{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                '}';
    }




    public void addUnavailableTime(DayOfWeek day, LocalTime start, LocalTime end) {
        TimeUnavailable newTimeUnavailable = new TimeUnavailable(day, start, end);
        unavailableTimes.add(newTimeUnavailable);
    }



    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getUserName() {
        return username;
    }

    public String getStatus() {
        return status;
    }

    public Schedule getSchedule(){
        return schedule;
    }

    public List<TimeUnavailable> getUnavailableTimes() {
        return unavailableTimes;
    }
}
