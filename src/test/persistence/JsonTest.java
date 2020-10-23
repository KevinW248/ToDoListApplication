package persistence;

import model.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//modeled on https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonTest {
    protected void checkTask(String details, int urgency, int progress, Task task) {
        assertEquals(details, task.getDetails());
        assertEquals(urgency, task.getUrgency());
        assertEquals(progress, task.getProgress());
        assertTrue(task.getId() > 0);
    }
}