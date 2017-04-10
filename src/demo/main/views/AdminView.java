package demo.main.views;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

/**
 * Created by radu on 10.04.2017.
 */
public class AdminView {

    private Object[][] usersData = {{"...","...","..."}};
    private String[] usersColumnNames = {"Username","Role","Name"};
    private Object[][] booksData = {{"...","...","...","...","..."}};
    private String[] booksColumnNames = {"Title","Author","Genre","Quantity","Price"};

    private JFrame frame = new JFrame("Store Administrator");
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JComponent userPanel = makeTextPanel();
    private JComponent bookPanel = makeTextPanel();
    private JComponent reportPanel = makeTextPanel();

    /**
     * private swing components for user GUI tab
     */
    private JLabel userIdLabel = new JLabel("User id: ");
    private JLabel userUsernameLabel = new JLabel("User username: ");
    private JLabel userRoleLabel = new JLabel("User role: ");
    private JLabel userNameLabel = new JLabel("User name: ");

    private JTextField userIdInput = new JTextField(30);
    private JTextField userUsernameInput = new JTextField(30);
    private JTextField userRoleInput = new JTextField(30);
    private JTextField userNameInput = new JTextField(30);

    private JButton userReadButton = new JButton("Display Users");
    private JButton userAddButton = new JButton("Add User");
    private JButton userEditButton = new JButton("Edit User");
    private JButton userDeleteButton = new JButton("Delete User");

