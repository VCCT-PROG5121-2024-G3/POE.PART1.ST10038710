/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poe;

//------------------------------------------------------------------------------

/**
 *
 * @author Storm Hendricks ST10038710
 */

//------------------------------------------------------------------------------
//Imports
import javax.swing.JOptionPane;

public final class Task {
    // Private fields to store task information
    private final String taskName;
    private final int taskNumber;
    private final String taskDescription;
    private final String developerDetails;
    private final double taskDuration;
    private final String taskID;
    private final String taskStatus;

    // Getters for each field
    public String getTaskName() {
        return taskName;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public String getDeveloperDetails() {
        return developerDetails;
    }

    public double getTaskDuration() {
        return taskDuration;
    }

    public String getTaskID() {
        return taskID;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    // Constructor to create a new Task object
    public Task(String taskName, int taskNumber, String taskDescription, String developerDetails, double taskDuration, String taskStatus) {
        this.taskName = taskName;
        this.taskNumber = taskNumber;
        this.taskDescription = taskDescription;
        this.developerDetails = developerDetails;
        this.taskDuration = taskDuration;
        this.taskStatus = taskStatus;
        this.taskID = createTaskID(); // Generate a unique task ID
    }

    // Static method to display a welcome message and prompt user for options
    public static void WelcomeMessage() {
        JOptionPane.showMessageDialog(null, "Welcome to EasyKanban");

        String input = JOptionPane.showInputDialog(null, """
                                                         Enter the option you would like to do:
                                                         1) Add Task
                                                         2) Show Report
                                                         3) Quit""",
                "Task Management",
                JOptionPane.PLAIN_MESSAGE);

        try {
            int choice = Integer.parseInt(input);
            switch (choice) {
                case 1 -> AddTask(); // Add a new task
                case 2 -> {
                    ShowReport(); // Display task report (currently commented out)
                }
                case 3 -> System.exit(0); // Quit the application
                default -> {
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    WelcomeMessage(); // Prompt user again
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid choice. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
            WelcomeMessage(); // Prompt user again
        }
    }

    // Static method to add a new task
    public static void AddTask() {
        int numTasks = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of tasks:"));
        Task[] tasks = new Task[numTasks];
        double totalHours = 0;

        for (int i = 0; i < numTasks; i++) {
            String taskName = JOptionPane.showInputDialog("Enter Task Name:");
            String taskDescription = JOptionPane.showInputDialog("Enter Task Description:");

            // Validate task description length
            while (taskDescription.length() > 50) {
                JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters.");
                taskDescription = JOptionPane.showInputDialog("Enter Task Description:");
            }

            String developerDetails = JOptionPane.showInputDialog("Enter Developer Details:");
            double taskDuration = Double.parseDouble(JOptionPane.showInputDialog("Enter Task Duration (hours):"));
            String taskStatus = JOptionPane.showInputDialog("Enter Task Status (To Do/Done/Doing):");

            tasks[i] = new Task(taskName, i, taskDescription, developerDetails, taskDuration, taskStatus);
            totalHours += taskDuration;

            JOptionPane.showMessageDialog(null, tasks[i].printTaskDetails());
        }

        JOptionPane.showMessageDialog(null, "Total combined hours of all entered tasks: " + totalHours + " hours");
    }

    // Method to check if task description is within the 50-character limit
    public boolean checkTaskDescription() {
        return taskDescription.length() <= 50;
    }

    // Method to generate a unique task ID
    public String createTaskID() {
        String initials = taskName.substring(0, 2).toUpperCase();
        String lastThreeLetters = developerDetails.substring(developerDetails.length() - 3).toUpperCase();
        return initials + ":" + taskNumber + ":" + lastThreeLetters;
    }

    // Method to print task details
    public String printTaskDetails() {
        return "Task Status: " + taskStatus + "\n" +
                "Developer Details: " + developerDetails + "\n" +
                "Task Number: " + taskNumber + "\n" +
                "Task Name: " + taskName + "\n" +
                "Task Description: " + taskDescription + "\n" +
                "Task ID: "+ taskID + "\n" +
                "Task Duration: " + taskDuration + " hours";
    }

    // Static method to calculate and display the total hours of all tasks
    public static void returnTotalHours(Task[] tasks) {
        double totalHours = 0;
        for (Task task : tasks) {
            totalHours += task.taskDuration;
        }
        JOptionPane.showMessageDialog(null, "Total combined hours of all entered tasks: " + totalHours + " hours");
    }

    // Static method to display a coming soon message for the report feature
    public static void ShowReport() {
        JOptionPane.showMessageDialog(null,"Coming soon");
    }

    //------------------------ END OF FILE ------------------------------------\\
}



     







             





