package demo.main.controllers;

import demo.main.entities.User;
import demo.main.repositories.UserRepositoryImpl;
import demo.main.services.UserService;
import demo.main.services.UserServiceImpl;
import demo.main.views.AdminView;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

/**
 * Created by radu on 10.04.2017.
 */
public class AdminController extends AbstractController {

    private AdminView adminView;
    private UserService userService;

    public AdminController(AdminView adminView) {
        this.adminView = adminView;
        this.userService = new UserServiceImpl();

        adminView.addAdminWindowListener(new MyWindowListener());
        adminView.addUserListener(new UserListener());
    }



    class UserListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()){
                case "Read":
                    try {
                        userService.getAllUsers();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (SAXException e1) {
                        e1.printStackTrace();
                    } catch (ParserConfigurationException e1) {
                        e1.printStackTrace();
                    }
                    break;
                case "Add":
                    try {
                        userService.addUser(new User(23,"admin","admin","admin","nume"));
                    } catch (ParserConfigurationException e1) {
                        e1.printStackTrace();
                    } catch (TransformerException e1) {
                        e1.printStackTrace();
                    } catch (SAXException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
                case "Edit":
                    try {
                        userService.editUser(new User(23,"user","user","user","user nume"));
                    } catch (ParserConfigurationException e1) {
                        e1.printStackTrace();
                    } catch (TransformerException e1) {
                        e1.printStackTrace();
                    } catch (SAXException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
                case "Delete":
                    try {
                        userService.deleteUserById(23);
                    } catch (SAXException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (ParserConfigurationException e1) {
                        e1.printStackTrace();
                    } catch (TransformerException e1) {
                        e1.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        }
    }

}
