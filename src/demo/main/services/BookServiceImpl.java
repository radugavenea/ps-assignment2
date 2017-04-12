package demo.main.services;

import demo.main.entities.Book;
import demo.main.repositories.BookRepository;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

/**
 * Created by radu on 12.04.2017.
 */
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() throws ParserConfigurationException, SAXException, IOException {
        return bookRepository.getBooks();
    }

    @Override
    public Book getBookByTitle(String title) throws ParserConfigurationException, SAXException, IOException {
        return bookRepository.getBookByTitle(title);
    }

    @Override
    public int addBook(Book book) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        return bookRepository.addBook(book);
    }

    @Override
    public int editBook(Book book) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        return bookRepository.editBook(book);
    }

    @Override
    public int deleteBookById(int id) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        return bookRepository.deleteBookById(id);
    }
}
