import lian.artyom.dao.TasksDAO;

/**
 * Created by dodler on 23/02/16.
 */
public class Test {
    public static void main(String[] args){
        TasksDAO dao = new TasksDAO();
        dao.createTask();
        dao.createTask();
        dao.createTask();
        dao.createTask();
    }
}
