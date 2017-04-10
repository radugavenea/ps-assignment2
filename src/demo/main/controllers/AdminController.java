package demo.main.controllers;

import demo.main.entities.User;
import demo.main.repositories.UserRepositoryImpl;
import demo.main.services.UserService;
import demo.main.services.UserServiceImpl;
import demo.main.views.AdminView;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
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
            if(e.getActionCommand().equals("Read users")){
                try {
                    userService.getAllUsers();
                } catch (ParserConfigurationException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (SAXException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

}
