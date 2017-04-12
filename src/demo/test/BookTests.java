package demo.test;

import demo.main.entities.Book;
import demo.main.repositories.BookRepositoryImpl;
import demo.main.services.BookService;
import demo.main.services.BookServiceImpl;
import demo.main.xmlDataAccess.XMLDataAccess;
import demo.main.xmlDataAccess.XMLFilePAth;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;

/**
 * Created by radu on 12.04.2017.
 */
public class BookTests {

    private XMLDataAccess xmlDataAccess = new XMLDataAccess();
    private Document document;
    private BookService bookService = new BookServiceImpl(new BookRepositoryImpl(XMLFilePAth.bookTestFilePath));

    @Before
    public void init() throws ParserConfigurationException, SAXException, IOException, TransformerException {
        document = xmlDataAccess.getXMLDocumentElement(XMLFilePAth.bookFilePath);
        bookService.addBook(new Book(1,"Femei","Charles Bukowski","fictiune",12,new Float(23.5)));
    }

    @Test
    public void getAllBooksTest() throws IOException, SAXException, ParserConfigurationException {
        assert bookService.getAllBooks().size() == 1;
    }

    @Test
    public void getBookByTitleTest() throws IOException, SAXException, ParserConfigurationException {
        assert Integer.toString(bookService.getBookByTitle("Femei").getId()).equals("1");
    }

    @Test
    public void addBookTest() throws ParserConfigurationException, TransformerException, SAXException, IOException {
        assert bookService.getAllBooks().size() == 1;
        bookService.addBook(new Book(2,"Imaparatul mustelor", "William Golding", "fictiune",234,new Float(29.95)));
        assert bookService.getAllBooks().size() == 2;
    }

    @Test
    public void editBookTest() throws IOException, SAXException, ParserConfigurationException, TransformerException {
        assert Float.toString(bookService.getBookByTitle("Femei").getPrice()).equals("23.5");
        Book book = bookService.getBookByTitle("Femei");
        book.setPrice(123);
        bookService.editBook(book);
        assert Float.toString(bookService.getBookByTitle("Femei").getPrice()).equals("123.0");
    }

    @Test
    public void deleteBookTest() throws ParserConfigurationException, TransformerException, SAXException, IOException {
        assert  bookService.getAllBooks().size() == 1;
        bookService.deleteBookById(1);
        assert bookService.getAllBooks() == null;
    }

    @After
    public void tearDown() throws ParserConfigurationException, IOException, SAXException, TransformerException {
        bookService.deleteBookById(1);
        bookService.deleteBookById(2);
        xmlDataAccess.setUpXMLTransformerWriter(document,XMLFilePAth.bookTestFilePath);
    }

}
