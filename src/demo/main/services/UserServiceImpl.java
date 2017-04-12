package demo.main.services;

import demo.main.entities.User;
import demo.main.repositories.UserRepository;
import demo.main.repositories.UserRepositoryImpl;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

/**
 * Created by radu on 11.04.2017.
 */
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() throws IOException, SAXException, ParserConfigurationException {
        return userRepository.getUsers();
    }

    @Override
    public User getUserByUsername(String username) throws IOException, SAXException, ParserConfigurationException {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public int addUser(User user) throws ParserConfigurationException, TransformerException, SAXException, IOException {
        return userRepository.addUser(user);
    }

    @Override
    public int editUser(User user) throws ParserConfigurationException, TransformerException, SAXException, IOException {
        return userRepository.editUser(user);
    }

    @Override
    public int deleteUserById(int id) throws SAXException, IOException, ParserConfigurationException, TransformerException {
        return userRepository.deleteUserById(id);
    }
}
