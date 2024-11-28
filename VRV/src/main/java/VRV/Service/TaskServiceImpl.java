package VRV.Service;

import VRV.Dao.TaskDao;
import VRV.Model.Task;
import VRV.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDao taskDao;

    @Override
    @Transactional
    public void createTask(Task task) {
        taskDao.save(task);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getTasksByUser(User user) {
        return taskDao.findByUser(user);
    }
}
