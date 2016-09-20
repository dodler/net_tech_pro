package lyan.artyom.dao;

import org.jetbrains.annotations.NotNull;
import lyan.artyom.dao.pojo.Action;
import lyan.artyom.dao.pojo.Task;

import java.util.Date;
import java.util.List;

/**
 * This is base interface for DAO object.
 * This application currently have hibernate implementation.
 * You can add any other implementation. To make it work change configuration
 * Beans.xml replacing TasksDAOImpl class with your's one.
 * Method's javadocs can be found in {@link lyan.artyom.dao.impl.TasksDAOImpl}
 * Created by dodler on 28/02/16.
 */
public interface TasksDAO {
    Integer createAction();
    Integer createTask();

    Integer createAction(String name, String classPath);
    Integer createTask(String name,
                   Boolean status,
                   Date time,
                   Action action,
                   String comment,
                   Boolean alarm);

    Integer createTask(String name,
                   Boolean status,
                   Date time,
                   Integer actionId,
                   String comment,
                   Boolean alarm);

    void modifyTask(@NotNull Integer id,
                    String name,
                    Boolean status,
                    Date time,
                    Integer actionId,
                    String comment,
                    Boolean alarm);

    Task getTask(@NotNull Integer id);
    List<Task> getTask(@NotNull String name);
    List<Task> getTask(@NotNull Boolean status);
    List<Task> getAllTasks();

    Action getAction(@NotNull Integer id);
    List<Action> getAllActions();

    void deleteTask(Integer id);
    void deleteAction(Integer id);
}
