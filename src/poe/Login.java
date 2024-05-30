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
import javax.swing.JOptionPane;

//------------------------------------------------------------------------------
//handles the login in and registering a user
public class Login {

    //--------------------------------------------------------------------------
    // Define class variables
    private static String Fname;
    private static String Lname;
    private static String UserName;
    private static String Password;
    private static String UserName2;
    private static String Password2;

    //--------------------------------------------------------------------------
    // Getter methods
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

    //--------------------------------------------------------------------------
    // Setter methods
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

    //--------------------------------------------------------------------------
    // Main method to start the program
    public static void main(String[] args) {
        Start(); // Call the Start method
    }

    //--------------------------------------------------------------------------
    // Method to initiate the login/register process
    public static void Start() {
        // Show dialog box with options to login or register
        int option = JOptionPane.showOptionDialog(null, "Welcome! Would you like to login or register?", "Login/Register", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Register", "Login"}, null);

        switch (option) {
            case JOptionPane.YES_OPTION: // Register
                GetFnameLname(); // Call method to get first and last names
                registerUser(); // Call method to register user
                Start(); // Start the process again
                break;
            case JOptionPane.NO_OPTION: // Login
                String[] loginCredentials = getUserLoginCredentials(); // Call method to get login credentials
                boolean loginStatus = returnLoginStatus(loginCredentials[0], loginCredentials[1]); // Check login status
                if (loginStatus) {
                    // Login successful, proceed with further actions
                } else {
                    // Login failed, handle accordingly
                }
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid choice. Please try again."); // Show error message
                Start(); // Start the process again
                break;
        }
    }

    // Method to get login credentials from user
    public static String[] getUserLoginCredentials() {
        String username = JOptionPane.showInputDialog(null, "Enter your Username:"); // Prompt for username
        String password = JOptionPane.showInputDialog(null, "Enter your Password:"); // Prompt for password
        return new String[]{username, password}; // Return the entered credentials
    }

    // Method to get first and last names from user
    public static void GetFnameLname() {
        Fname = JOptionPane.showInputDialog(null, "Enter your first name:"); // Prompt for first name
        Lname = JOptionPane.showInputDialog(null, "Enter your Last name:"); // Prompt for last name
    }

    // Method to register a user
    public static void registerUser() {
        // While loop to check conditions and choose what message to display
        while (true) {
            if (checkUserName(new Scanner("kyle!!!!!!!"))) {
                JOptionPane.showMessageDialog(null, "Username successfully captured"); // Show success message
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length."); // Show error message
            }
        }

        // While loop to check conditions and choose what message to display
        while (true) {
            if (checkPasswordComplexity(new Scanner("password*"))) {
                JOptionPane.showMessageDialog(null, "Password successfully captured"); // Show success message
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Password is not correctly formatted. Please ensure that the password contains at least 8 characters, a capital letter, a number, and a special character."); // Show error message
            }
        }
    }

    // Method to check if username is valid
    public static boolean checkUserName(Scanner scanner) {
        UserName = JOptionPane.showInputDialog(null, "Enter your Username:"); // Prompt for username

        if (UserName.contains("_") && UserName.length() <= 5) { // Check username format
            return true; // Username is valid
        } else {
            return false; // Username is invalid
        }
    }

    // Method to check if password complexity requirements are met
    public static boolean checkPasswordComplexity(Scanner scanner) {
        Password = JOptionPane.showInputDialog(null, "Enter your password:"); // Prompt for password

        if (Password.length() >= 8 && Password.matches(".*\\d.*") && Password.matches(".*[A-Z].*") && Password.matches(".*[^a-zA-Z0-9].*")) { // Check password complexity
            return true; // Password meets complexity requirements
        } else {
            return false; // Password does not meet complexity requirements
        }
    }

    // Method to check login status
    public static boolean returnLoginStatus(String providedUsername, String providedPassword) {
        // Check if provided username and password match stored values
        if (UserName.equals(providedUsername) && Password.equals(providedPassword)) {
            JOptionPane.showMessageDialog(null, "Welcome " + Fname + " " + Lname + ", it is great to see you again."); // Show welcome message
            Task.WelcomeMessage(); // Call welcome message method
            return true; // Login successful
        } else {
            JOptionPane.showMessageDialog(null, "Username or password incorrect, please try again."); // Show error message
            return false; // Login failed
        }
    }
    
    //------------------------ END OF FILE ------------------------------------\\
}

