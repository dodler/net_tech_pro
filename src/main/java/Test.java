import lian.artyom.tools.DbTool;

/**
 * Created by dodler on 23/02/16.
 */
public class Test {
    public static void main(String[] args) throws Exception {
//        TasksDAO dao = new TasksDAOImpl();
//        int id = dao.createTask();
//        final Task task = dao.getTask(id);
//        task.performAction();

        System.out.println(new DbTool().sendRequest("select * from task;"));

    }
}
