package persistence;

import model.Task;
import model.ToDoList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ToDoList toDo = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyToDoList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyToDoList.json");
        try {
            ToDoList toDo = reader.read();
            assertEquals("Test ToDo", toDo.getName());
            assertEquals(0, toDo.getTaskListSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralToDoList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralToDoList.json");
        try {
            ToDoList toDo = reader.read();
            assertEquals("Test ToDo", toDo.getName());
            List<Task> taskList = toDo.getTaskList();
            assertEquals(2, taskList.size());
            checkTask("Eat cupcakes", 1, Task.COMPLETE, taskList.get(0));
            checkTask("Study for 3 midterms", 3, Task.INCOMPLETE, taskList.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderInvalidDoToList() {
        JsonReader reader = new JsonReader("./data/testReaderInvalidToDoList.json");
        try {
            ToDoList toDo = reader.read();
            assertEquals("Test ToDo", toDo.getName());
            List<Task> taskList = toDo.getTaskList();
            assertEquals(2, taskList.size());
            checkTask("Default task, please remove and try to make your task again",
                    3, Task.INCOMPLETE, taskList.get(0));
            checkTask("Default task, please remove and try to make your task again",
                    3, Task.INCOMPLETE, taskList.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
