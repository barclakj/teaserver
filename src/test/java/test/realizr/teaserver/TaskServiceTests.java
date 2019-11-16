package test.realizr.teaserver;

import io.realizr.teaserver.TaskServer;
import io.realizr.teaserver.model.Task;
import io.realizr.teaserver.service.TaskExistsException;
import io.realizr.teaserver.service.TaskService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TaskServer.class)
public class TaskServiceTests {

    @Autowired
    private TaskService taskService;

    @Before
    public void clearData() {
        taskService.deleteTask(1);
        taskService.deleteTask(2);
        taskService.deleteTask(100);
        taskService.deleteTask(3);
        taskService.deleteTask(6);
    }

    // need to be able to add the data needed to support test
    @Test
    public void canAddDataForTest() throws TaskExistsException {
        taskService.addNewTask(new Task(1, "John Smith", "CEO", 0, 0L, 0L, null));
        taskService.addNewTask(new Task(2, "Jane Doe", null, 1, 0L, 0L, null));
        taskService.addNewTask(new Task(100, "Harry Henderson", null, 2, 0L, 0L, null));
        taskService.addNewTask(new Task(3, "Name 2", "Title 2", 1, 0L, 0L, null));
        taskService.addNewTask(new Task(6, "Name 6", "Title 6", 1, 0L, 0L, null));

        Task task = taskService.getTask(1);
        assertNotNull(task);
        System.out.println(task.getId() + " " + task.getName() + " " + task.getDue());
    }

    @Test
    public void getEmployee() throws TaskExistsException {
        taskService.addNewTask(new Task(1, "John Smith", "CEO", 0, 0L, 0L, null));
        taskService.addNewTask(new Task(2, "Jane Doe", null, 1, 0L, 0L, null));
        Task task = taskService.getTask(1);

        assertNotNull(task);
        System.out.println(task.getId() + " " + task.getName() + " " + task.getDue());
    }

    @Test
    public void canAddAndFetchEmployee() throws TaskExistsException {
        taskService.addNewTask(new Task(1, "John Smith", "tuesday", 0, 0L, 0L, null));
        Task emp = taskService.getTask(1);
        Assert.assertNotNull(emp);
        Assert.assertEquals(emp.getName(), "John Smith");
        Assert.assertEquals(emp.getDue(), "tuesday");
    }


    @Test
    public void canDeleteTask() throws TaskExistsException {
        int id = (int)(Math.random()*100.);
        taskService.addNewTask(new Task(id, "Some name", "some role", 0, 0L, 0L, null));
        Task task = taskService.getTask(id);
        Assert.assertNotNull(task);
        taskService.deleteTask(id);
        Assert.assertNull(taskService.getTask(id));
    }


    // need to be able to add the data needed to support test
    @Test(expected = TaskExistsException.class)
    public void getExceptioOnExistingTaskAdd() throws TaskExistsException {
        taskService.addNewTask(new Task(1, "John Smith", "CEO", 0, 0L, 0L, null));
        taskService.addNewTask(new Task(1, "SHOULD FAIL", "SHOULD FAIL", 0, 0L, 0L, null));

    }
}
