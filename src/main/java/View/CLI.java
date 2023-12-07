package View;

import Controller.File.JacksonEditor;
import Controller.File.JacksonGetter;
import Controller.UserAuth.SignInAuth;
import Model.Schedules.ShiftConflictException;
import Model.Staff.User;
import Model.Staff.Worker;
import Model.Time.Day;
import Model.Token;

import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class CLI {
    public void run()
    {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
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
                    System.out.print("Enter status (ST/AD): ");
                    String status = scanner.next();
                    JacksonEditor.addUser(username, password, status);

                    //Jackson.addWorker("test1", "pass1", "ST");
                    //BouncyCastle.loginTest("test1", "pass1");
                    break;
                case 2:
                    List<User> users = JacksonGetter.getAllUsers();
                    for (User user : users) {
                        System.out.println(user);
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
        Session.setToken
        //The user gets a token

        if (loginToken != null) {
            System.out.println("Sign in successful!");

        } else {
            System.out.println("Sign in failed. Please check your credentials.");
        }
    }

    private static void workerMain(){
        Scanner scn = new Scanner(System.in);
        System.out.print("See schedule (1) or input schedule(2)");
        int action = scn.nextInt();
switch(action) {
    case 1:
        //Display schedule

    case 2:
        //Input schedule
    default:
}
    }
    private static <Shift> void displaySchedule()
    {

        // Create a worker
        //Tests if we hardcode a worker
        //JacksonEditor.addWorker("test1", "pass1", "ST");


        Worker worker = (Worker) JacksonGetter.getWorkerByUsername("test1");

        //ArrayList<Worker> workers = (ArrayList<Worker>) JacksonGetter.getAllWorkers();

        if (worker == null) {
            System.out.println("Worker not found");
            return;
        }

        // Create days (assuming you have a Day class)
        Day monday = new Day("Monday");
        Day tuesday = new Day("Tuesday");

        // Create shifts
        Shift shift1 = new Shift(LocalTime.of(9, 0), LocalTime.of(17, 0), worker, monday);
        Shift shift2 = new Shift(LocalTime.of(10, 0), LocalTime.of(18, 0), worker, tuesday);

        // Assign shifts to the worker
        try {
            worker.addShift(shift1);
            worker.addShift(shift2);
        } catch (ShiftConflictException e) {
            System.out.println(e.getMessage());
        }

        // Print the worker's schedule
        System.out.println("Schedule for " + worker.getUserName() + ":");
        System.out.println(worker.printSchedule());
    }
}
