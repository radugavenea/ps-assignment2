package demo.main.services;

import demo.main.entities.Sell;
import demo.main.repositories.SellRepository;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

/**
 * Created by radu on 12.04.2017.
 */
public class SellServiceImpl implements SellService {

    private SellRepository sellRepository;

    public SellServiceImpl(SellRepository sellRepository) {
        this.sellRepository = sellRepository;
    }

    @Override
    public List<Sell> getAllSells() throws ParserConfigurationException, SAXException, IOException {
        return sellRepository.getSells();
    }

    @Override
    public Sell getSellById(int id) throws ParserConfigurationException, SAXException, IOException {
        return sellRepository.getSellById(id);
    }

    @Override
    public int addSell(Sell sell) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        return sellRepository.addSell(sell);
    }

    @Override
    public int editSell(Sell sell) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        return sellRepository.editSell(sell);
    }

    @Override
    public int deleteSellById(int id) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        return sellRepository.deleteSellById(id);
    }
}
