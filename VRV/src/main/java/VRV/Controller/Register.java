package VRV.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

import VRV.Model.User;
import VRV.Service.RegisterService;

@RestController
@RequestMapping("/api/register")
@CrossOrigin(origins = "http://localhost:5173")
public class Register {

    @Autowired
    private RegisterService registerService;

    @PostMapping
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody User user) {
        // Call the service to register the user
        registerService.registerUser(user);
        
        // Prepare a response map with a success message
        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully!");
        
        // Return the response as JSON
        return ResponseEntity.ok(response);
    }
}
