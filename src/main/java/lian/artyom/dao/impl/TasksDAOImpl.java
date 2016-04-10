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
import org.jetbrains.annotations.NotNull;

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
    public Integer createAction(){
        final Session session = sessionFactory.openSession();

        final Action action = new Action("SimpleaAction","lian.artyom.scheduler.action.TaskAction.SimpleActionImpl");

        final Transaction tx= session.beginTransaction();
        Integer actionId = null;
        try{
            actionId = (Integer)session.save(action);
            tx.commit();
        }catch(HibernateException he){
            he.printStackTrace();
            if (tx != null){
                tx.rollback();
            }
        }finally{
            session.close();
        }
        return actionId;
    }

    @Override
    public Integer createTask(){
        Session session = sessionFactory.openSession();
        Integer taskId = 0;
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
    public Integer createAction(final String name, final String classPath) {
        final Session session = sessionFactory.openSession();

        final Action action = new Action(name,classPath);

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
    public Integer createTask(String name, Boolean status, Date time, Action action, String comment, Boolean alarm) {
        Session session = sessionFactory.openSession();
        Integer taskId = 0;
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
            return null;
        }
        finally{
            session.close();
        }
        return taskId;
    }

    @Override
    public Integer createTask(String name, Boolean status, Date time, Integer actionId, String comment, Boolean alarm) {
        Action action = getAction(actionId);
        return createTask(name, status, time, action, comment, alarm);
    }

    @Override
    public void modifyTask(@NotNull Integer id,
                           String name, Boolean status, Date time,
                           Integer actionId, String comment, Boolean alarm) {
        if (id == null) return;
        Task task = getTask(id);

        Session session = sessionFactory.openSession();
        if (task == null){
            return;
        }
        task.setName(name);
        if (status != null) task.setStatus(status);
        task.setTime(time);
        if (actionId != null) task.setAction(getAction(actionId));
        task.setComment(comment);
        if (alarm != null) task.setAlarm(alarm);

        Transaction tx = session.beginTransaction();
        session.update(task);
        tx.commit();
        session.close();
    }

    /**
     * @param id of task in db
     * @return null if id douesn't exist in db
     */
    @Override
    public Task getTask( Integer id) {

        if (id == null){
            return null;
        }

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
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<Task> getAllTasks(){
        return sessionFactory.openSession().createCriteria(Task.class).list();
    }

    @Override
    public Action getAction(Integer id) {

        if (id  == null){
            return null;
        }

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

    @Override
    public void deleteTask(Integer id) {
        if (id == null) return;

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Task task = (Task) session.get(Task.class, id);
        task.setAction(null);
        session.update(task);
        tx.commit();

        tx = session.beginTransaction();

        session.delete(task);
        tx.commit();
        session.close();
    }

    @Override
    public void deleteAction(Integer id) {
        if (id == null) return;

        Action action = getAction(id);

        if (action == null) return;

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.delete(action);
        tx.commit();
        session.close();
    }
}
