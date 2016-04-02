package lian.artyom.dao.impl;

import lian.artyom.dao.TasksDAO;
import lian.artyom.dao.pojo.Action;
import lian.artyom.dao.pojo.Parameter;
import lian.artyom.dao.pojo.Task;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dodler on 23/02/16.
 */
public class TasksDAOImpl implements TasksDAO {

    private SessionFactory sessionFactory;

    public TasksDAOImpl(){
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public int createAction(){
        final Session session = sessionFactory.openSession();

        final Action action = new Action("SimpleaAction","lian.artyom.scheduler.action.TaskAction.SimpleActionImpl");

        final Transaction tx= session.beginTransaction();
        try{
            final int actionId = (Integer)session.save(action);
            tx.commit();
        }catch(HibernateException he){
            he.printStackTrace();
            if (tx != null){
                tx.rollback();
            }
        }finally{
            session.close();
        }
        return 0;
    }

    @Override
    public int createTask(){
        Session session = sessionFactory.openSession();
        int taskId = 0;
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Task task = new Task("task name",true, new Date(System.currentTimeMillis()), null, String.valueOf(System.currentTimeMillis()), false);

            ArrayList<Parameter> params = new ArrayList<Parameter>();
            params.add(new Parameter("Empty", "Task"));

            Action action = new Action("SimpleAction","lian.artyom.scheduler.action.impl.SampleTaskAction");

            task.setParameters(params);
            task.setAction(action);

            taskId = (Integer)session.save(task);

            tx.commit();
        }catch(HibernateException he){
            he.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        }
        finally{
            session.close();
        }
        return taskId;
    }

    @Override
    public int createAction(String name, String classPath) {
        return 0;
    }

    // TODO add nullable from guava
    @Override
    public int createTask(String name, boolean status, Date time, Action action, String comment, boolean alarm) {
        Session session = sessionFactory.openSession();
        int taskId = 0;
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Task task = new Task(name,status, time, action, comment, alarm);

            task.setAction(action);

            taskId = (Integer)session.save(task);

            tx.commit();
        }catch(HibernateException he){
            he.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        }
        finally{
            session.close();
        }
        return taskId;
    }

    @Override
    public int createTask(String name, boolean status, Date time, int actionId, String comment, boolean alarm) {
        Action action = getAction(actionId);
        return createTask(name, status, time, action, comment, alarm);
    }

    /**
     * @param id of task in db
     * @return null if id douesn't exist in db
     */
    @Override
    public Task getTask( int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            final Task task = (Task) session.get(Task.class, id);
            return task;
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<Task> getAllTasks(){
        return sessionFactory.openSession().createCriteria(Task.class).list();
    }

    @Override
    public Action getAction(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            final Action action = (Action)session.get(Action.class, id);
            return action;
        }catch(HibernateException he){
            if (tx != null){
                tx.rollback();
            }
            he.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            session.close();
        }
        return null;
    }

    @Override
    public List<Action> getAllActions() {
        return sessionFactory.openSession().createCriteria(Action.class).list();
    }
}
