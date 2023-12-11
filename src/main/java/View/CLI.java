package View;

import Controller.CSVController.CSVAvailabilityImporter;
import Controller.File.JacksonEditor;
import Controller.File.JacksonGetter;
import Controller.UserAuth.SessionAuth;
import Controller.UserAuth.SignInAuth;
import Model.Schedules.Shift;
import Model.Staff.Worker;

import Model.Time.Week;

import Model.Time.TimeUnavailable;

import Model.Token;

import java.util.List;
import java.util.Scanner;

import Controller.XMLControllers.Session;

import java.time.LocalTime;

public class CLI {


    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            //JacksonGetter.getAllWorkers();

            System.out.println("Welcome to Shift Optimizer CLI");
            System.out.println("1. Add Worker");
            System.out.println("2. View Workers");
            System.out.println("3. Sign In");
            System.out.println("4. Exit");
            System.out.println("5. Clear JSON File");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    // Add logic to add a worker

                    System.out.print("Enter username: ");
                    String username = scanner.next();
                    System.out.print("Enter password: ");
                    String password = scanner.next();
                    System.out.print("Enter status (ST/AD/MN): ");
                    String status = scanner.next();
                    if (status.equals("MN")) {
                        JacksonEditor.addManager(username, password);
                    } else {
                        JacksonEditor.addWorker(username, password, status);
                    }
                    //Jackson.addWorker("test1", "pass1", "ST");
                    //BouncyCastle.loginTest("test1", "pass1");
                    break;
                case 2:
                    List<Worker> workers = JacksonGetter.getAllWorkers();
                    for (Worker worker : workers) {
                        System.out.println(worker);
                    }
                    break;
                case 3:
                    signIn(scanner);
                    break;
                case 4:
                    running = false;
                    break;
                case 5:
                    JacksonEditor.clearJsonFile();
                    System.out.println("JSON file cleared.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }


    }

    //Helper method
    private static void signIn(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.next();

        System.out.print("Enter password: ");
        String password = scanner.next();

        //This should not be here in the real program
        Token loginToken = SignInAuth.signIn(username, password);
        System.out.println(loginToken);

        //The user gets a token

        if (loginToken != null) {
            System.out.println("Sign in successful!");
            Session.setToken(loginToken);
            workerMain(username);

        } else {
            System.out.println("Sign in failed. Please check your credentials.");
        }
    }

    private static void workerMain(String username) {
        Scanner scn = new Scanner(System.in);
        System.out.println("1. See work schedule");
        System.out.println("2. Input school schedule");
        System.out.print("Choose an option: ");
        int action = scn.nextInt();
        switch (action) {
            case 1:

                displaySchedule(username);

            case 2:
                inputSchedule();

            case 3:
                //View all shifts
            default:
        }
    }

    /*private static void inputSchedule()
    {
        Scanner scn = new Scanner(System.in);
        System.out.println("Input start time for shift");

    }*/
    public static void inputSchedule() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter worker's username: ");
        String username = scanner.next();

        System.out.print("Enter path to CSV file: ");
        String filePath = scanner.next();

        Worker worker = JacksonGetter.getWorkerByUsername(username);
        if (worker != null) {
            CSVAvailabilityImporter.importAvailability(filePath, worker);

            System.out.println("Unavailable Times for " + worker.getUserName() + ":");
            for (TimeUnavailable time : worker.getUnavailableTimes()) {
                System.out.println(time.getDay() + ": " + time.getStartTime() + " to " + time.getEndTime());
            }

            // Save the updated worker data back to jackson or JSON or whatever
        } else {
            System.out.println("Worker not found.");
        }
    }


    private static void displaySchedule(String username) {

        Worker test = JacksonGetter.getWorkerByUsername(SessionAuth.authenticateToken(Session.getToken()));
        Shift shift1 = new Shift(LocalTime.of(9, 0), LocalTime.of(17, 0));
        Shift shift2 = new Shift(LocalTime.of(10, 0), LocalTime.of(18, 0));

        test.getSchedule().getDay(Week.DayNames.Monday).addShift(shift1);
        test.getSchedule().getDay(Week.DayNames.Tuesday).addShift(shift2);

        System.out.println("Schedule for " + username + ":");
        System.out.println(test.getSchedule().printSchedule());
    }
}
