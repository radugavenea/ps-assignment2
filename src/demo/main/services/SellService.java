package demo.main.services;

import demo.main.entities.Sell;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

/**
 * Created by radu on 12.04.2017.
 */
public interface SellService {
    List<Sell> getAllSells() throws ParserConfigurationException, SAXException, IOException;
    Sell getSellById(int id) throws ParserConfigurationException, SAXException, IOException;
    int addSell(Sell sell) throws ParserConfigurationException, SAXException, IOException, TransformerException;
    int editSell(Sell sell) throws ParserConfigurationException, SAXException, IOException, TransformerException;
    int deleteSellById(int id) throws ParserConfigurationException, SAXException, IOException;

}
