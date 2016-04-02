package lian.artyom.dao.pojo;

import lian.artyom.scheduler.action.TaskAction;

import java.lang.reflect.InvocationTargetException;

/**
 * base class for task action
 * implementation will be kept in impl package
 * Action class that implements current interface will be set in
 * database, and loaded using reflection api
 * spaghetti planning in action
 * this should be POJO object, but it's not in pojo package
 * Created by dodler on 23/02/16.
 */
public class Action {

    private String name;
    private String classPath;
    private int id;

    public Action(){
    }

    public Action(String name, String classPath){
        this.name = name;
        this.classPath = classPath;
    }

    public void action() throws Exception {

        System.out.println(classPath);

        if ("".equals(classPath)){
            // no action is requried
            
            return;
        }else{
            try {
                Class actionClass = Class.forName(classPath);

                TaskAction ta = (TaskAction) actionClass.newInstance();
                ta.perform();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw e;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw e;
            }

        }
    }

    public void setClassPath(String classPath){
        this.classPath = classPath;
    }

    public String getClassPath(){
        return this.classPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
