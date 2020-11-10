package ui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToDoListAppGUI2 extends JFrame implements ActionListener, ListSelectionListener {

    private JTextField field1;
    private JTextField field2;

    JButton addTaskButton;
    JButton removeTaskButton;
    JButton loadButton;
    JButton saveButton;

    //testing
    private JList list;
    private DefaultListModel listModel;

    public ToDoListAppGUI2() {
        super("My To-Do List");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700, 500));

        setLayout(new BorderLayout());

        listModel = new DefaultListModel();
        listModel.addElement("Jane Doe");
        listModel.addElement("John Smith");
        listModel.addElement("Kathy Green");

        //Create the list and put it in a scroll pane.
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(10);
        JScrollPane listScrollPane = new JScrollPane(list);


        initializeFields();

        JPanel buttonPane = getButtonPane();


        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
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
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("addTask")) {
            field1.getText();
        } else if (e.getActionCommand().equals("removeTask")) {
            field1.getText();
        } else if (e.getActionCommand().equals("saveTodo")) {
            field1.getText();
        } else if (e.getActionCommand().equals("loadTodo")) {
            field1.getText();
        }

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                //No selection, disable fire button.
                removeTaskButton.setEnabled(false);

            } else {
                //Selection, enable the fire button.
                removeTaskButton.setEnabled(true);
            }
        }
    }
}
