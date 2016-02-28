package lian.artyom.scheduler.impl;

import lian.artyom.dao.pojo.Task;
import lian.artyom.scheduler.TaskScheduler;

/**
 * Created by dodler on 23/02/16.
 */
public class TaskSchedulerImpl implements TaskScheduler{
    @Override
    public void scheduleNewTask() {

    }

    @Override
    public void rescheduleExistingTask() {

    }

    @Override
    public void removeObsoleteTask() {

    }

    @Override
    public void performTask() {
        final Task task = new Task();
    }
}
