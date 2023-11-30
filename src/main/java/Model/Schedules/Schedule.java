package Model.Schedules;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private List<Shift> shifts;

    public Schedule() {
        this.shifts = new ArrayList<>();
    }

    public boolean addShift(Shift shift) throws ShiftConflictException {
        if (!hasConflict(shift)) {
            shifts.add(shift);
            shift.getDay().addShiftToDay(shift);// Also add the shift to the specific day
            return true;
        } else {
            throw new ShiftConflictException("Shift conflict detected for " + shift);
        }
    }

    public boolean removeShift(Shift shift) {
        if (shifts.remove(shift)) {
            shift.getDay().removeShiftFromDay(shift); // Update the day
            return true;
        }
        return false;
    }

    public List<Shift> getShifts() {
        return new ArrayList<>(shifts); // Return a copy to prevent external modification
    }

    // Method to check for shift conflicts
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




    public String printSchedule() {
        StringBuilder scheduleString = new StringBuilder();
        for (Shift shift : shifts) {
            scheduleString.append("Shift: ")
                    .append(shift.getStartTime())
                    .append(" to ")
                    .append(shift.getEndTime())
                    .append("\n");
        }
        return scheduleString.toString();
    }

    // Additional methods as needed
}