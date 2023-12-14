package Controller.ShiftAssigning;

import Model.Schedules.OptimizedSchedule.OptimizedSchedule;
import Model.Staff.Worker;
import Model.Time.Week;
import Model.Time.WorkdayConfig;

import java.time.LocalTime;
import java.util.ArrayList;

public class ShiftAssigner {
    private WorkerAvailabilityManager availabilityManager;

    public ShiftAssigner(WorkerAvailabilityManager availabilityManager) {
        this.availabilityManager = availabilityManager;
    }

    public static void optimizeWeek() {

        MinuteCounter.initialize();
        OptimizedSchedule.initialize();

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


}

