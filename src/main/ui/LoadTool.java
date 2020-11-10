//package ui;
//
//import model.ToDoList;
//import persistence.JsonReader;
//
//import javax.swing.*;
//import java.io.IOException;
//
//public class LoadTool extends Tool {
//
//    private static final String JSON_STORE = "./data/ToDoList.json";
//
//    JsonReader jsonReader;
//    ToDoList toDo;
//
//    public LoadTool(ToDoListAppGUI toDoGUI, JComponent parent) {
//        super(toDoGUI, parent);
//        jsonReader = new JsonReader(JSON_STORE);
//        toDo = toDoGUI.toDo;
//    }
//
//    @Override
//    protected void addListener() {
//
//    }
//
//    @Override
//    protected String getLabel() {
//        return "Load Save";
//    }
//
//    // MODIFIES: this
//    // EFFECTS: loads workroom from file
//    private void loadToDoList() {
//        try {
//            toDo = jsonReader.read();
//            System.out.println("Loaded " + toDo.getName() + " from " + JSON_STORE);
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + JSON_STORE);
//        }
//    }
//
//}
