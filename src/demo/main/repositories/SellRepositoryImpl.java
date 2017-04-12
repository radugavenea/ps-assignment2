package demo.main.repositories;

import demo.main.entities.Sell;
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
 * Created by radu on 12.04.2017.
 */
public class SellRepositoryImpl implements SellRepository {

    private XMLDataAccess xmlDataAccess;
    private Document document;
    private String xmlFile;

    public SellRepositoryImpl(String xmlFile) {
        this.xmlDataAccess = new XMLDataAccess();
        this.xmlFile = xmlFile;
    }

    @Override
    public List<Sell> getSells() throws ParserConfigurationException, SAXException, IOException {
        List<Sell> sells = new ArrayList<>();
        document = xmlDataAccess.getXMLDocumentElement(xmlFile);

        NodeList sellNodeList = document.getElementsByTagName("sell");
        for(int i=0; i<sellNodeList.getLength(); i++){
            Sell sell = new Sell();
            Node sellNode = sellNodeList.item(i);
            if(sellNode.getNodeType() == Node.ELEMENT_NODE){
                Element sellElement = (Element) sellNode;
                sell.setId(Integer.parseInt(sellElement.getAttribute("id")));
                sell.setBookId(Integer.parseInt(sellElement.getElementsByTagName("bookid").item(0).getTextContent()));
                sell.setQuantity(Integer.parseInt(sellElement.getElementsByTagName("quantity").item(0).getTextContent()));
                sells.add(sell);
            }
        }

        return !sells.isEmpty() ? sells : null;
    }

    @Override
    public Sell getSellById(int id) throws ParserConfigurationException, SAXException, IOException {
        Sell sell = null;
        document = xmlDataAccess.getXMLDocumentElement(xmlFile);

        NodeList sellNodeList = document.getElementsByTagName("sell");
        for(int i=0; i<sellNodeList.getLength(); i++){
            Node sellNode = sellNodeList.item(i);
            if(sellNode.getNodeType() == Node.ELEMENT_NODE){
                Element sellElement = (Element) sellNode;
                if(sellElement.getAttribute("id").equals(Integer.toString(id))){
                    sell = new Sell();
                    sell.setId(Integer.parseInt(sellElement.getAttribute("id")));
                    sell.setBookId(Integer.parseInt(sellElement.getElementsByTagName("bookid").item(0).getTextContent()));
                    sell.setQuantity(Integer.parseInt(sellElement.getElementsByTagName("quantity").item(0).getTextContent()));
                }
            }
        }

        return sell;
    }

    @Override
    public int addSell(Sell sell) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        document = xmlDataAccess.getXMLDocumentElement(xmlFile);

        Element sellRoot = document.getDocumentElement();
        Element sellElement = document.createElement("sell");
        Attr sellAttr = document.createAttribute("id");
        Element bookIdElement = document.createElement("bookid");
        Element quantityElement = document.createElement("quantity");

        sellAttr.setValue(Integer.toString(sell.getId()));
        bookIdElement.appendChild(document.createTextNode(Integer.toString(sell.getBookId())));
        quantityElement.appendChild(document.createTextNode(Integer.toString(sell.getQuantity())));

        sellRoot.appendChild(sellElement);
        sellElement.setAttributeNode(sellAttr);
        sellElement.appendChild(bookIdElement);
        sellElement.appendChild(quantityElement);

        xmlDataAccess.setUpXMLTransformerWriter(document,xmlFile);
        return sell.getId();
    }

    @Override
    public int editSell(Sell sell) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        document = xmlDataAccess.getXMLDocumentElement(xmlFile);

        NodeList sellNodeList = document.getElementsByTagName("sell");
        for(int i=0; i<sellNodeList.getLength(); i++){
            Node sellNode = sellNodeList.item(i);
            if(sellNode.getNodeType() == Node.ELEMENT_NODE){
                Element sellElement = (Element) sellNode;
                if(sellElement.getAttribute("id").equals(Integer.toString(sell.getId()))){
                    sellElement.getElementsByTagName("bookid").item(0).setTextContent(Integer.toString(sell.getBookId()));
                    sellElement.getElementsByTagName("quantity").item(0).setTextContent(Integer.toString(sell.getQuantity()));
                }
            }
        }

        xmlDataAccess.setUpXMLTransformerWriter(document,xmlFile);
        return sell.getId();
    }

    @Override
    public int deleteSellById(int id) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        document = xmlDataAccess.getXMLDocumentElement(xmlFile);

        Node sells = document.getFirstChild();
        Node sellNode = document.getElementsByTagName("sells").item(0);
        NodeList sellChildList = sellNode.getChildNodes();
        for(int i=0; i<sellChildList.getLength(); i++){
            Node node = sellChildList.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE){
                Element sell = (Element) node;
                if(sell.getAttribute("id").equals(Integer.toString(id))){
                    sells.removeChild(sell);
                }
            }
        }

        xmlDataAccess.setUpXMLTransformerWriter(document,xmlFile);
        return id;
    }
}
