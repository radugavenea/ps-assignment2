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

    List<Book> getBooks() throws ParserConfigurationException, SAXException, IOException;
    Book getBookByTitle(String title) throws ParserConfigurationException, SAXException, IOException;
    int addBook(Book book) throws ParserConfigurationException, SAXException, IOException, TransformerException;
    int editBook(Book book) throws ParserConfigurationException, SAXException, IOException, TransformerException;
    int deleteBookById(int id) throws ParserConfigurationException, SAXException, IOException, TransformerException;
}
