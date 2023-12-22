package Controller.ShiftAssignment;


import Controller.File.JacksonGetter;
import Model.Schedules.WorkerSchedule.DayWorkerSchedule;
import Model.Schedules.WorkerSchedule.TimeUnavailable;
import Model.Schedules.WorkerSchedule.WorkerSchedule;
import Model.Staff.Worker;
import Controller.Time.Week;

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
        LocalTime endTime = startTime.plusMinutes(30);
        DayWorkerSchedule daySchedule = workerSchedule.getDaySchedule(day);
        ArrayList<TimeUnavailable> timesUnavailable = daySchedule.getTimesUnavailable();

        // Check if the worker is already scheduled for the time and if adding more time exceeds the limit
        if (isTimeUnavailable(timesUnavailable, startTime, endTime) || !MinuteCounter.canAssignMoreMinutes(worker.getUserName(), 30)) {
            return false;
        }
        return true;
    }

    private static boolean isTimeUnavailable(ArrayList<TimeUnavailable> timesUnavailable, LocalTime startTime, LocalTime endTime) {
        for (TimeUnavailable timeUnavailable : timesUnavailable) {
            if (timesOverlap(timeUnavailable, startTime, endTime)) {
                return true;
            }
        }
        return false;
    }


    public static boolean timesOverlap(TimeUnavailable timeUnavailable, LocalTime startTime, LocalTime endTime) {

        boolean touching = timeUnavailable.getStartTime().equals(startTime) || timeUnavailable.getEndTime().equals(endTime);
        boolean inside = timeUnavailable.getStartTime().isBefore(startTime) && timeUnavailable.getEndTime().isAfter(endTime);

        return touching || inside;
    }
}

