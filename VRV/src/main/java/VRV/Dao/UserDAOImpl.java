package VRV.Dao;

import VRV.Model.User;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private final HibernateTemplate hibernateTemplate;

    public UserDAOImpl(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public List<User> getAllUsers() {
        return hibernateTemplate.loadAll(User.class);
    }

    @Override
    public void deleteUserById(Long id) {
        User user = hibernateTemplate.get(User.class, id);
        if (user != null) {
            hibernateTemplate.delete(user);
        }
    }
    
    public void updateUser(User user) {
        hibernateTemplate.update(user);
    }
    
   

}
