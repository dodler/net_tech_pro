package lian.artyom.dao;

import lian.artyom.dao.pojo.Task;

/**
 * Created by dodler on 28/02/16.
 */
public interface TasksDAO {
    int createAction();
    int createTask();

    Task getTask(int id);
}
