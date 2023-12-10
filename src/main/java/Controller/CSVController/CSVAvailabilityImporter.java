package Controller.CSVController;

import Model.Staff.Worker;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class CSVAvailabilityImporter {

    private static final Map<String, DayOfWeek> dayMap = new HashMap<>();
    static {
        dayMap.put("Sunday", DayOfWeek.SUNDAY);
        dayMap.put("Monday", DayOfWeek.MONDAY);
        dayMap.put("Tuesday", DayOfWeek.TUESDAY);
        dayMap.put("Wednesday", DayOfWeek.WEDNESDAY);
        dayMap.put("Thursday", DayOfWeek.THURSDAY);
        dayMap.put("Friday", DayOfWeek.FRIDAY);
        dayMap.put("Saturday", DayOfWeek.SATURDAY);
    }

    public static void importAvailability(String filePath, Worker worker) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            for (CSVRecord csvRecord : csvParser) {
                DayOfWeek day = dayMap.get(csvRecord.get("Day"));
                LocalTime start = LocalTime.parse(csvRecord.get("Start"), timeFormatter);
                LocalTime end = LocalTime.parse(csvRecord.get("End"), timeFormatter);

                worker.addUnavailableTime(day, start, end);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

