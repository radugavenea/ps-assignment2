package demo.main.repositories;

import demo.main.entities.Book;
import demo.main.xmlDataAccess.XMLDataAccess;
import demo.main.xmlDataAccess.XMLFilePAth;
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
    private String xmlFile;

    public BookRepositoryImpl(String xmlFile) {
        this.xmlDataAccess = new XMLDataAccess();
        this.xmlFile = xmlFile;
    }

    @Override
    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return !books.isEmpty() ? books : null;
    }

    @Override
    public List<Book> getBooksBySearchType(String searchType, String searchText) {
        List<Book> books = new ArrayList<>();
        try {
            document = xmlDataAccess.getXMLDocumentElement(xmlFile);
            NodeList bookNodeList = document.getElementsByTagName("book");
            for(int i=0; i<bookNodeList.getLength(); i++){
                Node bookNode = bookNodeList.item(i);
                if(bookNode.getNodeType() == Node.ELEMENT_NODE){
                    Book book = new Book();
                    Element bookElement = (Element) bookNode;
                    if(bookElement.getElementsByTagName(searchType).item(0).getTextContent().equals(searchText)){
                        book.setId(Integer.parseInt(bookElement.getAttribute("id")));
                        book.setTitle(bookElement.getElementsByTagName("title").item(0).getTextContent());
                        book.setAuthor(bookElement.getElementsByTagName("author").item(0).getTextContent());
                        book.setGenre(bookElement.getElementsByTagName("genre").item(0).getTextContent());
                        book.setQuantity(Integer.parseInt(bookElement.getElementsByTagName("quantity").item(0).getTextContent()));
                        book.setPrice(Float.parseFloat(bookElement.getElementsByTagName("price").item(0).getTextContent()));
                        books.add(book);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return !books.isEmpty() ? books : null;
    }

    @Override
    public Book getBookByTitle(String title) {
        Book book = null;
        try {
            document = xmlDataAccess.getXMLDocumentElement(xmlFile);
            NodeList bookNodeList = document.getElementsByTagName("book");
            for(int i=0; i<bookNodeList.getLength(); i++){
                Node bookNode = bookNodeList.item(i);
                if(bookNode.getNodeType() == Node.ELEMENT_NODE){
                    Element bookElement = (Element) bookNode;
                    if(bookElement.getElementsByTagName("title").item(0).getTextContent().equals(title)){
                        book = new Book();
                        book.setId(Integer.parseInt(bookElement.getAttribute("id")));
                        book.setTitle(bookElement.getElementsByTagName("title").item(0).getTextContent());
                        book.setAuthor(bookElement.getElementsByTagName("author").item(0).getTextContent());
                        book.setGenre(bookElement.getElementsByTagName("genre").item(0).getTextContent());
                        book.setQuantity(Integer.parseInt(bookElement.getElementsByTagName("quantity").item(0).getTextContent()));
                        book.setPrice(Float.parseFloat(bookElement.getElementsByTagName("price").item(0).getTextContent()));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return book;
    }

    @Override
    public int addBook(Book book) {
        try {
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

            xmlDataAccess.setUpXMLTransformerWriter(document,xmlFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        return book.getId();
    }

    @Override
    public int editBook(Book book) {
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        return book.getId();
    }

    @Override
    public int deleteBookById(int id)  {
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return id;
    }
}
