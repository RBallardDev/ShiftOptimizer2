package Model.Schedules.WorkerSchedule;

import Model.Schedules.OptimizedSchedule.Hour;
import Model.Time.Week;

import java.time.LocalTime;
import java.util.ArrayList;

public class DayWorkerSchedule {
    //IS PRETTY MUCH DAYSCHEDULE
    public Week.DayNames name;


    public Hour[] hours = new Hour[24];

    private ArrayList<TimeUnavailable> timesUnavailable = new ArrayList<TimeUnavailable>();

    public DayWorkerSchedule(Week.DayNames name) {
        this.name = name;
    }







    // Method to get all shifts for the day
    public ArrayList<TimeUnavailable> getTimesUnavailable() {
        return timesUnavailable;
    }


    public void addTimeUnavailableToDay(TimeUnavailable timeUnavailable) {
        LocalTime startTime = timeUnavailable.getStartTime();
        LocalTime endTime = timeUnavailable.getEndTime();

        // Iterate over each hour and half-hour segment within the shift duration
//        for (int hour = startTime.getHour(); hour <= endTime.getHour(); hour++) {
//            if (hour < hours.length) {
//                Hour currentHour = hours[hour];
//                for (int segment = 0; segment < 2; segment++) {
//                    LocalTime segmentTime = LocalTime.of(hour, segment * 30);
//                    if (!segmentTime.isBefore(startTime) && !segmentTime.plusMinutes(30).isAfter(endTime)) {
//                        currentHour.setHalfHour(segment, true);
//                    }
//                }
//            }
//        }

        timesUnavailable.add(timeUnavailable);

    }

    public void removeTimeUnavailableFromDay(TimeUnavailable timeUnavailable) {
        LocalTime startTime = timeUnavailable.getStartTime();
        LocalTime endTime = timeUnavailable.getEndTime();

        // Iterate over each hour and half-hour segment within the shift duration
//        for (int hour = startTime.getHour(); hour <= endTime.getHour(); hour++) {
//            if (hour < hours.length) {
//                Hour currentHour = hours[hour];
//                for (int segment = 0; segment < 2; segment++) {
//                    LocalTime segmentTime = LocalTime.of(hour, segment * 30);
//                    if (!segmentTime.isBefore(startTime) && !segmentTime.plusMinutes(30).isAfter(endTime)) {
//                        currentHour.setHalfHour(segment, false);
//                    }
//                }
//            }
//        }

        timesUnavailable.remove(timeUnavailable); // Remove the shift from the list of shifts*/
    }


}
