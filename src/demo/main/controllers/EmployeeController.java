package demo.main.controllers;

import demo.main.repositories.BookRepositoryImpl;
import demo.main.services.BookService;
import demo.main.services.BookServiceImpl;
import demo.main.views.EmployeeView;
import demo.main.xmlDataAccess.XMLFilePAth;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by radu on 10.04.2017.
 */
public class EmployeeController extends AbstractController {

    private EmployeeView employeeView;
    private BookService bookService;
    private String searchType = "title"; // default search is by title

    public EmployeeController(EmployeeView employeeView){
        this.employeeView = employeeView;
        this.bookService = new BookServiceImpl(new BookRepositoryImpl(XMLFilePAth.bookFilePath));

        employeeView.addEmployeeWindowListener(new MyWindowListener());
        employeeView.addRadioButtonsListener(new RadioButtonListener());
        employeeView.addSearchButtonListener(new SearchButtonListener());
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

    class SearchButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            employeeView.updateBookTableData(bookService.getMappedBooks(bookService.getBooksBySearchType(searchType,employeeView.getSearchInput())));
        }
    }

}
