package demo.main.services;

import demo.main.entities.User;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

/**
 * Created by radu on 11.04.2017.
 */
public interface UserService {

    List<User> getAllUsers() throws IOException, SAXException, ParserConfigurationException;
    int addUser(User user) throws ParserConfigurationException, TransformerException, SAXException, IOException;
    int editUser(User user) throws ParserConfigurationException, TransformerException, SAXException, IOException;
    int deleteUserById(int id) throws SAXException, IOException, ParserConfigurationException, TransformerException;
}
