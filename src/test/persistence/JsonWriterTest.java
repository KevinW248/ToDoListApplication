package persistence;

import exception.InvalidInputException;
import model.Task;
import model.ToDoList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            ToDoList toDo = new ToDoList("Test ToDo");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyToDoList() {
        try {
            ToDoList toDo = new ToDoList("Test ToDo");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyToDoList.json");
            writer.open();
            writer.write(toDo);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyToDoList.json");
            toDo = reader.read();
            assertEquals("Test ToDo", toDo.getName());
            assertEquals(0, toDo.getTaskListSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralToDoList() {
        try {
            ToDoList toDo = new ToDoList("Test ToDo");
            try {
                toDo.addTask(new Task("Eat cupcakes", 1, Task.COMPLETE));
                toDo.addTask(new Task("Study for 3 midterms", 3,Task.INCOMPLETE));
            } catch (InvalidInputException e) {
                fail("wasn't supposed to throw invalid input exception");
            }
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralToDoList.json");
            writer.open();
            writer.write(toDo);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralToDoList.json");
            toDo = reader.read();
            assertEquals("Test ToDo", toDo.getName());
            List<Task> taskList = toDo.getTaskList();
            assertEquals(2, taskList.size());
            checkTask("Eat cupcakes", 1, Task.COMPLETE, taskList.get(0));
            checkTask("Study for 3 midterms", 3, Task.INCOMPLETE, taskList.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }



}
