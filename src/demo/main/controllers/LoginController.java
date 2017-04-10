package demo.main.controllers;

import demo.main.views.AdminView;
import demo.main.views.EmployeeView;
import demo.main.views.LoginView;
import org.xml.sax.SAXException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by radu on 10.04.2017.
 */
public class LoginController {

    private LoginView loginView;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;

        loginView.addLoginListener(new LoginListener());
    }


    class LoginListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //check user for credentials
            if(true){                                  // trebuie schimbat cu admin
                    new AdminController(new AdminView());
            }
            else if(false){                              // trebuie schimbat cu user
                new EmployeeController(new EmployeeView());
            }
            else{
                loginView.errorMessage();
            }
        }
    }

}
