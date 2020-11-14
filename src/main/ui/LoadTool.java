package ui;

import persistence.JsonReader;

import java.io.IOException;
import java.io.Serializable;

//Tool that saves the GUI todolist to a file
public class LoadTool implements Serializable {
    private final ToDoListAppGUI toDoListAppGUI;
    final JsonReader jsonReader;

    public LoadTool(ToDoListAppGUI toDoListAppGUI) {
        this.toDoListAppGUI = toDoListAppGUI;
        this.jsonReader = new JsonReader(ToDoListAppGUI.JSON_STORE);
    }

    //MODIFIES: this
    //EFFECTS: loads todolist from file
    public void loadToDoList() {
        try {
            toDoListAppGUI.setToDoList(jsonReader.read());
            System.out.println("Loaded " + toDoListAppGUI.getToDoList().getName() + " from " + ToDoListAppGUI.JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + ToDoListAppGUI.JSON_STORE);
        }
    }
}