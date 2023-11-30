package Controller.FIle;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;

public class Jackson {

    protected static File getJsonFile() {
        File jsonFile = new File("src/main/resources/data.json");
        return jsonFile;
    }

    protected static JsonNode getRootNode() {

        //Getting and returning the root node
        try {
            File jsonFile = getJsonFile();
            ObjectMapper objectMapper = getObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonFile);
            return rootNode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    protected static ObjectMapper getObjectMapper() {

//        Creating and returning the objectmapper
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper;
    }

    protected static ObjectWriter getObjectWriter() {
        ObjectWriter objectWriter = getObjectMapper().writerWithDefaultPrettyPrinter();
        return objectWriter;
    }



    //Reece's stuff






}
