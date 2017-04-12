package demo.main.services;

import demo.main.entities.Book;
import demo.main.entities.Sell;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by radu on 12.04.2017.
 */
public interface BookService {
    List<Book> getAllBooks();
    Book getBookByTitle(String title);
    int addBook(Book book);
    int editBook(Book book);
    int deleteBookById(int id);
    Object[][] getMappedBooks();
    ArrayList<String> getMappedBookByTitle(String selectedBookName);
}
