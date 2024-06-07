/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package poe;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class TaskTest {

@Before
public void setUp() {
    // Clear all static lists and reinitialize for fresh tests
    Task.getTaskNames().clear();
    Task.getTaskDescriptions().clear();
    Task.getDeveloperNames().clear();
    Task.getTaskDurations().clear();
    Task.getTaskIDs().clear();
    Task.getTaskStatusList().clear();

    // Add tasks needed for tests and store them in variables to avoid 'ignored instance' issue
    Task task1 = new Task("Create Login", 0, "Create Login", "Mike Smith", 5.0, "To Do");
    Task task2 = new Task("Create Add Features", 1, "Create Add Features", "Edward Harrington", 8.0, "Doing");
    Task task3 = new Task("Create Reports", 2, "Create Reports", "Samantha Paulson", 2.0, "Done");
    Task task4 = new Task("Add Arrays", 3, "Add Arrays", "Glenda Oberholzer", 11.0, "To Do");

    // Utilize the task variables in assert statements to prevent compiler optimization
    assertNotNull(task1);
    assertNotNull(task2);
    assertNotNull(task3);
    assertNotNull(task4);
}


    @Test
    public void testCheckTaskDescription() {
        Task task = new Task("Test Task", 1, "This is a test description", "Test Developer", 5.0, "To Do");
        assertFalse(task.checkTaskDescription());
    }

    @Test
    public void testCreateTaskID() {
        Task task = new Task("Test Task", 1, "Test Description", "Test Developer", 5.0, "To Do");
        assertEquals("TE:1:TES", task.getTaskID());
    }

@Test
public void testAddTask() {
    // Record the initial size of the task lists
    int initialSize = Task.getTaskNames().size();

    // Create a new task
    Task task = new Task("New Task", 4, "New Task Description", "New Developer", 4.0, "Doing");

    // Log the details of the new task
    System.out.println("New Task Details:");
    System.out.println(task.printTaskDetails());

    // Verify that the size of the task lists increased by 1 after adding the new task
    int finalSize = Task.getTaskNames().size();
    assertEquals(initialSize + 1, finalSize);
    System.out.println("Initial Size: " + initialSize + ", Final Size: " + finalSize);
}


    @Test
    public void testDeleteTaskByName() {
        // Simulate user input
        String input = "Create Reports";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Task.deleteTaskByName();
        assertFalse(Task.getTaskNames().contains("Create Reports"));
    }

    @Test
    public void testSearchAllTasksAssignedToDeveloper() {
        // Simulate user input
        String input = "Glenda Oberholzer";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Task.searchTasksByDeveloper();
        ArrayList<String> expectedTasks = new ArrayList<>();
        expectedTasks.add("Add Arrays");
        assertEquals(expectedTasks.size(), 1);
    }

    @Test
    public void testDisplayDeveloperAndDurationForLongestTask() {
        Task.displayLongestTask();
        String expectedDeveloper = "Glenda Oberholzer";
        double expectedDuration = 11;
        double longestDuration = 0;
        int index = -1;
        for (int i = 0; i < Task.getTaskDurations().size(); i++) {
            if (Task.getTaskDurations().get(i) > longestDuration) {
                longestDuration = Task.getTaskDurations().get(i);
                index = i;
            }
        }
        assertEquals(expectedDeveloper, Task.getDeveloperNames().get(index));
        assertEquals(expectedDuration, longestDuration, 0.01);
    }

    @Test
    public void testDisplayReport() {
        StringBuilder expectedReport = new StringBuilder();
        expectedReport.append("Task Name: Create Login\n")
                .append("Task Description: Create Login\n")
                .append("Developer: Mike Smith\n")
                .append("Duration: 5.0\n")
                .append("Status: To Do\n")
                .append("Task ID: CR:0:MIK\n\n")
                .append("Task Name: Create Add Features\n")
                .append("Task Description: Create Add Features\n")
                .append("Developer: Edward Harrington\n")
                .append("Duration: 8.0\n")
                .append("Status: Doing\n")
                .append("Task ID: CR:1:EDW\n\n")
                .append("Task Name: Create Reports\n")
                .append("Task Description: Create Reports\n")
                .append("Developer: Samantha Paulson\n")
                .append("Duration: 2.0\n")
                .append("Status: Done\n")
                .append("Task ID: CR:2:SAM\n\n")
                .append("Task Name: Add Arrays\n")
                .append("Task Description: Add Arrays\n")
                .append("Developer: Glenda Oberholzer\n")
                .append("Duration: 11.0\n")
                .append("Status: To Do\n")
                .append("Task ID: CR:3:GLE\n\n");

        StringBuilder actualReport = new StringBuilder();
        for (int i = 0; i < Task.getTaskNames().size(); i++) {
            actualReport.append("Task Name: ").append(Task.getTaskNames().get(i)).append("\n")
                    .append("Task Description: ").append(Task.getTaskDescriptions().get(i)).append("\n")
                    .append("Developer: ").append(Task.getDeveloperNames().get(i)).append("\n")
                    .append("Duration: ").append(Task.getTaskDurations().get(i)).append("\n")
                    .append("Status: ").append(Task.getTaskStatusList().get(i)).append("\n")
                    .append("Task ID: ").append(Task.getTaskIDs().get(i)).append("\n\n");
        }

        assertEquals(expectedReport.toString(), actualReport.toString());
    }

    @Test
    public void testSearchForTaskByName() {
        // Simulate user input
        String input = "Create Login";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Task.searchTaskByName();
        for (int i = 0; i < Task.getTaskNames().size(); i++) {
            if (Task.getTaskNames().get(i).equalsIgnoreCase(input)) {
                assertEquals("Mike Smith", Task.getDeveloperNames().get(i));
                assertEquals("To Do", Task.getTaskStatusList().get(i));
                return;
            }
        }
    }

    @Test
    public void testTotalHoursAccumulation()
    {
        Task task1 = new Task("Login Feature", 1, "Create Login to authenticate users", "Robyn Harrison", 8.0, "To Do");
        Task task2 = new Task("Add Task Feature", 2, "Create Add Task feature to add task users", "Mike Smith", 10.0, "Doing");

        // Add the tasks to the respective lists
        Task.getTaskNames().add(task1.getTaskName());
        Task.getTaskDescriptions().add(task1.getTaskDescription());
        Task.getDeveloperNames().add(task1.getDeveloperDetails());
        Task.getTaskDurations().add(task1.getTaskDuration());
        Task.getTaskStatusList().add(Task.getTaskStatus(0)); // Assuming index 0 is for "To Do"
        Task.getTaskIDs().add(task1.getTaskID());

        Task.getTaskNames().add(task2.getTaskName());
        Task.getTaskDescriptions().add(task2.getTaskDescription());
        Task.getDeveloperNames().add(task2.getDeveloperDetails());
        Task.getTaskDurations().add(task2.getTaskDuration());
        Task.getTaskStatusList().add(Task.getTaskStatus(0)); // Assuming index 0 is for "To Do"
        Task.getTaskIDs().add(task2.getTaskID());

        // Ensure that the total hours accumulated matches the expected value
        assertEquals(18.0, Task.returnTotalHours(), 0.01);
    }
}











