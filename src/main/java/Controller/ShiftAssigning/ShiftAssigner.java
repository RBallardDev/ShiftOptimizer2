package Controller.ShiftAssigning;

import Model.Schedules.ManagerSchedule.AvailableShift;
import Model.Schedules.WorkerSchedule.TimeUnavailable;
import Model.Staff.Worker;

import java.time.LocalTime;
import java.util.List;

public class ShiftAssigner {
    private WorkerAvailabilityManager availabilityManager;

    public ShiftAssigner(WorkerAvailabilityManager availabilityManager) {
        this.availabilityManager = availabilityManager;
    }

    public void assignShifts(List<AvailableShift> shifts) {
        for (AvailableShift shift : shifts) {
            // Assuming each shift has a start time and end time
            LocalTime startTime = shift.getStartTime();
            LocalTime endTime = shift.getEndTime();

            // Iterate over each half-hour segment in the shift
            while (!startTime.isAfter(endTime)) {
                List<Worker> availableWorkers = availabilityManager.getAvailableWorkersForSegment(startTime);
                // Logic to assign shift to one of the available workers
                // Update the shift and worker's schedule accordingly

                startTime = startTime.plusMinutes(30); // Move to the next segment
            }
        }
    }
}
