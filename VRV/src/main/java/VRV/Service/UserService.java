package VRV.Service;
import VRV.Model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void deleteUserById(Long id);
    void updateUser(User user);
    
}

