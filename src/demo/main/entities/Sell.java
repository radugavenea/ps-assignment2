package demo.main.entities;

/**
 * Created by radu on 10.04.2017.
 */
public class Sell {

    private int id;
    private int bookId;
    private int quantity;

    public Sell(){}

    public Sell(int id, int bookId, int quantity) {
        this.id = id;
        this.bookId = bookId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getBookId() {
        return bookId;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
