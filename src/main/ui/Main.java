package ui;

import model.Task;
import model.ToDoList;

import java.util.ArrayList;
import java.util.Scanner;

//Structure of the Main class is based on B04-SimpleCalculatorStartLecLab
//           - handleCommands, Main constructor, psvm
public class Main {
    private Scanner scanner;
    private ToDoList toDo;

    public Main() {
        toDo = new ToDoList();
        scanner = new Scanner(System.in);
        handleCommands();
    }

    public void handleCommands() {
        String option;

        while (true) {
            System.out.println("Select an option: 'add', 'remove', 'complete', 'view', 'quit'");

            option = scanner.nextLine();

            if (option.equals("quit")) {
                break;
            } else if (option.equals("remove")) {
                removeTask();
            } else if (option.equals("add")) {
                addTask();
            } else if (option.equals("complete")) {
                finishTask();
            } else if (option.equals("view")) {
                printTasks();
            }

        }
        System.out.println("Have a nice day!");
    }

    public static void main(String[] args) {
        new Main();
    }

    //adds a task to the ToDoList
    public void addTask() {
        System.out.println("Type in your task's description!");
        String description = scanner.nextLine();
        System.out.println("Description received! Type in its urgency, where 1 is least urgent (1,2,3)!");
        int urgency = scanner.nextInt();

        Task t = new Task(description, urgency);
        toDo.addTask(t);
        System.out.println("Added task! Task ID is: " + t.getId());
    }

    public void finishTask() {
        System.out.println("Enter the ID of the task you have completed!");
        int id = scanner.nextInt();
        toDo.completeTask(id);
        System.out.println("Task is now marked as complete! Congratulations!");
    }

    public void removeTask() {
        System.out.println("Enter the ID of the task you would like to remove!");
        int id = scanner.nextInt();
        toDo.removeTask(id);
        System.out.println("Task is now removed!");
    }

    public void printTasks() {
        System.out.println("Would you like to filter which tasks to print? Type Y for yes, N for no!");
        String choice = scanner.nextLine();

        if (choice.equals("N")) {
            for (Task next : toDo.getTaskList()) {
                System.out.println("Task " + next.getId() + " " + next.getDetails());
            }
        } else {
            printTasksTwo();
        }
        System.out.println("Finished printing!");
    }

    public void printTasksTwo() {
        System.out.println("To filter by progress, Type 'P'. To filter by urgency, type U. For both, type B!");
        String choice = scanner.nextLine();

        if (choice.equals("P")) {
            printTasksProgress();
        } else if (choice.equals("U")) {
            printTasksUrgency();
        } else if (choice.equals("B")) {
            printTasksBoth();
        }
    }

    public void printTasksProgress() {
        System.out.println("Type 'I' for Incomplete, 'C' for Complete!");
        String choice = scanner.nextLine();
        int progress;

        if (choice.equals("I")) {
            progress = Task.INCOMPLETE;
            System.out.println("Filtering by incomplete tasks!");
        } else {
            progress = Task.COMPLETE;
            System.out.println("Filtering by complete tasks!");
        }
        printAll(progress);
    }

    public void printTasksUrgency() {
        System.out.println("Type '1' for Not important, '2' for Moderately Important, and '3' for Very Important!");
        int choice = scanner.nextInt();
        int urgency = choice;
        System.out.println("Filtering by Urgency level " + urgency + "!");

        printAll(urgency);
    }

    public void printTasksBoth() {
        System.out.println("First, Type 'I' for Incomplete, 'C' for Complete!");
        String choice = scanner.nextLine();
        int progress;
        System.out.println("Next, type '1' for Not important, '2' for Moderately Important, "
                + "and '3' for Very Important!");
        int urgency = scanner.nextInt();

        if (choice.equals("I")) {
            progress = Task.INCOMPLETE;
            System.out.println("Filtering by incomplete tasks!");
        } else {
            progress = Task.COMPLETE;
            System.out.println("Filtering by complete tasks!");
        }

        printAll(progress,urgency);
    }



    public void printAll(int i) {
        for (Task next : toDo.getTaskList()) {
            if (next.getProgress() == i) {
                String details = next.getDetails();
                System.out.println(details + " Task ID: " + next.getId());
            }
        }
    }

    public void printAll(int i1, int i2) {
        for (Task next : toDo.getTaskList()) {
            if (next.getProgress() == i1 && next.getUrgency() == i2) {
                String details = next.getDetails();
                System.out.println(details + " Task ID: " + next.getId());
            }
        }
    }
}