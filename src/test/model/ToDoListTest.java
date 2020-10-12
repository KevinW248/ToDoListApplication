package model;

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
        testToDo = new ToDoList();
    }

    @Test
    public void testToDoConstructor() {
        assertEquals(0,testToDo.getTaskListSize());
        assertEquals(0,testToDo.getIdListSize());
    }

    @Test
    public void testAddTask() {
        Task t = new Task(details[0],urgency[0]);
        testToDo.addTask(t);

        assertEquals(1,testToDo.getIdListSize());
        assertEquals(1,testToDo.getTaskListSize());

        Task t2 = testToDo.getTaskAt(0);
        assertEquals(details[0],t2.getDetails());
        assertEquals(urgency[0],t2.getUrgency());
    }

    @Test
    public void testRemoveTask() {
        Task t = new Task(details[0],urgency[0]);
        Task t1 = new Task(details[1],urgency[1]);
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
    public void testGetTotalCompleteAndIncomplete() {
        for (int i = 0; i < 6; i+=2) {
            Task t = new Task(details[i],urgency[i]);
            testToDo.addTask(t);
            t.markComplete();
        }
        for (int i = 1; i < 6; i+=2) {
            Task t = new Task(details[i],urgency[i]);
            testToDo.addTask(t);
        }
        assertEquals(3,testToDo.getTotalCompleted());
        assertEquals(3,testToDo.getTotalIncomplete());
    }


    @Test
    public void testGetLists() {
        Task t = new Task(details[0],urgency[0]);
        testToDo.addTask(t);

        ArrayList<Task> taskList = testToDo.getTaskList();
        assertEquals("Walk the dog",taskList.get(0).getDetails());

    }

}
