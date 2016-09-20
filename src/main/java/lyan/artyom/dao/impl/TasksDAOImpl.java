package lyan.artyom.dao.impl;

import lyan.artyom.dao.TasksDAO;
import lyan.artyom.dao.pojo.Action;
import lyan.artyom.dao.pojo.Parameter;
import lyan.artyom.dao.pojo.Task;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class contains simple CRUD methods for accessing db
 * using hibernate. If you need to provide different implementation
 * using another technology just implement TasksDAO interface and change
 * configuration in file Beans.xml for your current implementation.
 * <p>
 * Created by dodler on 23/02/16.
 */
public class TasksDAOImpl implements TasksDAO {

    private static Logger logger = Logger.getLogger(Task.class);

    private SessionFactory sessionFactory;

    /**
     * This contructor initialize internal variables.
     */
    public TasksDAOImpl() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    /**
     * This method creates empty action with default field values.
     * TODO consider to make it singleton
     * @return task id.
     */
    @Override
    public Integer createAction() {
        final Session session = sessionFactory.openSession();

        final Action action = new Action(Action.SAMPLE_ACTION_NAME, Action.SAMPLE_ACTION_CP);

        final Transaction tx = session.beginTransaction();
        Integer actionId = null;
        try {
            actionId = (Integer) session.save(action);
            tx.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return actionId;
    }

    /**
     * Empty task singleton.
     *
     * @return Empty task object. Empty task is common task with default parameters.
     */
    public static Task getEmptyTask() {

        Task emptyTaskSingleton = new Task(Task.EMPTY_TASK_NAME, true, new Date(), null, "Empty comment", false);
//        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        ArrayList<Parameter> params = new ArrayList<Parameter>();
        params.add(new Parameter("Empty", "Task"));

        Action action = new Action("SimpleAction", Action.SAMPLE_ACTION_ID);

        emptyTaskSingleton.setParameters(params);
        emptyTaskSingleton.setAction(action);
        return emptyTaskSingleton;
    }

    /**
     * This method creates empty task with default field values.
     *
     * @return new task id
     */
    @Override
    public Integer createTask() {
        Session session = sessionFactory.openSession();
        Integer taskId = 0;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            taskId = (Integer) session.save(getEmptyTask());

            tx.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return taskId;
    }

    /**
     * This method creates new Action for task.
     *
     * @param name      action name
     * @param classPath path to action class
     * @return id of new Action
     */
    @Override
    public Integer createAction(final String name, final String classPath) {
        final Session session = sessionFactory.openSession();

        final Action action = new Action(name, classPath);

        final Transaction tx = session.beginTransaction();
        try {
            final int actionId = (Integer) session.save(action);
            tx.commit();
            return actionId;
        } catch (HibernateException he) {
            he.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return 0;
    }

    /**
     * Method creates new task. This one requires action as object.
     *
     * @param name    task name
     * @param status  task status
     * @param time    task execution time
     * @param action  task action
     * @param comment task comment
     * @param alarm   alarm at task execution time
     * @return new task id
     */
    @Override
    public Integer createTask(String name, Boolean status, Date time, Action action, String comment, Boolean alarm) {
        Session session = sessionFactory.openSession();
        Integer taskId = 0;
        Task task = new Task(name, status, time, action, comment, alarm);
        task.setAction(action);
        Transaction tx = session.beginTransaction();
        logger.debug(task);
        logger.debug(time);
        try {
            taskId = (Integer) session.save(task);
            tx.commit();
            session.flush();
        } catch (HibernateException he) {
            he.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
            return createTask();
        } finally {
            session.close();
        }
        return taskId;
    }

    /**
     * Method creates new task in database with given parameters
     *
     * @param name     task name
     * @param status   task status
     * @param time     task execution time
     * @param actionId task action (action id should be given)
     * @param comment  comment to new task
     * @param alarm    alarm at given time
     * @return new task id
     */
    @Override
    public Integer createTask(String name, Boolean status, Date time, Integer actionId, String comment, Boolean alarm) {
        Action action = getAction(actionId);
        return createTask(name, status, time, action, comment, alarm);
    }

    /**
     * This method modifies task with given parameters
     * if parameter is null, no changes will be performed
     * task id cannot be null, in that case, no changes will
     * be performed.
     *
     * @param id       task id
     * @param name     new task name
     * @param status   new task status
     * @param time     new task time
     * @param actionId action id, that will be set to task
     * @param comment  new task comment
     * @param alarm    new task alarm
     */
    @Override
    public void modifyTask(@NotNull Integer id,
                           String name, Boolean status, Date time,
                           Integer actionId, String comment, Boolean alarm) {

        Task task = getTask(id);

        Session session = sessionFactory.openSession();
        if (task == null) {
            return;
        }

        task.setName(name);
        task.setStatus(status);
        task.setTime(time);
        if (actionId != null) {
            task.setAction(getAction(actionId));
        }
        task.setComment(comment);
        task.setAlarm(alarm);

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
    public Task getTask(Integer id) {

        if (id == null) {
            return null;
        }

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            final Task task = (Task) session.get(Task.class, id);
            return task;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    /**
     * Methods finds List of tasks that matches given name.
     *
     * @param name if task name in db contains this,
     *             result list will contains this entry
     * @return List of tasks, that have name <code>like</code> given one
     */
    @Override
    public List<Task> getTask(@NotNull String name) {
        Session session = sessionFactory.openSession();
        Criteria cr = session.createCriteria(Task.class);
        cr.add(Restrictions.like("name", "%" + name + "%"));


        return cr.list();
    }

    /**
     * This method finds tasks with status, that equals to given one.
     *
     * @param status status to search
     * @return List of tasks with given status
     */
    @Override
    public List<Task> getTask(@NotNull Boolean status) {
        return sessionFactory.openSession().createCriteria(Task.class).add(Restrictions.eq("status", status)).list();
    }

    /**
     * Method returns List of all tasks in database.
     *
     * @return List of tasks.
     */
    @Override
    public List<Task> getAllTasks() {
        return sessionFactory.openSession().createCriteria(Task.class).list();
    }

    /**
     * Method finds Action with given id. In case when given id
     * doesn't present in db, null is returned.
     *
     * @param id Action id
     * @return Action object
     */
    @Override
    public Action getAction(Integer id) {

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Action action = (Action) session.get(Action.class, id);
//            if (action == null){
//                action = (Action) session.get(Action.class, createAction());
//            }
            return action;
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            he.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    /**
     * Method return all Action in db.
     *
     * @return List of Actions.
     */
    @Override
    public List<Action> getAllActions() {
        return sessionFactory.openSession().createCriteria(Action.class).list();
    }

    /**
     * Method deletes Task with given id.
     *
     * @param id Task id to delete
     */
    @Override
    public void deleteTask(Integer id) {
        if (id == null) return;

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Task task = (Task) session.get(Task.class, id);
        session.delete(task);

        tx.commit();
        session.close();
    }

    /**
     * Method deletes Action with given id.
     *
     * @param id Action id to delete.
     */
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
