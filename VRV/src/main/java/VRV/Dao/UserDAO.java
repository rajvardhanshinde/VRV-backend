package VRV.Dao;

import VRV.Model.User;
import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();
    void deleteUserById(Long id);
    void updateUser(User user);
   


	

}

