package ui;

import model.Task;
import model.ToDoList;

import java.util.Scanner;

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


    //MODIFIES: this
    //EFFECTS: takes various inputs and calls the corresponding operations
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
    //EFFECTS: adds a task to the ToDoList
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

    //MODIFIES: this
    //EFFECTS: marks the task with the entered task ID as complete
    public void finishTask() {
        System.out.println("Enter the ID of the task you have completed!");
        int id = scanner.nextInt();
        toDo.completeTask(id);
        System.out.println("Task is now marked as complete! Congratulations!");
        scanner.nextLine();
    }

    //MODIFIES: this
    //EFFECTS: removes the task with the entered task ID from the ToDoList
    public void removeTask() {
        System.out.println("Enter the ID of the task you would like to remove!");
        int id = scanner.nextInt();
        toDo.removeTask(id);
        System.out.println("Task is now removed!");
        scanner.nextLine();
    }

    //EFFECTS: prints all tasks, including their progress & urgency levels, and their ID
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