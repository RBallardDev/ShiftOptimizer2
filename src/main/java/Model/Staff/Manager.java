package Model.Staff;

import Model.Schedules.ManagerSchedule.AvailableSchedule;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Manager
{
    public static AvailableSchedule availableSchedule;

    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;

    public Manager(){

    }

    public Manager(String username, String password){
        this.username = username;
        this.password = password;
    }
}
