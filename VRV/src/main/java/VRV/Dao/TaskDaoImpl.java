package VRV.Dao;

import VRV.Model.Task;
import VRV.Model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskDaoImpl implements TaskDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Task task) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(task); // Save a new task or update an existing one
    }

    @Override
    public List<Task> findByUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Task t WHERE t.user = :user";
        return session.createQuery(hql, Task.class)
                      .setParameter("user", user)
                      .getResultList();
    }
}
