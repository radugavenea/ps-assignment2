package demo.main.controllers;

import com.itextpdf.text.DocumentException;
import demo.main.entities.Book;
import demo.main.entities.User;
import demo.main.repositories.BookRepositoryImpl;
import demo.main.repositories.SellRepositoryImpl;
import demo.main.repositories.UserRepositoryImpl;
import demo.main.services.*;
import demo.main.views.AdminView;
import demo.main.xmlDataAccess.XMLFilePAth;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by radu on 10.04.2017.
 */
public class AdminController extends AbstractController implements Observer {

    private AdminView adminView;
    private UserService userService;
    private BookService bookService;
    private SellService sellService;

    public AdminController(AdminView adminView) {
        this.adminView = adminView;
        this.userService = new UserServiceImpl(new UserRepositoryImpl(XMLFilePAth.userFilePath));
        this.bookService = new BookServiceImpl(new BookRepositoryImpl(XMLFilePAth.bookFilePath));
        this.sellService = new SellServiceImpl(new SellRepositoryImpl(XMLFilePAth.sellFilePath));

        userService.addObserver(this);
        bookService.addObserver(this);

        adminView.addAdminWindowListener(new MyWindowListener());
        adminView.addUserButtonsListener(new UserListener());
        adminView.addBookButtonsListener(new BookListener());
        adminView.addBookTableListener(new BookListSelectionListener());
        adminView.addUserTableListener(new UserListSelectionListener());
        adminView.addFileGeneratorListener(new FileGeneratorListener());
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
                    userService.addUser(new User(userService.getIncrementedUserId(),adminView.getUserRoleInput(),
                            adminView.getUserUsernameInput(),"root",
                            adminView.getUserNameInput()));
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
                    bookService.addBook(new Book(bookService.getIncrementedBookId(),adminView.getBookTitleInput(),adminView.getBookAuthorInput(),
                            adminView.getBookGenreInput(),Integer.parseInt(adminView.getBookQuantityInput()),
                            Float.parseFloat(adminView.getBookPriceInput())));
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

    class FileGeneratorListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()){
                case "pdf":
                    ReportGenerator pdfGenerator = new PdfGenerator();
                    try {
                        pdfGenerator.generateReport(sellService.getTopTenBestsellers());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (DocumentException e1) {
                        e1.printStackTrace();
                    }
                    break;
                case "csv":
                    ReportGenerator csvGenerator = new CsvGenerator();
                    try {
                        csvGenerator.generateReport(sellService.getTopTenBestsellers());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (DocumentException e1) {
                        e1.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
