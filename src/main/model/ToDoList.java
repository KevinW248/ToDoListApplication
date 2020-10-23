package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

//Represents a ToDoList, with a name, and list of tasks task IDs (both empty at first)
//JSON functionality incorporated from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class ToDoList implements Writable {
    private String name;
    private ArrayList<Task> taskList;
    private ArrayList<Integer> idList;

    //EFFECTS: creates new ToDoList with specified name, and empty task and ID lists
    public ToDoList(String name) {
        this.name = name;
        taskList = new ArrayList<>();
        idList = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds task to list of tasks, and the task id to a list of ID
    public void addTask(Task t) {
        taskList.add(t);
        idList.add(t.getId());
    }

    //REQUIRES: task id must be in idList
    //MODIFIES: this
    //EFFECTS: uses task id to locate remove the task from the tasklist
    public Task removeTask(int id) {
        int index = idList.indexOf(id);
        Task t = taskList.get(index);
        idList.remove(index);
        taskList.remove(index);
        return t;
    }

    //REQUIRES: task id must be in idList
    //MODIFIES: this, Task
    //EFFECTS: uses task id to locate mark a task in tasklist as complete
    public Task completeTask(int id) {
        int index = idList.indexOf(id);
        Task t = taskList.get(index);
        t.setComplete();
        return t;
    }

    //EFFECTS: returns name of ToDoList
    public String getName() {
        return name;
    }

    //EFFECTS: returns size of taskList
    public int getTaskListSize() {
        return taskList.size();
    }

    //EFFECTS: returns size of idList
    public int getIdListSize() {
        return idList.size();
    }

    //returns task at position p
    public Task getTaskAt(int p) {
        return taskList.get(p);
    }


    //EFFECTS: returns number of completed items in taskList
    public int getTotalCompleted() {
        int total = 0;
        for (Task next : taskList) {
            int t = next.getProgress();
            if (t == Task.COMPLETE) {
                total++;
            }
        }
        return total;
    }

    //EFFECTS: returns number of incomplete items in taskList
    public int getTotalIncomplete() {
        int total = 0;
        for (Task next : taskList) {
            int t = next.getProgress();
            if (t == Task.INCOMPLETE) {
                total++;
            }
        }
        return total;
    }

    //EFFECTS: returns list of tasks
    public ArrayList<Task> getTaskList() {
        return taskList;
    }


    @Override
    //EFFECTS: converts ToDoList into a JSONObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("taskList", tasksToJson());
        return json;
    }

    //EFFECTS: returns tasks in this ToDoList as a JSON array
    private JSONArray tasksToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Task t: taskList) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }
}
