package ui;

import model.Task;
import model.ToDoList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

////To do List Application GUI
//Scroll pane functionality, JList functionality, some display based on ListDemo
//https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
//button functionality based on
// https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
public class ToDoListAppGUI extends JFrame implements ActionListener, ListSelectionListener {

    private final SaveTool saveTool = new SaveTool(this);
    private final LoadTool loadTool = new LoadTool(this);
    private final RemoveTaskTool removeTaskTool = new RemoveTaskTool(this);
    private final AddTaskTool addTaskTool = new AddTaskTool(this);
    //text fields
    private JTextField field1;
    private JTextField field2;

    //buttons
    JButton addTaskButton;
    JButton removeTaskButton;
    JButton loadButton;
    JButton saveButton;

    //List
    private ToDoList toDoList = new ToDoList("My ToDoList");
    private JList list;
    private DefaultListModel<String> listModel = new DefaultListModel();

    //Json functionality
    protected static final String JSON_STORE = "./data/ToDoList.json";

    //EFFECTS: initializes GUI, including scrolling/list area, setting button functionality,
    //         setting up the display
    public ToDoListAppGUI() {
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

    //EFFECTS: creates the scroll pane to display tasks
    private JScrollPane createList() {
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(10);
        return new JScrollPane(list);
    }

    //MODIFIES: this
    //EFFECTS: initializes button functionalities and text fields
    private void initializeFields() {
        addTaskButton = createTodoButton("Add Task: Description, Urgency(1-3)", "addTask");
        removeTaskButton = createTodoButton("Remove Task", "removeTask");
        loadButton = createTodoButton("Load To-Do List", "loadToDo");
        saveButton = createTodoButton("Save To-Do List", "saveToDo");
        field1 = new JTextField(30);
        field2 = new JTextField(1);
    }

    //EFFECTS: creates the button pane that will be displayed on GUI
    //BorderFactory.createEmptyBorder and Box.createHorizontalStrut is
    // inspired by ListDemo
    private JPanel getButtonPane() {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.PAGE_AXIS));

        JPanel subPane1 = getSubPaneA();
        JPanel subPane2 = getSubPaneB();

        buttonPane.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        buttonPane.add(subPane1);
        buttonPane.add(subPane2);
        return buttonPane;
    }

    //EFFECTS: creates the second row of the button pane that will be displayed on GUI
    private JPanel getSubPaneB() {
        JPanel subPane2 = new JPanel();
        subPane2.setLayout(new BoxLayout(subPane2, BoxLayout.LINE_AXIS));
        subPane2.add(field1);
        subPane2.add(Box.createHorizontalStrut(10));
        subPane2.add(field2);
        subPane2.add(Box.createHorizontalStrut(10));
        subPane2.add(addTaskButton);
        subPane2.setBorder(BorderFactory.createEmptyBorder(2, 5, 0, 5));
        return subPane2;
    }

    //EFFECTS: creates the first row of the button pane that will be displayed on GUI
    private JPanel getSubPaneA() {
        JPanel subPane1 = new JPanel();
        subPane1.setLayout(new BoxLayout(subPane1, BoxLayout.LINE_AXIS));
        subPane1.add(loadButton);
        subPane1.add(Box.createHorizontalStrut(5));
        subPane1.add(saveButton);
        subPane1.add(Box.createHorizontalStrut(5));
        subPane1.add(removeTaskButton);
        subPane1.setBorder(BorderFactory.createEmptyBorder(0, 5, 2, 5));
        return subPane1;
    }

    //EFFECTS: creates a new button for the TodoList GUI, with a specified button name and command
    private JButton createTodoButton(String buttonName, String commandName) {
        JButton newTaskButton = new JButton(buttonName);
        newTaskButton.setActionCommand(commandName);
        newTaskButton.addActionListener(this);
        newTaskButton.setBackground(new Color(186, 216, 247));
        return newTaskButton;
    }

    public ToDoList getToDoList() {
        return toDoList;
    }

    public void setToDoList(ToDoList toDoList) {
        this.toDoList = toDoList;
    }

    public JList getList() {
        return list;
    }

    public DefaultListModel<String> getListModel() {
        return listModel;
    }

    public JButton getRemoveTaskButton() {
        return removeTaskButton;
    }

    public JTextField getField1() {
        return field1;
    }

    public JTextField getField2() {
        return field2;
    }


    @Override
    //MODIFIES: this
    //EFFECTS: For each button, (either add task, remove task, save todolist, or load todolist
    //          performs the task specified by the button when clicked
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
        java.awt.Toolkit.getDefaultToolkit().beep();
    }

    //based on ListDemo
    //MODIFIES: this, todolist
    //EFFECTS: adds task to todolist and GUI based on input into both fields
    //         places task at area of selection or, if none selected, at the beginning
    public void addTask() {
        addTaskTool.addTask();
    }


    //EFFECTS: produces the string for the task information to be displayed
    private String getTaskInfo(Task newTask) {

        return addTaskTool.getTaskInfo(newTask);
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

    //based on ListDemo
    //MODIFIES: this, todolist
    //EFFECTS: method only called if a valid selection is made
    //         removes selected task from todolist and screen
    //         if no tasks remaining, deactivates button
    public void removeTask() {
        removeTaskTool.removeTask();
    }

    //MODIFIES: this
    //EFFECTS: loads todolist from file
    private void loadToDoList() {
        loadTool.loadToDoList();
    }

    //MODIFIES: this
    //EFFECTS: clears list model and replaces it with everything in todolist
    private void uploadToDoList() {
        listModel.clear();
        for (Task t : toDoList.getTaskList()) {
            String info = addTaskTool.getTaskInfo(t);
            listModel.addElement(info);
        }
    }

    // EFFECTS: saves the todolist to file
    private void saveToDoList() {
        saveTool.saveToDoList(toDoList);
    }
}
