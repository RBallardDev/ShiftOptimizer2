package Controller.ShiftAssigning;


import Controller.File.JacksonGetter;
import Model.Schedules.WorkerSchedule.DayWorkerSchedule;
import Model.Schedules.WorkerSchedule.TimeUnavailable;
import Model.Schedules.WorkerSchedule.WorkerSchedule;
import Model.Staff.Worker;
import Model.Time.Week;

import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class WorkerAvailabilityManager {
    public static ArrayList<Worker> avalilableWorkers(LocalTime time, Week.DayNames day) {
        ArrayList<Worker> availableWorkers = new ArrayList<>();
        List<Worker> allWorkers = JacksonGetter.getAllWorkers();
        for (Worker worker : allWorkers) {
            if (workerAvailable(worker, time, day)) {
                availableWorkers.add(worker);
            }
        }
        return availableWorkers;
    }

    public static boolean workerAvailable(Worker worker, LocalTime startTime, Week.DayNames day) {
        WorkerSchedule workerSchedule = worker.getSchedule();

        //Here is where you can change the time increments
        LocalTime endTime = startTime.plusMinutes(30);
        DayWorkerSchedule daySchedule = workerSchedule.getDaySchedule(day);
        ArrayList<TimeUnavailable> timesUnavailable = daySchedule.getTimesUnavailable();

        //Going through all the unavailabletimes and checking if they overlap
        for (TimeUnavailable timeUnavailable : timesUnavailable) {
            if(endTime.isAfter(timeUnavailable.getStartTime())||startTime.isBefore(timeUnavailable.getEndTime())){
                return false;
            }
        }
        return true;
    }
}

