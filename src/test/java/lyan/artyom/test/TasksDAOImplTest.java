package lyan.artyom.test;

import lyan.artyom.dao.TasksDAO;
import lyan.artyom.dao.impl.TasksDAOImpl;
import lyan.artyom.dao.pojo.Action;
import lyan.artyom.dao.pojo.Task;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by lyan on 19.09.16.
 */
@RunWith(JUnit4ClassRunner.class)
public class TasksDAOImplTest {

    private TasksDAO dao;

    @Before
    public void before() {
        dao = new TasksDAOImpl();
    }

    @Test
    public void createAction_shouldReturnNewActionId() {
        Action action = dao.getAction(dao.createAction());
        assertEquals(action.getName(), Action.SAMPLE_ACTION_NAME);
    }

    @Test
    public void createActionWithArguments_argumentsShouldBeEqual() {
        String name = "name", cp = "cp";

        Action action = dao.getAction(
                dao.createAction(name, cp)
        );

        assertTrue(
                action.getName().equals(name) && action.getClassPath().equals(cp)
        );
    }

    @Test
    public void createEmptyTask_newTaskParametersShouldEqualDefaults() {
        Task task = TasksDAOImpl.getEmptyTask();

        assertEquals(task.getName(), Task.EMPTY_TASK_NAME);
    }

    @Test
    public void createTaskWithArguments_argumentsShouldEqualToProvided() {
        String name = "testTaskName", comment = "testComment";
        Date date = new Date();
        int actionId = dao.createAction();
        boolean status = true, alarm = true;


        Task task = dao.getTask(
                dao.createTask(
                        name, status, date, actionId, comment, alarm
                )
        );

        assertTrue(
                task.getName().equals(name) &&
                        task.getAction().equals(
                                dao.getAction(actionId)
                        ) &&
                        task.isStatus() == status &&
                        task.isAlarm() == alarm &&
                        task.getComment().equals(comment)
        );
    }

    @Test
    public void modifyTask_parametersShouldChange(){
        String oldName = "oldName", newName = "newName", comment = "test";
        Date date = new Date();
        int actionId = dao.createAction();

        int taskId = dao.createTask(oldName, true, date, actionId , comment, true);

        dao.modifyTask(taskId, newName, true, date, actionId, comment, true);

        assertEquals(dao.getTask(taskId).getName(), newName);
    }

    @Test
    public void getTask_shouldReturnTask(){
        String oldName = "oldName", comment = "test";
        Date date = new Date();
        int actionId = dao.createAction();

        int taskId = dao.createTask(oldName, true, date, actionId , comment, true);

        Task task = dao.getTask(taskId);

        assertEquals(task.getName(), oldName);
    }

    @Test
    public void getTaskWithNamePattern_shouldReturnFilteredTasks(){
        String taskName = "TestTaskForSearch", comment = "test";
        Date date = new Date();
        int actionId = dao.createAction();

        dao.createTask(taskName, true, date, actionId , comment, true);

        List<Task> tasks = dao.getTask("earc");

        assertTrue(tasks.size() >= 1 && tasks.get(0).getName().contains("earc"));
    }


    @Test
    public void getTaskWithStatusPattern_returnResultShouldNotBeEmpty(){
        String oldName = "oldName", newName = "newName", comment = "test";
        Date date = new Date();
        int actionId = dao.createAction();

        int taskId = dao.createTask(oldName, true, date, actionId , comment, true);

        assertTrue(dao.getTask(true).size() >= 1);
    }


    @Test
    public void getAllTasks_shouldReturnNotEmptyList(){
        String oldName = "oldName", newName = "newName", comment = "test";
        Date date = new Date();
        int actionId = dao.createAction();

        int taskId = dao.createTask(oldName, true, date, actionId , comment, true);

        assertTrue(dao.getAllTasks().size() >= 1);
    }

    @Test
    public void getAction_shouldReturnNotNullAction(){
        int actionId = dao.createAction();
        Action action = dao.getAction(actionId);

        assertEquals(action.getClassPath(), Action.SAMPLE_ACTION_CP);
    }

    @Test
    public void getAllAction_shouldNotEmptyList(){
        dao.createAction();

        assertTrue(dao.getAllTasks().size() >= 1);
    }

    @Test
    public void deleteTask_shouldReturnNullTask(){
        int taskId = dao.createTask();

        dao.deleteTask(taskId);

        assertEquals(dao.getTask(taskId), null);
    }

    @Test
    public void deleteAction_shouldReturnNullAction(){
        int actionId = dao.createAction();

        dao.deleteAction(actionId);

        assertEquals(dao.getAction(actionId), null);
    }

}
