package Controller.File;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import Model.Staff.Worker;

import java.util.ArrayList;
import java.util.List;

public class JacksonGetter extends Jackson{
    public static int getIndexFromUsername(String username) {
        JsonNode rootNode = getRootNode();

        for (int i = 0; i < rootNode.get("workers").size(); i++) {
            if (username.equals(rootNode.get("workers").get(i).get("username").asText())) {
                return i;
            }
        }
        return -1;
    }

    public static String getPasswordFromIndex(int i){
        JsonNode rootNode = getRootNode();

        return rootNode.get("workers").get(i).get("password").asText();

    }

    public static List<Worker> getAllWorkers() {
        try {
            JsonNode rootNode = getRootNode();
            ObjectMapper objectMapper = getObjectMapper();
            // Assuming the workers are stored in an array under the "workers" field
            if (rootNode != null && rootNode.has("workers")) {
                return objectMapper.convertValue(
                        rootNode.get("workers"),
                        new TypeReference<List<Worker>>() {}
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
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


}
