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
import java.util.ArrayList;
import java.util.Scanner;

public final class Task {

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

    public static String getTaskStatus(int index) {
        return TaskStatus.get(index);
    }
    
    public String getTaskID() {
        return taskID;
    }

    public static ArrayList<Double> getTaskDurations() {
        return TaskDurations;
    }

    public static ArrayList<String> getTaskIDs() {
        return TaskIDs;
    }

    public static ArrayList<String> getTaskNames() {
        return TaskNames;
    }

    public static ArrayList<String> getDeveloperNames() {
        return DeveloperNames;
    }

    public static ArrayList<String> getTaskDescriptions() {
        return TaskDescriptions;
    }

    public static ArrayList<String> getTaskStatusList() {
        return TaskStatus;
    }

    // Private fields to store task information
    private final String taskName;
    private final int taskNumber;
    private final String taskDescription;
    private final String developerDetails;
    private final double taskDuration;
    private final String taskID;
    private final String taskStatus;

    // Arrays to store task details
    private static final ArrayList<String> TaskStatus = new ArrayList<>();
    private static final ArrayList<Double> TaskDurations = new ArrayList<>();
    private static final ArrayList<String> TaskIDs = new ArrayList<>();
    private static final ArrayList<String> TaskNames = new ArrayList<>();
    private static final ArrayList<String> DeveloperNames = new ArrayList<>();
    private static final ArrayList<String> TaskDescriptions = new ArrayList<>();

    // Scanner object for reading input
    private static final Scanner scanner = new Scanner(System.in);

    public Task(String taskName, int taskNumber, String taskDescription, String developerDetails, double taskDuration, String taskStatus) {
        this.taskName = taskName;
        this.taskNumber = taskNumber;
        this.taskDescription = taskDescription;
        this.developerDetails = developerDetails;
        this.taskDuration = taskDuration;
        this.taskStatus = taskStatus;
        this.taskID = createTaskID(); // Generate a unique task ID

        // Add to static lists
        TaskNames.add(taskName);
        TaskDescriptions.add(taskDescription);
        DeveloperNames.add(developerDetails);
        TaskDurations.add(taskDuration);
        TaskStatus.add(taskStatus); // Add the task status directly
        TaskIDs.add(taskID);
    }

