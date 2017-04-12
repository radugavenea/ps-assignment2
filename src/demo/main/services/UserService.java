package demo.main.services;

import demo.main.entities.User;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
}
