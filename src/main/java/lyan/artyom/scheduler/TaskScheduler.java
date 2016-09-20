package lyan.artyom.scheduler;

/**
 * base interface for task scheduler
 * implementation will be kept in impl package
 * will containt logic for recheduling tasks, adding new tasks
 * and removing obsolete tasks
 *
 *
 * WARNING THIS INTERFACE IS NOT IMPLEMENTED WELL YET.
 * THIS FUNCTIONALITY WILL BE ADDED IN FURTHER RELEASES.
 * Created by dodler on 23/02/16.
 */
public interface TaskScheduler {
    void scheduleNewTask();
    void rescheduleExistingTask();
    void removeObsoleteTask();
    void performTask();
}
