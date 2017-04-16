package demo.main.services;

import demo.main.controllers.AdminController;
import demo.main.entities.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * Created by radu on 11.04.2017.
 */
public interface UserService {

    List<User> getAllUsers();
    User getUserByUsername(String username);
    int addUser(User user);
    int editUser(User user);
    int deleteUserById(int id);
    Object[][] getMappedUsers();
    ArrayList<String> getMappedUserByUsername(String selectedUsername);
    String getsUserRole(String username, String password);
    int getIncrementedUserId();
    void addObserver(Observer o);
}
