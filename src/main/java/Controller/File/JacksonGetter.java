package Controller.File;

import Model.Schedules.Schedule;
import Model.Schedules.Shift;
import Model.Staff.Worker;
import Model.Time.Day;
import Model.Time.Week;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JacksonGetter extends Jackson {
    public static int getIndexFromUsername(String username) {
        JsonNode rootNode = getRootNode();

        for (int i = 0; i < rootNode.get("workers").size(); i++) {
            if (username.equals(rootNode.get("workers").get(i).get("username").asText())) {
                return i;
            }
        }

        return -1;
    }

    public static String getPasswordFromIndex(int i) {
        JsonNode rootNode = getRootNode();

        return rootNode.get("workers").get(i).get("password").asText();

    }

    public static List<Worker> getAllWorkers() {

        JsonNode rootNode = getRootNode();
        ObjectMapper objectMapper = getObjectMapper();
        List<Worker> workers = new ArrayList<>();

        // Assuming the workers are stored in an array under the "workers" field
        if (rootNode != null && rootNode.has("workers")) {
            JsonNode workersNode = rootNode.get("workers");
            if (workersNode.isArray()) {
                //Iterate through all the workers
                Iterator<JsonNode> iterator = workersNode.elements();
                while (iterator.hasNext()) {
                    JsonNode workerNode = iterator.next();

                    String username = workerNode.get("username").asText();
                    String password = workerNode.get("password").asText();
                    String status = workerNode.get("status").asText();
                    JsonNode schedulesNode = workerNode.path("schedules");

                    //Initialize the schedule object to fill

                    Schedule newSchedule = new Schedule();

                    //Iterate through all the schedule days
                    if (schedulesNode.isArray()) {
                        Iterator<JsonNode> iteratorSchedule = schedulesNode.elements();

                        for (int i = 0; iteratorSchedule.hasNext(); i++) {

                            //Initialize a day to fill
                            Day newDay = new Day(Week.DAY_NAMES[i]);

                            JsonNode dayScheduleNode = iteratorSchedule.next();


                            Iterator<JsonNode> iteratorShifts = dayScheduleNode.path("shifts").elements();

                            //Iterate through all the shifts
                            while (iteratorShifts.hasNext()) {
                                JsonNode shiftNode = iteratorShifts.next();
                                int[] shiftTimes = objectMapper.convertValue(shiftNode, int[].class);
                                Shift shift = new Shift(LocalTime.of(shiftTimes[0], shiftTimes[1]), LocalTime.of(shiftTimes[2],shiftTimes[3]));
                                newDay.addShiftToDay(shift);
                            }
                            newSchedule.setDay(newDay, i);
                        }
                    }


                    Worker worker = new Worker(username, password, status, newSchedule);
                    System.out.println(newSchedule.printSchedule());
                    workers.add(worker);
                }


            }
        }
        return workers;
    }

    public static Worker getWorkerByUsername(String username) {
        List<Worker> workers = getAllWorkers();
        for (Worker worker : workers) {
            if (worker.getUserName().equals(username)) {
                return worker;
            }
        }
        return null; // or throw an exception if the worker is not found
    }

    public static String getStatusByUsername(String username) {
        List<Worker> workers = getAllWorkers();
        for (Worker worker : workers) {
            if (worker.getUserName().equals(username)) {
                return worker.getStatus();
            }
        }
        return null; // or throw an exception if the worker is not found
    }

}
