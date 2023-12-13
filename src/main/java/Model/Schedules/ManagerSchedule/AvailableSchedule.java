package Model.Schedules.ManagerSchedule;

import java.util.ArrayList;

public class AvailableSchedule
{
AvailableDaySchedule[] availableDaySchedules = new AvailableDaySchedule[7];

    public void setDay(AvailableDaySchedule newAvailableDaySchedule, int i) {
        availableDaySchedules[i] = newAvailableDaySchedule;
    }
}
