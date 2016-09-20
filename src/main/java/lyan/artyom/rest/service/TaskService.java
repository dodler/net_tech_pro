package lyan.artyom.rest.service;

import lyan.artyom.dao.pojo.Task;

import java.util.List;
import java.util.function.BooleanSupplier;

/**
 * This is service for working with {@link lyan.artyom.rest.RestaurantController}
 * Author assumes, that this interface implementation will
 * rely on existing {@link lyan.artyom.dao.TasksDAO} interaface
 * and it's implementations, as it is done in {@link lyan.artyom.rest.service.impl.TaskServiceImpl}
 *
 * Created by lyan on 14.09.16.
 */
public interface TaskService {

    int createTask(String name,
                          String status,
                          String time,
                          String action,
                          String comment,
                          String alarm);
    Task getTask(String id);
    List<Task> getAllTasks();
    List<Task> getTaskByName(String name);
    List<Task> getTaskByStatus(String status);

    void deleteTask(String id);
    void modifyTask(String id,
                    String name,
                    String status,
                    String time,
                    String action,
                    String comment,
                    String alarm);
}
