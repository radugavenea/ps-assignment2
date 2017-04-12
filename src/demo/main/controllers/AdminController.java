package demo.main.controllers;

import demo.main.entities.User;
import demo.main.repositories.BookRepositoryImpl;
import demo.main.repositories.UserRepositoryImpl;
import demo.main.services.BookService;
import demo.main.services.BookServiceImpl;
import demo.main.services.UserService;
import demo.main.services.UserServiceImpl;
import demo.main.views.AdminView;
import demo.main.xmlDataAccess.XMLFilePAth;
import org.xml.sax.SAXException;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by radu on 10.04.2017.
 */
public class AdminController extends AbstractController implements Observer {

    private AdminView adminView;
    private UserService userService;
    private BookService bookService;

    public AdminController(AdminView adminView) {
        this.adminView = adminView;
        this.userService = new UserServiceImpl(new UserRepositoryImpl(XMLFilePAth.userFilePath));
        this.bookService = new BookServiceImpl(new BookRepositoryImpl(XMLFilePAth.bookFilePath));

        adminView.addAdminWindowListener(new MyWindowListener());
        adminView.addUserListener(new UserListener());
        adminView.addBookListener(new BookListener());
        adminView.addBookTableListener(new BookListSelectionListener());
        adminView.addUserTableListener(new UserListSelectionListener());
    }

    @Override
    public void update(Observable o, Object arg) {
            adminView.updateBookTableData(bookService.getMappedBooks());
            adminView.updateUserTableData(userService.getMappedUsers());

    }


    class UserListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()){
                case "Read":
                    userService.getAllUsers();
                    adminView.updateUserTableData(userService.getMappedUsers());
                    break;
                case "Add":

                    break;
                case "Edit":

                    break;
                case "Delete":

                    break;
                default:
                    break;
            }
        }
    }


    class BookListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()){
                case "Read":
                    adminView.updateBookTableData(bookService.getMappedBooks());
                    break;
                case "Add":

                    break;
                case "Edit":

                    break;
                case "Delete":

                    break;
            }
        }
    }

    class UserListSelectionListener implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if(adminView.getSelectedUsername() != null){
                adminView.updateUserTableFields(userService.getMappedUserByUsername(adminView.getSelectedUsername()));
            }
        }
    }

    class BookListSelectionListener implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if(adminView.getSelectedBookName() != null){
                adminView.updateBookTableFields(bookService.getMappedBookByTitle(adminView.getSelectedBookName()));
            }
        }
    }
}
