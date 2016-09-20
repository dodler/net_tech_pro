package lyan.artyom.rest.service.impl;

import lyan.artyom.dao.DAOHelper;
import lyan.artyom.dao.pojo.Task;
import lyan.artyom.rest.service.TaskService;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.List;

/**
 * This is implementation of {@link TaskService}.
 * It uses existing {@link lyan.artyom.dao.TasksDAO} implementation
 * via {@link DAOHelper}.
 *
 * Note. All those pretty weird methods with String signatures were made
 * to make them convinient to use from JSP pages. Now they are pretty
 * convinient to use during handling rest requests.
 * Created by lyan on 14.09.16.
 */
@Component
public class TaskServiceImpl implements TaskService {
    private DAOHelper helper = DAOHelper.getDAOHelper();

    /**
     * This method creates new tasks with given parameters.
     * In case if new task was created it will return task id.
     * In case when parameters are bad, they will be replaced with default ones.
     * @see DAOHelper#createTask(String, String, String, String, String, String)
     * @param name task name
     * @param status task status - boolean (active or not)
     * @param time task time execution, should be passed as {@link java.util.Date}
     * @param action task action id - action to perform on given time.
     * @param comment commentary to task.
     * @param alarm task alarm - should be notified on given time - boolean.
     * @return new task id.
     */
    @Override
    public int createTask(String name,
                          String status,
                          String time,
                          String action,
                          String comment,
                          String alarm)  {
        return helper.createTask(name, status, time, action, comment, alarm);
    }

    /**
     * TODO add argument checks.
     * This method retrieves task by given id from db.
     * @see DAOHelper#getTask(int)
     * @param id task id
     * @return Task object with given id.
     */
    @Override
    public Task getTask(String id) {
        return helper.getTask(Integer.valueOf(id));
    }

    /**
     * This method returns list of tasks with name like given one.
     * @see DAOHelper#getTasksByName(String)
     * @param name pattern to search in db.
     * @return list of appropriate tasks.
     */
    @Override
    public List<Task> getTaskByName(String name){
        return helper.getTasksByName(name);
    }

    /**
     * This method returns list of tasks with status equals to given one.
     * @see DAOHelper#getTaskByStatus(Boolean)
     * @param status string representation of {@link Boolean} - task status
     * @return
     */
    @Override
    public List<Task> getTaskByStatus(String status) {
        return helper.getTaskByStatus(Boolean.valueOf(status));
    }

    /**
     * This method deletes task with given id.
     * @see DAOHelper#deleteTask(String)
     * @param id task id to delete.
     */
    @Override
    public void deleteTask(String id) {
        helper.deleteTask(id);
    }

    /**
     * This method modifies existing task with given id. If any of arguments
     * is corrupted or bad it will be replaced with default one.
     * @see DAOHelper#deleteTask(String)
     * @param id task id to modify.
     * @param name new task name.
     * @param status new task status.
     * @param time new task execution time.
     * @param action new task action id.
     * @param comment new task comment.
     * @param alarm new task alarm status.
     */
    @Override
    public void modifyTask(String id, String name, String status, String time, String action, String comment, String alarm) {
        helper.modifyTask(id, name, status, time, action, comment, alarm);
    }

    /**
     * This method returns list of all existing tasks in db.
     * @see DAOHelper#getAllTasks()
     * @return list of all tasks.
     */
    @Override
    public List<Task> getAllTasks() {
        return helper.getAllTasks();
    }
}
