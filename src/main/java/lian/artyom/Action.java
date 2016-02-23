package lian.artyom;

import java.lang.reflect.InvocationTargetException;

/**
 * base class for task action
 * implementation will be kept in impl package
 * Action class that implements current interface will be set in
 * database, and loaded using reflection api
 * Created by dodler on 23/02/16.
 */
public class Action {

    private String classPath;
    private int id;

    public Action(){
        this.classPath = "";
        this.id = 0;
    }

    public Action(String classPath, int id){
        this.classPath = classPath;
        this.id = id;
    }

    public void action() throws Exception {
        if ("".equals(classPath)){
            // no action is requried
            return;
        }else{
            try {
                Class actionClass = Class.forName(classPath);
                if (actionClass.isAssignableFrom(this.getClass())){
                    actionClass.getDeclaredMethod("action").invoke(this);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw e;
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                throw e;
            } catch (NoSuchMethodException e) {
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
}
