package ui;

import model.ToDoList;
import persistence.JsonWriter;

import javax.swing.*;
import java.io.FileNotFoundException;

public class SaveTool extends Tool {
    private static final String JSON_STORE = "./data/ToDoList.json";

    JsonWriter jsonWriter;
    ToDoList toDo;

    public SaveTool(ToDoListAppGUI toDoGUI, JComponent parent) {
        super(toDoGUI, parent);
        jsonWriter = new JsonWriter(JSON_STORE);
        toDo = toDoGUI.toDo;
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected String getLabel() {
        return "Save Task";
    }

    // EFFECTS: saves the workroom to file
    private void saveToDoList() {
        try {
            jsonWriter.open();
            jsonWriter.write(toDo);
            jsonWriter.close();
            System.out.println("Saved " + toDo.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

}
