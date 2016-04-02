package lian.artyom.dao;

import lian.artyom.dao.pojo.Action;
import lian.artyom.dao.pojo.Task;

import java.util.Date;
import java.util.List;

/**
 * Created by dodler on 28/02/16.
 */
public interface TasksDAO {
    int createAction();
    int createTask();

    int createAction(String name, String classPath);
    int createTask(String name,
                   boolean status,
                   Date time,
                   Action action,
                   String comment,
                   boolean alarm);

    int createTask(String name,
                   boolean status,
                   Date time,
                   int actionId,
                   String comment,
                   boolean alarm);

    Task getTask(int id);
    List<Task> getAllTasks();

    Action getAction(int id);
    List<Action> getAllActions();
}
