package ui;

import model.Task;
import model.ToDoList;

import java.util.Scanner;

public class Main {
    private Scanner scanner;

    public static void main(String[] args) {
        ToDoList toDo = new ToDoList();
        Scanner mainScanner = new Scanner(System.in);

        System.out.println("Welcome to your amazing to-do list!");
        System.out.println("");

    }

    public void addTask() {

    }

    public void removeTask() {

    }

    public Boolean printTasks(ToDoList toDo) {
        System.out.println("You have selected: print tasks");
        System.out.println("Would you like to filter which tasks to print? Type Y for yes, N for no!");
        String first = scanner.nextLine();

        if (first.equals("N")) {
            for (Task next : toDo.getTaskList()) {
                System.out.println("Task " + next.getId() + " " + next.getDetails());
            }
            return false;
        } else {
        System.out.println("To filter by progress Type 0 for Incomplete,  for Complete, or 0 for all");
        int second = scanner.nextint();

        if (second==Task.COMPLETE || second.equals("C")) {

        }

        }
        System.out.println("To filter by importance, Type 1 for unimportant, 2 for moderate and 3 for urgent " +
                "or N for all");
        int third = scanner.nextInt();
    }


}
