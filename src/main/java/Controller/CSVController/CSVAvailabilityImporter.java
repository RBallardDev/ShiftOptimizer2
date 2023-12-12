package Controller.CSVController;

import Controller.File.JacksonEditor;
import Model.Staff.Worker;
import Model.Time.TimeUnavailable;
import Model.Time.Week;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import java.io.Reader;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalTime;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;


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

    public static void importAvailability(String filePath, String username) {
        DateTimeFormatter timeFormatter = new DateTimeFormatterBuilder()
                .appendPattern("H:mm")
                .optionalStart()
                .appendPattern("HH:mm")
                .optionalEnd()
                .toFormatter();

        try {
            Path path = Paths.get(filePath);
            // Use BufferedReader to handle BOM
            try (BufferedReader br = new BufferedReader(new InputStreamReader(Files.newInputStream(path), StandardCharsets.UTF_8))) {
                // Skip BOM (if exists)
                br.mark(1);
                if (br.read() != 0xFEFF) {
                    br.reset();
                }

                try (CSVParser csvParser = new CSVParser(br, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
                    for (CSVRecord csvRecord : csvParser) {
                        Week.DayNames day = Week.DayNames.valueOf(csvRecord.get("Day"));
                        LocalTime start = LocalTime.parse(csvRecord.get("Start"), timeFormatter);
                        LocalTime end = LocalTime.parse(csvRecord.get("End"), timeFormatter);
                        TimeUnavailable timeUnavailable = new TimeUnavailable(day, start, end);
                        JacksonEditor.addTimeUnavailable(username, day, timeUnavailable);

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

