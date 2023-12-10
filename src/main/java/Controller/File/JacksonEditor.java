package Controller.File;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import static Controller.BouncyCastleEncrypter.hashPassword;


public class JacksonEditor extends Jackson {

    public static void addWorker(String username, String password, String status) {
        //Have to check for duplicate usernames

        // I updated what you wrote here
        JsonNode rootNode = getRootNode();
        ObjectMapper objectMapper = getObjectMapper();
        ObjectWriter objectWriter = getObjectWriter();

        // Check if username already exists
        boolean usernameExists = false;
        JsonNode workersNode = rootNode.path("workers");
        for (JsonNode workerNode : workersNode) {
            if (workerNode.path("username").asText().equals(username)) {
                usernameExists = true;
                break;
            }
        }



        // Add new worker only if username is unique
        if (!usernameExists) {
            String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

            ArrayNode newSchedule = objectMapper.createArrayNode();

            for(String day : daysOfWeek){
                JsonNode newDaySchedule = objectMapper.createObjectNode().put("day-name", day)
                        .set("shifts", objectMapper.createArrayNode());
                newSchedule.add(newDaySchedule);
            }



            JsonNode newWorker = objectMapper.createObjectNode()
                    .put("username", username)
                    .put("password", hashPassword(password))
                    .put("status", status)
                    .set("schedules", newSchedule);



            ((com.fasterxml.jackson.databind.node.ArrayNode) workersNode).add(newWorker);

            try {
                objectWriter.writeValue(getJsonFile(), rootNode);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("Could not add worker");
        }

    }


    public static void removeWorker(String username) {
        JsonNode rootNode = getRootNode();
        ObjectMapper objectMapper = getObjectMapper();
        ObjectWriter objectWriter = getObjectWriter();

        for (int i = 0; i < rootNode.get("workers").size(); i++) {
            if (username.equals(rootNode.get("workers").get(i).get("username").asText())) {
                ((com.fasterxml.jackson.databind.node.ArrayNode) rootNode.get("workers")).remove(i);
                try {
                    objectWriter.writeValue(getJsonFile(), rootNode);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

    }
    public static void clearJsonFile() {
        ObjectMapper objectMapper = getObjectMapper();
        ObjectWriter objectWriter = getObjectWriter();

        // Create a new JSON object with an empty "workers" array
        ObjectNode rootNode = objectMapper.createObjectNode();
        rootNode.putArray("workers");

        // Write the new empty structure back to the file
        try {
            objectWriter.writeValue(getJsonFile(), rootNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
