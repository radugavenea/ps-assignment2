package demo.main.repositories;

import demo.main.entities.User;
import demo.main.xmlDataAccess.XMLDataAccess;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by radu on 10.04.2017.
 */
public class UserRepositoryImpl implements UserRepository {

    private String xmlFile = "users.xml";
    private Document document;
    private XMLDataAccess xmlDataAccess;

    /**
     * Constructor
     */
    public UserRepositoryImpl() {
        this.xmlDataAccess = new XMLDataAccess();
    }


    /**
     * Retrieve all users
     * @return  List of User
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public List<User> getUsers() throws ParserConfigurationException, IOException, SAXException {
        List<User> users = new ArrayList<>();

        document = xmlDataAccess.getXMLDocumentElement(xmlFile);
        NodeList userNodeList = document.getElementsByTagName("user");
        for (int i = 0; i < userNodeList.getLength(); i++) {
            User user = new User();
            Node userNode = userNodeList.item(i);
            if (userNode.getNodeType() == Node.ELEMENT_NODE) {
                Element userElement = (Element) userNode;
                user.setId(Integer.parseInt(userElement.getAttribute("id")));
                user.setRole(userElement.getElementsByTagName("role").item(0).getTextContent());
                user.setUsername(userElement.getElementsByTagName("username").item(0).getTextContent());
                user.setPassword(userElement.getElementsByTagName("password").item(0).getTextContent());
                user.setName(userElement.getElementsByTagName("name").item(0).getTextContent());
            }
            users.add(user);
        }
        System.out.println("Root element: " + document.getDocumentElement().getNodeName());

        return !users.isEmpty() ? users : null;
    }

    /**
     * Retrieve a user by its username
     * @param username
     * @return User
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    @Override
    public User getUserByUsername(String username) throws ParserConfigurationException, IOException, SAXException {
        User user = new User();

        document = xmlDataAccess.getXMLDocumentElement(xmlFile);
        NodeList userNodeList = document.getElementsByTagName("user");
        for (int i=0; i<userNodeList.getLength(); i++){
            Node userNode = userNodeList.item(i);
            if(userNode.getNodeType() == Node.ELEMENT_NODE){
                Element userElement = (Element) userNode;
                if(userElement.getElementsByTagName("username").item(1).getTextContent().equals(username)){
                    user.setId(Integer.parseInt(userElement.getAttribute("id")));
                    user.setRole(userElement.getElementsByTagName("role").item(0).getTextContent());
                    user.setUsername(userElement.getElementsByTagName("username").item(0).getTextContent());
                    user.setPassword(userElement.getElementsByTagName("password").item(0).getTextContent());
                    user.setName(userElement.getElementsByTagName("name").item(0).getTextContent());
                    return user;
                }
            }
        }
        return null;
    }

    @Override
    public int addUser(User user) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        document = xmlDataAccess.getXMLDocumentElement(xmlFile);

        Element userRoot = document.getDocumentElement();
        Element userElement = document.createElement("user");
        userRoot.appendChild(userElement);
        Attr userIdAttr = document.createAttribute("id");
        userIdAttr.setValue(Integer.toString(user.getId()));
        userElement.setAttributeNode(userIdAttr);

        Element userRole = document.createElement("role");
        Element userUsername = document.createElement("username");
        Element userPassword = document.createElement("password");
        Element userName = document.createElement("name");

        userRole.appendChild(document.createTextNode(user.getRole()));
        userUsername.appendChild(document.createTextNode(user.getUsername()));
        userPassword.appendChild(document.createTextNode(user.getPassword()));
        userName.appendChild(document.createTextNode(user.getName()));

        userElement.appendChild(userRole);
        userElement.appendChild(userUsername);
        userElement.appendChild(userPassword);
        userElement.appendChild(userName);

        xmlDataAccess.setUpXMLTransformerWriter(document, xmlFile);
        return user.getId();
    }

    @Override
    public int editUser(User user) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        document = xmlDataAccess.getXMLDocumentElement(xmlFile);
        NodeList userNodeList = document.getElementsByTagName("user");
        for (int i=0; i<userNodeList.getLength(); i++){
            Node userNode = userNodeList.item(i);
            if(userNode.getNodeType() == Node.ELEMENT_NODE){
                Element userElement = (Element) userNode;
                if(userElement.getAttribute("id").equals(Integer.toString(user.getId()))){
                    userElement.getElementsByTagName("role").item(0).setTextContent(user.getRole());
                    userElement.getElementsByTagName("username").item(0).setTextContent(user.getUsername());
                    userElement.getElementsByTagName("password").item(0).setTextContent(user.getPassword());
                    userElement.getElementsByTagName("name").item(0).setTextContent(user.getName());
                }
            }
        }
        xmlDataAccess.setUpXMLTransformerWriter(document, xmlFile);
        return user.getId();
    }

    @Override
    public int deleteUserById(int id) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        document = xmlDataAccess.getXMLDocumentElement(xmlFile);

        Node users = document.getFirstChild();
        Node userNode = document.getElementsByTagName("users").item(0);
        NodeList userChildList = userNode.getChildNodes();
        for (int i=0; i<userChildList.getLength(); i++){
            Node node = userChildList.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE){
                Element userElement = (Element) node;
                if(userElement.getAttribute("id").equals(Integer.toString(id))){
                    users.removeChild(node);
                }
            }
        }
        xmlDataAccess.setUpXMLTransformerWriter(document, xmlFile);
        return 0;
    }

}
