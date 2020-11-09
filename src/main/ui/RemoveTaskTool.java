package ui;

import javax.swing.*;

public class RemoveTaskTool extends Tool {
    public RemoveTaskTool(ToDoListAppGUI toDoGUI, JComponent parent) {
        super(toDoGUI, parent);
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected String getLabel() {
        return "Remove Task";
    }
}
