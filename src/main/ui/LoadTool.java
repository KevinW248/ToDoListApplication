package ui;

import model.ToDoList;
import persistence.JsonReader;

import java.io.IOException;

public class LoadTool {
    private static final String JSON_STORE = "./data/ToDoList.json";

    JsonReader jsonReader;
    ToDoList toDo;

    public LoadTool(ToDoList toDo) {
        jsonReader = new JsonReader(JSON_STORE);
        this.toDo = toDo;
    }


    // MODIFIES: this
    // EFFECTS: loads todolist from file
    private void loadToDoList() {
        try {
            toDo = jsonReader.read();
            System.out.println("Loaded " + toDo.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}