package VRV.Service;

import VRV.Model.Task;
import VRV.Model.User;

import java.util.List;

public interface TaskService {
    void createTask(Task task);  // Method to create a new task
    List<Task> getTasksByUser(User user);  // Method to fetch tasks by user
}
