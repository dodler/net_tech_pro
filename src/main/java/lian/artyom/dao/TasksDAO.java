package lian.artyom.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Date;

/**
 * Created by dodler on 23/02/16.
 */
public class TasksDAO {

    private SessionFactory sessionFactory;

    public TasksDAO(){
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public int createTask(){
        Session session = sessionFactory.openSession();
        int taskId = 0;
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            Task task = new Task(true, new Date(System.currentTimeMillis()), null, String.valueOf(System.currentTimeMillis()), false);
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
}
