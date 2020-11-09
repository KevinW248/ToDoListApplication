package ui;

import model.ToDoList;

import javax.swing.*;

public class AddTaskTool extends Tool {


    public AddTaskTool(ToDoListAppGUI toDoGUI, JComponent parent) {
        super(toDoGUI, parent);
    }


    @Override
    protected void addListener() {

    }

    @Override
    protected String getLabel() {
        return "Add Task";
    }
}
