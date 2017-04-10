package demo.main.repositories;

import demo.main.entities.User;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 * Created by radu on 11.04.2017.
 */
public interface UserRepository {

    public List<User> getUsers() throws ParserConfigurationException, IOException, SAXException;
}
