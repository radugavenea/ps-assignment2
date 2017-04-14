package demo.main.views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

/**
 * Created by radu on 10.04.2017.
 */
public class EmployeeView {

    private Object[][] booksData = {{"...","...","...","...","..."}};
    private String[] booksColumnNames = {"Title","Author","Genre","Quantity","Price"};

    private JFrame frame = new JFrame("Store Employee");
    private JComponent bookPanel = makeTextPanel();

    /**
     * private swing components for employee GUI
     */
    private JRadioButton searchByTitleButton = new JRadioButton("Search by title   ");
    private JRadioButton searchByAuthorButton = new JRadioButton("Search by author   ");
    private JRadioButton searchByGenreButton = new JRadioButton("Search by genre   ");
    private JTextField searchInput = new JTextField(30);
    private JButton searchButton = new JButton("Search");

    private JTable bookTable = new JTable();
    private JPanel bookButtonPanel = new JPanel();
    private JScrollPane bookTableSP = new JScrollPane(bookTable);
    private JSplitPane insideBookSplitPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, bookPanel, bookButtonPanel);
    private JSplitPane bookSplitPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, bookTableSP, insideBookSplitPanel);

    private DefaultTableModel bookTableModel = new DefaultTableModel(booksData, booksColumnNames){
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };

    private JLabel infoMessage = new JLabel("Please select a book from the list above and the quantity in the field below");
    private JLabel blank = new JLabel("                                ");
    private JLabel blank2 = new JLabel("                                          ");
    private JLabel blank3 = new JLabel("                                                                                                                                                                                     ");
    private JLabel bookQuantityLabel = new JLabel("Quantity: ");
    private JTextField bookQuantityInput = new JTextField(30);
    private JButton sellButton = new JButton("Sell");

    /**
     * The actual constructor of the Employee GUI
     */
    public EmployeeView(){

        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setSize(new Dimension(800,600));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((dim.width - frame.getWidth())/2, (dim.height - frame.getHeight())/2);
        frame.setResizable(false);

        setUpBookPanel();
        setUpBookSellPanel();

        frame.add(bookSplitPanel);
        frame.setVisible(true);
    }

    /**
     * setters and getters
     * @return
     */
    public String getSearchInput() {
        return searchInput.getText();
    }
    public void setSearchInput(String searchText) {
        searchInput.setText(searchText);
    }
    public String getBookQuantityInput(){return bookQuantityInput.getText();}
    public void setBookQuantityInput(String quantityInput){bookQuantityInput.setText(quantityInput);}



    public void makeSearchByTitleSelected(){
        searchByTitleButton.setSelected(true);
        searchByAuthorButton.setSelected(false);
        searchByGenreButton.setSelected(false);
    }

    public void makeSearchByAuthorSelected(){
        searchByTitleButton.setSelected(false);
        searchByAuthorButton.setSelected(true);
        searchByGenreButton.setSelected(false);
    }

    public void makeSearchByGenreSelected(){
        searchByTitleButton.setSelected(false);
        searchByAuthorButton.setSelected(false);
        searchByGenreButton.setSelected(true);
    }

    public void updateBookTableData(Object[][] booksData){
        this.booksData = booksData;
        bookTableModel.setDataVector(booksData,booksColumnNames);
        bookTableModel.fireTableDataChanged();
    }


    public String getSelectedBookTitle(){
        int row = bookTable.getSelectedRow();
        return row > -1 ? bookTable.getValueAt(row,0).toString() : null;
    }


    /**
     * Listen to employee window specific events
     * @param windowListener
     */
    public void addEmployeeWindowListener(WindowListener windowListener) {
        frame.addWindowListener(windowListener);
    }

    public void addRadioButtonsListener(ActionListener listener){
        searchByTitleButton.addActionListener(listener);
        searchByTitleButton.setActionCommand("title");
        searchByAuthorButton.addActionListener(listener);
        searchByAuthorButton.setActionCommand("author");
        searchByGenreButton.addActionListener(listener);
        searchByGenreButton.setActionCommand("genre");
    }

    public void addEmployeeButtonListener(ActionListener listener){
        searchButton.addActionListener(listener);
        searchButton.setActionCommand("search");
        sellButton.addActionListener(listener);
        sellButton.setActionCommand("sell");
    }

    //////////////////// private methods ///////////////////////

    private void setUpBookPanel() {
        bookTable.setModel(bookTableModel);
        bookSplitPanel.setDividerLocation(250);
        insideBookSplitPanel.setDividerLocation(80);
        searchByTitleButton.setSelected(true);
        bookPanel.add(searchByTitleButton);
        bookPanel.add(searchByAuthorButton);
        bookPanel.add(searchByGenreButton);
        bookPanel.add(searchInput);
        bookPanel.add(searchButton);
    }

    private void setUpBookSellPanel(){
        bookButtonPanel.add(blank);
        bookButtonPanel.add(infoMessage);
        bookButtonPanel.add(blank2);
        bookButtonPanel.add(blank3);
        bookQuantityInput.setText("1");
        bookButtonPanel.add(bookQuantityLabel);
        bookQuantityInput.setPreferredSize(new Dimension(2,30));
        bookButtonPanel.add(bookQuantityInput);
        bookButtonPanel.add(sellButton);
    }


    /**
     * Set up panels
     * @return
     */
    protected JComponent makeTextPanel() {
        JPanel panel = new JPanel(false);
//        panel.setLayout(new GridLayout(0, 2));
        return panel;
    }

}
