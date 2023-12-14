package Controller.ShiftAssigning;

import Controller.File.JacksonGetter;
import Model.Staff.Worker;

import java.util.HashMap;
import java.util.List;

public class MinuteCounter {
    protected static HashMap<String, Integer> minuteCounts= new HashMap<String , Integer>();

    public static void initialize(){
        List<Worker> workers = JacksonGetter.getAllWorkers();

        for(Worker worker: workers){
            minuteCounts.put(worker.getUserName(),0);
        }
    }

    public static void addMinutes(String username, int minutesAdded){
        minuteCounts.replace(username, minuteCounts.get(username) + minutesAdded);
    }

    public static int getMinuteCount(String username){
        return minuteCounts.get(username);
    }
}
