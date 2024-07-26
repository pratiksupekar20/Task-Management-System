package com.jap.task;

import java.util.*;

public class TaskOperations {
    //declare the attribute taskName,userTaskMap,categoryTaskMap

    private Set<String> taskNames;
    private Map<User, List<Task>> userTaskMap;
    private Map<Category, List<Task>> categoryTaskMap;

    List<String> listOfTasks;

    public TaskOperations() {
        //initialize the attributes

        taskNames = new HashSet<String>();
        userTaskMap = new HashMap<User, List<Task>>();
        categoryTaskMap = new HashMap<Category, List<Task>>();
    }


    public boolean addTask(Category selectedCategory, Task task, User authenticatedUser) {

        if (taskNames.contains(task.getTaskName())) {
            System.out.println("Task already exists.");
            return false;
        }

        List<Task> userTasks = userTaskMap.get(authenticatedUser);
        if (userTasks == null) {
            userTasks = new ArrayList<Task>();
            userTaskMap.put(authenticatedUser, userTasks);
        }
        userTasks.add(task);

        List<Task> categoryTasks = categoryTaskMap.get(selectedCategory);
        if (categoryTasks == null) {
            categoryTasks = new ArrayList<Task>();
            categoryTaskMap.put(selectedCategory, categoryTasks);
        }
        categoryTasks.add(task);

        taskNames.add(task.getTaskName());
        return true;
    }

    public List<String> listAllTasks(String categoryName) {
        Category selectedCategory = CategoryOperations.getCategoryByName(categoryTaskMap, categoryName);
        List<String> sortedTasks = new ArrayList<String>();
        if (selectedCategory == null) {
            System.out.println("Category not found.");
            return sortedTasks;
        }
        List<Task> tasks = categoryTaskMap.get(selectedCategory);
        if (tasks != null) {
            for (Task task : tasks) {
                sortedTasks.add("Category: " + categoryName + " - " +
                        "Name: " + task.getTaskName() + " Priority: " + task.getPriority() +
                        " Description: " + task.getDescription() + " Status: " + (task.isCompleted() ? "Completed" : "Pending"));
            }
        }
        return sortedTasks;
    }

    public boolean markTaskAsCompleted(String taskToComplete, String statusInput) {

        for (List<Task> tasks : categoryTaskMap.values()) {
            for (Task task : tasks) {
                if (task.getTaskName().equals(taskToComplete)) {
                    if (statusInput.equalsIgnoreCase("completed")) {
                        task.setCompleted(true);
                        return true;
                    } else if (statusInput.equalsIgnoreCase("pending")) {
                        task.setCompleted(false);
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeTask(String taskToRemove) throws TaskNotFoundException {
        //iterate the list of task from categoryTaskMap
        //check if the task with the given name is present
        // remove the task
        //remove the task from taskNames
        //return true if the task is removed
        //if task is not removed throw TaskNotFoundException

        boolean taskRemoved = false;
        Iterator<Map.Entry<Category, List<Task>>> categoryIterator = categoryTaskMap.entrySet().iterator();
        while (categoryIterator.hasNext()) {
            Map.Entry<Category, List<Task>> entry = categoryIterator.next();
            Iterator<Task> taskIterator = entry.getValue().iterator();
            while (taskIterator.hasNext()) {
                Task task = taskIterator.next();
                if (task.getTaskName().equals(taskToRemove)) {
                    taskIterator.remove();
                    taskNames.remove(taskToRemove);
                    taskRemoved = true;
                }
            }
        }
        if (!taskRemoved) {
            throw new TaskNotFoundException("Task not found: " + taskToRemove);
        }
        return taskRemoved;
    }

    public Task searchTasksByName(String taskName) {
        //iterate the list of task from categoryTaskMap
        //check if the task with the given name is present
        //add it to the matchingTaskList
        for (List<Task> tasks : categoryTaskMap.values()) {
            for (Task task : tasks) {
                if (task.getTaskName().equals(taskName)) {
                    return task;
                }
            }
        }
        return null;
    }
}

