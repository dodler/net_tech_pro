package lyan.artyom.dao.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lyan.artyom.scheduler.action.TaskAction;

import java.util.Objects;

/**
 * Base class for task action.
 * implementation will be kept in impl package
 * Action class that implements current interface will be set in
 * database, and loaded using reflection api
 * spaghetti planning in action.
 * this should be POJO object, but it's not in pojo package
 * Created by dodler on 23/02/16.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Action {

    public static final String SAMPLE_ACTION_CP = "lyan.artyom.scheduler.action.impl.SampleTaskAction";
    public static final String SAMPLE_ACTION_ID = "0";
    public static final String SAMPLE_ACTION_NAME = "SampleAction";

    private String name;
    private String classPath;
    private int id;

    public Action() {
    }

    /**
     * Simple constructor. Consider change to factory.
     * @param name action name.
     * @param classPath action classpath.
     */
    public Action(String name, String classPath) {
        this.name = name;
        this.classPath = classPath;
    }

    /**
     * This method performs action with given classpath.
     * @throws Exception in case when classpath leads to class
     * with wrong credits.
     */
    public void action() throws Exception {

        if ("".equals(classPath)) {
            // no action is requried

            return;
        } else {
            try {
                Class actionClass = Class.forName(classPath);

                TaskAction ta = (TaskAction) actionClass.newInstance();
                ta.perform();

            } catch (Exception e ){ // I know that's bad practice.
                e.printStackTrace();
                throw e;
            }

        }
    }

    /**
     * Simple naive getter method.
     * @return current classpath.
     */
    public String getClassPath() {
        return this.classPath;
    }

    /**
     * Simple naive setter method.
     */
    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    /**
     * Simple naive getter method.
     * @return current action id.
     */
    public int getId() {
        return id;
    }

    /**
     * Simple naive setter method.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Simple naive getter method.
     * @return current action name.
     */
    public String getName() {
        return name;
    }

    /**
     * Simple naive setter method.
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Action action = (Action) o;
        return Objects.equals(name, action.name) &&
                Objects.equals(classPath, action.classPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id ,name, classPath);
    }
}
