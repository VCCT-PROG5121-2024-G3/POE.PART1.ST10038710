/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poe;

/**
 *
 * @author Storm Hendricks ST10038710
 */

import java.util.Scanner;

public class Login {

    private static String Fname;
    private static String Lname;
    private static String UserName;
    private static String Password;
    private static String UserName2;
    private static String Password2;

    public static String getFname() {
        return Fname;
    }

    public static String getLname() {
        return Lname;
    }

    public static String getUserName() {
        return UserName;
    }

    public static String getPassword() {
        return Password;
    }

    public static String getUserName2() {
        return UserName2;
    }

    public static String getPassword2() {
        return Password2;
    }

    public static void setFname(String aFname) {
        Fname = aFname;
    }

    public static void setLname(String aLname) {
        Lname = aLname;
    }

    public static void setUserName(String aUserName) {
        UserName = aUserName;
    }

    public static void setPassword(String aPassword) {
        Password = aPassword;
    }

    public static void setUserName2(String aUserName2) {
        UserName2 = aUserName2;
    }

    public static void setPassword2(String aPassword2) {
        Password2 = aPassword2;
    }

    public static void main(String[] args) {
        Start();
    }

 public static void Start() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Welcome! Would you like to login or register? (Type 'register' or 'login')");
    String choice = scanner.nextLine();

    switch (choice.toLowerCase()) {
        case "register":
            GetFnameLname(scanner);
            registerUser(scanner);
            Start();
            break;
        case "login":
            String[] loginCredentials = getUserLoginCredentials(scanner);
            boolean loginStatus = returnLoginStatus(loginCredentials[0], loginCredentials[1]);
            if (loginStatus) {
                // Login successful, proceed with further actions
            } else {
                // Login failed, handle accordingly
            }
            break;
        default:
            System.out.println("Invalid choice. Please try again.");
            Start();
            break;
    }

    scanner.close();
}

public static String[] getUserLoginCredentials(Scanner scanner) {
    System.out.println("Enter your Username: ");
    String username = scanner.nextLine();
    System.out.println("Enter your Password: ");
    String password = scanner.nextLine();
    return new String[]{username, password};
}

    public static void GetFnameLname(Scanner scanner) {
        System.out.println("Enter your first name: ");
        Fname = scanner.nextLine();
        System.out.println("Enter your Last name: ");
        Lname = scanner.nextLine();
    }

    public static void registerUser(Scanner scanner) {
        String m = "Username successfully captured";
        while (true) {
            if (checkUserName(scanner)) {
                System.out.println(m);
                break;
            } else {
                System.out.println("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.");
            }
        }

        while (true) {
            if (checkPasswordComplexity(scanner)) {
                System.out.println("Password successfully captured");
                break;
            } else {
                System.out.println("Password is not correctly formatted. Please ensure that the password contains at least 8 characters, a capital letter, a number, and a special character.");
            }
        }
    }

    public static boolean checkUserName(Scanner scanner) {
        System.out.println("Enter your Username: ");
        UserName = scanner.nextLine();

        if (UserName.contains("_") && UserName.length() <= 5) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkPasswordComplexity(Scanner scanner) {
        System.out.println("Enter your password: ");
        Password = scanner.nextLine();

        if (Password.length() >= 8 && Password.matches(".*\\d.*") && Password.matches(".*[A-Z].*") && Password.matches(".*[^a-zA-Z0-9].*")) {
            return true;
        } else {
            return false;
        }
    }

   public static boolean returnLoginStatus(String providedUsername, String providedPassword) {
    // Check if provided username and password match stored values
    if (UserName.equals(providedUsername) && Password.equals(providedPassword)) {
        System.out.println("Welcome " + Fname + " " + Lname + ", it is great to see you again.");
        return true;
    } else {
        System.out.println("Username or password incorrect, please try again.");
        return false;
    }
}
}


