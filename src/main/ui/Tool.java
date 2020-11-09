package ui;

import model.ToDoList;

import javax.swing.*;
import java.awt.event.MouseEvent;

public abstract class Tool {

    protected JButton button;
    protected ToDoListAppGUI toDoGUI;
    private boolean active;


    public Tool(ToDoListAppGUI toDoGUI, JComponent parent) {
        this.toDoGUI = toDoGUI;
        createButton(parent);
        addToParent(parent);
        active = false;
        addListener();
    }

    // MODIFIES: this
    // EFFECTS:  customizes the button used for this tool
    protected JButton customizeButton(JButton button) {
        button.setBorderPainted(true);
        button.setFocusPainted(true);
        button.setContentAreaFilled(true);
        return button;
    }

    // getters
    public boolean isActive() {
        return active;
    }

    // EFFECTS: sets this Tool's active field to true
    public void activate() {
        active = true;
    }

    // EFFECTS: sets this Tool's active field to false
    public void deactivate() {
        active = false;
    }

    // EFFECTS: creates button and adds to parent
    protected void createButton(JComponent parent) {
        button = new JButton(getLabel());
        addToParent(parent);
    }

    // EFFECTS: adds a listener for this tool
    protected abstract void addListener();

    // MODIFIES: parent
    // EFFECTS:  adds the given button to the parent component
    public void addToParent(JComponent parent) {
        parent.add(button);
    }



    //EFFECTS: Returns the string for the label.
    protected abstract String getLabel();

    // EFFECTS: default behaviour does nothing
    public void mousePressedInDrawingArea(MouseEvent e) {
    }

    // EFFECTS: default behaviour does nothing
    public void mouseReleasedInDrawingArea(MouseEvent e) {
    }

    // EFFECTS: default behaviour does nothing
    public void mouseClickedInDrawingArea(MouseEvent e) {
    }

    // EFFECTS: default behaviour does nothing
    public void mouseDraggedInDrawingArea(MouseEvent e) {
    }

}

