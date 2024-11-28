package VRV.Service;

import VRV.Model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface LoginService {
    boolean authenticateUser(User user);
    UserDetails loadUserByUsername(String email);
    User findUserByEmail(String email); // To fetch a User entity directly
}
