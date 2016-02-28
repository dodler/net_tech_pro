package lian.artyom.dao.impl;

import lian.artyom.dao.TasksDAO;
import lian.artyom.dao.pojo.Action;
import lian.artyom.dao.pojo.Parameter;
import lian.artyom.dao.pojo.Task;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

        final Action action = new Action("lian.artyom.scheduler.action.TaskAction.SimpleActionImpl");

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
            Task task = new Task(true, new Date(System.currentTimeMillis()), null, String.valueOf(System.currentTimeMillis()), false);

            ArrayList<Parameter> params = new ArrayList<Parameter>();
            params.add(new Parameter("1", "2"));
            params.add(new Parameter("1", "3"));
            params.add(new Parameter("1", "4"));

            Action action = new Action("lian.artyom.scheduler.action.impl.SampleTaskAction");

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

    /**
     * @param id of task in db
     * @return null if id douesn't exist in db
     */
    @Override
    public Task getTask(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            final Task task = session.get(Task.class, id);
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
}
