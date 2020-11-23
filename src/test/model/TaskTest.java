package model;

import exception.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    private Task testTask;

    private static final int COMPLETE = Task.COMPLETE;

    @BeforeEach
    public void setup() {
        try {
            testTask = new Task("Finish CPSC-210 Personal project",3, Task.INCOMPLETE);
        } catch (InvalidInputException e) {
            fail("threw invalid input exception when not supposed to");
        }

    }

    @Test
    public void testTaskConstructorNormalUrgencyandProgress() {
        assertEquals("Finish CPSC-210 Personal project",testTask.getDetails());
        assertEquals(3,testTask.getUrgency());
        assertEquals(Task.INCOMPLETE,testTask.getProgress());
        assertTrue(testTask.getId()>0);

        testTaskConstructorNormalInputsHelper(2,Task.INCOMPLETE);
        testTaskConstructorNormalInputsHelper(1,Task.COMPLETE);
    }

    public void testTaskConstructorNormalInputsHelper(int urgency, int progress) {
        Task testTask2 = null;
        try {
            testTask2 = new Task("Test new task", urgency, progress);
        } catch (InvalidInputException e) {
            fail("not supposed to throw invalid input exception");
        }
        assertEquals(urgency, testTask2.getUrgency());
        assertEquals(progress, testTask2.getProgress());
        assertTrue(testTask2.getId() > 0);
    }

    @Test
    public void testTaskConstructorInvalidUrgency() {
        testTaskConstructorInvalidUrgencyHelper(4);
        testTaskConstructorInvalidUrgencyHelper(0);
    }

    public void testTaskConstructorInvalidUrgencyHelper(int i){
        Task testTask2 = null;
        try {
            testTask2 = new Task("Test new task", i, Task.INCOMPLETE);
            fail("supposed to throw invalid input exception");
        } catch (InvalidInputException e) {
            //pass
        }
    }

    @Test
    public void testTaskConstructorInvalidProgress() {
        testTaskConstructorInvalidProgressHelper(10);
        testTaskConstructorInvalidProgressHelper(-1);
    }

    public void testTaskConstructorInvalidProgressHelper(int i){
        Task testTask2 = null;
        try {
            testTask2 = new Task("Test new task", 2, i);
            fail("supposed to throw invalid input exception");
        } catch (InvalidInputException e) {
            //pass
        }
    }

    @Test
    public void testDefaultTaskConstructor() {
        Task t = new Task();
        assertEquals("Default task, please remove and try to make your task again",
                t.getDetails());
        assertEquals(3, t.getUrgency());
        assertEquals(Task.INCOMPLETE, t.getProgress());
        assertTrue(t.getId() > 1);
    }



    @Test
    public void testSetters() {
        testTask.setDetails("Feed the dog");
        assertEquals("Feed the dog",testTask.getDetails());

        testSettersHelperAssertTrue(1);

        testSettersHelperAssertTrue(3);

        testSettersHelperAssertTrue(2);

        testSettersHelperThrowException(4);

        testSettersHelperThrowException(0);
    }

    private void testSettersHelperAssertTrue(int i ) {
        try {
            assertTrue(testTask.setUrgency(i));
        } catch (InvalidInputException e) {
            fail("threw invalid input exception when not supposed to");
        }
        assertEquals(i,testTask.getUrgency());
    }

    private void testSettersHelperThrowException(int i) {
        try {
            assertTrue(testTask.setUrgency(i));
            fail("supposed to throw invalid input exception");
        } catch (InvalidInputException e) {
            //pass
        }
    }

    @Test
    public void testMarkComplete() {
        assertTrue(testTask.setComplete());
        assertEquals(COMPLETE, testTask.getProgress());

        assertFalse(testTask.setComplete());
    }

}