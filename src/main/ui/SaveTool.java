package ui;

import model.ToDoList;
import persistence.JsonWriter;
import ui.ToDoListAppGUI;

import java.io.FileNotFoundException;
import java.io.Serializable;

//Tool that loads a saved todolist to the GUI
public class SaveTool implements Serializable {

    private final ToDoListAppGUI toDoListAppGUI;
    final JsonWriter jsonWriter;

    public SaveTool(ToDoListAppGUI toDoListAppGUI) {
        this.toDoListAppGUI = toDoListAppGUI;
        this.jsonWriter = new JsonWriter(ToDoListAppGUI.JSON_STORE);
    }

    // EFFECTS: saves the todolist to file
    public void saveToDoList(ToDoList toDo) {
        try {
            jsonWriter.open();
            jsonWriter.write(toDoListAppGUI.getToDoList());
            jsonWriter.close();
            System.out.println("Saved " + toDoListAppGUI.getToDoList().getName() + " to "
                    + ToDoListAppGUI.JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + ToDoListAppGUI.JSON_STORE);
        }
    }
}