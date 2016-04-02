package lian.artyom.dao.pojo;

import lian.artyom.dao.exception.NoActionException;

import java.util.Date;
import java.util.List;

/**
 * simple POJO object
 * Created by dodler on 23/02/16.
 */
public class Task {

    private List parameters;

    private int id;

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

    public Task(){
    }

    public Task(
            String name,
            boolean status,
                Date time,
                Action action,
                String comment,
                boolean alarm){

        this.name = name;
        this.status = status;
        this.time = time;
        this.action = action;
        this.comment = comment;
        this.alarm = alarm;
    }

    @Override
    public String toString(){
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

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isAlarm() {
        return alarm;
    }

    public void setAlarm(boolean alarm) {
        this.alarm = alarm;
    }

    public void performAction() throws Exception {
        if (action == null){
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

    public void setName(String name) {
        this.name = name;
    }
}
