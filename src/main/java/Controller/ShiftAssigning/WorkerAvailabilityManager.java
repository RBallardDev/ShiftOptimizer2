package Controller.ShiftAssigning;


import Controller.File.JacksonGetter;
import Model.Staff.Worker;
import Model.Time.Week;

import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class WorkerAvailabilityManager {
    public static ArrayList<Worker> AvalilableWorkers(LocalTime time, Week.DayNames day){
        ArrayList<Worker> availableWorkers = new ArrayList<>();
        List<Worker> allWorkers = JacksonGetter.getAllWorkers();
        for(Worker worker: allWorkers){
            if(WorkerAvailable(worker, time, day)){
                availableWorkers.add(worker);
            }
        }
        return availableWorkers;
    }

    public static boolean WorkerAvailable(Worker worker, LocalTime time, Week.DayNames day){

    }
}

