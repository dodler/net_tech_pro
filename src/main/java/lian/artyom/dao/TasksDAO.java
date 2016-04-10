package lian.artyom.dao;

import org.jetbrains.annotations.NotNull;
import lian.artyom.dao.pojo.Action;
import lian.artyom.dao.pojo.Task;

import java.util.Date;
import java.util.List;

/**
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
    List<Task> getAllTasks();

    Action getAction(@NotNull Integer id);
    List<Action> getAllActions();

    void deleteTask(Integer id);
    void deleteAction(Integer id);
}
