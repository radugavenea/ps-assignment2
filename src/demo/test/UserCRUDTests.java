package demo.test;

import demo.main.entities.Book;
import demo.main.entities.User;
import demo.main.repositories.BookRepositoryImpl;
import demo.main.repositories.UserRepositoryImpl;
import demo.main.services.BookService;
import demo.main.services.BookServiceImpl;
import demo.main.services.UserService;
import demo.main.services.UserServiceImpl;
import demo.main.xmlDataAccess.XMLDataAccess;
import demo.main.xmlDataAccess.XMLFilePAth;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

/**
 * Created by radu on 12.04.2017.
 */
public class UserCRUDTests {

    private XMLDataAccess xmlDataAccess = new XMLDataAccess();
    private Document document;
    private UserService userService = new UserServiceImpl(new UserRepositoryImpl(XMLFilePAth.userTestFilePath));

    @Before
    public void init() throws ParserConfigurationException, SAXException, IOException, TransformerException {
        document = xmlDataAccess.getXMLDocumentElement(XMLFilePAth.userTestFilePath);
        userService.addUser(new User(1,"admin","admin","password","Gigel"));
    }

    @Test
    public void getAllUsersTest() throws IOException, SAXException, ParserConfigurationException {
        assert userService.getAllUsers().size() == 1;
    }

    @Test
    public void getUserByUsernameTest() throws ParserConfigurationException, SAXException, IOException {
        assert Integer.toString(userService.getUserByUsername("admin").getId()).equals("1");
    }

    @Test
    public void addUserTest() throws ParserConfigurationException, TransformerException, SAXException, IOException {
        assert userService.getAllUsers().size() == 1;
        userService.addUser(new User(2,"user","username","password","Trica"));
        assert userService.getAllUsers().size() == 2;
    }

    @Test
    public void editUserTest() throws IOException, SAXException, ParserConfigurationException, TransformerException {
        assert userService.getUserByUsername("admin").getName().equals("Gigel");
        User user = userService.getUserByUsername("admin");
        user.setName("nu gigel");
        userService.editUser(user);
        assert userService.getUserByUsername("admin").getName().equals("nu gigel");
    }

    @Test
    public void deleteUserTest() throws ParserConfigurationException, TransformerException, SAXException, IOException {
        assert  userService.getAllUsers().size() == 1;
        userService.deleteUserById(1);
        assert userService.getAllUsers() == null;
    }

    @After
    public void tearDown() throws ParserConfigurationException, IOException, SAXException, TransformerException {
        userService.deleteUserById(1);
        userService.deleteUserById(2);
        xmlDataAccess.setUpXMLTransformerWriter(document,XMLFilePAth.userTestFilePath);
    }
}
