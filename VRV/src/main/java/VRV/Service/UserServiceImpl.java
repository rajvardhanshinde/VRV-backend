package VRV.Service;

import VRV.Dao.UserDAO;
import VRV.Model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public void deleteUserById(Long id) {
        userDAO.deleteUserById(id);
    }
    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }
    


}
