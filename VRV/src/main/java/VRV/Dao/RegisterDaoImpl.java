package VRV.Dao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import VRV.Model.User;

@Repository
public class RegisterDaoImpl implements RegisterDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    @Transactional
    public void save(User user) {
        hibernateTemplate.save(user);
    }
    
    @Override
    public User findByEmail(String email) {
        return (User) hibernateTemplate.execute(session -> {
            return session.createQuery("FROM User WHERE email = :email", User.class)
                          .setParameter("email", email)
                          .uniqueResult();
        });
    }

}

