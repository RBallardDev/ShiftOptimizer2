package Controller.ShiftAssignment;

import Controller.File.JacksonGetter;
import Model.Staff.Worker;

import java.util.HashMap;
import java.util.List;

public class MinuteCounter {
    protected static HashMap<String, Integer> minuteCounts= new HashMap<String , Integer>();
    private static final int MAX_MINUTES_PER_WEEK = 600; // 10 hours * 60 minutes

    public static void initialize(){
        List<Worker> workers = JacksonGetter.getAllWorkers();

        for(Worker worker: workers){
            minuteCounts.put(worker.getUserName(),0);
        }
    }

    public static boolean addMinutes(String username, int minutesAdded){
        int currentMinutes = getMinuteCount(username);
        if(currentMinutes + minutesAdded <= MAX_MINUTES_PER_WEEK){
            minuteCounts.replace(username, currentMinutes + minutesAdded);
            return true;
        }
        return false;
        //minuteCounts.replace(username, minuteCounts.get(username) + minutesAdded);
    }


    public static int getMinuteCount(String username){
        return minuteCounts.get(username);
    }

    public static boolean canAssignMoreMinutes(String username, int minutesToAssign) {
        return getMinuteCount(username) + minutesToAssign <= MAX_MINUTES_PER_WEEK;
    }
}
