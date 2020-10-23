package ui;

import model.Task;
import model.ToDoList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//To do List Application
//Structure of this class is partially based on https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
//JSON functionality incorporated from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class ToDoListApp {
    private Scanner scanner;
    private ToDoList toDo;
    private static final String JSON_STORE = "./data/workroom.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    public ToDoListApp() {
        toDo = new ToDoList("My ToDoList");
        scanner = new Scanner(System.in);
        //jsonWriter = new JsonWriter(JSON_STORE);
        //jsonReader = new JsonReader(JSON_STORE);
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
            displayOptions();

            option = scanner.nextLine();
            option = option.toLowerCase();


            if (option.equals("quit")) {
                keepGoing = false;
            } else {
                handleCommands(option);
            }

        }
        System.out.println("Have a nice day!");
    }

    //format based on https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    //EFFECTS: displays all options for the user
    public void displayOptions() {
        System.out.println("\nSelect an option!");
        System.out.println("\tadd - adds a task");
        System.out.println("\tremove - removes a task");
        System.out.println("\tcomplete - completes a task");
        System.out.println("\tview - views all tasks");
        System.out.println("\tquit - quits the toDoList");
    }

    //design as a helper function based on https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    //MODIFIES: this, Task, ToDoList
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

    //MODIFIES: this, ToDoList
    //EFFECTS: adds a task to the ToDoList with a description and urgency provided by user
    public void addTask() {
        System.out.println("Type in your task's description!");
        String description = scanner.nextLine();
        System.out.println("Description received! Type in its urgency, where 1 is least urgent (1,2,3)!");
        int urgency = Integer.parseInt(scanner.nextLine());

        Task t = new Task(description, urgency, Task.INCOMPLETE);
        toDo.addTask(t);
        System.out.println("Added task! Task ID is: " + t.getId());
    }

    //REQUIRES: valid ID input
    //MODIFIES: this, ToDoList, Task
    //EFFECTS: marks the task with the entered task ID as complete
    public void finishTask() {
        System.out.println("Enter the ID of the task you have completed!");
        int id = Integer.parseInt(scanner.nextLine());
        toDo.completeTask(id);
        System.out.println("Task is now marked as complete! Congratulations!");
    }

    //REQUIRES: valid ID input
    //MODIFIES: this, ToDoList
    //EFFECTS: removes the task with the entered task ID from the ToDoList
    public void removeTask() {
        System.out.println("Enter the ID of the task you would like to remove!");
        int id = Integer.parseInt(scanner.nextLine());
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

    //TODO: add save and load functionality

//    // EFFECTS: saves the workroom to file
//    private void saveWorkRoom() {
//        try {
//            jsonWriter.open();
//            jsonWriter.write(workRoom);
//            jsonWriter.close();
//            System.out.println("Saved " + workRoom.getName() + " to " + JSON_STORE);
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to write to file: " + JSON_STORE);
//        }
//    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadWorkRoom() {
        try {
            toDo = jsonReader.read();
            System.out.println("Loaded " + toDo.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}