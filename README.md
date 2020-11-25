# To-Do List Application

## *For convenient task tracking!*

This application will provide functionalities useful for anyone to navigate their everyday tasks.
Things that can be done include:  

- **Adding tasks**
- **Viewing all current tasks**
- **Marking tasks as complete**
- **Removing tasks**

This application can be used by anyone!

*I am interested in making life easier for myself and others via coding this application!*

## *User Stories*

A user should be able to: 
- Add a task to their to-do list
- View their current list of tasks on their to-do list
    - See the number of incomplete and completed tasks as well
- Mark a task as complete on their to-do list
- Delete a task from their to-do-list
- Save their to-do list to file
- load their to-do list from file 

## *Phase 4: Task 2*

The Task class was modified to include the exception, InvalidInputException.
A new method, Task(), was designed, to create a default Task that did not throw an exception

- Class 1, Task
    - Exception thrown when the Task constructor receives invalid progress and urgency inputs
    - Exception thrown when setUrgency receives invalid urgency
- Class 2, JsonReader
    - Exception caught, and addTask() will now put a default task if an invalid one is read
- Class 3, AddTaskTool
    - Exception caught, and addTask() will now add the default task if invalid fields were in the text box
    
## *Phase 4: Task 3*

If I had more time, I think it would have been a good idea to see if I could have made a new type hierarchy
by making a Tool abstract class as a superclass and finding overlapping functionality between the tools
    -  All of my Tools have a ToDoListAppGUI field and initialize it in the constructor
I think that it would be better to have JButton be a common functionality in all the tools
Instead of intializing all the buttons in ToDoListAppGUI
    - In a Tool abstract class, I could have a JButton field and a JButton intialization method
    - in the Tool subclasses, I could actually initialize the JButton field by using the initializing method
    - what the button does when clicked could be specified in the tool subclass
    - There could be a method in the tool subclass to add the button to the GUI
    
