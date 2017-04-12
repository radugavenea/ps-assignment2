package demo.main.repositories;

import demo.main.entities.Book;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

/**
 * Created by radu on 11.04.2017.
 */
public interface BookRepository {

    List<Book> getBooks();
    List<Book> getBooksBySearchType(String searchType, String searchText);
    Book getBookByTitle(String title);
    int addBook(Book book);
    int editBook(Book book);
    int deleteBookById(int id);
}
