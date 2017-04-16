package demo.main.services;

import demo.main.entities.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * Created by radu on 12.04.2017.
 */
public interface BookService {
    List<Book> getAllBooks();
    List<Book> getBooksBySearchType(String searchType, String searchText);
    Book getBookByTitle(String title);
    int addBook(Book book);
    int editBook(Book book);
    int deleteBookById(int id);
    Object[][] getMappedBooks(List<Book> books);
    ArrayList<String> getMappedBookByTitle(String selectedBookName);
    void adjustStock(Book book, int quantity);
    int getIncrementedBookId();
    void addObserver(Observer o);
}
