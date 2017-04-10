package demo.main.repositories;

import demo.main.entities.User;
import demo.main.xmlDataAccess.XMLDataAccess;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by radu on 10.04.2017.
 */
public class UserRepositoryImpl implements UserRepository {

    private String xmlPath = "users.xml";
    private Document document;
    private XMLDataAccess xmlDataAccess;

    public UserRepositoryImpl() {
        this.xmlDataAccess = new XMLDataAccess();
    }


    public List<User> getUsers() throws ParserConfigurationException, IOException, SAXException {
        List<User> users = new ArrayList<>();

        document = xmlDataAccess.setUpXMLConnection(xmlPath);
        NodeList userNodeList = document.getElementsByTagName("user");
        for (int i = 0; i < userNodeList.getLength(); i++) {
            User user = new User();
            Node userNode = userNodeList.item(i);
            if (userNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) userNode;
                user.setId(Integer.parseInt(eElement.getAttribute("id")));
                user.setRole(eElement.getElementsByTagName("role").item(0).getTextContent());
                user.setUsername(eElement.getElementsByTagName("username").item(0).getTextContent());
                user.setPassword(eElement.getElementsByTagName("password").item(0).getTextContent());
                user.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
            }
            users.add(user);
        }
        System.out.println("Root element: " + document.getDocumentElement().getNodeName());

        return users;
    }
}
