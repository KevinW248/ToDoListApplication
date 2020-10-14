package model;

import java.util.ArrayList;

//EFFECTS: Represents a ToDoList, with a list of tasks and a list of task IDs (both empty at first)
public class ToDoList {
    private ArrayList<Task> taskList = new ArrayList<>();
    private ArrayList<Integer> idList = new ArrayList<>();

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
    //MODIFIES: this
    //EFFECTS: uses task id to locate mark a task in tasklist as complete
    public Task completeTask(int id) {
        int index = idList.indexOf(id);
        Task t = taskList.get(index);
        t.setComplete();
        return t;
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

    //returns number of incomplete items in taskList
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

}
