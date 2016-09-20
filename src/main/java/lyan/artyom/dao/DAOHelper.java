package lyan.artyom.dao;

import lyan.artyom.dao.pojo.Task;
import org.jboss.logging.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * TODO add logging
 * Created by dodler on 23/02/16.
 */
public class DAOHelper {
    private TasksDAO dao;
    private static ApplicationContext ctx;

    Logger logger = Logger.getLogger(DAOHelper.class);

    /**
     * Returns helper for simple operations with
     * db.
     *
     * @return DAOHelper instance.
     */
    public static DAOHelper getDAOHelper() {
        if (ctx == null) {
            ctx = new ClassPathXmlApplicationContext("Beans.xml");
        }
        return (DAOHelper) ctx.getBean("DAOHelper");
    }

    private DAOHelper(TasksDAO dao) {
        this.dao = dao;
    }

    /**
     * Delegates task retriving to TasksDAO.
     *
     * @param taskId task id
     * @return Task object
     * @see TasksDAO#getTask(Integer)
     */
    public Task getTask(int taskId) {
        return dao.getTask(taskId);
    }

    /**
     * Filter for retrieving tasks that name like given one.
     *
     * @param name pattern to search in db.
     * @return List of tasks, that have names like given one.
     * @see TasksDAO#getTask(String)
     */
    public List<Task> getTasksByName(String name) {
        return dao.getTask(name);
    }

    /**
     * Filter for retrieving tasks that have
     * status equals to given one.
     *
     * @param status task status.
     * @return List of such tasks.
     * @see TasksDAO#getTask(Boolean)
     */
    public List<Task> getTaskByStatus(Boolean status) {
        return dao.getTask(status);
    }

    /**
     * Method for use from jsp pages to pass argument in non-handled view
     * this method parse all data from request and creates new task according to
     * them. in case of null parameters, parameters will be set as default
     * <p>
     * TODO possibly whole action from this method should be moven to <code>TasksDAOImpl</code>
     *
     * @param name    Task name
     * @param status  Task status given as string
     * @param time    Task time, given as string
     * @param action  Task actionId given as string
     * @param comment Task comment
     * @param alarm   Task alarm given as string
     * @return new task id
     */
    public int createTask(String name,
                          String status,
                          String time,
                          String action,
                          String comment,
                          String alarm) {
        boolean taskStatus = status == null ? false : Boolean.valueOf(status),
                useAlarm = alarm == null ? false : Boolean.valueOf(alarm);

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        if (!checkStringIsEmptyOrNull(time))
            try {
                date = format.parse(time);
            } catch (ParseException e) {
                logger.debug(e);
            } catch (Exception e) {
                logger.debug(e);
            }

        // TODO implement creating action with cp
        int actionId = 0;
        if (!checkStringIsEmptyOrNull(action))
            try {
                actionId = Integer.valueOf(action);
            } catch (NumberFormatException e) {
                logger.debug(e);
            }

        return dao.createTask(checkStringIsEmptyOrNull(name) ? Task.EMPTY_TASK_NAME : name
                , taskStatus, date, actionId,
                checkStringIsEmptyOrNull(comment) ? Task.EMPTY_COMMENT : comment, useAlarm);
    }

    private static boolean checkStringIsEmptyOrNull(String s) {
        return s == null || "".equals(s);
    }

    /**
     * Method returns all tasks that exist in db
     *
     * @return
     * @see TasksDAO#getAllTasks()
     */
    public List<Task> getAllTasks() {
        return dao.getAllTasks();
    }


    /**
     * Method modifies existing task in db.
     * In case of invalid parameters including null,
     * parameters will be set as default.
     * All parameters should be given as string.
     *
     * @param id      task id
     * @param name    new task name
     * @param status  new task status
     * @param time    new task time
     * @param action  new task action id
     * @param comment new task comment
     * @param alarm   new task alarm
     */
    public void modifyTask(@NotNull String id,
                           String name,
                           String status,
                           String time,
                           String action,
                           String comment,
                           String alarm) {

        boolean taskStatus = status == null ? false : Boolean.valueOf(status),
                useAlarm = alarm == null ? false : Boolean.valueOf(alarm);

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        if (!checkStringIsEmptyOrNull(time))
            try {
                date = format.parse(time);
            } catch (ParseException e) {
                logger.debug(e);
            } catch (Exception e) {
                logger.debug(e);
            }

        // TODO implement creating action with cp
        int actionId = 0;
        if (!checkStringIsEmptyOrNull(action))
            try {
                actionId = Integer.valueOf(action);
            } catch (NumberFormatException e) {
                logger.debug(e);
            }

        dao.modifyTask(Integer.valueOf(id), checkStringIsEmptyOrNull(name) ? Task.EMPTY_TASK_NAME : name
                , taskStatus, date, actionId,
                checkStringIsEmptyOrNull(comment) ? Task.EMPTY_COMMENT : comment, useAlarm);
    }

    /**
     * Method removes task from db.
     *
     * @param id task id to remove.
     */
    public void deleteTask(@NotNull String id) {
        int taskId = 0;
        try {
            taskId = Integer.valueOf(id);
            dao.deleteTask(taskId);
        } catch (NumberFormatException e) {
            logger.debug(e);
        }
    }
}
