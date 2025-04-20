

public class User {
    private String username;
    private String password;
    private String name;
    private String role; // "student" or "admin"
    
    public User(String username, String password, String name, String role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
    }
    
    // Getters
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getName() {
        return name;
    }
    
    public String getRole() {
        return role;
    }
}