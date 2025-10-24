import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Arrays;

public class gui {
    private JFrame frame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new gui().createLogin());
    }

    private void createLogin() {
        frame = new JFrame("Library Management System");
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel userLabel = new JLabel("Username");
        JTextField userField = new JTextField();

        JLabel passLabel = new JLabel("Password");
        JPasswordField passField = new JPasswordField();

        JButton ExitBtn = new JButton("Exit");
        JButton loginBtn = new JButton("Login");

        panel.add(userLabel);
        panel.add(userField);
        panel.add(passLabel);
        panel.add(passField);
        panel.add(ExitBtn);
        panel.add(loginBtn);

        frame.add(panel, BorderLayout.CENTER);

        ExitBtn.addActionListener(J -> {
            frame.dispose();
        });

        loginBtn.addActionListener(e -> {
            String id = userField.getText();
            String password = new String(passField.getPassword());

            if (id.equals("") && password.equals("")) {
                librarymanagmentSystem(id,0);
            } else {
                JOptionPane.showMessageDialog(frame, "invalid username or password", "login failed",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        frame.setVisible(true);
    }

    private void librarymanagmentSystem(String username, int tabIndex) {
        frame.getContentPane().removeAll();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());

        JLabel welcome = new JLabel("Welcome, " + username, SwingConstants.CENTER);
        welcome.setFont(new Font("SansSerif", Font.BOLD, 28));
        welcome.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        frame.add(welcome, BorderLayout.NORTH);

        // 🔹 Tabbed Pane for different sections
        JTabbedPane tabbedPane = new JTabbedPane();

        // === Dashboard Tab ===
        JPanel dashboardPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        dashboardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton addBook = new JButton("Add a Book");
        JButton searchbytitle = new JButton("Search Book by Title");
        JButton searchbyauthor = new JButton("Search Book by Author");
        JButton checkout = new JButton("Check Out Book");
        JButton returnbook = new JButton("Return Book");
        JButton display = new JButton("Display All Books");
        JButton reminders = new JButton("Send Return Reminders");
        JButton logout = new JButton("Log Out");

        dashboardPanel.add(addBook);
        dashboardPanel.add(searchbytitle);
        dashboardPanel.add(searchbyauthor);
        dashboardPanel.add(checkout);
        dashboardPanel.add(returnbook);
        dashboardPanel.add(display);
        dashboardPanel.add(reminders);
        dashboardPanel.add(logout);

        tabbedPane.addTab("Dashboard", dashboardPanel);

        // === Management Tab ===
        JPanel ManagementPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        ManagementPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton addCustomer = new JButton("Add a Customer");
        JButton addUser = new JButton("Add a User");
        JButton addAuthor = new JButton("Add a Author");
        JButton addPublisher = new JButton("Add a Publisher");
        JButton addcategories = new JButton("Add a Category");
        JButton telegramapi = new JButton("Telegram Api");
        JButton ui = new JButton("UI");
        JButton logouts = new JButton("Log Out");

        ManagementPanel.add(addCustomer);
        ManagementPanel.add(addUser);
        ManagementPanel.add(addAuthor);
        ManagementPanel.add(addPublisher);
        ManagementPanel.add(addcategories);
        ManagementPanel.add(telegramapi);
        ManagementPanel.add(ui);
        ManagementPanel.add(logouts);


        tabbedPane.addTab("Management", ManagementPanel);

        // === Reports panel ====

        JPanel reportspanel = new JPanel(new GridLayout(2, 2, 10, 10));
        reportspanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        tabbedPane.addTab("Reports", reportspanel);


        tabbedPane.setSelectedIndex(tabIndex);

        // === Add tabbedPane to frame ===
        frame.add(tabbedPane, BorderLayout.CENTER);

        // === Button actions ===
        logout.addActionListener(l -> {
            frame.dispose();
            createLogin();
        });

        addBook.addActionListener(j -> {
            addbook(username);
        });

        searchbytitle.addActionListener(e -> {
            SearchByTitle(username);
        });

        searchbyauthor.addActionListener(j -> {
            SearchByAuthor(username);
        });

        checkout.addActionListener(b -> {
            checkoutBook(username);
        });

        returnbook.addActionListener(g -> {
            returnBook(username);
        });

        display.addActionListener(v -> {
            displayAllBooks(username);
        });

        logout.addActionListener(l -> {
            frame.dispose();
            createLogin();
        });

        addCustomer.addActionListener(l -> {
            addcustomers(username);
        });

        addUser.addActionListener(l -> {
            adduser(username);
        });

        addAuthor.addActionListener(l -> {
            addauthor(username);

        });

        addPublisher.addActionListener(l -> {
            addpublisher(username);

        });

        addcategories.addActionListener(l -> {
            addcategory(username);

        });

        telegramapi.addActionListener(l -> {
            telegramapi(username);

        });

        frame.revalidate();
        frame.repaint();
    }

    private void addbook(String username) {
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("Add New Book", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        frame.add(title, BorderLayout.NORTH);

        JPanel wrapper = new JPanel(new GridBagLayout());
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Book Title
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel booktitlelabel = new JLabel("Book Title:");
        booktitlelabel.setHorizontalAlignment(SwingConstants.CENTER);
        form.add(booktitlelabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JTextField booktextfield = new JTextField(40);
        form.add(booktextfield, gbc);

        // Author (Dropdown)
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel authorlabel = new JLabel("Author:");
        authorlabel.setHorizontalAlignment(SwingConstants.CENTER);
        form.add(authorlabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JComboBox<String> authorDropdown = new JComboBox<>(new String[] {
                "Select Author", "J.K. Rowling", "George Orwell", "Stephen King", "Other..."
        });
        form.add(authorDropdown, gbc);

        // Publisher (Dropdown)
        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel publisherLabel = new JLabel("Publisher:");
        publisherLabel.setHorizontalAlignment(SwingConstants.CENTER);
        form.add(publisherLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        JComboBox<String> publisherDropdown = new JComboBox<>(new String[] {
                "Select Publisher", "Penguin Books", "HarperCollins", "O’Reilly", "Other..."
        });
        form.add(publisherDropdown, gbc);

        // ISBN
        gbc.gridx = 0;
        gbc.gridy = 6;
        JLabel isbnLabel = new JLabel("ISBN:");
        isbnLabel.setHorizontalAlignment(SwingConstants.CENTER);
        form.add(isbnLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;

        JPanel isbnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));

        JTextField isbnTextfield = new JTextField(35);
        JButton checkIsbnButton = new JButton("Check");

        isbnPanel.add(isbnTextfield);
        isbnPanel.add(checkIsbnButton);

        form.add(isbnPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        JLabel yearLabel = new JLabel("Year Published:");
        yearLabel.setHorizontalAlignment(SwingConstants.CENTER);
        form.add(yearLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;

        // Spinner for year selection
        int year = Year.now().getValue();
        SpinnerModel yearModel = new SpinnerNumberModel(year, 1500, year, 1);
        JSpinner yearSpinner = new JSpinner(yearModel);
        form.add(yearSpinner, gbc);

        // Category (Dropdown)
        gbc.gridx = 0;
        gbc.gridy = 10;
        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        form.add(categoryLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 11;
        JComboBox<String> categoryDropdown = new JComboBox<>(new String[] {
                "Select Category", "Fiction", "Science", "History", "Technology", "Other..."
        });
        form.add(categoryDropdown, gbc);

        // Copies
        gbc.gridx = 0;
        gbc.gridy = 12;
        JLabel copiesLabel = new JLabel("Number of Copies:");
        copiesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        form.add(copiesLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 13;
        JTextField copiesTextfield = new JTextField(40);
        form.add(copiesTextfield, gbc);

        // Add Book Button
        gbc.gridx = 0;
        gbc.gridy = 14;
        gbc.fill = GridBagConstraints.NONE;
        JButton addbookbutton = new JButton("Add Book");
        form.add(addbookbutton, gbc);

        wrapper.add(form);
        frame.add(wrapper, BorderLayout.CENTER);

        // Back button
        JButton back = new JButton("Back to Dashboard");
        back.addActionListener(j -> {
            librarymanagmentSystem(username, 0);
        });

        frame.add(back, BorderLayout.SOUTH);

        frame.revalidate();
        frame.repaint();
    }

    private void SearchByTitle(String username) {
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("Search by Title", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        frame.add(title, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel booktitlelabel = new JLabel("Book Title:");
        booktitlelabel.setHorizontalAlignment(SwingConstants.CENTER);
        form.add(booktitlelabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JTextField booktextfield = new JTextField(40);
        form.add(booktextfield, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        JButton searchBtn = new JButton("Search");
        form.add(searchBtn, gbc);

        JPanel formWrapper = new JPanel(new GridBagLayout());
        formWrapper.add(form);

        mainPanel.add(formWrapper);

        String[] columnNames = { "Title", "Author", "Availability" };
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable resultsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(resultsTable);

        mainPanel.add(scrollPane);

        frame.add(mainPanel, BorderLayout.CENTER);

        mainPanel.add(scrollPane);

        frame.add(mainPanel, BorderLayout.CENTER);

        searchBtn.addActionListener(e -> {
            tableModel.setRowCount(0);

            String searchText = booktextfield.getText().toLowerCase();

            if (searchText.contains("book")) {
                tableModel.addRow(new Object[] { "The Book of Dreams", "Author A", "Available" });
                tableModel.addRow(new Object[] { "A Good Book", "Author B", "Unavailable" });
                tableModel.addRow(new Object[] { "A Good Book", "Author B", "Unavailable" });
                tableModel.addRow(new Object[] { "A Good Book", "Author B", "Unavailable" });
                tableModel.addRow(new Object[] { "A Good Book", "Author B", "Unavailable" });
                tableModel.addRow(new Object[] { "A Good Book", "Author B", "Unavailable" });
                tableModel.addRow(new Object[] { "A Good Book", "Author B", "Unavailable" });
                tableModel.addRow(new Object[] { "A Good Book", "Author B", "Unavailable" });
                tableModel.addRow(new Object[] { "A Good Book", "Author B", "Unavailable" });
                tableModel.addRow(new Object[] { "A Good Book", "Author B", "Unavailable" });
                tableModel.addRow(new Object[] { "A Good Book", "Author B", "Unavailable" });
                tableModel.addRow(new Object[] { "A Good Book", "Author B", "Unavailable" });
                tableModel.addRow(new Object[] { "A Good Book", "Author B", "Unavailable" });
                tableModel.addRow(new Object[] { "A Good Book", "Author B", "Unavailable" });
                tableModel.addRow(new Object[] { "A Good Book", "Author B", "Unavailable" });
                tableModel.addRow(new Object[] { "A Good Book", "Author B", "Unavailable" });
                tableModel.addRow(new Object[] { "A Good Book", "Author B", "Unavailable" });
                tableModel.addRow(new Object[] { "A Good Book", "Author B", "Unavailable" });
                tableModel.addRow(new Object[] { "A Good Book", "Author B", "Unavailable" });
                tableModel.addRow(new Object[] { "A Good Book", "Author B", "Unavailable" });
                tableModel.addRow(new Object[] { "A Good Book", "Author B", "Unavailable" });
                tableModel.addRow(new Object[] { "A Good Book", "Author B", "Unavailable" });
                tableModel.addRow(new Object[] { "A Good Book", "Author B", "Unavailable" });
                tableModel.addRow(new Object[] { "A Good Book", "Author B", "Unavailable" });
                tableModel.addRow(new Object[] { "A Good Book", "Author B", "Unavailable" });
                tableModel.addRow(new Object[] { "A Good Book", "Author B", "Unavailable" });
                tableModel.addRow(new Object[] { "A Good Book", "Author B", "Unavailable" });
                tableModel.addRow(new Object[] { "A Good Book", "Author B", "Unavailable" });
                tableModel.addRow(new Object[] { "A Good Book", "Author B", "Unavailable" });
                tableModel.addRow(new Object[] { "A Good Book", "Author B", "Unavailable" });
                tableModel.addRow(new Object[] { "A Good Book", "Author B", "Unavailable" });
                tableModel.addRow(new Object[] { "A Good Book", "Author B", "Unavailable" });
                tableModel.addRow(new Object[] { "A Good Book", "Author B", "Unavailable" });
                tableModel.addRow(new Object[] { "A Good Book", "Author B", "Unavailable" });
                tableModel.addRow(new Object[] { "A Good Book", "Author B", "Unavailable" });
                tableModel.addRow(new Object[] { "A Good Book", "Author B", "Unavailable" });
                tableModel.addRow(new Object[] { "A Good Book", "Author B", "Unavailable" });
                tableModel.addRow(new Object[] { "A Good Book", "Author B", "Unavailable" });
                tableModel.addRow(new Object[] { "A Good Book", "Author B", "Unavailable" });
                tableModel.addRow(new Object[] { "A Good Book", "Author B", "Unavailable" });
            } else {
                tableModel.addRow(new Object[] { "No results found", "", "" });
            }
        });

        JButton back = new JButton("Back to Dashboard");
        back.addActionListener(j -> {
            librarymanagmentSystem(username, 0);
        });

        frame.add(back, BorderLayout.SOUTH);

        frame.revalidate();
        frame.repaint();
    }

    private void SearchByAuthor(String username) {
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("Search by Author", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        frame.add(title, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel authorlabel = new JLabel("Book Author:");
        authorlabel.setHorizontalAlignment(SwingConstants.CENTER);
        form.add(authorlabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JTextField authortextfield = new JTextField(40);
        form.add(authortextfield, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        JButton searchBtn = new JButton("Search");
        form.add(searchBtn, gbc);

        JPanel formWrapper = new JPanel(new GridBagLayout());
        formWrapper.add(form);

        mainPanel.add(formWrapper);

        String[] columnNames = { "Title", "Author", "Availability" };
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable resultsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(resultsTable);

        mainPanel.add(scrollPane);

        frame.add(mainPanel, BorderLayout.CENTER);

        mainPanel.add(scrollPane);

        frame.add(mainPanel, BorderLayout.CENTER);

        searchBtn.addActionListener(e -> {
            tableModel.setRowCount(0);

            String searchText = authortextfield.getText().toLowerCase();

            if (searchText.contains("andrew")) {
                tableModel.addRow(new Object[] { "The Book of Dreams", "andrew", "Available" });
            } else {
                tableModel.addRow(new Object[] { "No results found", "", "" });
            }
        });

        JButton back = new JButton("Back to Dashboard");
        back.addActionListener(j -> {
            librarymanagmentSystem(username, 0);
        });

        frame.add(back, BorderLayout.SOUTH);

        frame.revalidate();
        frame.repaint();
    }

    private void checkoutBook(String username) {
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("Check Out Book", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        frame.add(title, BorderLayout.NORTH);

        JPanel wrapper = new JPanel(new GridBagLayout());
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Member ComboBox (non-editable)
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel memberLabel = new JLabel("Member:");
        memberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        form.add(memberLabel, gbc);

        gbc.gridy = 1;
        JComboBox<String> memberBox = new JComboBox<>(new String[] { "John Doe", "Jane Smith", "Ali Ahmad" });
        memberBox.setEditable(false);
        form.add(memberBox, gbc);

        // Search Book
        gbc.gridy = 2;
        JLabel searchLabel = new JLabel("Search Book (Title or ISBN):");
        searchLabel.setHorizontalAlignment(SwingConstants.CENTER);
        form.add(searchLabel, gbc);

        gbc.gridy = 3;
        JTextField searchField = new JTextField(40);
        form.add(searchField, gbc);

        // Book ComboBox (non-editable)
        gbc.gridy = 4;
        JLabel bookLabel = new JLabel("Select Book:");
        bookLabel.setHorizontalAlignment(SwingConstants.CENTER);
        form.add(bookLabel, gbc);

        gbc.gridy = 5;
        JComboBox<String> bookBox = new JComboBox<>(new String[] {
                "978-3-16-148410-0 - The Book of Dreams",
                "978-0-14-028333-4 - A Good Book",
                "978-1-56619-909-4 - Another Book"
        });
        bookBox.setEditable(false);
        form.add(bookBox, gbc);

        // Search field action
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                updateBook();
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                updateBook();
            }

            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                updateBook();
            }

            private void updateBook() {
                String text = searchField.getText().toLowerCase();
                for (int i = 0; i < bookBox.getItemCount(); i++) {
                    String item = bookBox.getItemAt(i).toLowerCase();
                    if (item.contains(text)) {
                        bookBox.setSelectedIndex(i);
                        return;
                    }
                }
                bookBox.setSelectedIndex(-1); // no match
            }
        });

        // Checkout Date
        gbc.gridy = 6;
        JLabel checkoutLabel = new JLabel("Checkout Date:");
        checkoutLabel.setHorizontalAlignment(SwingConstants.CENTER);
        form.add(checkoutLabel, gbc);

        gbc.gridy = 7;
        JSpinner checkoutDate = new JSpinner(new SpinnerDateModel());
        form.add(checkoutDate, gbc);

        // Return Date
        gbc.gridy = 8;
        JLabel returnLabel = new JLabel("Return Date:");
        returnLabel.setHorizontalAlignment(SwingConstants.CENTER);
        form.add(returnLabel, gbc);

        gbc.gridy = 9;
        JSpinner returnDate = new JSpinner(new SpinnerDateModel());
        form.add(returnDate, gbc);

        // Checkout Button
        gbc.gridy = 10;
        gbc.fill = GridBagConstraints.NONE;
        JButton checkoutBtn = new JButton("Check Out");
        form.add(checkoutBtn, gbc);

        wrapper.add(form);
        frame.add(wrapper, BorderLayout.CENTER);

        // Back button
        JButton back = new JButton("Back to Dashboard");
        back.addActionListener(j -> librarymanagmentSystem(username, 0));
        frame.add(back, BorderLayout.SOUTH);

        frame.revalidate();
        frame.repaint();
    }

    private void returnBook(String username) {
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("Return Book", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        frame.add(title, BorderLayout.NORTH);

        JPanel wrapper = new JPanel(new GridBagLayout());
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Member ComboBox (non-editable)
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel memberLabel = new JLabel("Member:");
        memberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        form.add(memberLabel, gbc);

        gbc.gridy = 1;
        JComboBox<String> memberBox = new JComboBox<>(new String[] { "John Doe", "Jane Smith", "Ali Ahmad" });
        memberBox.setEditable(false);
        form.add(memberBox, gbc);

        // Books currently checked out by the selected member
        gbc.gridy = 2;
        JLabel bookLabel = new JLabel("Select Book to Return:");
        bookLabel.setHorizontalAlignment(SwingConstants.CENTER);
        form.add(bookLabel, gbc);

        gbc.gridy = 3;
        JComboBox<String> bookBox = new JComboBox<>();
        bookBox.setEditable(false);
        form.add(bookBox, gbc);

        // Populate books when member is selected
        memberBox.addActionListener(e -> {
            bookBox.removeAllItems();
            String member = (String) memberBox.getSelectedItem();

            // Example data: in real app, fetch from database
            if (member.equals("John Doe")) {
                bookBox.addItem("978-3-16-148410-0 - The Book of Dreams");
                bookBox.addItem("978-0-14-028333-4 - A Good Book");
            } else if (member.equals("Jane Smith")) {
                bookBox.addItem("978-1-56619-909-4 - Another Book");
            } else if (member.equals("Ali Ahmad")) {
                bookBox.addItem("978-0-262-13472-9 - Learning Java");
            }
        });
        memberBox.setSelectedIndex(0); // trigger initial population

        // Return Button
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        JButton returnBtn = new JButton("Return Book");
        form.add(returnBtn, gbc);

        returnBtn.addActionListener(e -> {
            String member = (String) memberBox.getSelectedItem();
            String book = (String) bookBox.getSelectedItem();

            if (book != null) {
                JOptionPane.showMessageDialog(frame,
                        "Book '" + book + "' has been successfully returned by " + member + ".",
                        "Return Successful",
                        JOptionPane.INFORMATION_MESSAGE);
                bookBox.removeItem(book); // remove returned book from ComboBox
            } else {
                JOptionPane.showMessageDialog(frame, "No book selected.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        wrapper.add(form);
        frame.add(wrapper, BorderLayout.CENTER);

        // Back button
        JButton back = new JButton("Back to Dashboard");
        back.addActionListener(j -> librarymanagmentSystem(username, 0));
        frame.add(back, BorderLayout.SOUTH);

        frame.revalidate();
        frame.repaint();
    }

    private void displayAllBooks(String username) {
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("All Books", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        frame.add(title, BorderLayout.NORTH);

        // Table columns
        String[] columnNames = { "Title", "Author", "ISBN", "Year", "Category", "Copies Available" };

        // Example data: in a real app, fetch from database
        Object[][] data = {
                { "The Book of Dreams", "Author A", "978-3-16-148410-0", "2020", "Fiction", 5 },
                { "A Good Book", "Author B", "978-0-14-028333-4", "2018", "Fiction", 2 },
                { "Learning Java", "Ali Ahmad", "978-0-262-13472-9", "2021", "Technology", 3 },
                { "Another Book", "Jane Smith", "978-1-56619-909-4", "2019", "Science", 4 }
        };

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // make table read-only
            }
        };

        JTable booksTable = new JTable(tableModel);
        booksTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(booksTable);

        frame.add(scrollPane, BorderLayout.CENTER);

        // Back button
        JButton back = new JButton("Back to Dashboard");
        back.addActionListener(j -> librarymanagmentSystem(username, 0));
        frame.add(back, BorderLayout.SOUTH);

        frame.revalidate();
        frame.repaint();
    }

    private void addcustomers(String username) {
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("Add Customer", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        frame.add(title, BorderLayout.NORTH);

        JPanel wrapper = new JPanel(new GridBagLayout());
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel Namelabel = new JLabel("Name:");
        Namelabel.setHorizontalAlignment(SwingConstants.CENTER);
        form.add(Namelabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JTextField Nametextfield = new JTextField(40);
        form.add(Nametextfield, gbc);

        // Surname
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel Surnamelabel = new JLabel("Surname:");
        Surnamelabel.setHorizontalAlignment(SwingConstants.CENTER);
        form.add(Surnamelabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JTextField Surnametextfield = new JTextField(40);
        form.add(Surnametextfield, gbc);

        // Phone Number
        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel NumberLabel = new JLabel("Phone Number:");
        NumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        form.add(NumberLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        JTextField Numbertextfield = new JTextField(40);
        form.add(Numbertextfield, gbc);

        // Age
        gbc.gridx = 0;
        gbc.gridy = 6;
        JLabel AgeLabel = new JLabel("Age:");
        AgeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        form.add(AgeLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        JSpinner ageSpinner = new JSpinner(new SpinnerNumberModel(18, 1, 120, 1));
        form.add(ageSpinner, gbc);

        // Add Customer Button
        gbc.gridx = 0;
        gbc.gridy = 14;
        gbc.fill = GridBagConstraints.NONE;
        JButton addcustomerbutton = new JButton("Add Customer");
        form.add(addcustomerbutton, gbc);

        wrapper.add(form);
        frame.add(wrapper, BorderLayout.CENTER);

        // Back button
        JButton back = new JButton("Back to Management");
        back.addActionListener(j -> librarymanagmentSystem(username, 1));
        frame.add(back, BorderLayout.SOUTH);

        frame.revalidate();
        frame.repaint();
    }

    private void adduser(String username) {
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("Add User", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        frame.add(title, BorderLayout.NORTH);

        JPanel wrapper = new JPanel(new GridBagLayout());
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel Namelabel = new JLabel("Name:");
        Namelabel.setHorizontalAlignment(SwingConstants.CENTER);
        form.add(Namelabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JTextField Nametextfield = new JTextField(40);
        form.add(Nametextfield, gbc);

        // Surname
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel Surnamelabel = new JLabel("Surname:");
        Surnamelabel.setHorizontalAlignment(SwingConstants.CENTER);
        form.add(Surnamelabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JTextField Surnametextfield = new JTextField(40);
        form.add(Surnametextfield, gbc);

        // Phone Number
        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel NumberLabel = new JLabel("Pin");
        NumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        form.add(NumberLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        JTextField Numbertextfield = new JTextField(40);
        form.add(Numbertextfield, gbc);


        // Add User Button
        gbc.gridx = 0;
        gbc.gridy = 14;
        gbc.fill = GridBagConstraints.NONE;
        JButton adduserbutton = new JButton("Add User");
        form.add(adduserbutton, gbc);

        wrapper.add(form);
        frame.add(wrapper, BorderLayout.CENTER);

        // Back button
        JButton back = new JButton("Back to Management");
        back.addActionListener(j -> librarymanagmentSystem(username, 1));
        frame.add(back, BorderLayout.SOUTH);

        frame.revalidate();
        frame.repaint();
    }

    private void addauthor(String username) {
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("Add Author", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        frame.add(title, BorderLayout.NORTH);

        JPanel wrapper = new JPanel(new GridBagLayout());
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel Namelabel = new JLabel("Name:");
        Namelabel.setHorizontalAlignment(SwingConstants.CENTER);
        form.add(Namelabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JTextField Nametextfield = new JTextField(40);
        form.add(Nametextfield, gbc);

        // Surname
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel Surnamelabel = new JLabel("Surname:");
        Surnamelabel.setHorizontalAlignment(SwingConstants.CENTER);
        form.add(Surnamelabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JTextField Surnametextfield = new JTextField(40);
        form.add(Surnametextfield, gbc);

        // Phone Number
        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel NumberLabel = new JLabel("Phone Number");
        NumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        form.add(NumberLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        JTextField Numbertextfield = new JTextField(40);
        form.add(Numbertextfield, gbc);

        // Age
        gbc.gridx = 0;
        gbc.gridy = 8;
        JLabel AgeLabel = new JLabel("Age");
        AgeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        form.add(AgeLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        JTextField agetextfield = new JTextField(40);
        form.add(agetextfield, gbc);


        // Add User Button
        gbc.gridx = 0;
        gbc.gridy = 14;
        gbc.fill = GridBagConstraints.NONE;
        JButton addauthorbutton = new JButton("Add Author");
        form.add(addauthorbutton, gbc);

        wrapper.add(form);
        frame.add(wrapper, BorderLayout.CENTER);

        // Back button
        JButton back = new JButton("Back to Management");
        back.addActionListener(j -> librarymanagmentSystem(username, 1));
        frame.add(back, BorderLayout.SOUTH);

        frame.revalidate();
        frame.repaint();
    }

    private void addpublisher(String username) {
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("Add Publisher", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        frame.add(title, BorderLayout.NORTH);

        JPanel wrapper = new JPanel(new GridBagLayout());
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel Namelabel = new JLabel("Name:");
        Namelabel.setHorizontalAlignment(SwingConstants.CENTER);
        form.add(Namelabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JTextField Nametextfield = new JTextField(40);
        form.add(Nametextfield, gbc);

        // Location
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel Locationlabel = new JLabel("Location:");
        Locationlabel.setHorizontalAlignment(SwingConstants.CENTER);
        form.add(Locationlabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JTextField Locationtextfield = new JTextField(40);
        form.add(Locationtextfield, gbc);

        // Phone Number
        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel NumberLabel = new JLabel("Phone Number");
        NumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        form.add(NumberLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        JTextField Numbertextfield = new JTextField(40);
        form.add(Numbertextfield, gbc);


        // Add User Button
        gbc.gridx = 0;
        gbc.gridy = 14;
        gbc.fill = GridBagConstraints.NONE;
        JButton addPublisherbutton = new JButton("Add Publisher");
        form.add(addPublisherbutton, gbc);

        wrapper.add(form);
        frame.add(wrapper, BorderLayout.CENTER);

        // Back button
        JButton back = new JButton("Back to Management");
        back.addActionListener(j -> librarymanagmentSystem(username, 1));
        frame.add(back, BorderLayout.SOUTH);

        frame.revalidate();
        frame.repaint();
    }

    private void addcategory(String username) {
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout(10, 10));

        // --- 1. Title ---
        JLabel title = new JLabel("Category Management", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        frame.add(title, BorderLayout.NORTH);

        // --- 2. Table Model and View ---
        String[] columnNames = {"Category Name", "Category ID"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable categoryTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(categoryTable);

        // Load initial data
        loadCategoryData(tableModel);

        // --- 3. Input Form Panel (WEST) ---
        JPanel formPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField nameTextField = new JTextField(20);
        JButton addButton = new JButton("Add Category");
        JButton deleteButton = new JButton("Delete Category");


        formPanel.add(new JLabel("Enter New Category Name:"));
        formPanel.add(nameTextField);
        formPanel.add(addButton);
        formPanel.add(deleteButton);


        // --- 4. Center Layout ---
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, formPanel, scrollPane);
        splitPane.setDividerLocation(250);
        splitPane.setResizeWeight(0.0);
        frame.add(splitPane, BorderLayout.CENTER);

        // --- 5. Back Button (SOUTH) ---
        JButton back = new JButton("Back to Management");
        back.addActionListener(e -> librarymanagmentSystem(username, 1));
        frame.add(back, BorderLayout.SOUTH);

        // --- 6. Action Listener for Dynamic Update ---
        addButton.addActionListener(e -> {
            String newCategoryName = nameTextField.getText().trim();
            if (!newCategoryName.isEmpty()) {
                // 1. Save and get the ID (Simulated)
                String newId = saveCategory(newCategoryName);

                // 2. Create the row data as an Object array
                Object[] rowData = {newCategoryName, newId};

                // 3. Add the new row to the table model (automatic UI update)
                tableModel.addRow(rowData);

                // 4. Clear input
                nameTextField.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "Category name can't be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        deleteButton.addActionListener(i -> {
            int selectedRow = categoryTable.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Please select a category to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String categoryId = (String) tableModel.getValueAt(selectedRow, 1);
            String categoryName = (String) tableModel.getValueAt(selectedRow, 0);

            int confirm = JOptionPane.showConfirmDialog(
                    frame,
                    "Are you sure you want to delete the category: " + categoryName + "?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                // 1. Delete from 'database' (Simulated)
                boolean success = deleteCategory(categoryId);

                if (success) {
                    // 2. Remove the row from the table model (automatic UI update)
                    // Note: Always remove from the table model using its index.
                    tableModel.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(frame, categoryName + " deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to delete category in database.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.revalidate();
        frame.repaint();
    }
    private boolean deleteCategory(String id) {
        // In a real application: Execute DELETE statement where ID = id
        System.out.println("Deleting category with ID: " + id);
        // Simulate successful deletion
        return true;
    }

    private void loadCategoryData(DefaultTableModel model) {
        model.setRowCount(0);
        List<Object[]> categories = fetchCategories();
        for (Object[] categoryRow : categories) {
            model.addRow(categoryRow);
        }
    }

    /** Fetches simulated categories from a 'database' using ArrayList and Object[]. */
    public List<Object[]> fetchCategories() {
        List<Object[]> data = new ArrayList<>();
        // Each element is an Object array representing a table row {Name, ID}
        data.add(new Object[]{"Fiction", "C001"});
        data.add(new Object[]{"Science", "C002"});
        data.add(new Object[]{"History", "C003"});
        return data;
    }

    /** Saves a new category to the 'database' and returns the new ID. */
    private String saveCategory(String name) {
        // Replace with actual database INSERT and ID retrieval.
        System.out.println("Saving new category: " + name);
        return "C" + new Random().nextInt(900) + 100; // Simulated 3-digit ID
    }


    private void telegramapi(String username) {
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout(10, 10));

        // --- 1. Title ---
        JLabel title = new JLabel("Telegram api", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        frame.add(title, BorderLayout.NORTH);

        // --- 2. Table Model and View ---
        String[] columnNames = {"api", "id"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable categoryTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(categoryTable);

        // Load initial data
        loadapi(tableModel);

        // --- 3. Input Form Panel (WEST) ---
        JPanel formPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField nameTextField = new JTextField(20);
        JButton addButton = new JButton("Add api");
        JButton deleteButton = new JButton("Delete api");

        formPanel.add(new JLabel("Enter New api:"));
        formPanel.add(nameTextField);
        formPanel.add(addButton);
        formPanel.add(deleteButton);

        // --- 4. Center Layout ---
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, formPanel, scrollPane);
        splitPane.setDividerLocation(250);
        splitPane.setResizeWeight(0.0);
        frame.add(splitPane, BorderLayout.CENTER);

        // --- 5. Back Button (SOUTH) ---
        JButton back = new JButton("Back to Management");
        back.addActionListener(e -> librarymanagmentSystem(username, 1));
        frame.add(back, BorderLayout.SOUTH);

        // --- 6. Action Listener for Dynamic Update ---
        addButton.addActionListener(e -> {
            String newapi = nameTextField.getText().trim();
            if (!newapi.isEmpty()) {
                // 1. Save and get the ID (Simulated)
                String newId = saveapi(newapi);

                // 2. Create the row data as an Object array
                Object[] rowData = {newapi, newId};

                // 3. Add the new row to the table model (automatic UI update)
                tableModel.addRow(rowData);

                // 4. Clear input
                nameTextField.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "api box can't be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.revalidate();
        frame.repaint();
    }

    private void loadapi(DefaultTableModel model) {
        model.setRowCount(0);
        List<Object[]> api = fetchapi();
        for (Object[] apiRow : api) {
            model.addRow(apiRow);
        }
    }

    /** Fetches simulated categories from a 'database' using ArrayList and Object[]. */
    private List<Object[]> fetchapi() {
        List<Object[]> data = new ArrayList<>();
        return data;
    }

    /** Saves a new category to the 'database' and returns the new ID. */
    private String saveapi(String name) {
        // Replace with actual database INSERT and ID retrieval.
        System.out.println("Saving new api: " + name);
        return "T" + new Random().nextInt(900) + 100; // Simulated 3-digit ID
    }



}