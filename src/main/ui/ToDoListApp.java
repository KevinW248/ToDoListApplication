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
        runToDo();
    }


    //MODIFIES: this
    //EFFECTS:  If user enters quit, stops program
    //            Otherwise, calls handleCommands to process the command
    public void runToDo() {
        String option;
        //keepGoing based on https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
        boolean keepGoing = true;

        while (keepGoing) {
            System.out.println("Select an option: 'add', 'remove', 'complete', 'view', 'quit'");

            option = scanner.nextLine();


            if (option.equals("quit")) {
                keepGoing = false;
            } else {
                handleCommands(option);
            }

            resolveEmpty();

        }
        System.out.println("Have a nice day!");
    }

    //EFFECTS: allows runToDo() to skip over any leftover empty strings picked up by the scanner
    private void resolveEmpty() {
        //Based on StackOverflow/CPSC Piazza:
        //https://stackoverflow.com/questions/16040601/why-is-nextline-returning-an-empty-string/16040699#16040699
        String currLine;
        while (!(currLine = scanner.nextLine()).isEmpty()) {
            System.out.println(currLine + "<");
        }
    }

    //design as a helper function based on https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    //MODIFIES: this
    //EFFECTS:  if command is add, adds a task for the user
    //          if remove, user chooses a task to remove
    //          if complete, user chooses a task to mark complete
    //          if view, prints all tasks for user to view
    //              Otherwise, states that option is invalid
    private void handleCommands(String option) {
        if (option.equals("add")) {
            addTask();
        } else if (option.equals("remove")) {
            removeTask();
        } else if (option.equals("complete")) {
            finishTask();
        } else if (option.equals("view")) {
            printTasks();
        } else {
            System.out.println("Selected option not valid");
        }
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
    }

    //REQUIRES: valid ID input
    //MODIFIES: this
    //EFFECTS: marks the task with the entered task ID as complete
    public void finishTask() {
        System.out.println("Enter the ID of the task you have completed!");
        int id = scanner.nextInt();
        toDo.completeTask(id);
        System.out.println("Task is now marked as complete! Congratulations!");
    }

    //REQUIRES: valid ID input
    //MODIFIES: this
    //EFFECTS: removes the task with the entered task ID from the ToDoList
    public void removeTask() {
        System.out.println("Enter the ID of the task you would like to remove!");
        int id = scanner.nextInt();
        toDo.removeTask(id);
        System.out.println("Task is now removed!");
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

        System.out.println("Finished printing! Press enter to continue:");
    }


}