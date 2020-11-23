package ui;

import exception.InvalidInputException;
import model.Task;

import java.io.Serializable;

//based on ListDemo
////https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
//Tool that adds tasks to the todolist
public class AddTaskTool implements Serializable {
    private final ToDoListAppGUI toDoListAppGUI;

    public AddTaskTool(ToDoListAppGUI toDoListAppGUI) {
        this.toDoListAppGUI = toDoListAppGUI;
    }

    //based on ListDemo
    //MODIFIES: this, todolist
    //EFFECTS: adds task to todolist and GUI based on input into both fields
    //         places task at area of selection or, if none selected, at the beginning
    public void addTask() {
        String details = toDoListAppGUI.getField1().getText();
        int urgency = Integer.parseInt(toDoListAppGUI.getField2().getText());

        Task newTask = null;
        try {
            newTask = new Task(details, urgency, Task.INCOMPLETE);
        } catch (InvalidInputException e) {
            newTask = new Task();
        }
        toDoListAppGUI.getToDoList().addTask(newTask);

        int index = toDoListAppGUI.getList().getSelectedIndex(); //get selected index
        if (index == -1) { //no selection, so insert at beginning
            index = 0;
        } else {           //add after the selected item
            index++;
        }

        String text = getTaskInfo(newTask);
        toDoListAppGUI.getListModel().insertElementAt(text, index);

        //Reset text fields.
        toDoListAppGUI.getField1().requestFocusInWindow();
        toDoListAppGUI.getField1().setText("");
        toDoListAppGUI.getField2().requestFocusInWindow();
        toDoListAppGUI.getField2().setText("");

        //Select the new item and make it visible.
        toDoListAppGUI.getList().setSelectedIndex(index);
        toDoListAppGUI.getList().ensureIndexIsVisible(index);
    }

    //EFFECTS: produces the string for the task information to be displayed
    String getTaskInfo(Task newTask) {
        String progress;
        if (newTask.getProgress() == Task.INCOMPLETE) {
            progress = "incomplete";
        } else {
            progress = "complete";
        }
        String taskText = "Details: " + newTask.getDetails()
                + "    Urgency: " + newTask.getUrgency()
                + "    Progress: " + progress
                + "    ID: " + newTask.getId();

        return taskText;
    }
}