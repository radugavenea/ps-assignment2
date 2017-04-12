package demo.main.repositories;

import demo.main.entities.Book;
import demo.main.xmlDataAccess.XMLDataAccess;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by radu on 11.04.2017.
 */
public class BookRepositoryImpl implements BookRepository {

    private XMLDataAccess xmlDataAccess;
    private Document document;
    private String xmlFile = "books.xml";

    public BookRepositoryImpl() {
        this.xmlDataAccess = new XMLDataAccess();
    }

    @Override
    public List<Book> getBooks() throws ParserConfigurationException, SAXException, IOException {
        List<Book> books = new ArrayList<>();
        document = xmlDataAccess.getXMLDocumentElement(xmlFile);

        NodeList bookNodeList = document.getElementsByTagName("book");
        for(int i=0; i<bookNodeList.getLength(); i++){
            Book book = new Book();
            Node bookNode = bookNodeList.item(i);
            if(bookNode.getNodeType() == Node.ELEMENT_NODE){
                Element bookElement = (Element) bookNode;
                book.setId(Integer.parseInt(bookElement.getAttribute("id")));
                book.setTitle(bookElement.getElementsByTagName("title").item(0).getTextContent());
                book.setAuthor(bookElement.getElementsByTagName("author").item(0).getTextContent());
                book.setGenre(bookElement.getElementsByTagName("genre").item(0).getTextContent());
                book.setQuantity(Integer.parseInt(bookElement.getElementsByTagName("quantity").item(0).getTextContent()));
                book.setPrice(Float.parseFloat(bookElement.getElementsByTagName("price").item(0).getTextContent()));
                books.add(book);
            }
        }

        return !books.isEmpty() ? books : null;
    }

    @Override
    public Book getBookByTitle(String title) throws ParserConfigurationException, SAXException, IOException {
        Book book = null;
        document = xmlDataAccess.getXMLDocumentElement(xmlFile);

        NodeList bookNodeList = document.getElementsByTagName("book");
        for(int i=0; i<bookNodeList.getLength(); i++){
            Node bookNode = bookNodeList.item(i);
            if(bookNode.getNodeType() == Node.ELEMENT_NODE){
                Element bookElement = (Element) bookNode;
                if(bookElement.getAttribute("title").equals(title)){
                    book = new Book();
                    book.setId(Integer.parseInt(bookElement.getElementsByTagName("id").item(0).getTextContent()));
                    book.setTitle(bookElement.getElementsByTagName("title").item(0).getTextContent());
                    book.setAuthor(bookElement.getElementsByTagName("author").item(0).getTextContent());
                    book.setGenre(bookElement.getElementsByTagName("genre").item(0).getTextContent());
                    book.setQuantity(Integer.parseInt(bookElement.getElementsByTagName("quantity").item(0).getTextContent()));
                    book.setPrice(Float.parseFloat(bookElement.getElementsByTagName("price").item(0).getTextContent()));
                }
            }
        }

        return book;
    }

    @Override
    public int addBook(Book book) throws ParserConfigurationException, SAXException, IOException {
        document = xmlDataAccess.getXMLDocumentElement(xmlFile);

        Element bookRoot = document.getDocumentElement();
        Element bookElement = document.createElement("book");
        Attr bookIdAttr = document.createAttribute("id");
        Element bookTitle = document.createElement("title");
        Element bookAuthor = document.createElement("author");
        Element bookGenre = document.createElement("genre");
        Element bookQuantity = document.createElement("quantity");
        Element bookPrice = document.createElement("price");

        bookTitle.appendChild(document.createTextNode(book.getTitle()));
        bookAuthor.appendChild(document.createTextNode(book.getAuthor()));
        bookGenre.appendChild(document.createTextNode(book.getGenre()));
        bookQuantity.appendChild(document.createTextNode(Integer.toString(book.getQuantity())));
        bookPrice.appendChild(document.createTextNode(Float.toString(book.getPrice())));

        bookRoot.appendChild(bookElement);
        bookIdAttr.setValue(Integer.toString(book.getId()));
        bookElement.setAttributeNode(bookIdAttr);
        bookElement.appendChild(bookTitle);
        bookElement.appendChild(bookAuthor);
        bookElement.appendChild(bookGenre);
        bookElement.appendChild(bookQuantity);
        bookElement.appendChild(bookPrice);

        return book.getId();    // validate before return id
    }

    @Override
    public int editBook(Book book) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        document = xmlDataAccess.getXMLDocumentElement(xmlFile);

        NodeList bookNodeList = document.getElementsByTagName("book");
        for(int i=0; i<bookNodeList.getLength(); i++) {
            Node bookNode = bookNodeList.item(i);
            if(bookNode.getNodeType() == Node.ELEMENT_NODE){
                Element bookElement = (Element) bookNode;
                if(bookElement.getAttribute("id").equals(Integer.toString(book.getId()))){
                    bookElement.getElementsByTagName("title").item(0).setTextContent(book.getTitle());
                    bookElement.getElementsByTagName("author").item(0).setTextContent(book.getAuthor());
                    bookElement.getElementsByTagName("genre").item(0).setTextContent(book.getGenre());
                    bookElement.getElementsByTagName("quantity").item(0).setTextContent(Integer.toString(book.getQuantity()));
                    bookElement.getElementsByTagName("price").item(0).setTextContent(Float.toString(book.getPrice()));
                }
            }
        }

        xmlDataAccess.setUpXMLTransformerWriter(document, xmlFile);
        return book.getId();        // it has to be validated before or something
    }

    @Override
    public int deleteBookById(int id) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        document = xmlDataAccess.getXMLDocumentElement(xmlFile);

        Node books = document.getFirstChild();
        Node bookNodes = document.getElementsByTagName("books").item(0);
        NodeList bookChildNodes = bookNodes.getChildNodes();
        for(int i=0; i<bookChildNodes.getLength(); i++){
            Node book = bookChildNodes.item(i);
            if(book.getNodeType() == Node.ELEMENT_NODE){
                Element bookElement = (Element) book;
                if(bookElement.getAttribute("id").equals(Integer.toString(id))){
                    books.removeChild(book);
                }
            }
        }

        xmlDataAccess.setUpXMLTransformerWriter(document,xmlFile);
        return id;      // if it was deleted
    }
}
