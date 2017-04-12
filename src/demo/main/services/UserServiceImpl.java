package demo.main.services;

import demo.main.entities.User;
import demo.main.repositories.UserRepository;
import demo.main.repositories.UserRepositoryImpl;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by radu on 11.04.2017.
 */
public class UserServiceImpl extends Observable implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getUsers();
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public int addUser(User user) {
        return userRepository.addUser(user);
    }

    @Override
    public int editUser(User user) {
        return userRepository.editUser(user);
    }

    @Override
    public int deleteUserById(int id) {
        return userRepository.deleteUserById(id);
    }

    @Override
    public  Object[][] getMappedUsers() {
        List<User> users = userRepository.getUsers();
        Object[][] userObjects = new Object[users.size()][3];
        for(int i=0; i<users.size(); i++){
            userObjects[i][0] = users.get(i).getUsername();
            userObjects[i][1] = users.get(i).getRole();
            userObjects[i][2] = users.get(i).getName();
        }
        return userObjects;
    }

    @Override
    public ArrayList<String> getMappedUserByUsername(String selectedUsername) {
        ArrayList<String> userFields = new ArrayList<>();
        User user = userRepository.getUserByUsername(selectedUsername);
        userFields.add(Integer.toString(user.getId()));
        userFields.add(user.getUsername());
        userFields.add(user.getRole());
        userFields.add(user.getName());
        return userFields;
    }

}
