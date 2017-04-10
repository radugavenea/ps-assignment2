package demo.main.controllers;

import demo.main.views.EmployeeView;

import java.awt.event.WindowListener;

/**
 * Created by radu on 10.04.2017.
 */
public class EmployeeController extends AbstractController {

    private EmployeeView employeeView;

    public EmployeeController(EmployeeView employeeView){
        this.employeeView = employeeView;

        employeeView.addEmployeeWindowListener(new MyWindowListener());
    }

}
