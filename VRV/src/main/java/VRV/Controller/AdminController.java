package VRV.Controller;

import VRV.Model.User;
import VRV.Service.JWTUtility;
import VRV.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtility jwtUtility;

    // Helper method to validate the token
    private ResponseEntity<?> validateToken(String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or Invalid Token");
        }

        String jwtToken = token.substring(7); // Remove "Bearer " part
        String email = jwtUtility.extractEmail(jwtToken);

        if (email == null || jwtUtility.isTokenExpired(jwtToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is expired or invalid");
        }

        // Add logic to check if the email belongs to an admin
        String role = jwtUtility.extractRole(jwtToken);
        if (!"ROLE_ADMIN".equalsIgnoreCase(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
        }

        return null; // Token is valid
    }

    // Get all users
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(@RequestHeader("Authorization") String token) {
        ResponseEntity<?> tokenValidationResponse = validateToken(token);
        if (tokenValidationResponse != null) {
            return tokenValidationResponse; // Return the error response if token is invalid
        }

        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Delete a user by ID
    @DeleteMapping("/users/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        ResponseEntity<?> tokenValidationResponse = validateToken(token);
        if (tokenValidationResponse != null) {
            return tokenValidationResponse; // Return the error response if token is invalid
        }

        userService.deleteUserById(id);
        return ResponseEntity.ok("User with ID " + id + " deleted successfully.");
    }

    // Update a user's details
    @PutMapping("/users/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User updatedUser, @RequestHeader("Authorization") String token) {
        ResponseEntity<?> tokenValidationResponse = validateToken(token);
        if (tokenValidationResponse != null) {
            return tokenValidationResponse; // Return the error response if token is invalid
        }

        User existingUser = userService.getAllUsers()
                                       .stream()
                                       .filter(user -> user.getId().equals(id))
                                       .findFirst()
                                       .orElse(null);

        if (existingUser == null) {
            return ResponseEntity.status(404).body("User with ID " + id + " not found.");
        }

        // Update user details
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setRole(updatedUser.getRole());

        userService.updateUser(existingUser);
        return ResponseEntity.ok("User with ID " + id + " updated successfully.");
    }
}