    // Static method to display a welcome message and prompt user for options
    public static void WelcomeMessage() {
        System.out.println("Welcome to EasyKanban");

        System.out.println("""
                           Enter the option you would like to do:
                           1) Add Task
                           2) Show Report
                           3) Display Tasks by Status
                           4) Display Longest Task
                           5) Search Task by Name
                           6) Search Tasks by Developer
                           7) Delete Task
                           8) Quit""");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1 -> AddTask(); // Add a new task
            case 2 -> ShowReport(); // Display task report
            case 3 -> displayTasksByStatus(); // Display tasks by status
            case 4 -> displayLongestTask(); // Display the longest task
            case 5 -> searchTaskByName(); // Search task by name
            case 6 -> searchTasksByDeveloper(); // Search tasks by developer
            case 7 -> deleteTaskByName(); // Delete task by name
            case 8 -> System.exit(0); // Quit the application
            default -> {
                System.out.println("Invalid choice. Please try again.");
                WelcomeMessage(); // Prompt user again
            }
        }
    }

    // Static method to add a new task
    public static void AddTask() {
        // Asks the user how many tasks they want to add
        System.out.println("Enter the number of tasks:");
        int numTasks = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Task[] tasks = new Task[numTasks];

        for (int i = 0; i < numTasks; i++) {
            System.out.println("Enter Task Name:");
            String taskName = scanner.nextLine();

            System.out.println("Enter Task Description:");
            String taskDescription = scanner.nextLine();

            // Validate task description length
            while (taskDescription.length() > 50) {
                System.out.println("Please enter a task description of less than 50 characters.");
                taskDescription = scanner.nextLine();
            }

            System.out.println("Enter Developer Details:");
            String developerDetails = scanner.nextLine();

            System.out.println("Enter Task Duration (hours):");
            double taskDuration = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            System.out.println("Enter Task Status:");
            System.out.println("1. To Do");
            System.out.println("2. Done");
            System.out.println("3. Doing");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            String taskStatus;

            taskStatus = switch (choice)
            {
                case 1 -> "To Do";
                case 2 -> "Done";
                case 3 -> "Doing";
                default -> "Invalid Choice";
            };

            tasks[i] = new Task(taskName, i, taskDescription, developerDetails, taskDuration, taskStatus);

            System.out.println(tasks[i].printTaskDetails());
        }

        // Print total combined hours of all entered tasks
        System.out.println("Total combined hours of all entered tasks: " + returnTotalHours() + " hours");
        
        WelcomeMessage();
        
    }

    // Method to check if task description is within the 50-character limit
    public boolean checkTaskDescription() {
        return taskDescription.length() <= 50;
    }

    // Method to generate a unique task ID
    public String createTaskID() {
        String taskInitials = taskName.substring(0, 2).toUpperCase();
        String developerInitials = developerDetails.substring(0, 3).toUpperCase();
        return taskInitials + ":" + taskNumber + ":" + developerInitials;
    }

    // Method to print task details
    public String printTaskDetails() {
        return "Task Status: " + taskStatus + "\n" +
                "Developer Details: " + developerDetails + "\n" +
                "Task Number: " + taskNumber + "\n" +
                "Task Name: " + taskName + "\n" +
                "Task Description: " + taskDescription + "\n" +
                "Task ID: " + taskID + "\n" +
                "Task Duration: " + taskDuration + " hours";
    }

    // Method that displays all the stored tasks
    public static void ShowReport() {
        StringBuilder report = new StringBuilder();
        for (int i = 0; i < TaskNames.size(); i++) {
            report.append("Task Name: ").append(TaskNames.get(i)).append("\n")
                    .append("Task Description: ").append(TaskDescriptions.get(i)).append("\n")
                    .append("Developer: ").append(DeveloperNames.get(i)).append("\n")
                    .append("Duration: ").append(TaskDurations.get(i)).append("\n")
                    .append("Status: ").append(TaskStatus.get(i)).append("\n")
                    .append("Task ID: ").append(TaskIDs.get(i)).append("\n\n");
        }
        System.out.println(report.toString());
        
        WelcomeMessage();
    }

    // Method to display tasks by user-selected status
    public static void displayTasksByStatus() {
        System.out.println("Enter the status of tasks to display (To Do/Done/Doing):");
        String status = scanner.nextLine();
        StringBuilder filteredTasks = new StringBuilder();
        for (int i = 0; i < TaskStatus.size(); i++) {
            if (status.equalsIgnoreCase(TaskStatus.get(i))) {
                filteredTasks.append("Developer: ").append(DeveloperNames.get(i)).append(", ")
                        .append("Task Name: ").append(TaskNames.get(i)).append(", ")
                        .append("Duration: ").append(TaskDurations.get(i)).append(" hours\n");
            }
        }
        if (filteredTasks.length() > 0) {
            System.out.println(filteredTasks.toString());
        } else {
            System.out.println("No tasks found with the status '" + status + "'.");
        }
        
        WelcomeMessage();
    }

    // Method to display the task with the longest duration
    public static void displayLongestTask() {
        double longestDuration = 0;
        int index = -1;
        for (int i = 0; i < TaskDurations.size(); i++) {
            if (TaskDurations.get(i) > longestDuration) {
                longestDuration = TaskDurations.get(i);
                index = i;
            }
        }
        if (index != -1) {
            System.out.println("Developer: " + DeveloperNames.get(index) + ", Duration: " + longestDuration + " hours");
        }
        
        WelcomeMessage();
    }

    // Method to search for a task by its name
    public static void searchTaskByName() {
        System.out.println("Enter the Task Name to search:");
        String taskName = scanner.nextLine();
        for (int i = 0; i < TaskNames.size(); i++) {
            if (TaskNames.get(i).equalsIgnoreCase(taskName)) {
                System.out.println("Task Name: " + TaskNames.get(i) + "\nDeveloper: " + DeveloperNames.get(i) + "\nStatus: " + TaskStatus.get(i));
                return;
            }
        }
        System.out.println("Task not found.");
        
        WelcomeMessage();
    }

    // Method to search for all tasks assigned to a specific developer
    public static void searchTasksByDeveloper() {
        System.out.println("Enter the Developer's Name to search:");
        String developerName = scanner.nextLine();
        StringBuilder tasksByDeveloper = new StringBuilder();
        for (int i = 0; i < DeveloperNames.size(); i++) {
            if (DeveloperNames.get(i).equalsIgnoreCase(developerName)) {
                tasksByDeveloper.append("Task Name: ").append(TaskNames.get(i)).append(", ")
                        .append("Status: ").append(TaskStatus.get(i)).append("\n");
            }
        }
        if (tasksByDeveloper.length() > 0) {
            System.out.println(tasksByDeveloper.toString());
        } else {
            System.out.println("No tasks found for developer '" + developerName + "'.");
        }
        
        WelcomeMessage();
    }

    // Method to delete a task by its name
    public static void deleteTaskByName() {
        System.out.println("Enter the Task Name to delete:");
        String taskNameToDelete = scanner.nextLine();
        for (int i = 0; i < TaskNames.size(); i++) {
            if (TaskNames.get(i).equalsIgnoreCase(taskNameToDelete)) {
                TaskNames.remove(i);
                TaskDescriptions.remove(i);
                DeveloperNames.remove(i);
                TaskDurations.remove(i);
                TaskStatus.remove(i); // Remove the task status directly
                TaskIDs.remove(i);
                System.out.println("Task '" + taskNameToDelete + "' deleted.");
                return;
            }
        }
        System.out.println("Task not found.");
        
        WelcomeMessage();
    }

    // Method to return the total hours accumulated for all entered tasks
    public static double returnTotalHours() {
        double totalHours = 0;
        for (Double duration : TaskDurations) {
            totalHours += duration;
        }
        return totalHours;
    }
}








     







             





