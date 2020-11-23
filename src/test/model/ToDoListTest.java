package model;

import exception.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoListTest {

    private ToDoList testToDo;
    private String details[] = {"Walk the dog", "Wash the dishes", "Take out the trash",
                                "Eat vitamins", "Do homework", "Sharpen pencils"};
    private int urgency[] = {1,2,3,2,3,1};

    @BeforeEach
    public void setup() {
        testToDo = new ToDoList("Test");
    }

    @Test
    public void testToDoConstructor() {
        assertEquals(0,testToDo.getTaskListSize());
        assertEquals(0,testToDo.getIdListSize());
        assertEquals("Test",testToDo.getName());
    }

    @Test
    public void testAddTask() {
        Task t = validTaskMaker(details[0],urgency[0],Task.INCOMPLETE);
        testToDo.addTask(t);

        assertEquals(1,testToDo.getIdListSize());
        assertEquals(1,testToDo.getTaskListSize());

        Task t2 = testToDo.getTaskAt(0);
        assertEquals(details[0],t2.getDetails());
        assertEquals(urgency[0],t2.getUrgency());
    }

    @Test
    public void testRemoveTask() {
        Task t = validTaskMaker(details[0],urgency[0],Task.INCOMPLETE);
        Task t1 = validTaskMaker(details[1],urgency[1],Task.INCOMPLETE);
        testToDo.addTask(t);
        testToDo.addTask(t1);

        assertEquals(2,testToDo.getIdListSize());
        assertEquals(2,testToDo.getTaskListSize());

        //unique IDs
        Task t10 = testToDo.removeTask(t.getId());
        assertEquals(details[0],t10.getDetails());
        assertEquals(urgency[0],t10.getUrgency());
    }

    @Test
    public void testCompleteTask() {
        Task t = validTaskMaker(details[0],urgency[0],Task.INCOMPLETE);
        Task t1 = validTaskMaker(details[1],urgency[1],Task.INCOMPLETE);
        testToDo.addTask(t);
        testToDo.addTask(t1);

        testToDo.completeTask(t.getId());
        testToDo.completeTask(t1.getId());

        assertEquals(Task.COMPLETE,t.getProgress());
        assertEquals(Task.COMPLETE,t1.getProgress());
    }

    @Test
    public void testGetTotalCompleteAndIncomplete() {
        for (int i = 0; i < 6; i+=2) {
            Task t = validTaskMaker(details[i],urgency[i],Task.INCOMPLETE);
            testToDo.addTask(t);
            t.setComplete();
        }
        for (int i = 1; i < 6; i+=2) {
            Task t = validTaskMaker(details[i],urgency[i],Task.INCOMPLETE);
            testToDo.addTask(t);
        }
        assertEquals(3,testToDo.getTotalCompleted());
        assertEquals(3,testToDo.getTotalIncomplete());
    }


    @Test
    public void testGetLists() {
        Task t = validTaskMaker(details[0],urgency[0],Task.INCOMPLETE);
        testToDo.addTask(t);

        ArrayList<Task> taskList = testToDo.getTaskList();
        assertEquals("Walk the dog",taskList.get(0).getDetails());

    }

    public Task validTaskMaker(String details, int urgency, int progress) {
        Task t = null;
        try {
            t = new Task(details, urgency, progress);
        } catch (InvalidInputException e) {
            fail("Wasn't supposed to throw invalid input exception");
        }
        return t;
    }

}
