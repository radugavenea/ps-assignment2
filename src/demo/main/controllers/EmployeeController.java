package demo.main.controllers;

import demo.main.repositories.BookRepositoryImpl;
import demo.main.repositories.SellRepositoryImpl;
import demo.main.services.BookService;
import demo.main.services.BookServiceImpl;
import demo.main.services.SellService;
import demo.main.services.SellServiceImpl;
import demo.main.views.EmployeeView;
import demo.main.xmlDataAccess.XMLFilePAth;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by radu on 10.04.2017.
 */
public class EmployeeController extends AbstractController implements Observer {

    private EmployeeView employeeView;
    private BookService bookService;
    private SellService sellService;
    private String searchType = "title"; // default search is by title

    public EmployeeController(EmployeeView employeeView){
        this.employeeView = employeeView;
        this.bookService = new BookServiceImpl(new BookRepositoryImpl(XMLFilePAth.bookFilePath));
        this.sellService = new SellServiceImpl(new SellRepositoryImpl(XMLFilePAth.sellFilePath));

        bookService.addObserver(this);

        employeeView.addEmployeeWindowListener(new MyWindowListener());
        employeeView.addRadioButtonsListener(new RadioButtonListener());
        employeeView.addEmployeeButtonListener(new EmployeeButtonListener());
    }

    @Override
    public void update(Observable observable, Object arg) {
        employeeView.updateBookTableData(bookService.getMappedBooks(bookService.getBooksBySearchType(searchType,employeeView.getSearchInput())));
    }


    class RadioButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()){
                case "title":
                    employeeView.makeSearchByTitleSelected();
                    searchType = "title";
                    break;
                case "author":
                    employeeView.makeSearchByAuthorSelected();
                    searchType = "author";
                    break;
                case "genre":
                    employeeView.makeSearchByGenreSelected();
                    searchType = "genre";
                    break;
                default:
                    break;
            }
        }
    }

    class EmployeeButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()){
                case "search":
                    employeeView.updateBookTableData(bookService.getMappedBooks(bookService.getBooksBySearchType(searchType,employeeView.getSearchInput())));
                    break;
                case "sell":
                    sellService.makeSell(bookService.getBookByTitle(employeeView.getSelectedBookTitle()).getId(),
                            Integer.parseInt(employeeView.getBookQuantityInput()));
                    bookService.adjustStock(bookService.getBookByTitle(employeeView.getSelectedBookTitle()),
                            Integer.parseInt(employeeView.getBookQuantityInput()));
                    break;
                default:
                    break;
            }
        }
    }

}
