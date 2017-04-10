package demo.main.controllers;

import demo.main.views.LoginView;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by radu on 10.04.2017.
 */
public abstract class AbstractController {

    class MyWindowListener implements WindowListener{

        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {
            // remove user from session
            e.getWindow().dispose();
            new LoginController(new LoginView());
        }

        @Override
        public void windowClosed(WindowEvent e) {
        }

        @Override
        public void windowIconified(WindowEvent e) {

        }

        @Override
        public void windowDeiconified(WindowEvent e) {

        }

        @Override
        public void windowActivated(WindowEvent e) {

        }

        @Override
        public void windowDeactivated(WindowEvent e) {

        }
    }
}
