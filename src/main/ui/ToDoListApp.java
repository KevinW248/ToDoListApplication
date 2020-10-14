package ui;

import model.Task;
import model.ToDoList;

import java.util.Scanner;

//To do List Application
//Structure of this class is partially based on B04-SimpleCalculatorStartLecLab
//           - handleCommands method, constructor
public class ToDoListApp {
    private Scanner scanner;
    private ToDoList toDo;

    public ToDoListApp() {
        toDo = new ToDoList();
        scanner = new Scanner(System.in);
        handleCommands();
    }

    //REQUIRES: must enter inputs of either quit, add, remove complete, view
    //MODIFIES: this
    //EFFECTS: only responds to 5 inputs:
    //               - if user types quit, stops program
    //               - if add, adds a task for the user
    //               - if remove, user chooses a task to remove
    //               - if complete, user chooses a task to mark complete
    //               - if view, prints all tasks for user to view
    public void handleCommands() {
        String option;

        while (true) {
            System.out.println("Select an option: 'add', 'remove', 'complete', 'view', 'quit'");

            option = scanner.nextLine();


            if (option.equals("quit")) {
                break;
            } else if (option.equals("add")) {
                addTask();
            } else if (option.equals("remove")) {
                removeTask();
            } else if (option.equals("complete")) {
                finishTask();
            } else if (option.equals("view")) {
                printTasks();
            }
        }
        System.out.println("Have a nice day!");
    }

    //MODIFIES: this
    //EFFECTS: adds a task to the ToDoList with a description and urgency provided by user
    public void addTask() {
        System.out.println("Type in your task's description!");
        String description = scanner.nextLine();
        System.out.println("Description received! Type in its urgency, where 1 is least urgent (1,2,3)!");
        int urgency = scanner.nextInt();

        Task t = new Task(description, urgency);
        toDo.addTask(t);
        System.out.println("Added task! Task ID is: " + t.getId());
        //necessary vvv to prevent handleCommands from printing twice
        scanner.nextLine();
    }

    //REQUIRES: valid ID input
    //MODIFIES: this
    //EFFECTS: marks the task with the entered task ID as complete
    public void finishTask() {
        System.out.println("Enter the ID of the task you have completed!");
        int id = scanner.nextInt();
        toDo.completeTask(id);
        System.out.println("Task is now marked as complete! Congratulations!");
        scanner.nextLine();
    }

    //REQUIRES: valid ID input
    //MODIFIES: this
    //EFFECTS: removes the task with the entered task ID from the ToDoList
    public void removeTask() {
        System.out.println("Enter the ID of the task you would like to remove!");
        int id = scanner.nextInt();
        toDo.removeTask(id);
        System.out.println("Task is now removed!");
        scanner.nextLine();
    }

    //EFFECTS: prints all tasks, including their progress & urgency levels, and their IDs
    public void printTasks() {
        System.out.println("Printing all tasks!");

        for (Task next : toDo.getTaskList()) {
            String progress;
            if (next.getProgress() == Task.INCOMPLETE) {
                progress = "incomplete";
            } else {
                progress = "complete";
            }
            System.out.println(next.getDetails() + ", Urgency level: "
                    + next.getUrgency() + ", Progress: " + progress + ", Task ID: " + next.getId());
        }
        System.out.println("# Tasks completed: " + toDo.getTotalCompleted());
        System.out.println("# Tasks incomplete: " + toDo.getTotalIncomplete());

        System.out.println("Finished printing!");
    }
}