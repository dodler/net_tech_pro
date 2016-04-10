package lian.artyom.dao;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TODO add logging
 * Created by dodler on 23/02/16.
 */
public class DAOHelper {
    private TasksDAO dao;
    private static ApplicationContext ctx;

    public static DAOHelper getDAOHelper(){
        if (ctx == null) {
            ctx = new ClassPathXmlApplicationContext("Beans.xml");
        }
        return (DAOHelper) ctx.getBean("DAOHelper");
    }

    private DAOHelper(TasksDAO dao){
        this.dao = dao;
    }

    /**
     * method for use from jsp pages to pass argument in non-handled view
     * this method parse all data from request and creates new task according to
     * them. in case of invalid parameters, parameters will be set as default
     * @param name
     * @param status
     * @param time
     * @param action
     * @param comment
     * @param alarm
     * @return
     */
    public int createTask(String name,
                          String status,
                          String time,
                          String action,
                          String comment,
                          String alarm)  {
        try {
            boolean taskStatus = status == null ? false : Boolean.valueOf(status),
                    useAlarm = alarm == null ? false : Boolean.valueOf(alarm);

            DateFormat format = new SimpleDateFormat("YYYY-MM-DD");
            Date date = new Date();
            try {
                date = format.parse(time);
            } catch (ParseException e) {

            } catch (Exception e) {

            }
            int actionId = action == null ? 0 : Integer.valueOf(action);

            return dao.createTask(name, taskStatus, date, actionId, comment, useAlarm);
        }catch(Throwable e){
            return dao.createTask();
        }
    }

    private static final String ON_PATTERN = "on";
    private static final String OFF_PATTERN = "off";


    public void modifyTask(@NotNull String id,
                    String name,
                    String status,
                    String time,
                    String action,
                    String comment,
                    String alarm){

        Boolean taskStatus = status==null?null:ON_PATTERN.equals(status),
                useAlarm = alarm==null?null:ON_PATTERN.equals(alarm);

        DateFormat format = new SimpleDateFormat("YYYY-MM-DD");
        Date date;
        try {
            date = format.parse(time);
        }catch(ParseException e){
            date = null;
        }catch(Exception e){
            date = null;
        }

        Integer actionId = null;

        try{
            actionId = action==null?null:Integer.valueOf(action);
        }catch(NumberFormatException e){

        }catch (Throwable e){

        }
        Integer taskId = Integer.valueOf(id);
        dao.modifyTask(taskId, name, taskStatus, date, actionId, comment, useAlarm);
    }

    public void deleteTask(@NotNull String id){
        int taskId = 0;
        try{
            taskId = Integer.valueOf(id);
            dao.deleteTask(taskId);
        }catch(NumberFormatException e){

        }
    }
}
