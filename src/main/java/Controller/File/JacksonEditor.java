package Controller.File;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ObjectNode;

import static Controller.BouncyCastleEncrypter.hashPassword;


public class JacksonEditor extends Jackson {

    public static void addUser(String username, String password, String status) {
        //Have to check for duplicate usernames

        // I updated what you wrote here
        JsonNode rootNode = getRootNode();
        ObjectMapper objectMapper = getObjectMapper();
        ObjectWriter objectWriter = getObjectWriter();

        // Check if username already exists
        boolean usernameExists = false;
        JsonNode usersNode = rootNode.path("users");
        for (JsonNode userNode : usersNode) {
            if (usersNode.path("username").asText().equals(username)) {
                usernameExists = true;
                break;
            }
        }



        // Add new worker only if username is unique
        if (!usernameExists) {
            JsonNode newUser = objectMapper.createObjectNode()
                    .put("username", username)
                    .put("password", hashPassword(password))
                    .put("status", status);
            ((com.fasterxml.jackson.databind.node.ArrayNode) usersNode).add(newUser);

            try {
                objectWriter.writeValue(getJsonFile(), rootNode);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("Could not add user");
        }

    }


    public static void removeUser(String username) {
        JsonNode rootNode = getRootNode();
        ObjectMapper objectMapper = getObjectMapper();
        ObjectWriter objectWriter = getObjectWriter();

        for (int i = 0; i < rootNode.get("users").size(); i++) {
            if (username.equals(rootNode.get("users").get(i).get("username").asText())) {
                ((com.fasterxml.jackson.databind.node.ArrayNode) rootNode.get("users")).remove(i);
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
        rootNode.putArray("users");

        // Write the new empty structure back to the file
        try {
            objectWriter.writeValue(getJsonFile(), rootNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
