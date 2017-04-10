package demo.main.entities;

/**
 * Created by radu on 10.04.2017.
 */
public class User {

    private int id;
    private String role;    // employee or admin
    private String username;
    private String password;
    private String name;

    public User(int id, String role, String username, String password, String name) {
        this.id = id;
        this.role = role;
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
