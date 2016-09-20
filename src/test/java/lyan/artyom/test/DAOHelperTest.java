package lyan.artyom.test;

import lyan.artyom.dao.DAOHelper;
import lyan.artyom.dao.pojo.Task;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by lyan on 19.09.16.
 */
@RunWith(JUnit4ClassRunner.class)
public class DAOHelperTest {

    private DAOHelper helper;

    @Before
    public void before(){
        helper = DAOHelper.getDAOHelper();
    }

    @Test
    public void createTaskWithNull_shouldCreateEmptyTask(){

        assertTrue( helper.getTask(
                helper.createTask(null, null, null, null, null, null)
        ).getName().equals(Task.EMPTY_TASK_NAME));
    }

    @Test
    public void createTaskWithEmptyStrings_shouldCreateEmptyTask(){
        assertTrue( helper.getTask(
                helper.createTask("", "", "", "", "", "")
        ).getName().equals(Task.EMPTY_TASK_NAME));
    }

    @Test
    public void createTaskWithBadActionId_shouldNotFail(){
        boolean failed = false;
        try{
            helper.createTask(null, null, null, "test", null, null);
        }catch(Throwable e){
            failed = true;
        }

        assertFalse(failed);
    }

    @Test
    public void modifyTaskWithNewName_shouldReturnNewName(){
        String testName = "testName";
        int taskId = helper.createTask(null, null,null,null,null,null);
        helper.modifyTask(String.valueOf(taskId), testName, null,null,null,null,null);
        assertTrue(helper.getTask(taskId).getName().equals(testName));
    }

    @Test
    public void deleteTask_shouldReturnNullOnNonExistingTask(){
        int taskId = helper.createTask(null, null, null,null,null,null);// TODO consider introduce task builder
        // instread of writing each time 6 nulls
        helper.deleteTask(String.valueOf(taskId));
        assertNull(helper.getTask(taskId));
    }




}
