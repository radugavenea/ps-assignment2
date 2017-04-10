package demo.main.services;

import demo.main.entities.User;
import demo.main.repositories.UserRepository;
import demo.main.repositories.UserRepositoryImpl;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 * Created by radu on 11.04.2017.
 */
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl() {
        this.userRepository = new UserRepositoryImpl();
    }

    @Override
    public List<User> getAllUsers() throws IOException, SAXException, ParserConfigurationException {
        return userRepository.getUsers();
    }
}
