package Controller.ShiftAssignment;

import Controller.File.JacksonGetter;
import Model.Schedules.ManagerSchedule.AvailableDaySchedule;
import Model.Schedules.ManagerSchedule.AvailableSchedule;
import Model.Schedules.ManagerSchedule.AvailableShift;
import Model.Schedules.OptimizedSchedule.OptimizedSchedule;
import Model.Staff.Worker;
import Controller.Time.Week;
import Controller.Time.WorkdayConfig;

import java.time.LocalTime;
import java.util.ArrayList;

public class ShiftAssigner {

    public ShiftAssigner() {
    }

    public static void optimizeWeek() {

        MinuteCounter.initialize();
        OptimizedSchedule.initialize();
        JacksonGetter.loadAvailableSchedule();

        for (Week.DayNames day : Week.DAY_NAMES) {
            optimizeDay(day);
        }
    }

    public static void optimizeDay(Week.DayNames day) {


        //Getting start of day
        LocalTime workdayStart = WorkdayConfig.getWorkdayStart();
        int startHour = workdayStart.getHour();
        int startMinutes = workdayStart.getMinute();

        //Getting end of day
        LocalTime workdayEnd = WorkdayConfig.getWorkdayEnd();
        int endHour = workdayEnd.getHour();
        int endMinutes = workdayEnd.getMinute();

        //Getting the moving segment
        LocalTime currentWorkTimeSegment = workdayStart;


        //Going through all the half hour segments to assign the workshift to the employee with the least hours
        for (int i = startHour; i < endHour; i++) {
            //Only look through the hours specified for shifts

            if (timeSegmentIsAShift(day, currentWorkTimeSegment)) {
                //For the first runthrough if there is a half hour segment
                if (i == startHour && startMinutes != 0) {

                    //Get the list of available workers for that time
                    Worker minWorker = findWorkerToShift(currentWorkTimeSegment, day);

                    //From the list, have a way to organize who gets the shift
                    setWorkerToShift(minWorker, day, currentWorkTimeSegment);
                    currentWorkTimeSegment = currentWorkTimeSegment.plusMinutes(30);


                } else {

                    //For all the other hours to loop through

                    //FOR THE FIRST HALF OF THE HOUR
                    //Here you can change the segment lengths
                    Worker minWorker = findWorkerToShift(currentWorkTimeSegment, day);


                    //Give the shift to the lowest hour and add the minutes to the counter
                    setWorkerToShift(minWorker, day, currentWorkTimeSegment);
                    currentWorkTimeSegment = currentWorkTimeSegment.plusMinutes(30);


                    //FOR THE SECOND HALF OF THE HOUR
                    //If that person is available for the next shift, give it to them as well

                    if (minWorker != null && WorkerAvailabilityManager.workerAvailable(minWorker, LocalTime.of(i, 30), day)) {
                        setWorkerToShift(minWorker, day, currentWorkTimeSegment);
                        currentWorkTimeSegment = currentWorkTimeSegment.plusMinutes(30);


                    } else {

                        minWorker = findWorkerToShift(currentWorkTimeSegment, day);

                        setWorkerToShift(minWorker, day, currentWorkTimeSegment);
                        currentWorkTimeSegment = currentWorkTimeSegment.plusMinutes(30);

                    }

                    //Go once more for that last half hour
                    if (i == endHour - 1 && currentWorkTimeSegment.getMinute() == 30 && endMinutes != 0) {

                        //Get the list of available workers for that time
                        minWorker = findWorkerToShift(currentWorkTimeSegment, day);

                        //From the list, have a way to organize who gets the shift
                        setWorkerToShift(minWorker, day, currentWorkTimeSegment);
                        currentWorkTimeSegment = currentWorkTimeSegment.plusMinutes(30);


                    }
                    //Check for both half hour segments
                }
            } else {
                //Time segment is not a shift
                currentWorkTimeSegment = currentWorkTimeSegment.plusMinutes(30);

            }
        }


    }

    public static Worker findWorkerToShift(LocalTime currentWorkTimeSegment, Week.DayNames day) {
        ArrayList<Worker> availableWorkers = WorkerAvailabilityManager.avalilableWorkers(currentWorkTimeSegment, day);
        if (availableWorkers.size() != 0) {
            String minUsername = availableWorkers.get(0).getUserName();
            int minMinutes = MinuteCounter.getMinuteCount(availableWorkers.get(0).getUserName());
            Worker minWorker = availableWorkers.get(0);

            for (Worker worker : availableWorkers) {
                if (MinuteCounter.getMinuteCount(worker.getUserName()) < minMinutes) {
                    minMinutes = MinuteCounter.getMinuteCount(worker.getUserName());
                    minUsername = worker.getUserName();
                    minWorker = worker;
                }
            }

            return minWorker;
        }
        return null;
    }

    public static void setWorkerToShift(Worker minWorker, Week.DayNames day, LocalTime currentWorkTimeSegment) {
        if (minWorker != null) {


            OptimizedSchedule.setNameToShift(minWorker.getUserName(), day, currentWorkTimeSegment);
            MinuteCounter.addMinutes(minWorker.getUserName(), 30);
        }
    }

    public static boolean timeSegmentIsAShift(Week.DayNames day, LocalTime startTime) {
        AvailableDaySchedule availableDaySchedule = AvailableSchedule.getAvailableDaySchedule(day);

        //Iterate through all of the shifts to check if the time segment is an actual shift

        LocalTime endTime = startTime.plusMinutes(30);
        for (AvailableShift availableShift : availableDaySchedule.getAvailableShifts()) {
            if (timeSegmentOverlaps(availableShift, startTime, endTime)) {
                return true;
            }
        }
        return false;
    }

    public static boolean timeSegmentOverlaps(AvailableShift availableShift, LocalTime startTime, LocalTime endTime) {
        boolean touching = availableShift.getStartTime().equals(startTime) || availableShift.getEndTime().equals(endTime);
        boolean inside = availableShift.getStartTime().isBefore(startTime) && availableShift.getEndTime().isAfter(endTime);

        return touching || inside;
    }
}

