package Model.Schedules;

import Model.Time.Hour;
import Model.Time.Week;

import java.time.LocalTime;
import java.util.ArrayList;

public class DaySchedule {
    //IS PRETTY MUCH DAYSCHEDULE
    public Week.DayNames name;


    public Hour[] hours = new Hour[24];

    private ArrayList<Shift> shifts = new ArrayList<Shift>();

    public DaySchedule(Week.DayNames name) {
        this.name = name;
        for (int i = 0; i < hours.length; i++) {
            hours[i] = new Hour(i);
        }
    }


    public Hour getHour(int hourIndex) {
        if (hourIndex >= 0 && hourIndex < hours.length) {
            return hours[hourIndex];
        } else {
            throw new IllegalArgumentException("Invalid hour index: " + hourIndex);
        }
    }


    public void addShift(Shift shift) {
        shifts.add(shift);

    }

    // Method to get all shifts for the day
    public ArrayList<Shift> getShifts() {
        return shifts;
    }


    public void addShiftToDay(Shift shift) {
        LocalTime startTime = shift.getStartTime();
        LocalTime endTime = shift.getEndTime();

        // Iterate over each hour and half-hour segment within the shift duration
        for (int hour = startTime.getHour(); hour <= endTime.getHour(); hour++) {
            if (hour < hours.length) {
                Hour currentHour = hours[hour];
                for (int segment = 0; segment < 2; segment++) {
                    LocalTime segmentTime = LocalTime.of(hour, segment * 30);
                    if (!segmentTime.isBefore(startTime) && !segmentTime.plusMinutes(30).isAfter(endTime)) {
                        currentHour.setHalfHour(segment, true);
                    }
                }
            }
        }

        shifts.add(shift);

    }

    public void removeShiftFromDay(Shift shift) {
        LocalTime startTime = shift.getStartTime();
        LocalTime endTime = shift.getEndTime();

        // Iterate over each hour and half-hour segment within the shift duration
        for (int hour = startTime.getHour(); hour <= endTime.getHour(); hour++) {
            if (hour < hours.length) {
                Hour currentHour = hours[hour];
                for (int segment = 0; segment < 2; segment++) {
                    LocalTime segmentTime = LocalTime.of(hour, segment * 30);
                    if (!segmentTime.isBefore(startTime) && !segmentTime.plusMinutes(30).isAfter(endTime)) {
                        currentHour.setHalfHour(segment, false);
                    }
                }
            }
        }

        shifts.remove(shift); // Remove the shift from the list of shifts*/
    }
    public boolean hasConflict(Shift newShift) {
        for (Shift existingShift : shifts) {
            if (isSameDay(existingShift, newShift) && shiftsOverlap(existingShift, newShift)) {
                return true;
            }
        }
        return false;
    }

    //HELPER METHODS
    private boolean shiftsOverlap(Shift shift1, Shift shift2) {
        return !shift1.getEndTime().isBefore(shift2.getStartTime()) &&
                !shift2.getEndTime().isBefore(shift1.getStartTime());
    }

    private boolean isSameDay(Shift shift1, Shift shift2) {
        return shift1.getDay().equals(shift2.getDay());
    }


}
