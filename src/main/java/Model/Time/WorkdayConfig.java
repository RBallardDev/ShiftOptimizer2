package Model.Time;

import Controller.File.Jackson;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.time.LocalTime;

public class WorkdayConfig {
    private static LocalTime workdayStart = LocalTime.of(8, 0); // Default start time
    private static LocalTime workdayEnd = LocalTime.of(23, 0); // Default end time

    public static void setWorkdayStart(int hour, int minute) {
        JsonNode rootNode = Jackson.getRootNode();
        JsonNode configNode = rootNode.get("workday-config");
        int[] workdayStart = new int[]{hour, minute};
        ((ObjectNode) configNode).put("workday-start-hour", hour);
        ((ObjectNode) configNode).put("workday-start-minute", minute);

    }

    public static void setWorkdayEnd(int hour, int minute) {
        JsonNode rootNode = Jackson.getRootNode();
        JsonNode configNode = rootNode.get("workday-config");
        int[] workdayStart = new int[]{hour, minute};
        ((ObjectNode) configNode).put("workday-end-hour", hour);
        ((ObjectNode) configNode).put("workday-end-minute", minute);
    }

    public static LocalTime getWorkdayStart() {

        JsonNode rootNode = Jackson.getRootNode();
        JsonNode configNode = rootNode.get("workday-config");
        return LocalTime.of(configNode.get("workday-start-hour").asInt(),configNode.get("workday-start-minute").asInt());
    }

    public static LocalTime getWorkdayEnd() {
        JsonNode rootNode = Jackson.getRootNode();
        JsonNode configNode = rootNode.get("workday-config");
        return LocalTime.of(configNode.get("workday-end-hour").asInt(),configNode.get("workday-end-minute").asInt());
    }

    public static boolean isWithinWorkHours(LocalTime time) {
        return !time.isBefore(workdayStart) && !time.isAfter(workdayEnd);
    }
}
