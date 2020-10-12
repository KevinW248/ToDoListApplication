package model;

//Represents a task having an id, details, urgency, and progress
public class Task {
    //nextTaskId based on https://github.students.cs.ubc.ca/CPSC210/TellerApp
    private static int nextTaskId = 1;
    private int id;
    private String details;
    private int urgency;
    private int progress;

    //REQUIRES: details is longer than 0 characters
    //EFFECTS: makes a new task, where:
    //         id is set to a unique positive integer for each new task,
    //         details are set to taskDetails,
    //         urgency is set to urgencyLevel if 1<=urgencyLevel<=3
    //              otherwise, urgencyLevel is 3
    //         progress is set to 0
    public Task(String taskDetails, int urgencyLevel) {
        ////nextTaskId based on https://github.students.cs.ubc.ca/CPSC210/TellerApp
        id = nextTaskId++;
        this.details = taskDetails;
        if (1 <= urgencyLevel && urgencyLevel <= 3) {
            this.urgency = urgencyLevel;
        } else {
            this.urgency = 3;
        }
        progress = 0;
    }

    public int getId() {
        return id;
    }

    public String getDetails() {
        return details;
    }

    public int getProgress() {
        return progress;
    }

    public int getUrgency() {
        return urgency;
    }

    //MODIFIES: this
    //EFFECTS: changes task details to string s
    public void editDetails(String s) {
        details = s;
    }

    //MODIFIES: this
    //EFFECTS: if 1<= u <= 3,
    //            - updates Urgency to u and returns true
    //         otherwise, urgency doesn't change and returns false
    public Boolean editUrgency(int u) {
        if (u >= 1 && u <= 3) {
            urgency = u;
            return true;
        }
        return false;
    }

}
