package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    private Task testTask;

    @BeforeEach
    public void setup() {
        testTask = new Task("Finish CPSC-210 Personal project",3);
    }

    @Test
    public void testTaskConstructor() {
        assertEquals("Finish CPSC-210 Personal project",testTask.getDetails());
        assertEquals(3,testTask.getUrgency());
        assertEquals(0,testTask.getProgress());
        assertTrue(testTask.getId()>0);
    }

    @Test
    public void testTaskConstructorDifferentUrgencyLevel(){
        Task testTask2 = new Task("Test new task",4);
        assertEquals("Test new task",testTask2.getDetails());
        assertEquals(3,testTask2.getUrgency());
        assertEquals(0,testTask2.getProgress());
        assertTrue(testTask2.getId()>0);
    }

    @Test
    public void testSetters() {
        testTask.editDetails("Feed the dog");
        assertEquals("Feed the dog",testTask.getDetails());

        assertTrue(testTask.editUrgency(1));
        assertEquals(1,testTask.getUrgency());

        assertFalse(testTask.editUrgency(4));
        assertEquals(1,testTask.getUrgency());
    }


}