package ui;

import java.io.Serializable;


//based on ListDemo
////https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html

//Tool that removes selected tasks on the todolist
public class RemoveTaskTool implements Serializable {
    private final ToDoListAppGUI toDoListAppGUI;

    public RemoveTaskTool(ToDoListAppGUI toDoListAppGUI) {
        this.toDoListAppGUI = toDoListAppGUI;
    }

    //based on ListDemo
    //MODIFIES: this, todolist
    //EFFECTS: method only called if a valid selection is made
    //         removes selected task from todolist and screen
    //         if no tasks remaining, deactivates button
    public void removeTask() {

        int index = toDoListAppGUI.getList().getSelectedIndex();

        String taskString = toDoListAppGUI.getListModel().get(index);
        int taskID = Integer.parseInt(String.valueOf(taskString.charAt(taskString.length() - 1)));

        toDoListAppGUI.getListModel().remove(index);
        toDoListAppGUI.getToDoList().removeTask(taskID);

        int size = toDoListAppGUI.getListModel().getSize();

        if (size == 0) { //Disable removing
            toDoListAppGUI.getRemoveTaskButton().setEnabled(false);

        } else { //Select an index.
            if (index == toDoListAppGUI.getListModel().getSize()) {
                //removed item in last position
                index--;
            }

            toDoListAppGUI.getList().setSelectedIndex(index);
            toDoListAppGUI.getList().ensureIndexIsVisible(index);
        }
    }
}