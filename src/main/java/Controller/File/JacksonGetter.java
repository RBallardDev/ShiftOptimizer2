package Controller.File;

import Model.Staff.Worker;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JacksonGetter extends Jackson{
    public static int getIndexFromUsername(String username) {
        JsonNode rootNode = getRootNode();

        for (int i = 0; i < rootNode.get("users").size(); i++) {
            if (username.equals(rootNode.get("users").get(i).get("username").asText())) {
                return i;
            }
        }

        return -1;
    }

    public static String getPasswordFromIndex(int i){
        JsonNode rootNode = getRootNode();

        return rootNode.get("users").get(i).get("password").asText();

    }

    public static List<Worker> getAllWorkers() {

            JsonNode rootNode = getRootNode();
            ObjectMapper objectMapper = getObjectMapper();
            List<Worker> users = new ArrayList<>();

            // Assuming the workers are stored in an array under the "workers" field
            if (rootNode != null && rootNode.has("users")) {
                JsonNode usersNode = rootNode.get("users");
                if (usersNode.isArray()) {
                    Iterator<JsonNode> iterator = usersNode.elements();
                    while (iterator.hasNext()) {
                        JsonNode userNode = iterator.next();

                        String username = userNode.get("username").asText();
                        String password = userNode.get("password").asText();
                        String status = userNode.get("status").asText();

                        Worker user = new Worker(username, password, status);
                        users.add(user);
                    }


                }
            }
        return users;
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

    public static String getStatusByUsername(String username){
        List<Worker> workers = getAllWorkers();
        for (Worker worker : workers) {
            if (worker.getUserName().equals(username)) {
                return worker.getStatus();
            }
        }
        return null; // or throw an exception if the worker is not found
    }

}
