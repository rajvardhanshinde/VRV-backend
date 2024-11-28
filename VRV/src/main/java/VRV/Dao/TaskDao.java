package VRV.Dao;

import VRV.Model.Task;
import VRV.Model.User;

import java.util.List;

public interface TaskDao {
    void save(Task task);              // Create or update a task
    List<Task> findByUser(User user);  // Retrieve tasks by user
}
