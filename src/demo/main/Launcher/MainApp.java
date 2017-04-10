package demo.main.Launcher;

import demo.main.controllers.AdminController;
import demo.main.controllers.EmployeeController;
import demo.main.views.AdminView;
import demo.main.views.EmployeeView;

/**
 * Created by radu on 10.04.2017.
 */
public class MainApp {


    public static void main(String[] args) {
//        new AdminController(new AdminView());
        new EmployeeController(new EmployeeView());
    }
}
