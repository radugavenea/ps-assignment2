package demo.main.repositories;

import demo.main.entities.User;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

/**
 * Created by radu on 11.04.2017.
 */
public interface UserRepository {

    List<User> getUsers() throws ParserConfigurationException, IOException, SAXException;
    User getUserByUsername(String username) throws ParserConfigurationException,IOException,SAXException;
    int addUser(User user) throws ParserConfigurationException, IOException, SAXException, TransformerException;
    int editUser(User user) throws ParserConfigurationException, IOException, SAXException, TransformerException;
    int deleteUserById(int id) throws ParserConfigurationException, IOException, SAXException, TransformerException;
}
