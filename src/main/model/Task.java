package model;

import exception.InvalidInputException;
import org.json.JSONObject;
import persistence.Writable;

//Represents a task having an id, details, urgency, and progress
public class Task implements Writable {

    //represents urgency levels
    //
    public static final int NOT_IMPORTANT = 1;
    public static final int VERY_IMPORTANT = 3;

    public static final int INCOMPLETE = 0;
    public static final int COMPLETE = 1;

    //nextTaskId based on https://github.students.cs.ubc.ca/CPSC210/TellerApp
    private static int nextTaskId = 1;              //keeps track of ID for next task created
    private int id;                                 //task ID
    private String details;                         //task details
    private int urgency;                            //task urgency
    private int progress;                           //task progress

    //EFFECTS: makes a new task, where:
    //         id is set to a unique positive integer for each new task,
    //         details are set to taskDetails,
    //         urgency is set to urgencyLevel if 1<=urgencyLevel<=3
    //               otherwise, throws invalid input exception
    //         progress is set to indicated progress level
    //               if the progress level is invalid, throws invalid input exception
    public Task(String taskDetails, int urgencyLevel, int progress) throws InvalidInputException {
        ////nextTaskId based on https://github.students.cs.ubc.ca/CPSC210/TellerApp
        this.details = taskDetails;
        if (NOT_IMPORTANT <= urgencyLevel && urgencyLevel <= VERY_IMPORTANT) {
            this.urgency = urgencyLevel;
        } else {
            throw new InvalidInputException();
        }

        if (progress == INCOMPLETE || progress == COMPLETE) {
            this.progress = progress;
        } else {
            throw new InvalidInputException();
        }
        id = nextTaskId++;
    }

    //constructs a default task with preset task details, urgency, and progress
    public Task() {
        id = nextTaskId++;
        this.details = "Default task, please remove and try to make your task again";
        this.urgency = VERY_IMPORTANT;
        this.progress = INCOMPLETE;
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
    public void setDetails(String s) {
        details = s;
    }

    //MODIFIES: this
    //EFFECTS: if 1<= u <= 3,
    //            - updates Urgency to u and returns true
    //         otherwise, throws InvalidInputException
    public Boolean setUrgency(int u) throws InvalidInputException {
        if (u >= NOT_IMPORTANT && u <= VERY_IMPORTANT) {
            urgency = u;
            return true;
        }
        throw new InvalidInputException();
    }

    //MODIFIES: this
    //EFFECTS: if progress is incomplete
    //              - update it to be complete and return true
    //         otherwise, return false
    public Boolean setComplete() {
        if (progress == INCOMPLETE) {
            progress = COMPLETE;
            return true;
        }
        return false;
    }


    @Override
    //EFFECTS: converts task into a JSONObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("details", details);
        json.put("urgency", urgency);
        json.put("progress", progress);
        return json;
    }

}
