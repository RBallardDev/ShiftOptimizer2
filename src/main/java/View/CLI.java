package View;

import Controller.File.JacksonEditor;
import Controller.File.JacksonGetter;
import Controller.UserAuth.SignInAuth;
import Model.Staff.User;
import Model.Staff.Worker;
import Model.Token;

import java.util.List;
import java.util.Scanner;
import Controller.XMLControllers.Session;
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

        //The user gets a token

        if (loginToken != null) {
            System.out.println("Sign in successful!");

            workerMain(username);
            Session.setToken(loginToken);
        } else {
            System.out.println("Sign in failed. Please check your credentials.");
        }
    }

    private static void workerMain(String username){
        Scanner scn = new Scanner(System.in);
        System.out.print("See schedule (1) or input schedule(2)");
        int action = scn.nextInt();
switch(action) {
    case 1:

        displaySchedule(username);

    case 2:
        inputSchedule();

    case 3:
        //View all shifts
    default:
        }
    }

    private static void inputSchedule()
    {

    }


    private static void displaySchedule(String username) {
        System.out.println(username);

        JacksonGetter.getWorkerByUsername(username);


       /* Worker worker =  (Worker) JacksonGetter.getWorkerByUsername( username );

        if (worker == null) {
            System.out.println("Worker not found");
            return;
        }

        Day monday = new Day("Monday");
        Day tuesday = new Day("Tuesday");

        Shift shift1 = new Shift(LocalTime.of(9, 0), LocalTime.of(17, 0), worker, monday);
        Shift shift2 = new Shift(LocalTime.of(10, 0), LocalTime.of(18, 0), worker, tuesday);

        try {
            worker.addShift(shift1);
            worker.addShift(shift2);
        } catch (ShiftConflictException e) {
            System.out.println("Error adding shift: " + e.getMessage());
        }

        System.out.println("Schedule for " + worker.getUserName() + ":");
        System.out.println(worker.printSchedule());*/
    }
}
