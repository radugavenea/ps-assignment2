package demo.main.repositories;

import demo.main.entities.Sell;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

/**
 * Created by radu on 12.04.2017.
 */
public interface SellRepository {
    List<Sell> getSells();
    Sell getSellById(int id);
    int addSell(Sell sell);
    int editSell(Sell sell);
    int deleteSellById(int id);
}
