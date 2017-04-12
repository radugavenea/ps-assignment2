package demo.main.repositories;

import demo.main.entities.User;
import demo.main.xmlDataAccess.XMLDataAccess;
import demo.main.xmlDataAccess.XMLFilePAth;
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

    private XMLDataAccess xmlDataAccess;
    private Document document;
    private String xmlFile;


    public UserRepositoryImpl(String xmlFile) {
        this.xmlDataAccess = new XMLDataAccess();
        this.xmlFile = xmlFile;
    }


    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return !users.isEmpty() ? users : null;
    }


    @Override
    public User getUserByUsername(String username) {
        User user = null;
        try {
            document = xmlDataAccess.getXMLDocumentElement(xmlFile);
            NodeList userNodeList = document.getElementsByTagName("user");
            for (int i=0; i<userNodeList.getLength(); i++){
                Node userNode = userNodeList.item(i);
                if(userNode.getNodeType() == Node.ELEMENT_NODE){
                    Element userElement = (Element) userNode;
                    if(userElement.getElementsByTagName("username").item(0).getTextContent().equals(username)){
                        user = new User();
                        user.setId(Integer.parseInt(userElement.getAttribute("id")));
                        user.setRole(userElement.getElementsByTagName("role").item(0).getTextContent());
                        user.setUsername(userElement.getElementsByTagName("username").item(0).getTextContent());
                        user.setPassword(userElement.getElementsByTagName("password").item(0).getTextContent());
                        user.setName(userElement.getElementsByTagName("name").item(0).getTextContent());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public int addUser(User user) {
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        return user.getId();
    }

    @Override
    public int editUser(User user) {
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        return user.getId();
    }

    @Override
    public int deleteUserById(int id) {
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        return id;
    }

}
