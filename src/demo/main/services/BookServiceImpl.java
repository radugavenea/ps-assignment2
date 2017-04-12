package demo.main.services;

import demo.main.entities.Book;
import demo.main.repositories.BookRepository;
import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by radu on 12.04.2017.
 */
public class BookServiceImpl extends Observable implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks()  {
        return bookRepository.getBooks();
    }

    @Override
    public Book getBookByTitle(String title)  {
        return bookRepository.getBookByTitle(title);
    }

    @Override
    public int addBook(Book book)  {
        return bookRepository.addBook(book);
    }

    @Override
    public int editBook(Book book)  {
        return bookRepository.editBook(book);
    }

    @Override
    public int deleteBookById(int id)  {
        return bookRepository.deleteBookById(id);
    }

    @Override
    public Object[][] getMappedBooks() {
        List<Book> books = bookRepository.getBooks();
        Object[][] bookObjects = new Object[books.size()][5];
        for(int i=0; i<books.size(); i++){
            bookObjects[i][0] = books.get(i).getTitle();
            bookObjects[i][1] = books.get(i).getAuthor();
            bookObjects[i][2] = books.get(i).getGenre();
            bookObjects[i][3] = books.get(i).getQuantity();
            bookObjects[i][4] = books.get(i).getPrice();
        }
        return bookObjects;
    }

    @Override
    public ArrayList<String> getMappedBookByTitle(String selectedBookName) {
        ArrayList<String> bookFields = new ArrayList<>();
        Book book = bookRepository.getBookByTitle(selectedBookName);
        bookFields.add(Integer.toString(book.getId()));
        bookFields.add(book.getTitle());
        bookFields.add(book.getAuthor());
        bookFields.add(book.getGenre());
        bookFields.add(Integer.toString(book.getQuantity()));
        bookFields.add(Float.toString(book.getPrice()));
        return bookFields;
    }
}
