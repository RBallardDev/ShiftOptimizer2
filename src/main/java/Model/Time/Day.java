package Model.Time;

import Model.Schedules.Shift;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Day {
    public String name;
    public Hour[] hours = new Hour[24];

    private List<Shift> shifts = new ArrayList<>();

    public Day(String name) {
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
    public List<Shift> getShifts() {
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

        shifts.remove(shift); // Remove the shift from the list of shifts
    }
}
