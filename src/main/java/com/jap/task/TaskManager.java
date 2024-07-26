package com.jap.task;

//import sun.jvm.hotspot.oops.TypeArrayKlass;

import java.util.*;

public class TaskManager {
    // Attributes for category and task operations
    private CategoryOperations categoryOperations;
    private TaskOperations taskOperations;

    /**
     * Constructs a new TaskManager object.
     * Initializes category and task operation objects.
     */
    List<Category> listOfCategory;

    public TaskManager() {
        // Initialize category and task operation objects
        categoryOperations = new CategoryOperations();
        taskOperations = new TaskOperations();
        new LinkedList<Category>();

    }

    /**
     * Allows the authenticated user to interact with the task manager by providing menu choices.
     *
     * @param authenticatedUser The authenticated user accessing the task manager.
     */
    public void takeChoices(User authenticatedUser) {
        Scanner scanner = new Scanner(System.in);
        String categoryName = "";
        Category selectedCategory;

        int choice;

        // Display the menu options and handle user choices until the user chooses to exit
        do {
            System.out.println("\nTask Manager Menu");
            System.out.println("1. Add a Task");
            System.out.println("2. List all Tasks");
            System.out.println("3. Mark a Task as Completed");
            System.out.println("4. Remove a Task");
            System.out.println("5. Add a Category");
            System.out.println("6. List all Categories");
            System.out.println("7. Search for a Task by Name");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter category name: ");
                    categoryName = scanner.next();
                    selectedCategory = categoryOperations.findCategory(categoryName);
                    if (selectedCategory != null) {
                        System.out.print("Enter task name: ");
                        String taskName = scanner.next();
                        System.out.print("Enter task priority: ");
                        int priority = scanner.nextInt();
                        System.out.print("Enter task description: ");
                        String description = scanner.next();
                        Task newTask = new Task(taskName, priority, description, false, selectedCategory);
                        if (taskOperations.addTask(selectedCategory, newTask, authenticatedUser)) {
                            System.out.println("Task added successfully.");
                        } else {
                            System.out.println("Task already exists.");
                        }
                    } else {
                        System.out.println("Category not found.");
                    }
                    break;
                case 2:
                    // List all tasks
                    System.out.println("Enter the category name");
                    String categoryNes = scanner.next();
                    List<String> listOfTasks = new ArrayList<String>();

                    listOfTasks = taskOperations.listAllTasks(categoryNes);
                    System.out.println(listOfTasks);


                    // Enter category name and list tasks
                    break;
                case 3:
                    System.out.print("Enter task name: ");
                    String taskToComplete = scanner.next();
                    System.out.print("Enter status (completed/pending): ");
                    String status = scanner.next();
                    if (taskOperations.markTaskAsCompleted(taskToComplete, status)) {
                        System.out.println("Task status updated successfully.");
                    } else {
                        System.out.println("Task not found or invalid status.");
                    }
                    break;
                case 4:
                    System.out.print("Enter task name: ");
                    String taskToRemove = scanner.next();
                    try {
                        if (taskOperations.removeTask(taskToRemove)) {
                            System.out.println("Task removed successfully.");
                        } else {
                            System.out.println("Task not found.");
                        }
                    } catch (TaskNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    System.out.print("Enter category name: ");
                    categoryName = scanner.next();
                    if (categoryOperations.addCategory(categoryName)) {
                        System.out.println("Category added successfully.");
                    } else {
                        System.out.println("Category already exists.");
                    }
                    break;
                case 6:
                    System.out.println("Categories:");
                    for (Category category : categoryOperations.listAllCategories()) {
                        System.out.println(category.getCategoryName());
                    }
                    break;
                case 7:
                    System.out.print("Enter task name: ");
                    String taskName = scanner.next();
                    Task foundTask = taskOperations.searchTasksByName(taskName);
                    if (foundTask != null) {
                        System.out.println(foundTask);
                    } else {
                        System.out.println("Task not found.");
                    }
                    break;
                case 8:
                    // Exit the program
                    System.out.println("Exiting Task Manager...");
                    break;
                default:
                    // Invalid choice
                    System.out.println("Invalid choice. Please enter a number between 1 and 8.");
                    break;
            }
        } while (choice != 8);

        // Close the scanner to avoid resource leak
        scanner.close();
    }
}
