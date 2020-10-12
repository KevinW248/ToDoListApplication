package model;

//Represents a task having an id, details, urgency, and progress
public class Task {

    //represents urgency levels
    public static final int NOT_IMPORTANT = 1;
    public static final int MODERATE_IMPORTANT = 2;
    public static final int VERY_IMPORTANT = 3;

    public static final int INCOMPLETE = 0;
    public static final int COMPLETE = 1;

    //nextTaskId based on https://github.students.cs.ubc.ca/CPSC210/TellerApp
    private static int nextTaskId = 1;              //keeps track of ID for next task created
    private int id;                                 //task ID
    private String details;                         //task details
    private int urgency;                            //task urgency
    private int progress;                           //task progress

    //REQUIRES: details is longer than 0 characters
    //EFFECTS: makes a new task, where:
    //         id is set to a unique positive integer for each new task,
    //         details are set to taskDetails,
    //         urgency is set to urgencyLevel if 1<=urgencyLevel<=3
    //              otherwise, urgencyLevel is 3
    //         progress is set to INCOMPLETE
    public Task(String taskDetails, int urgencyLevel) {
        ////nextTaskId based on https://github.students.cs.ubc.ca/CPSC210/TellerApp
        id = nextTaskId++;
        this.details = taskDetails;
        if (NOT_IMPORTANT <= urgencyLevel && urgencyLevel <= VERY_IMPORTANT) {
            this.urgency = urgencyLevel;
        } else {
            this.urgency = VERY_IMPORTANT;
        }
        progress = INCOMPLETE;
    }

    public int getId() {
        return id;
    }

    public String getDetails() {
        return details;
    }

    public int getUrgency() {
        return urgency;
    }

    public int getProgress() {
        return progress;
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

    //MODIFIES: this
    //EFFECTS: if progress is incomplete
    //              - update it to be complete and return true
    //         otherwise, return false
    public Boolean markComplete() {
        if (progress == INCOMPLETE) {
            progress = COMPLETE;
            return true;
        }
        return false;
    }

}
