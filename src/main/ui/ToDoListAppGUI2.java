package ui;

import model.Task;
import model.ToDoList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ToDoListAppGUI2 extends JFrame implements ActionListener, ListSelectionListener {

    private JTextField field1;
    private JTextField field2;

    JButton addTaskButton;
    JButton removeTaskButton;
    JButton loadButton;
    JButton saveButton;

    //testing
    private ToDoList toDo = new ToDoList("My ToDoList");
    private JList list;
    private DefaultListModel<String> listModel = new DefaultListModel();

    private static final String JSON_STORE = "./data/ToDoList.json";
    private JsonReader jsonReader = new JsonReader(JSON_STORE);
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);

    public ToDoListAppGUI2() {
        super("My To-Do List");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700, 500));

        setLayout(new BorderLayout());

        //Create the list and put it in a scroll pane, based on ListDemoProject
        JScrollPane listScrollPane = createList();
        initializeFields();
        JPanel buttonPane = getButtonPane();


        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }

//    @org.jetbrains.annotations.NotNull
    private JScrollPane createList() {
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(10);
        JScrollPane listScrollPane = new JScrollPane(list);
        return listScrollPane;
    }

    private void initializeFields() {
        addTaskButton = createTodoButton("Add Task: Description, Urgency(1-3)", "addTask");
        removeTaskButton = createTodoButton("Remove Task", "removeTask");
        loadButton = createTodoButton("Load To-Do List", "loadToDo");
        saveButton = createTodoButton("Save To-Do List", "saveToDo");
        field1 = new JTextField(30);
        field2 = new JTextField(1);
    }

//    @org.jetbrains.annotations.NotNull
    private JPanel getButtonPane() {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,BoxLayout.PAGE_AXIS));

        JPanel subPane1 = getSubPaneA();
        JPanel subPane2 = getSubPaneB();

        buttonPane.setBorder(BorderFactory.createEmptyBorder(10,5,10,5));
        buttonPane.add(subPane1);
        buttonPane.add(subPane2);
        return buttonPane;
    }

//    @org.jetbrains.annotations.NotNull
    private JPanel getSubPaneB() {
        JPanel subPane2 = new JPanel();
        subPane2.setLayout(new BoxLayout(subPane2, BoxLayout.LINE_AXIS));
        subPane2.add(field1);
        subPane2.add(Box.createHorizontalStrut(10));
        subPane2.add(field2);
        subPane2.add(Box.createHorizontalStrut(10));
        subPane2.add(addTaskButton);
        subPane2.setBorder(BorderFactory.createEmptyBorder(2,5,0,5));
        return subPane2;
    }

//    @org.jetbrains.annotations.NotNull
    private JPanel getSubPaneA() {
        JPanel subPane1 = new JPanel();
        subPane1.setLayout(new BoxLayout(subPane1,BoxLayout.LINE_AXIS));
        subPane1.add(loadButton);
        subPane1.add(Box.createHorizontalStrut(5));
        subPane1.add(saveButton);
        subPane1.add(Box.createHorizontalStrut(5));
        subPane1.add(removeTaskButton);
        subPane1.setBorder(BorderFactory.createEmptyBorder(0,5,2,5));
        return subPane1;
    }

    private JButton createTodoButton(String buttonName, String commandName) {
        JButton addTaskButton = new JButton(buttonName);
        addTaskButton.setActionCommand(commandName);
        addTaskButton.addActionListener(this);
        return addTaskButton;
    }


    @Override
    //EFFECTS: if
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("addTask")) {
            addTask();
        } else if (e.getActionCommand().equals("removeTask")) {
            removeTask();
        } else if (e.getActionCommand().equals("saveToDo")) {
            saveToDoList();
        } else if (e.getActionCommand().equals("loadToDo")) {
            loadToDoList();
            uploadToDoList();
        }

    }

    public void addTask() {
        String details = field1.getText();
        int urgency = Integer.parseInt(field2.getText());

        Task newTask = new Task(details, urgency, 0);
        toDo.addTask(newTask);

        int index = list.getSelectedIndex(); //get selected index
        if (index == -1) { //no selection, so insert at beginning
            index = 0;
        } else {           //add after the selected item
            index++;
        }

        String text = getTaskInfo(newTask);
        listModel.insertElementAt(text, index);
        //If we just wanted to add to the end, we'd do this:
//        listModel.addElement(employeeName.getText());

        //Reset the text fields.
        field1.requestFocusInWindow();
        field1.setText("");
        field2.requestFocusInWindow();
        field2.setText("");

        //Select the new item and make it visible.
        list.setSelectedIndex(index);
        list.ensureIndexIsVisible(index);
    }


    //EFFECTS: produces the string for the task information to be displayed
    private String getTaskInfo(Task newTask) {
        String progress;
        if (newTask.getProgress() == Task.INCOMPLETE) {
            progress = "incomplete";
        } else {
            progress = "complete";
        }
        String text = "Details: " + newTask.getDetails() + "    Urgency: " + newTask.getUrgency()
                + "    Progress: " + progress;

        return text;
    }

    @Override
    //based on ListDemoProject
    //MODIFIES: this
    //EFFECTS: If there is no task selected, disable remove button
    //         If there is, enable it
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                removeTaskButton.setEnabled(false);

            } else {
                removeTaskButton.setEnabled(true);
            }
        }
    }

    public void removeTask() {
        //This method can be called only if
        //there's a valid selection
        //so go ahead and remove whatever's selected.
        int index = list.getSelectedIndex();
        listModel.remove(index);

        int size = listModel.getSize();

        if (size == 0) { //Nobody's left, disable firing.
            removeTaskButton.setEnabled(false);

        } else { //Select an index.
            if (index == listModel.getSize()) {
                //removed item in last position
                index--;
            }

            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }
    }

    //MODIFIES: this
    //EFFECTS: loads todolist from file
    private void loadToDoList() {
        try {
            toDo = jsonReader.read();
            System.out.println("Loaded " + toDo.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: clears list model and replaces it with everything in todolist
    private void uploadToDoList() {
        listModel.clear();
        //TODO: make this unmodifiable getTaskList
        for (Task t: toDo.getTaskList()) {
            String info = getTaskInfo(t);
            listModel.addElement(info);
        }
    }

    // EFFECTS: saves the todolist to file
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
