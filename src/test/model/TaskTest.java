package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    private Task testTask;

    private static final int COMPLETE = Task.COMPLETE;

    @BeforeEach
    public void setup() {
        testTask = new Task("Finish CPSC-210 Personal project",3, Task.INCOMPLETE);
    }

    @Test
    public void testTaskConstructorNormalUrgency() {
        assertEquals("Finish CPSC-210 Personal project",testTask.getDetails());
        assertEquals(3,testTask.getUrgency());
        assertEquals(0,testTask.getProgress());
        assertTrue(testTask.getId()>0);

        Task testTask2 = new Task("a",2, Task.INCOMPLETE);
        Task testTask3 = new Task("b",1, Task.INCOMPLETE);
        assertEquals(2,testTask2.getUrgency());
        assertEquals(1,testTask3.getUrgency());
        assertTrue(testTask2.getId()>0);
        assertTrue(testTask3.getId()>0);

        Task testTask4 = new Task("c",0, Task.COMPLETE);
        assertEquals(3,testTask4.getUrgency());
        assertTrue(testTask2.getId()>0);
        assertEquals(Task.COMPLETE,testTask4.getProgress());
    }

    @Test
    public void testTaskConstructorDifferentUrgencyLevel(){
        Task testTask2 = new Task("Test new task",4, Task.INCOMPLETE);
        assertEquals("Test new task",testTask2.getDetails());
        assertEquals(3,testTask2.getUrgency());
        assertEquals(0,testTask2.getProgress());
        assertTrue(testTask2.getId()>0);
    }

    @Test
    public void testSetters() {
        testTask.setDetails("Feed the dog");
        assertEquals("Feed the dog",testTask.getDetails());

        assertTrue(testTask.setUrgency(1));
        assertEquals(1,testTask.getUrgency());

        assertTrue(testTask.setUrgency(3));
        assertEquals(3,testTask.getUrgency());

        assertTrue(testTask.setUrgency(2));
        assertEquals(2,testTask.getUrgency());

        assertFalse(testTask.setUrgency(4));
        assertEquals(2,testTask.getUrgency());

        assertFalse(testTask.setUrgency(0));
        assertEquals(2,testTask.getUrgency());
    }

    @Test
    public void testMarkComplete() {
        assertTrue(testTask.setComplete());
        assertEquals(COMPLETE, testTask.getProgress());

        assertFalse(testTask.setComplete());
    }

}