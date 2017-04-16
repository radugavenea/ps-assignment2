package demo.main.services;

import demo.main.entities.Sell;
import demo.main.repositories.SellRepository;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.*;

/**
 * Created by radu on 12.04.2017.
 */
public class SellServiceImpl extends Observable implements SellService {

    private SellRepository sellRepository;

    public SellServiceImpl(SellRepository sellRepository) {
        this.sellRepository = sellRepository;
    }

    @Override
    public List<Sell> getAllSells() {
        return sellRepository.getSells();
    }

    @Override
    public Sell getSellById(int id) {
        return sellRepository.getSellById(id);
    }

    @Override
    public int addSell(Sell sell) {
        return sellRepository.addSell(sell);
    }

    @Override
    public int editSell(Sell sell) {
        return sellRepository.editSell(sell);
    }

    @Override
    public int deleteSellById(int id) {
        return sellRepository.deleteSellById(id);
    }

    @Override
    public void makeSell(int id, int bookId, int quantity) {
        Sell sell = new Sell(id,bookId,quantity);
        sellRepository.addSell(sell);
    }

    /**
     * get new available id for xml records
     * @return 0 if there are no sells and last record id + 1 otherwise
     */
    @Override
    public int getIncrementedSellId() {
        List<Sell> sells = sellRepository.getSells();
        return sells == null ? 0 : sells.get(sells.size() - 1).getId() + 1;
    }

    @Override
    public List<Sell> getTopTenBestsellers() {
        List<Sell> sells = sellRepository.getSells();
        Collections.sort(sells, (a,b) -> a.getQuantity() < b.getQuantity() ? -1 : a.getQuantity() == b.getQuantity() ? 0 : 1);
//        Collections.sort(sells, new SellComparator());
        Collections.reverse(sells);
        if(sells == null) return null;
        if(sells.size() < 10) return sells;
        else{
            return sells.subList(0,9);
        }
    }


    class SellComparator implements Comparator<Sell> {

        @Override
        public int compare(Sell firstSell, Sell secondSell) {
            Integer first = firstSell.getQuantity();
            Integer second = secondSell.getQuantity();
            return first.compareTo(second);
        }
    }
}
