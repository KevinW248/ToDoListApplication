package ui;

import javax.swing.*;

public class ViewTasksTool extends Tool {

    public ViewTasksTool(ToDoListAppGUI toDoGUI, JComponent parent) {
        super(toDoGUI, parent);
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected String getLabel() {
        return "View Tasks";
    }

}
