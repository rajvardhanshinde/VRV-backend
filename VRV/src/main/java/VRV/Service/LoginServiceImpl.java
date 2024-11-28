package VRV.Service;

import VRV.Model.User;
import VRV.Dao.RegisterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class LoginServiceImpl implements LoginService, UserDetailsService {

    @Autowired
    private RegisterDao registerDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public boolean authenticateUser(User user) {
        // Check if user exists in DB
        User existingUser = registerDao.findByEmail(user.getEmail());
        if (existingUser != null && passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            // Set the role of the existing user
            user.setRole(existingUser.getRole());
            return true;
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = registerDao.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole())  // Assume roles are correctly mapped to User entity
                .build();
    }

    @Override
    public User findUserByEmail(String email) {
        return registerDao.findByEmail(email);
    }
}
