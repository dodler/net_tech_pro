package lyan.artyom.dao.pojo;

import lyan.artyom.dao.exception.NoActionException;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * simple POJO object
 * Created by dodler on 23/02/16.
 */
public class Task {

    public static final String EMPTY_TASK_NAME = "Empty task";
    public static final String EMPTY_COMMENT = "Empty comment";

    private List parameters;

    private int id;

    /**
     * SImple setter method
     * @param status
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Simple setter method
     * @param alarm
     */
    public void setAlarm(boolean alarm) {
        this.alarm = alarm;
    }

    /**
     * task status
     * if true, task is active
     */
    private boolean status;
    /**
     * time, for which task is scheduled
     * can be null even if status is active
     */
    private Date time;
    /**
     * action to perform in task
     * should implement {@link Action}
     */
    private Action action;
    /**
     * additional commentary for task
     */
    private String comment;
    /**
     * if true, a notification will be performed (probably email)
     */
    private boolean alarm; // not sure about alarm implementation

    private String name;

    public Task() {
    }

    public Task(
            String name,
            boolean status,
            Date time,
            Action action,
            String comment,
            boolean alarm) {

        this.name = name;
        this.status = status;
        this.time = time;
        this.action = action;
        this.comment = comment;
        this.alarm = alarm;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Task ");
        builder.append("isActive=[");
        builder.append(status);
        builder.append("], scheduledTime=[");
        builder.append(time);
        builder.append("], action=[");
        builder.append(action); // TODO make action descriptable
        builder.append("], comment=[");
        builder.append(comment);
        builder.append("], isAlarmed=[");
        builder.append(alarm);
        builder.append("].");

        return builder.toString();
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        if (status == null){
            return;
        }
        this.status = status;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        if (time == null) return;
        this.time = time;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        if (action == null){
            return;
        }
        this.action = action;
    }

    public String getComment() {

        if (comment.length() > COMMENT_MAX_LEN){
            comment = comment.substring(0, COMMENT_MAX_LEN);
        }
        return comment;
    }

    public void setComment(String comment) {
        if (comment == null && "".equals(comment)) return;
        this.comment = comment;
    }

    public boolean isAlarm() {
        return alarm;
    }

    public void setAlarm(Boolean alarm) {
        if (alarm == null){
            return; // TODO replace here all such checks to checkNotNull, or at least consider on this
        }
        this.alarm = alarm;
    }

    public void performAction() throws Exception {
        if (action == null) {
            throw new NoActionException();
        }

        action.action();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public List getParameters() {
        return parameters;
    }

    public void setParameters(List parameters) {
        this.parameters = parameters;
    }

    public String getName() {
        return name;
    }

    private static final int NAME_MAX_LEN = 50;
    private static final int COMMENT_MAX_LEN = 100;

    public void setName(String name) {
        if (name == null && "".equals(name)) return;
        if (name.length() > NAME_MAX_LEN){
            name = name.substring(0, NAME_MAX_LEN);
        }
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return status == task.status &&
                alarm == task.alarm &&
                Objects.equals(parameters, task.parameters) &&
                Objects.equals(time, task.time) &&
                Objects.equals(action, task.action) &&
                Objects.equals(comment, task.comment) &&
                Objects.equals(name, task.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, time, action, comment, alarm, name);
    }
}
