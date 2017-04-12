package demo.main.controllers;

import demo.main.entities.Book;
import demo.main.entities.User;
import demo.main.repositories.BookRepositoryImpl;
import demo.main.repositories.UserRepositoryImpl;
import demo.main.services.BookService;
import demo.main.services.BookServiceImpl;
import demo.main.services.UserService;
import demo.main.services.UserServiceImpl;
import demo.main.views.AdminView;
import demo.main.xmlDataAccess.XMLFilePAth;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by radu on 10.04.2017.
 */
public class AdminController extends AbstractController implements Observer {

    private AdminView adminView;
    private UserService userService;// = new UserServiceImpl(new UserRepositoryImpl(XMLFilePAth.userFilePath));
    private BookService bookService;

    public AdminController(AdminView adminView) {
        this.adminView = adminView;
        this.userService = new UserServiceImpl(new UserRepositoryImpl(XMLFilePAth.userFilePath));
        this.bookService = new BookServiceImpl(new BookRepositoryImpl(XMLFilePAth.bookFilePath));

        userService.addObserver(this);
        bookService.addObserver(this);

        adminView.addAdminWindowListener(new MyWindowListener());
        adminView.addUserButtonsListener(new UserListener());
        adminView.addBookButtonsListener(new BookListener());
        adminView.addBookTableListener(new BookListSelectionListener());
        adminView.addUserTableListener(new UserListSelectionListener());
    }

    @Override
    public void update(Observable observable, Object arg) {
        adminView.updateBookTableData(bookService.getMappedBooks(bookService.getAllBooks()));
        adminView.updateUserTableData(userService.getMappedUsers());
    }


    class UserListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()){
                case "read":
                    userService.getAllUsers();
                    adminView.updateUserTableData(userService.getMappedUsers());
                    break;
                case "add":
                    //id increment should be implemented
//                    userService.addUser(new User(100,adminView.getUserRoleInput(),
//                            adminView.getUserUsernameInput(),"root",
//                            adminView.getUserNameInput()));
                    break;
                case "edit":
                    userService.editUser(new User(Integer.parseInt(adminView.getUserIdInput()),adminView.getUserRoleInput(),
                            adminView.getUserUsernameInput(),userService.getUserByUsername(adminView.getSelectedUsername()).getPassword(),
                            adminView.getUserNameInput()));
                    break;
                case "delete":
                    userService.deleteUserById(Integer.parseInt(adminView.getUserIdInput()));
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
                case "read":
                    adminView.updateBookTableData(bookService.getMappedBooks(bookService.getAllBooks()));
                    break;
                case "add":
                    // id increment should be implemented
//                    bookService.addBook(new Book(100,adminView.getBookTitleInput(),adminView.getBookAuthorInput(),
//                            adminView.getBookGenreInput(),Integer.parseInt(adminView.getBookQuantityInput()),
//                            Float.parseFloat(adminView.getBookPriceInput())));
                    break;
                case "edit":
                    bookService.editBook(new Book(Integer.parseInt(adminView.getBookIdInput()),
                            adminView.getBookTitleInput(),adminView.getBookAuthorInput(),adminView.getBookGenreInput(),
                            Integer.parseInt(adminView.getBookQuantityInput()),Float.parseFloat(adminView.getBookPriceInput())));
                    break;
                case "delete":
                    bookService.deleteBookById(Integer.parseInt(adminView.getBookIdInput()));
                    break;
                default:
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
