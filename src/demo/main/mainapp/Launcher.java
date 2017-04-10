package demo.main.mainapp;

import demo.main.controllers.EmployeeController;
import demo.main.controllers.LoginController;
import demo.main.views.EmployeeView;
import demo.main.views.LoginView;

/**
 * Created by radu on 10.04.2017.
 */
public class Launcher {


    public static void main(String[] args) {
//        new AdminController(new AdminView());
//        new EmployeeController(new EmployeeView());
        new LoginController(new LoginView());
    }
}
