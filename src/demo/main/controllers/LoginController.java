package demo.main.controllers;

import demo.main.repositories.UserRepositoryImpl;
import demo.main.services.UserService;
import demo.main.services.UserServiceImpl;
import demo.main.views.AdminView;
import demo.main.views.EmployeeView;
import demo.main.views.LoginView;
import demo.main.xmlDataAccess.XMLFilePAth;
import org.xml.sax.SAXException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by radu on 10.04.2017.
 */
public class LoginController {

    private LoginView loginView;
    private UserService userService;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        this.userService = new UserServiceImpl(new UserRepositoryImpl(XMLFilePAth.userFilePath));

        loginView.addLoginListener(new LoginListener());
    }


    class LoginListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //check user for credentials
            if(userService.getsUserRole(loginView.getUsername(),loginView.getPassword()).equals("admin")){                                  // trebuie schimbat cu admin
                    new AdminController(new AdminView());
            }
            else if(userService.getsUserRole(loginView.getUsername(),loginView.getPassword()).equals("employee")){                              // trebuie schimbat cu user
                new EmployeeController(new EmployeeView());
            }
            else{
                loginView.errorMessage();
            }
        }
    }

}
