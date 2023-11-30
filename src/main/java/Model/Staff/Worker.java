package Model.Staff;

import com.fasterxml.jackson.annotation.JsonProperty;
import main.java.Model.Schedules.Schedule;
import main.java.Model.Schedules.Shift;
import main.java.Model.Schedules.ShiftConflictException;

public class Worker
{
    @JsonProperty("username")
    private String userName;
    @JsonProperty("password")
    private String passWord;
    @JsonProperty("status")
    private String status;

    private Schedule schedule;

    //int workerID;




    public Worker()
    {
        this.schedule = new Schedule();
    }

    public void addShift(Shift shift) throws ShiftConflictException {
        if (!schedule.hasConflict(shift)) {
            schedule.addShift(shift);
            shift.setWorker(this);
        } else {
            System.out.println("Shift conflict detected for worker: " + userName);
        }
    }

    public void removeShift(Shift shift) {
        if (schedule.removeShift(shift)) {
            System.out.println("Shift removed for worker: " + userName);
        } else {
            System.out.println("Shift not found for worker: " + userName);
        }

    }

    public String printSchedule() {
        return schedule.printSchedule();
    }

    @Override
    public String toString() {
        return "Worker{" +
                "username='" + userName + '\'' +
                ", password='" + passWord + '\'' +
                ", status='" + status + '\'' +
                '}';
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName(){
        return userName;
    }
}