    private JTable userTable = new JTable();
    private JScrollPane tableSP = new JScrollPane(userTable);
    private JPanel userButtonPanel = new JPanel();
    private JSplitPane insideSplitPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, userPanel, userButtonPanel);
    private JSplitPane userSplitPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT,tableSP,insideSplitPanel);

    private DefaultTableModel userTableModel = new DefaultTableModel(usersData, usersColumnNames){
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };


    /**
     * private swing components for account GUI tab
     */
    private JLabel bookIdLabel = new JLabel("Book id: ");
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

    private JTable bookTable = new JTable();
    private JScrollPane bookTableSP = new JScrollPane(bookTable);
    private JPanel bookButtonPanel = new JPanel();
    private JSplitPane insideBookSplitPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, bookPanel, bookButtonPanel);
    private JSplitPane bookSplitPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, bookTableSP, insideBookSplitPanel);

    private DefaultTableModel bookTableModel = new DefaultTableModel(booksData, booksColumnNames){
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };

    /**
     * private swing components for report GUI tab
     */



    /**
     * The ClerkView constructor
     * @throws HeadlessException
     */
    public AdminView() throws HeadlessException {

        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setSize(800,600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((dim.width-frame.getWidth())/2, (dim.height-frame.getHeight())/2);
        frame.setResizable(false);

        setUpUserPanel();
        setUpBookPanel();
        setUpReportPanel();

        tabbedPane.add("Users", userSplitPanel);
        tabbedPane.add("Books", bookSplitPanel);
        tabbedPane.add("Reports",reportPanel);

        frame.add(tabbedPane);
        frame.setVisible(true);
    }


    /**
     * setters and getters for text inputs
     */
    public String getUserIdInput() {
        return userIdInput.getText();
    }
    public void setUserIdInput(String clientId) {
        userIdInput.setText(clientId);
    }
    public String getUserUsernameInput() {
        return userUsernameInput.getText();
    }
    public void setUserUsernameInput(String clientName) {
        userUsernameInput.setText(clientName);
    }
    public String getUserRoleInput() {
        return userRoleInput.getText();
    }
    public void setUserRoleInput(String clientIdentityCard) {
        userRoleInput.setText(clientIdentityCard);
    }
    public String getUserNameInput() {
        return userNameInput.getText();
    }
    public void setUserNameInput(String numericalCode) {
        userNameInput.setText(numericalCode);
    }
    public String getBookIdInput() {
        return bookIdInput.getText();
    }
    public void setBookIdInput(String accountId) {
        bookIdInput.setText(accountId);
    }
    public String getBookTitleInput() {
        return bookTitleInput.getText();
    }
    public void setBookTitleInput(String accountIdentificationNumber) {
        bookTitleInput.setText(accountIdentificationNumber);
    }
    public String getBookAuthorInput() {
        return bookAuthorInput.getText();
    }
    public void setBookAuthorInput(String accountCurrency) {
        bookAuthorInput.setText(accountCurrency);
    }
    public String getBookGenreInput() {
        return bookGenreInput.getText();
    }
    public void setBookGenreInput(String accountBalance) {
        bookGenreInput.setText(accountBalance);
    }
    public String getBookQuantityInput() {
        return bookQuantityInput.getText();
    }
    public void setBookQuantityInput(String accountDate) {
        bookQuantityInput.setText(accountDate);
    }

    /**
     * Listeners
     */
    public void addAdminWindowListener(WindowListener adminWindowListener){
        frame.addWindowListener(adminWindowListener);
    }

    public void addUserListener(ActionListener listener){
        userReadButton.addActionListener(listener);
        userReadButton.setActionCommand("Read users");
        userAddButton.addActionListener(listener);
        userAddButton.setActionCommand("Add user");
        userEditButton.addActionListener(listener);
        userEditButton.setActionCommand("Edit user");
        userDeleteButton.addActionListener(listener);
        userDeleteButton.setActionCommand("Delete user");
    }

    public void addBookListener(ActionListener listener){
        bookReadButton.addActionListener(listener);
        bookReadButton.setActionCommand("Read books");
        bookAddButton.addActionListener(listener);
        bookAddButton.setActionCommand("Add book");
        bookEditButton.addActionListener(listener);
        bookEditButton.setActionCommand("Edit book");
        bookDeleteButton.addActionListener(listener);
        bookDeleteButton.setActionCommand("Delete book");
    }

    public void addUserTableListener(ListSelectionListener listSelectionListener){
        userTable.getSelectionModel().addListSelectionListener(listSelectionListener);
    }

    public void addBookTableListener(ListSelectionListener listSelectionListener){
        bookTable.getSelectionModel().addListSelectionListener(listSelectionListener);
    }


    /**
     * Public methods to update view based on model changes
     * @param usersData
     */
    public void updateUserTableData(Object[][] usersData){
        this.usersData = usersData;
        userTableModel.setDataVector(usersData, usersColumnNames);
        userTableModel.fireTableDataChanged();
    }
    public void updateBookTableData(Object[][] booksData){
        this.booksData = booksData;
        bookTableModel.setDataVector(booksData, booksColumnNames);
        bookTableModel.fireTableDataChanged();
    }


    public void updateUserTableFields(String[] userArray){
        bookIdInput.setText(userArray[0]);
        bookTitleInput.setText(userArray[1]);
        bookAuthorInput.setText(userArray[2]);
        bookGenreInput.setText(userArray[3]);
        bookQuantityInput.setText(userArray[4]);
    }

    public void updateBookTableFields(String[] bookArray){
        userIdInput.setText(bookArray[0]);
        userUsernameInput.setText(bookArray[1]);
        userRoleInput.setText(bookArray[2]);
        userNameInput.setText(bookArray[3]);
    }

    /**
     * Method gets the username of selected client from the userTable
     * @return String if row is selected, null otherwise
     */
    public String getSelectedEmployeeUsername(){
        int row = userTable.getSelectedRow();
        return row >= 0 ? userTable.getValueAt(row,0).toString() : null;
    }

    public String getSelectedBookName(){
        int row = bookTable.getSelectedRow();
        return row > -1 ? bookTable.getValueAt(row,0).toString() : null;
    }

//////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////// private zone ////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////


    /**
     *  Sets up the User Tab
     */
    private void setUpUserPanel() {
        userTable.setModel(userTableModel);
        userSplitPanel.setDividerLocation(300);

        userIdInput.setEditable(false);
        userPanel.add(userIdLabel);
        userPanel.add(userIdInput);
        userPanel.add(userUsernameLabel);
        userPanel.add(userUsernameInput);
        userPanel.add(userRoleLabel);
        userPanel.add(userRoleInput);
        userPanel.add(userNameLabel);
        userPanel.add(userNameInput);

        userButtonPanel.add(userReadButton);
        userButtonPanel.add(userAddButton);
        userButtonPanel.add(userEditButton);
        userButtonPanel.add(userDeleteButton);

    }

    /**
     * Set up the Book Tab
     */
    private void setUpBookPanel() {
        bookTable.setModel(bookTableModel);
        bookSplitPanel.setDividerLocation(300);

        bookIdInput.setEditable(false);
        bookPanel.add(bookIdLabel);
        bookPanel.add(bookIdInput);
        bookPanel.add(bookTitleLabel);
        bookPanel.add(bookTitleInput);
        bookPanel.add(bookAuthorLabel);
        bookPanel.add(bookAuthorInput);
        bookPanel.add(bookGenreLabel);
        bookPanel.add(bookGenreInput);
        bookPanel.add(bookQuantityLabel);
        bookPanel.add(bookQuantityInput);
        bookPanel.add(bookPriceLabel);
        bookPanel.add(bookPriceInput);

        bookButtonPanel.add(bookReadButton);
        bookButtonPanel.add(bookAddButton);
        bookButtonPanel.add(bookEditButton);
        bookButtonPanel.add(bookDeleteButton);
    }

    private void setUpReportPanel(){

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
