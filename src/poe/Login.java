/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poe;

/**
 *
 * @author Storm Hendricks ST10038710
 */

//------------------------------------------------------------------------------
//Imports
import java.util.Scanner;

// Class for managing user login and registration
public class Login {

    // Class variables for user information
    private static String Fname;
    private static String Lname;
    private static String UserName;
    private static String Password;

    // Scanner object for reading input
    private static final Scanner scanner = new Scanner(System.in);

    // Method to start the login or registration process
    public static void Start() {
        System.out.println("Welcome! Would you like to login or register?");
        System.out.println("1. Register");
        System.out.println("2. Login");
        int option = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (option) {
            case 1 ->
            {
                GetFnameLname(); // Prompt user to enter first and last name
                registerUser(); // Register the user
                Start(); // Restart the process
            }
            case 2 ->
            {
                String[] loginCredentials = getUserLoginCredentials(); // Prompt user for login credentials
                boolean loginStatus = returnLoginStatus(loginCredentials[0], loginCredentials[1]); // Check login status
                if (loginStatus) {
                    Task.WelcomeMessage(); // Welcome user if login is successful
                } else {
                    // If login fails, restart the process
                    Start();
                }
            }
            default ->
            {
                System.out.println("Invalid choice. Please try again.");
                Start();
            }
        }
    }

    // Method to get user login credentials
    public static String[] getUserLoginCredentials() {
        System.out.println("Enter your Username:");
        String username = scanner.nextLine();
        System.out.println("Enter your Password:");
        String password = scanner.nextLine();
        return new String[]{username, password};
    }

    // Method to prompt user for first and last name
    public static void GetFnameLname() {
        System.out.println("Enter your first name:");
        Fname = scanner.nextLine();
        System.out.println("Enter your Last name:");
        Lname = scanner.nextLine();
    }

    // Method to register a new user
    public static void registerUser() {
        while (true) {
            System.out.println("Enter your Username:");
            UserName = scanner.nextLine();
            if (checkUserName(UserName)) {
                System.out.println("Username successfully captured");
                break;
            } else {
                // Prompt user to enter correct username format
                System.out.println("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.");
            }
        }

        while (true) {
            System.out.println("Enter your Password:");
            Password = scanner.nextLine();
            if (checkPasswordComplexity(Password)) {
                System.out.println("Password successfully captured");
                break;
            } else {
                // Prompt user to enter a password with correct complexity
                System.out.println("Password is not correctly formatted. Please ensure that the password contains at least 8 characters, a capital letter, a number, and a special character.");
            }
        }
    }

    // Method to check if username meets the required format
    public static boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // Method to check if password meets the required complexity
    public static boolean checkPasswordComplexity(String password) {
        return password.length() >= 8 && password.matches(".*\\d.*") && password.matches(".*[A-Z].*") && password.matches(".*[^a-zA-Z0-9].*");
    }

    // Method to check login credentials and return login status
    public static boolean returnLoginStatus(String providedUsername, String providedPassword) {
        if (UserName.equals(providedUsername) && Password.equals(providedPassword)) {
            System.out.println("Welcome " + Fname + " " + Lname + ", it is great to see you again.");
            return true; // Return true if login is successful
        } else {
            // If login fails, prompt user to try again
            System.out.println("Username or password incorrect, please try again.");
            Start();
            return false; // Return false if login fails
        }
    }

}




