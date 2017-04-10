package demo.main.views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowListener;

/**
 * Created by radu on 10.04.2017.
 */
public class EmployeeView {

    private Object[][] booksData = {{"...","...","...","...","..."}};
    private String[] booksColumnNames = {"Title","Author","Genre","Quantity","Price"};

    private JFrame frame = new JFrame("Store Administrator");
    private JComponent bookPanel = makeTextPanel();

    /**
     * private swing components for account GUI tab
     */
    private JLabel bookTitleLabel = new JLabel("Title: ");
    private JLabel bookAuthorLabel = new JLabel("Author: ");
    private JLabel bookGenreLabel = new JLabel("Genre: ");
    private JLabel bookQuantityLabel = new JLabel("Quantity: ");
    private JLabel bookPriceLabel = new JLabel("Price: ");

    private JTextField bookIdInput = new JTextField(30);
    private JTextField bookTitleInput = new JTextField(30);
    private JTextField bookAuthorInput = new JTextField(30);
    private JTextField bookGenreInput = new JTextField(30);
    private JTextField bookQuantityInput = new JTextField(30);
    private JTextField bookPriceInput = new JTextField(30);

    private JButton bookReadButton = new JButton("Display Books");
    private JButton bookEditButton = new JButton("Edit Book");
    private JButton bookAddButton = new JButton("Add Book");
    private JButton bookDeleteButton = new JButton("Delete Book");

    private JRadioButton searchByTitleLabel = new JRadioButton("Search by title: ");
    private JRadioButton searchByAuthorLabel = new JRadioButton("Search by author: ");
    private JRadioButton searchByGenreLabel = new JRadioButton("Search by genre: ");

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

        frame.add(bookSplitPanel);
        frame.setVisible(true);
    }


    /**
     * Listen to window specific events
     * @param windowListener
     */
    public void addEmployeeWindowListener(WindowListener windowListener) {
        frame.addWindowListener(windowListener);
    }



    private void setUpBookPanel() {
        bookTable.setModel(bookTableModel);
        bookSplitPanel.setDividerLocation(250);
        bookPanel.add();
    }


    /**
     * Set up panels
     * @return
     */
    protected JComponent makeTextPanel() {
        JPanel panel = new JPanel(false);
        panel.setLayout(new GridLayout(0, 2));
        return panel;
    }

}
