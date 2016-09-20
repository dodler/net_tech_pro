package lyan.artyom.test;

import lyan.artyom.dao.impl.TasksDAOImpl;
import lyan.artyom.dao.pojo.Action;
import lyan.artyom.dao.pojo.Task;
import lyan.artyom.rest.service.TaskService;
import lyan.artyom.rest.service.impl.TaskServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * TODO recofigure for use with SpringJUnit4ClassRunner
 * Created by lyan on 17.09.16.
 */
@RunWith(JUnit4ClassRunner.class)
public class TaskServiceImplTest {
    private TaskService service;

    @Before
    public void before(){
        service = new TaskServiceImpl();
    }

    @Test
    public void createEmptyTask_shouldReturnIdOfNewEmptyTask(){
        String taskId = String.valueOf(service.createTask(null,null,null, null,null,null));
        Task task = service.getTask(taskId);

        // TODO full comparing, not name only, check about date
        assertTrue(task.getName().equals(TasksDAOImpl.getEmptyTask().getName()));
    }

    @Test
    public void modifyTaskName_nameShouldChange(){

        String testName = "testName",
                baseName = "baseName";

        int taskId = service.createTask(baseName,
                String.valueOf(true),
                new Date().toString(),
                Action.SAMPLE_ACTION_CP,
                "test comment",
                String.valueOf(true));

        service.modifyTask(
                String.valueOf(taskId), testName, null, null, null, null, null);

        assertTrue(service.getTask(String.valueOf(taskId)).getName().equals(testName));
    }

    @Test
    public void createTaskWithExtraName_shouldFilterIt(){

        final String findMe = String.valueOf("TestFindMeName");
        service.createTask(findMe,
                    String.valueOf(true),
                    new Date().toString(),
                    Action.SAMPLE_ACTION_CP,
                    "test comment",
                    String.valueOf(true));

        List<Task> tasks = service.getTaskByName(findMe);

        assertTrue(tasks.size() >= 1 && tasks.get(0).getName().equals(findMe));
    }

    @Test
    public void createDeleteTask_tasksNumberShouldNotChange(){
        List<Task> tasks = service.getAllTasks();
        int tasksSize = tasks.size();

        int taskId = service.createTask(null,null,null, null,null,null);

        service.deleteTask(
                String.valueOf(taskId)
        );

        assertTrue(tasksSize == service.getAllTasks().size());
    }
}
