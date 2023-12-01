package Model.Staff;

import com.fasterxml.jackson.annotation.JsonProperty;
import Model.Schedules.Schedule;
import Model.Schedules.Shift;
import Model.Schedules.ShiftConflictException;

public class Worker {
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("status")
    private String status;

    private Schedule schedule;

    //int workerID;


    public Worker() {
        this.schedule = new Schedule();
    }

    public Worker(String username, String password, String status) {
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public void addShift(Shift shift) throws ShiftConflictException {
        if (!schedule.hasConflict(shift)) {
            schedule.addShift(shift);
            shift.setWorker(this);
        } else {
            System.out.println("Shift conflict detected for worker: " + username);
        }
    }

    public void removeShift(Shift shift) {
        if (schedule.removeShift(shift)) {
            System.out.println("Shift removed for worker: " + username);
        } else {
            System.out.println("Shift not found for worker: " + username);
        }

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


    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getUserName() {
        return username;
    }

    public String getStatus() {
        return status;
    }
}
