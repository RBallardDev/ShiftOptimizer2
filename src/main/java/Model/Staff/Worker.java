package Model.Staff;

import com.fasterxml.jackson.annotation.JsonProperty;
import Model.Schedules.WorkerSchedule.WorkerSchedule;

public class Worker {
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("status")
    private String status;
    @JsonProperty("schedule")
    private WorkerSchedule workerSchedule;


    //int workerID;


    public Worker() {
        this.workerSchedule = new WorkerSchedule();
    }

    public Worker(String username, String password, String status, WorkerSchedule workerSchedule) {
        this.username = username;
        this.password = password;
        this.status = status;

        this.workerSchedule = workerSchedule;

        this.workerSchedule = workerSchedule;


    }


    public String printSchedule() {
        return workerSchedule.buildUnavailableTimesScheduleString();
    }

    @Override
    public String toString() {
        return "Worker{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                '}';
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

    public WorkerSchedule getSchedule() {
        return workerSchedule;
    }

    public int getTotalScheduledMinutes() {
        return workerSchedule.getTotalScheduledMinutes();
    }


}