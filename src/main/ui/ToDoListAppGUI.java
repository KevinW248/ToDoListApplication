//package ui;
//
//import model.ToDoList;
//import persistence.JsonReader;
//import persistence.JsonWriter;
//
//import javax.swing.*;
//import java.awt.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ToDoListAppGUI extends JFrame {
//
//    public static final int WIDTH = 1000;
//    public static final int HEIGHT = 800;
//
//    private List<Tool> tools = new ArrayList<>();
//    protected ToDoList toDo;
//    protected JsonWriter jsonWriter;
//    protected JsonReader jsonReader;
//
//    public ToDoListAppGUI() {
//        super("To-Do List");
//        initializeFields();
//        initializeGraphics();
////        initializeSound();
////        initializeInteraction();
//    }
//
//    // MODIFIES: this
//    // EFFECTS:  sets toDo to be a new, empty ToDoList
//    public void initializeFields() {
//        toDo = new ToDoList("My ToDoList");
//    }
//
//    // MODIFIES: this
//    // EFFECTS:  draws the JFrame window where this ToDoListApp operates, and populates the tools to be used
//    //           to manipulate this drawing
//    //TODO: change effects
//    public void initializeGraphics() {
//        setLayout(new BorderLayout());
//        setMinimumSize(new Dimension(WIDTH, HEIGHT));
//
//        createTools();
//
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//        setVisible(true);
//    }
//
//    private void createTools() {
//        JPanel toolArea = new JPanel();
//        toolArea.setLayout(new GridLayout(0,1));
//        toolArea.setSize(new Dimension(0, 0));
//        add(toolArea, BorderLayout.SOUTH);
//
//
//        SaveTool saveTool = new SaveTool(this, toolArea);
//        tools.add(saveTool);
//
//        LoadTool loadTool = new LoadTool(this, toolArea);
//        tools.add(loadTool);
//
//        AddTaskTool addTool = new AddTaskTool(this, toolArea);
//        tools.add(addTool);
//
//        RemoveTaskTool removeTool = new RemoveTaskTool(this, toolArea);
//        tools.add(removeTool);
//
//        ViewTasksTool viewTasksTool = new ViewTasksTool(this, toolArea);
//        tools.add(viewTasksTool);
////
////        setActiveTool(rectTool);
//    }




//}
