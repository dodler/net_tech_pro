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
            Task task = new Task(true, new Date(System.currentTimeMillis()), null, "test task", false);
            session.save(task);
        }catch(HibernateException he){
            he.printStackTrace();
        }
    }
}
