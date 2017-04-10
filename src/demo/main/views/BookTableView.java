package demo.main.views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Created by radu on 10.04.2017.
 */
public class BookTableView {

    private Object[][] booksData = {{"...","...","...","...","..."}};
    private String[] booksColumnNames = {"Title","Author","Genre","Quantity","Price"};

    private JPanel bookPanel = new JPanel();
    private JTable bookTable = new JTable();
    private JScrollPane bookScroll = new JScrollPane(bookTable);
//    private JPanel bookButtonPanel = new JPanel();
//    private JSplitPane insideAccountSplitPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, bookPanel, bookButtonPanel);
//    private JSplitPane bookSplitPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, bookTableSP,insideAccountSplitPanel);

    private DefaultTableModel bookTableModel = new DefaultTableModel(booksData, booksColumnNames){
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };

    public BookTableView() throws HeadlessException {




    }
}
