

import java.util.HashMap;
import java.util.Map;

public class AuthenticationManager {
    private Map<String, User> users;
    private static AuthenticationManager instance;
    private User currentUser;
    
    private AuthenticationManager() {
        users = new HashMap<>();
        // Add some default users for testing
        users.put("student1", new User("student1", "password", "John Doe", "student"));
        users.put("student2", new User("student2", "password", "Jane Smith", "student"));
        users.put("admin", new User("admin", "admin123", "Admin User", "admin"));
    }
    
    public static AuthenticationManager getInstance() {
        if (instance == null) {
            instance = new AuthenticationManager();
        }
        return instance;
    }
    
    public boolean authenticate(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            return true;
        }
        return false;
    }
    
    public void logout() {
        currentUser = null;
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
    
    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }
}
