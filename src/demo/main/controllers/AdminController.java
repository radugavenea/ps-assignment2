package demo.main.controllers;

import demo.main.views.AdminView;

/**
 * Created by radu on 10.04.2017.
 */
public class AdminController extends AbstractController {

    private AdminView adminView;

    public AdminController(AdminView adminView) {
        this.adminView = adminView;

        adminView.addAdminWindowListener(new MyWindowListener());
    }

}
