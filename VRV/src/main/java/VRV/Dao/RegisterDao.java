package VRV.Dao;

import VRV.Model.User;

public interface RegisterDao {
    void save(User user);
    User findByEmail(String email);

}
