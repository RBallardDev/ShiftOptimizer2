package Model.Time;

import java.time.LocalTime;

public class WorkdayConfig {
    private static LocalTime workdayStart = LocalTime.of(8, 0); // Default start time
    private static LocalTime workdayEnd = LocalTime.of(23, 0); // Default end time

    public static void setWorkdayStart(int hour, int minute) {
        workdayStart = LocalTime.of(hour, minute);
    }

    public static void setWorkdayEnd(int hour, int minute) {
        workdayEnd = LocalTime.of(hour, minute);
    }

    public static LocalTime getWorkdayStart() {
        return workdayStart;
    }

    public static LocalTime getWorkdayEnd() {
        return workdayEnd;
    }

    public static boolean isWithinWorkHours(LocalTime time) {
        return !time.isBefore(workdayStart) && !time.isAfter(workdayEnd);
    }
}
