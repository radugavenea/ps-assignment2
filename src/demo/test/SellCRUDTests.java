package demo.test;

import demo.main.entities.Sell;
import demo.main.repositories.SellRepositoryImpl;
import demo.main.services.SellService;
import demo.main.services.SellServiceImpl;
import demo.main.xmlDataAccess.XMLDataAccess;
import demo.main.xmlDataAccess.XMLFilePAth;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

/**
 * Created by radu on 12.04.2017.
 */
public class SellCRUDTests {

    private XMLDataAccess xmlDataAccess = new XMLDataAccess();
    private Document document;
    private SellService sellService = new SellServiceImpl(new SellRepositoryImpl(XMLFilePAth.sellTestFilePath));

    @Before
    public void init() throws ParserConfigurationException, SAXException, IOException, TransformerException {
        document = xmlDataAccess.getXMLDocumentElement(XMLFilePAth.sellTestFilePath);
        sellService.addSell(new Sell(1,1,12));
    }

    @Test
    public void getAllSellTest() throws IOException, SAXException, ParserConfigurationException {
            assert sellService.getAllSells().size() == 1;
    }

    @Test
    public void getSellById() throws IOException, SAXException, ParserConfigurationException {
        assert sellService.getSellById(1).getBookId() == 1;
    }

    @Test
    public void addSellTest() throws ParserConfigurationException, TransformerException, SAXException, IOException {
        assert sellService.getAllSells().size() == 1;
        sellService.addSell(new Sell(2,1,47));
        assert sellService.getAllSells().size() == 2;
    }

    @Test
    public void editSellTest() throws IOException, SAXException, ParserConfigurationException, TransformerException {
        assert Integer.toString(sellService.getSellById(1).getQuantity()).equals("12");
        Sell sell = sellService.getSellById(1);
        sell.setQuantity(123);
        sellService.editSell(sell);
        assert Integer.toString(sellService.getSellById(1).getQuantity()).equals("123");
    }

    @Test
    public void deleteSellTest() throws ParserConfigurationException, TransformerException, SAXException, IOException {
        assert  sellService.getAllSells().size() == 1;
        sellService.deleteSellById(1);
        assert sellService.getAllSells() == null;
    }

    @After
    public void tearDown() throws ParserConfigurationException, IOException, SAXException, TransformerException {
        sellService.deleteSellById(1);
        sellService.deleteSellById(2);
        xmlDataAccess.setUpXMLTransformerWriter(document,XMLFilePAth.sellTestFilePath);
    }

}
