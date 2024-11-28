package VRV.Controller;

import VRV.Model.Task;
import VRV.Model.User;
import VRV.Service.JWTUtility;
import VRV.Service.TaskService;
import VRV.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/tasks")
@CrossOrigin(origins = "http://localhost:5173") // Update for production
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private LoginService loginService;

    // Helper method to validate the token
    private ResponseEntity<?> validateToken(String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or Invalid Token");
        }

        String jwtToken = token.substring(7); // Remove "Bearer " part
        if (!jwtToken.contains(".")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Malformed JWT Token");
        }

        String email = jwtUtility.extractEmail(jwtToken);
        User user = loginService.findUserByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }

        return null; // Token is valid
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTask(@RequestBody Task task, @RequestHeader("Authorization") String token) {
        // Validate the token
        ResponseEntity<?> tokenValidationResponse = validateToken(token);
        if (tokenValidationResponse != null) {
            return tokenValidationResponse; // Return the error response if token is invalid
        }

        // Extract the email from the token
        String email = jwtUtility.extractEmail(token.substring(7));
        User user = loginService.findUserByEmail(email);

        // Associate the user with the task
        task.setUser(user);
        taskService.createTask(task); // Persist the task in the database

        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getTasks(@RequestHeader("Authorization") String token) {
        // Validate the token
        ResponseEntity<?> tokenValidationResponse = validateToken(token);
        if (tokenValidationResponse != null) {
            return tokenValidationResponse; // Return the error response if token is invalid
        }

        // Extract the email from the token
        String email = jwtUtility.extractEmail(token.substring(7));
        User user = loginService.findUserByEmail(email);

        // Retrieve tasks for the user
        List<Task> tasks = taskService.getTasksByUser(user);
        return ResponseEntity.ok(tasks);
    }
}
