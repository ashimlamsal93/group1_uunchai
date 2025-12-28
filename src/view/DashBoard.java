/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import model.UserSession;
import model.FlightModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;


public class DashBoard extends javax.swing.JFrame {
    private static final Logger logger = Logger.getLogger(DashBoard.class.getName());
    private String currentView = "flights";
    private UserSession userSession = UserSession.getInstance();
    private List<String> notifications = new ArrayList<>();
    private List<String> paymentHistory = new ArrayList<>();
    private List<String> bookingHistory = new ArrayList<>();
    private BookFlightCallback bookFlightCallback;
    private List<FlightModel> flightList;
    
     public interface BookFlightCallback {
        void onBookFlight(int flightIndex);
    }
    
      public void setupUI() {
        // Initialize your UI components here
        // This is called from the controller
    }
   public void setUserInfo(String info) {
        // Update a label to show user info
        // Example: userLabel.setText(info);
    }
    
    public void displayStats(String[] stats) {
        // Display dashboard statistics
        // Example: 
        // totalBookingsLabel.setText(stats[0]);
        // totalSpentLabel.setText("$" + stats[1]);
        // upcomingFlightsLabel.setText(stats[2]);
        // pendingPaymentsLabel.setText(stats[3]);
    }
    
    public void displayFlights(List<FlightModel> flights) {
        this.flightList = flights;
        // Display flights in your UI
        // Update flight display components
    }
    
    public void showDashboardPanel() {
        // Show dashboard panel and hide others
        // Example: dashboardPanel.setVisible(true);
        //          flightsPanel.setVisible(false);
    }
    
    public void showFlightsPanel() {
        // Show flights panel and hide others
        // Example: dashboardPanel.setVisible(false);
        //          flightsPanel.setVisible(true);
    }
    
    public void addDashboardMenuListener(MouseListener listener) {
        // Example: dashBoardLabel.addMouseListener(listener);
    }
    
    public void addFlightsMenuListener(MouseListener listener) {
        // Example: flightsLabel.addMouseListener(listener);
    }
    
    public void addLogoutMenuListener(MouseListener listener) {
        // Example: logoutLabel.addMouseListener(listener);
    }
    
    public void addSearchButtonListener(ActionListener listener) {
        // Example: searchButton.addActionListener(listener);
    }
    
    public void setBookFlightCallback(BookFlightCallback callback) {
        this.bookFlightCallback = callback;
    }
    
    public JTextField getSourceField() {
        // Return your source text field
        // Example: return sourceTextField;
        return null; // Replace with actual field
    }
    
    public JTextField getDestinationField() {
        // Return your destination text field
        // Example: return destinationTextField;
        return null; // Replace with actual field
    }
    
    public JTextField getDateField() {
        // Return your date text field
        // Example: return dateTextField;
        return null; // Replace with actual field
    }
    
    // Method to be called when book button is clicked
    public void onBookButtonClicked(int flightIndex) {
        if (bookFlightCallback != null) {
            bookFlightCallback.onBookFlight(flightIndex);
        }
    }
    
    public DashBoard() {
        initComponents();
        setDefaultFlightData();
        initSidebarNavigation();
        setupMenuHoverEffects();
        setupFlightButtonListeners();
        initializeSampleData();
        setLocationRelativeTo(null);
        setTitle("Flight Booking Dashboard - " + userSession.getUserEmail());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        logger.info("Dashboard initialized for user: " + userSession.getUserEmail());
    }
    
    private void initializeSampleData() {
        // Sample notifications
        notifications.add("Flight KA101 to Pokhara confirmed for tomorrow");
        notifications.add("Payment of 45,000 RS for booking BK00122 successful");
        notifications.add("Check-in for flight DL202 opens in 24 hours");
        notifications.add("New flight offers to Mustang available");
        notifications.add("Your feedback has been received. Thank you!");
        notifications.add("Profile update completed successfully");
        notifications.add("Baggage checked in for flight NA303");
        notifications.add("You've earned 250 loyalty points!");
        
        // Sample payment history
        paymentHistory.add("2024-01-15 - BK00123 - 40,000 RS - Credit Card - Completed");
        paymentHistory.add("2024-01-10 - BK00122 - 45,000 RS - Digital Wallet - Completed");
        paymentHistory.add("2024-01-05 - BK00121 - 30,000 RS - Debit Card - Completed");
        paymentHistory.add("2024-01-02 - BK00120 - 50,000 RS - Cash - Pending");
        
        // Sample booking history
        bookingHistory.add("BK00123 - KA101 - Kathmandu to Pokhara - 2 Passengers - 40,000 RS - Confirmed");
        bookingHistory.add("BK00122 - KA102 - Kathmandu to Janakpur - 1 Passenger - 45,000 RS - Completed");
        bookingHistory.add("BK00121 - KA103 - Lumbini to Kathmandu - 3 Passengers - 30,000 RS - Completed");
    }
     // ============ SIDEBAR NAVIGATION ============
    private void initSidebarNavigation() {
        dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                showDashboardView();
            }
        });
        
        flights.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                showFlightsView();
            }
        });
        
        bagdetails.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                showBaggageDetails();
            }
        });
        
        payment.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                showPaymentsView();
            }
        });
        
        feedback.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                showFeedbackView();
            }
        });
        
        notification.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                showNotificationsView();
            }
        });
        
        userprofile.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                showProfileView();
            }
        });
        
        settings.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                showSettingsView();
            }
        });
    }
    
    private void setupMenuHoverEffects() {
        JLabel[] menuItems = {dashboard, flights, bagdetails, payment, 
                             feedback, notification, userprofile, settings};
        
        for (JLabel item : menuItems) {
            item.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    if (!item.getBackground().equals(new Color(0, 102, 204))) {
                        item.setBackground(new Color(0, 122, 204));
                        item.setForeground(Color.WHITE);
                        item.setOpaque(true);
                    }
                }
                
                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    if (!item.getBackground().equals(new Color(0, 102, 204))) {
                        item.setBackground(new Color(0, 153, 255));
                        item.setForeground(Color.BLACK);
                        item.setOpaque(true);
                    }
                }
            });
        }
    }
    
    private void updateActiveMenuItem(JLabel activeItem) {
        JLabel[] menuItems = {dashboard, flights, bagdetails, payment, 
                             feedback, notification, userprofile, settings};
        
        for (JLabel item : menuItems) {
            item.setBackground(new Color(0, 153, 255));
            item.setOpaque(true);
            item.setForeground(Color.BLACK);
        }
        
        activeItem.setBackground(new Color(0, 102, 204));
        activeItem.setForeground(Color.WHITE);
    }
    
    // ============ VIEW METHODS ============
    private void showDashboardView() {
        currentView = "dashboard";
        updateActiveMenuItem(dashboard);
        hideFlightPanels();
        showDashboardContent();
        logger.info("Dashboard view loaded");
    }
    
    private void showFlightsView() {
        currentView = "flights";
        updateActiveMenuItem(flights);
        showFlightPanels();
        hideDashboardContent();
        searchflight.setText("Search Flights");
        searchflight.setEditable(true);
        logger.info("Flights view loaded");
    }
    
    private void showBaggageDetails() {
        currentView = "baggage";
        updateActiveMenuItem(bagdetails);
        hideFlightPanels();
        showBaggageDetailsPanel();
        logger.info("Baggage details view loaded");
    }
    
    private void showPaymentsView() {
        currentView = "payments";
        updateActiveMenuItem(payment);
        hideFlightPanels();
        showPaymentsPanel();
        logger.info("Payments view loaded");
    }
    
    private void showFeedbackView() {
        currentView = "feedback";
        updateActiveMenuItem(feedback);
        hideFlightPanels();
        showFeedbackPanel();
        logger.info("Feedback view loaded");
    }
    
    private void showNotificationsView() {
        currentView = "notifications";
        updateActiveMenuItem(notification);
        hideFlightPanels();
        showNotificationsPanel();
        logger.info("Notifications view loaded");
    }
    
    private void showProfileView() {
        currentView = "profile";
        updateActiveMenuItem(userprofile);
        hideFlightPanels();
        showProfilePanel();
        logger.info("Profile view loaded");
    }
    
    private void showSettingsView() {
        currentView = "settings";
        updateActiveMenuItem(settings);
        hideFlightPanels();
        showSettingsPanel();
        logger.info("Settings view loaded");
    }
    
    // ============ PANEL VISIBILITY METHODS ============
    private void hideFlightPanels() {
        jPanel5.setVisible(false);
        jPanel7.setVisible(false);
        jPanel8.setVisible(false);
        jPanel6.setVisible(false);
        mountainflights.setVisible(false);
        roundtrip.setVisible(false);
        oneway.setVisible(false);
        multicity.setVisible(false);
    }
    
    private void showFlightPanels() {
        jPanel5.setVisible(true);
        jPanel7.setVisible(true);
        jPanel8.setVisible(true);
        jPanel6.setVisible(true);
        mountainflights.setVisible(true);
        roundtrip.setVisible(true);
        oneway.setVisible(true);
        multicity.setVisible(true);
    }
    
    private void showDashboardContent() {
        String welcomeMessage = String.format(
            "Welcome %s!\n\n" +
            "Dashboard Overview:\n" +
            "• Email: %s\n" +
            "• Status: Active\n" +
            "• Notifications: %d unread\n" +
            "• Recent Bookings: %d\n" +
            "• Loyalty Points: 1,250\n\n" +
            "Quick Actions:\n" +
            "1. Book a new flight\n" +
            "2. Check baggage details\n" +
            "3. View payment history\n" +
            "4. Update profile settings",
            userSession.getUserEmail(),
            notifications.size(),
            bookingHistory.size()
        );
        
        searchflight.setText(welcomeMessage);
        searchflight.setEditable(false);
    }
    
    private void hideDashboardContent() {
        searchflight.setText("Search Flights");
        searchflight.setEditable(true);
    }
    
    // ============ BAGGAGE DETAILS ============
    private void showBaggageDetailsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel title = new JLabel("Baggage Details & Allowance", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground(new Color(0, 102, 204));
        
        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setFont(new Font("Arial", Font.PLAIN, 14));
        infoArea.setText(
            "✈️ BAGGAGE ALLOWANCE POLICY\n" +
            "══════════════════════════\n\n" +
            "📦 CHECKED BAGGAGE:\n" +
            "• Economy Class: 20 kg\n" +
            "• Business Class: 30 kg\n" +
            "• First Class: 40 kg\n\n" +
            "🎒 HAND LUGGAGE:\n" +
            "• All Classes: 7 kg (max 55x40x20 cm)\n\n" +
            "💰 EXCESS BAGGAGE CHARGES:\n" +
            "• Domestic: 500 RS per kg\n" +
            "• International: 1,000 RS per kg\n\n" +
            "⚠️ RESTRICTED ITEMS:\n" +
            "• Sharp objects\n" +
            "• Flammable liquids\n" +
            "• Explosives\n" +
            "• Lithium batteries (over 100Wh)\n\n" +
            "✅ YOUR CURRENT BAGGAGE:\n" +
            "• Flight KA101: 18 kg checked, 5 kg hand\n" +
            "• Flight DL202: 22 kg checked, 8 kg hand\n" +
            "• Total used: 40 kg of 60 kg allowance"
        );
        infoArea.setBackground(new Color(240, 248, 255));
        
        JPanel calcPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        calcPanel.setBorder(BorderFactory.createTitledBorder("Baggage Calculator"));
        
        calcPanel.add(new JLabel("Current Weight (kg):"));
        JTextField weightField = new JTextField("20");
        calcPanel.add(weightField);
        
        calcPanel.add(new JLabel("Your Class:"));
        JComboBox<String> classCombo = new JComboBox<>(new String[]{"Economy", "Business", "First Class"});
        calcPanel.add(classCombo);
        
        calcPanel.add(new JLabel("Excess Charge:"));
        JLabel chargeLabel = new JLabel("0 RS", JLabel.CENTER);
        chargeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        chargeLabel.setForeground(Color.RED);
        calcPanel.add(chargeLabel);
        
        JButton calcButton = new JButton("Calculate Charge");
        calcButton.addActionListener(e -> {
            try {
                double weight = Double.parseDouble(weightField.getText());
                String flightClass = (String) classCombo.getSelectedItem();
                double allowance = getBaggageAllowance(flightClass);
                double excess = Math.max(0, weight - allowance);
                double charge = excess * 500;
                chargeLabel.setText(String.format("%,.0f RS", charge));
            } catch (NumberFormatException ex) {
                chargeLabel.setText("Invalid weight");
            }
        });
        
        panel.add(title, BorderLayout.NORTH);
        panel.add(new JScrollPane(infoArea), BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(calcPanel, BorderLayout.CENTER);
        bottomPanel.add(calcButton, BorderLayout.SOUTH);
        
        panel.add(bottomPanel, BorderLayout.SOUTH);
        
        JOptionPane.showMessageDialog(this, panel, "Baggage Details", JOptionPane.PLAIN_MESSAGE);
    }
    
    private double getBaggageAllowance(String flightClass) {
        switch (flightClass) {
            case "Economy": return 20;
            case "Business": return 30;
            case "First Class": return 40;
            default: return 20;
        }
    }
    
    // ============ PAYMENTS VIEW ============
    private void showPaymentsPanel() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Payment History", createPaymentHistoryPanel());
        tabbedPane.addTab("Make Payment", createMakePaymentPanel());
        tabbedPane.addTab("Payment Methods", createPaymentMethodsPanel());
        
        JOptionPane.showMessageDialog(this, tabbedPane, "Payment Management", JOptionPane.PLAIN_MESSAGE);
    }
    
    private JPanel createPaymentHistoryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        String[] columns = {"Date", "Booking ID", "Amount", "Method", "Status"};
        Object[][] data = {
            {"2024-01-15", "BK00123", "40,000 RS", "Credit Card", "Completed"},
            {"2024-01-10", "BK00122", "45,000 RS", "Digital Wallet", "Completed"},
            {"2024-01-05", "BK00121", "30,000 RS", "Debit Card", "Completed"},
            {"2024-01-02", "BK00120", "50,000 RS", "Cash", "Pending"}
        };
        
        JTable table = new JTable(data, columns);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 300));
        
        JPanel statsPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        statsPanel.add(createStatCard("Total Spent", "1,65,000 RS", Color.BLUE));
        statsPanel.add(createStatCard("Transactions", "12", Color.GREEN));
        statsPanel.add(createStatCard("Pending", "1", Color.ORANGE));
        statsPanel.add(createStatCard("Avg. Payment", "13,750 RS", Color.MAGENTA));
        
        panel.add(new JLabel("Payment History", JLabel.CENTER), BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(statsPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createMakePaymentPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        panel.add(new JLabel("Booking ID:"));
        JTextField bookingIdField = new JTextField();
        panel.add(bookingIdField);
        
        panel.add(new JLabel("Amount (RS):"));
        JTextField amountField = new JTextField();
        panel.add(amountField);
        
        panel.add(new JLabel("Payment Method:"));
        JComboBox<String> methodCombo = new JComboBox<>(new String[]{
            "Credit Card", "Debit Card", "Digital Wallet", "Bank Transfer", "Cash"
        });
        panel.add(methodCombo);
        
        panel.add(new JLabel("Card Number:"));
        JTextField cardField = new JTextField();
        panel.add(cardField);
        
        panel.add(new JLabel("Expiry Date:"));
        JTextField expiryField = new JTextField("MM/YY");
        panel.add(expiryField);
        
        JButton payButton = new JButton("Make Payment");
        payButton.setBackground(new Color(0, 153, 0));
        payButton.setForeground(Color.WHITE);
        payButton.addActionListener(e -> {
            if (!bookingIdField.getText().isEmpty() && !amountField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Payment Successful!\n\n" +
                    "Booking ID: " + bookingIdField.getText() + "\n" +
                    "Amount: " + amountField.getText() + " RS\n" +
                    "Method: " + methodCombo.getSelectedItem(),
                    "Payment Confirmation",
                    JOptionPane.INFORMATION_MESSAGE);
                
                paymentHistory.add(new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + 
                    " - " + bookingIdField.getText() + " - " + 
                    amountField.getText() + " RS - " + 
                    methodCombo.getSelectedItem() + " - Completed");
            }
        });
        
        panel.add(new JLabel());
        panel.add(payButton);
        
        return panel;
    }
    
    private JPanel createPaymentMethodsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("💳 Visa **** 1234 (Primary)");
        listModel.addElement("💳 MasterCard **** 5678");
        listModel.addElement("📱 Digital Wallet (eSewa)");
        listModel.addElement("🏦 Bank Transfer (NIC Asia)");
        
        JList<String> list = new JList<>(listModel);
        list.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add New Method");
        JButton removeButton = new JButton("Remove Selected");
        JButton setPrimaryButton = new JButton("Set as Primary");
        
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(setPrimaryButton);
        
        panel.add(new JScrollPane(list), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createStatCard(String title, String value, Color color) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(color);
        card.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel valueLabel = new JLabel(value, JLabel.CENTER);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 16));
        valueLabel.setForeground(Color.WHITE);
        
        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        titleLabel.setForeground(Color.WHITE);
        
        card.add(valueLabel);
        card.add(titleLabel);
        
        return card;
    }
    
    // ============ FEEDBACK VIEW ============
    private void showFeedbackPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel title = new JLabel("Share Your Feedback", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground(new Color(0, 102, 204));
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Subject:"), gbc);
        
        gbc.gridx = 1; gbc.gridwidth = 2;
        JTextField subjectField = new JTextField(30);
        formPanel.add(subjectField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        formPanel.add(new JLabel("Rating:"), gbc);
        
        gbc.gridx = 1; gbc.gridwidth = 2;
        JPanel ratingPanel = new JPanel(new FlowLayout());
        ButtonGroup ratingGroup = new ButtonGroup();
        for (int i = 1; i <= 5; i++) {
            JRadioButton star = new JRadioButton("★" + i);
            ratingGroup.add(star);
            ratingPanel.add(star);
            if (i == 5) star.setSelected(true);
        }
        formPanel.add(ratingPanel, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Category:"), gbc);
        
        gbc.gridx = 1; gbc.gridwidth = 2;
        JComboBox<String> categoryCombo = new JComboBox<>(new String[]{
            "General", "Booking Experience", "Flight Service", "Website", "Customer Support"
        });
        formPanel.add(categoryCombo, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Comments:"), gbc);
        
        gbc.gridx = 1; gbc.gridwidth = 2; gbc.gridheight = 2;
        JTextArea commentsArea = new JTextArea(8, 30);
        JScrollPane scrollPane = new JScrollPane(commentsArea);
        formPanel.add(scrollPane, gbc);
        
        JButton submitButton = new JButton("Submit Feedback");
        submitButton.setBackground(new Color(0, 153, 0));
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(e -> {
            if (subjectField.getText().isEmpty() || commentsArea.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Please fill in all fields", 
                    "Incomplete Form", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            JOptionPane.showMessageDialog(this, 
                "Thank you for your feedback!\n\n" +
                "Your feedback has been submitted successfully.\n" +
                "We value your input and will use it to improve our services.",
                "Feedback Submitted",
                JOptionPane.INFORMATION_MESSAGE);
            
            subjectField.setText("");
            commentsArea.setText("");
        });
        
        panel.add(title, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(submitButton, BorderLayout.SOUTH);
        
        JOptionPane.showMessageDialog(this, panel, "Feedback", JOptionPane.PLAIN_MESSAGE);
    }
    
    // ============ NOTIFICATIONS VIEW ============
    private void showNotificationsPanel() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        
        for (String notification : notifications) {
            listModel.addElement(notification);
        }
        
        JList<String> list = new JList<>(listModel);
        list.setFont(new Font("Arial", Font.PLAIN, 14));
        list.setFixedCellHeight(50);
        
        list.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, 
                    int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus);
                label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                label.setIconTextGap(10);
                return label;
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(500, 400));
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton markReadButton = new JButton("Mark All as Read");
        JButton clearButton = new JButton("Clear All");
        JButton addButton = new JButton("Add Test Notification");
        
        markReadButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                "All notifications marked as read!", 
                "Notifications", JOptionPane.INFORMATION_MESSAGE);
        });
        
        clearButton.addActionListener(e -> {
            listModel.clear();
            notifications.clear();
        });
        
        addButton.addActionListener(e -> {
            String newNotification = "🔔 Test notification added at " + 
                new SimpleDateFormat("HH:mm:ss").format(new Date());
            listModel.addElement(newNotification);
            notifications.add(newNotification);
        });
        
        buttonPanel.add(markReadButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(addButton);
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Notifications (" + listModel.getSize() + ")", JLabel.CENTER), 
            BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        JOptionPane.showMessageDialog(this, panel, "Notifications", JOptionPane.PLAIN_MESSAGE);
    }
    
    // ============ PROFILE VIEW ============
    private void showProfilePanel() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Personal Information", createPersonalInfoPanel());
        tabbedPane.addTab("Booking History", createBookingHistoryPanel());
        tabbedPane.addTab("Loyalty Program", createLoyaltyPanel());
        
        JOptionPane.showMessageDialog(this, tabbedPane, "My Profile", JOptionPane.PLAIN_MESSAGE);
    }
    
    private JPanel createPersonalInfoPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridheight = 4;
        JLabel profilePic = new JLabel("👤", JLabel.CENTER);
        profilePic.setFont(new Font("Arial", Font.PLAIN, 60));
        profilePic.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        panel.add(profilePic, gbc);
        
        String[][] fields = {
            {"Full Name:", "John Doe"},
            {"Email:", userSession.getUserEmail()},
            {"Phone:", "+977 98XXXXXXXX"},
            {"Address:", "Kathmandu, Nepal"},
            {"Date of Birth:", "01/01/1990"},
            {"Passport No:", "A12345678"},
            {"Nationality:", "Nepalese"}
        };
        
        for (int i = 0; i < fields.length; i++) {
            gbc.gridx = 1; gbc.gridy = i; gbc.gridheight = 1;
            panel.add(new JLabel(fields[i][0]), gbc);
            
            gbc.gridx = 2;
            JTextField field = new JTextField(fields[i][1], 20);
            field.setEditable(i != 1);
            panel.add(field, gbc);
        }
        
        gbc.gridx = 1; gbc.gridy = fields.length; gbc.gridwidth = 2;
        JButton saveButton = new JButton("Save Changes");
        saveButton.setBackground(new Color(0, 102, 204));
        saveButton.setForeground(Color.WHITE);
        saveButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                "Profile updated successfully!", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
        });
        panel.add(saveButton, gbc);
        
        return panel;
    }
    
    private JPanel createBookingHistoryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        String[] columns = {"Booking ID", "Flight", "Date", "Passengers", "Amount", "Status"};
        Object[][] data = {
            {"BK00123", "KA101 (KTM-PKR)", "2024-01-15", "2", "40,000 RS", "Confirmed"},
            {"BK00122", "KA102 (KTM-JKR)", "2024-01-10", "1", "45,000 RS", "Completed"},
            {"BK00121", "KA103 (LUA-KTM)", "2024-01-05", "3", "30,000 RS", "Completed"},
            {"BK00120", "KA104 (BKT-KTM)", "2024-01-02", "2", "50,000 RS", "Cancelled"}
        };
        
        JTable table = new JTable(data, columns);
        table.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(table);
        
        JPanel summaryPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        summaryPanel.add(new JLabel("Total Bookings: 12"));
        summaryPanel.add(new JLabel("Total Spent: 1,65,000 RS"));
        summaryPanel.add(new JLabel("Upcoming: 2"));
        summaryPanel.add(new JLabel("Completed: 8"));
        summaryPanel.add(new JLabel("Cancelled: 2"));
        summaryPanel.add(new JLabel("Loyalty Points: 1,250"));
        
        panel.add(new JLabel("Booking History", JLabel.CENTER), BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(summaryPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createLoyaltyPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JProgressBar progressBar = new JProgressBar(0, 10000);
        progressBar.setValue(1250);
        progressBar.setString("1,250 / 10,000 points");
        progressBar.setStringPainted(true);
        progressBar.setForeground(new Color(255, 193, 7));
        
        JTextArea benefitsArea = new JTextArea();
        benefitsArea.setEditable(false);
        benefitsArea.setFont(new Font("Arial", Font.PLAIN, 14));
        benefitsArea.setText(
            "🎁 LOYALTY PROGRAM BENEFITS\n" +
            "══════════════════════════\n\n" +
            "⭐ Silver Tier (0-2,499 points):\n" +
            "   • Priority check-in\n" +
            "   • 5% discount on bookings\n" +
            "   • Free seat selection\n\n" +
            "⭐⭐ Gold Tier (2,500-7,499 points):\n" +
            "   • Lounge access\n" +
            "   • 10% discount on bookings\n" +
            "   • Extra baggage allowance\n" +
            "   • Priority boarding\n\n" +
            "⭐⭐⭐ Platinum Tier (7,500+ points):\n" +
            "   • Business class upgrades\n" +
            "   • 15% discount on bookings\n" +
            "   • Personal travel assistant\n" +
            "   • Free cancellation\n\n" +
            "Your Current Tier: ⭐ Silver\n" +
            "Next Tier: ⭐⭐ Gold (1,250 points needed)"
        );
        
        panel.add(new JLabel("Loyalty Program", JLabel.CENTER), BorderLayout.NORTH);
        panel.add(progressBar, BorderLayout.CENTER);
        panel.add(new JScrollPane(benefitsArea), BorderLayout.SOUTH);
        
        return panel;
    }
    
    // ============ SETTINGS VIEW ============
    private void showSettingsPanel() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Account", createAccountSettingsPanel());
        tabbedPane.addTab("Notifications", createNotificationSettingsPanel());
        tabbedPane.addTab("Privacy", createPrivacySettingsPanel());
        tabbedPane.addTab("Appearance", createAppearanceSettingsPanel());
        
        JOptionPane.showMessageDialog(this, tabbedPane, "Settings", JOptionPane.PLAIN_MESSAGE);
    }
    
    private JPanel createAccountSettingsPanel() {
        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        panel.add(new JLabel("Change Email:"));
        JTextField emailField = new JTextField(userSession.getUserEmail());
        panel.add(emailField);
        
        panel.add(new JLabel("Change Password:"));
        JPasswordField passField = new JPasswordField();
        panel.add(passField);
        
        panel.add(new JLabel("Confirm Password:"));
        JPasswordField confirmField = new JPasswordField();
        panel.add(confirmField);
        
        panel.add(new JLabel("Two-Factor Auth:"));
        JCheckBox twoFactorBox = new JCheckBox("Enable");
        panel.add(twoFactorBox);
        
        panel.add(new JLabel("Auto Login:"));
        JCheckBox autoLoginBox = new JCheckBox("Enable");
        panel.add(autoLoginBox);
        
        panel.add(new JLabel("Session Timeout:"));
        JComboBox<String> timeoutCombo = new JComboBox<>(new String[]{
            "15 minutes", "30 minutes", "1 hour", "2 hours", "Never"
        });
        panel.add(timeoutCombo);
        
        panel.add(new JLabel("Delete Account:"));
        JButton deleteButton = new JButton("Delete My Account");
        deleteButton.setForeground(Color.RED);
        deleteButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete your account?\nThis action cannot be undone.",
                "Delete Account",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
            
            if (confirm == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this,
                    "Account deletion request submitted.\nYou will receive a confirmation email.",
                    "Account Deletion",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
        panel.add(deleteButton);
        
        panel.add(new JLabel());
        JButton saveButton = new JButton("Save Account Settings");
        saveButton.setBackground(new Color(0, 102, 204));
        saveButton.setForeground(Color.WHITE);
        saveButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                "Account settings saved successfully!",
                "Settings Saved",
                JOptionPane.INFORMATION_MESSAGE);
        });
        panel.add(saveButton);
        
        return panel;
    }
    
    private JPanel createNotificationSettingsPanel() {
        JPanel panel = new JPanel(new GridLayout(8, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        panel.add(new JLabel("📧 Email Notifications:"));
        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JCheckBox emailBooking = new JCheckBox("Booking confirmations", true);
        JCheckBox emailPromo = new JCheckBox("Promotional offers", true);
        JCheckBox emailNews = new JCheckBox("Newsletter", false);
        emailPanel.add(emailBooking);
        emailPanel.add(emailPromo);
        emailPanel.add(emailNews);
        panel.add(emailPanel);
        
        panel.add(new JLabel("📱 SMS Notifications:"));
        JPanel smsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JCheckBox smsBooking = new JCheckBox("Booking updates", true);
        JCheckBox smsReminder = new JCheckBox("Flight reminders", true);
        smsPanel.add(smsBooking);
        smsPanel.add(smsReminder);
        panel.add(smsPanel);
        
        panel.add(new JLabel("🔔 Push Notifications:"));
        JPanel pushPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JCheckBox pushDeals = new JCheckBox("Deal alerts", true);
        JCheckBox pushStatus = new JCheckBox("Flight status", true);
        pushPanel.add(pushDeals);
        pushPanel.add(pushStatus);
        panel.add(pushPanel);
        
        panel.add(new JLabel("Notification Frequency:"));
        JComboBox<String> frequencyCombo = new JComboBox<>(new String[]{
            "Immediately", "Daily Digest", "Weekly Summary"
        });
        panel.add(frequencyCombo);
        
        panel.add(new JLabel("Quiet Hours:"));
        JPanel quietPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        quietPanel.add(new JLabel("From:"));
        JComboBox<String> startCombo = new JComboBox<>(new String[]{"10 PM", "11 PM", "12 AM"});
        quietPanel.add(startCombo);
        quietPanel.add(new JLabel("To:"));
        JComboBox<String> endCombo = new JComboBox<>(new String[]{"6 AM", "7 AM", "8 AM"});
        quietPanel.add(endCombo);
        panel.add(quietPanel);
        
        JButton saveButton = new JButton("Save Notification Settings");
        saveButton.setBackground(new Color(0, 153, 0));
        saveButton.setForeground(Color.WHITE);
        saveButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                "Notification settings saved successfully!",
                "Settings Saved",
                JOptionPane.INFORMATION_MESSAGE);
        });
        panel.add(saveButton);
        
        return panel;
    }
    
    private JPanel createPrivacySettingsPanel() {
        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        panel.add(new JLabel("Profile Visibility:"));
        JComboBox<String> visibilityCombo = new JComboBox<>(new String[]{
            "Public", "Friends Only", "Private"
        });
        panel.add(visibilityCombo);
        
        panel.add(new JLabel("Share Booking History:"));
        JCheckBox shareBooking = new JCheckBox("Allow sharing");
        panel.add(shareBooking);
        
        panel.add(new JLabel("Data Collection:"));
        JCheckBox dataCollection = new JCheckBox("Allow analytics", true);
        panel.add(dataCollection);
        
        panel.add(new JLabel("Location Services:"));
        JCheckBox locationServices = new JCheckBox("Enable location tracking");
        panel.add(locationServices);
        
        panel.add(new JLabel("Cookies:"));
        JComboBox<String> cookiesCombo = new JComboBox<>(new String[]{
            "Accept All", "Essential Only", "Custom"
        });
        panel.add(cookiesCombo);
        
        panel.add(new JLabel("Download My Data:"));
        JButton downloadButton = new JButton("Request Data Export");
        panel.add(downloadButton);
        
        panel.add(new JLabel("Clear History:"));
        JButton clearButton = new JButton("Clear All History");
        clearButton.setForeground(Color.RED);
        panel.add(clearButton);
        
        JButton saveButton = new JButton("Save Privacy Settings");
        saveButton.setBackground(new Color(102, 0, 204));
        saveButton.setForeground(Color.WHITE);
        panel.add(saveButton);
        
        return panel;
    }
    
    private JPanel createAppearanceSettingsPanel() {
        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        panel.add(new JLabel("Theme:"));
        JComboBox<String> themeCombo = new JComboBox<>(new String[]{
            "Light", "Dark", "Blue", "Green", "Automatic"
        });
        panel.add(themeCombo);
        
        panel.add(new JLabel("Language:"));
        JComboBox<String> languageCombo = new JComboBox<>(new String[]{
            "English", "Nepali", "Hindi", "Chinese", "Japanese"
        });
        panel.add(languageCombo);
        
        panel.add(new JLabel("Font Size:"));
        JSlider fontSizeSlider = new JSlider(10, 24, 14);
        panel.add(fontSizeSlider);
        
        panel.add(new JLabel("Currency:"));
        JComboBox<String> currencyCombo = new JComboBox<>(new String[]{
            "NPR (रु)", "USD ($)", "EUR (€)", "GBP (£)", "INR (₹)"
        });
        panel.add(currencyCombo);
        
        panel.add(new JLabel("Time Format:"));
        JComboBox<String> timeFormatCombo = new JComboBox<>(new String[]{
            "12-hour", "24-hour"
        });
        panel.add(timeFormatCombo);
        
        panel.add(new JLabel("Date Format:"));
        JComboBox<String> dateFormatCombo = new JComboBox<>(new String[]{
            "DD/MM/YYYY", "MM/DD/YYYY", "YYYY-MM-DD"
        });
        panel.add(dateFormatCombo);
        
        panel.add(new JLabel("Animation:"));
        JCheckBox animationBox = new JCheckBox("Enable animations", true);
        panel.add(animationBox);
        
        JButton saveButton = new JButton("Save Appearance Settings");
        saveButton.setBackground(new Color(255, 153, 0));
        saveButton.setForeground(Color.WHITE);
        panel.add(saveButton);
        
        return panel;
    }
    
    // ============ FLIGHT BOOKING FUNCTIONALITY ============
    private void setupFlightButtonListeners() {
        paymant1.addActionListener(e -> handleFlightBooking1());
        paymant2.addActionListener(e -> handleFlightBooking2());
        paymant3.addActionListener(e -> handleFlightBooking3());
        paymant4.addActionListener(e -> handleFlightBooking4());
        paymant5.addActionListener(e -> handleFlightBooking5());
        paymant6.addActionListener(e -> handleFlightBooking6());
        paymant7.addActionListener(e -> handleFlightBooking7());
        paymant8.addActionListener(e -> handleFlightBooking8());
        paymant9.addActionListener(e -> handleFlightBooking9());
    }
    
    // Flight booking handlers
    private void handleFlightBooking1() { 
        bookFlight("Flight KA101", source1.getText(), destination1.getText(), 
                  sourcetime1.getText(), destinationtime1.getText(), 
                  "2 hours", "40,000 RS"); 
    }
    
    private void handleFlightBooking2() { 
        bookFlight("Flight KA102", source2.getText(), destination2.getText(), 
                  sourcetime2.getText(), destinationtime2.getText(), 
                  "2.5 hours", "45,000 RS"); 
    }
    
    private void handleFlightBooking3() { 
        bookFlight("Flight KA103", source3.getText(), destination3.getText(), 
                  sourcetime3.getText(), destinationtime3.getText(), 
                  "2.75 hours", "20,000 RS"); 
    }
    
    private void handleFlightBooking4() { 
        bookFlight("Flight KA104", source4.getText(), destination4.getText(), 
                  sourcetime4.getText(), destinationtime4.getText(), 
                  "1.75 hours", "20,000 RS"); 
    }
    
    private void handleFlightBooking5() { 
        bookFlight("Flight KA105", source5.getText(), destination5.getText(), 
                  sourcetime5.getText(), destinationtime5.getText(), 
                  "2.25 hours", "45,000 RS"); 
    }
    
    private void handleFlightBooking6() { 
        bookFlight("Flight KA106", source6.getText(), destination6.getText(), 
                  sourcetime6.getText(), destinationtime6.getText(), 
                  "2.5 hours", "30,000 RS"); 
    }
    
    private void handleFlightBooking7() { 
        bookFlight("Flight KA107", source7.getText(), destination7.getText(), 
                  sourcetime7.getText(), destinationtime7.getText(), 
                  "2.5 hours", "35,000 RS"); 
    }
    
    private void handleFlightBooking8() { 
        bookFlight("Flight KA108", source8.getText(), destination8.getText(), 
                  sourcetime8.getText(), destinationtime8.getText(), 
                  "2.5 hours", "40,000 RS"); 
    }
    
    private void handleFlightBooking9() { 
        bookFlight("Flight KA109", source9.getText(), destination9.getText(), 
                  sourcetime9.getText(), destinationtime9.getText(), 
                  "2 hours", "50,000 RS"); 
    }
    
    private void bookFlight(String flightName, String fromCity, String toCity,
                           String departureTime, String arrivalTime, 
                           String duration, String price) {
        // Ask for number of tickets
        String ticketInput = JOptionPane.showInputDialog(this,
            "Book: " + flightName + "\n" +
            "Route: " + fromCity + " → " + toCity + "\n" +
            "Departure: " + departureTime + "\n" +
            "Arrival: " + arrivalTime + "\n" +
            "Duration: " + duration + "\n" +
            "Price: " + price + " per ticket\n\n" +
            "Enter number of tickets (1-10):",
            "1");
        
        if (ticketInput == null) return;
        
        try {
            int tickets = Integer.parseInt(ticketInput);
            if (tickets < 1 || tickets > 10) {
                JOptionPane.showMessageDialog(this,
                    "Please enter 1-10 tickets only.",
                    "Invalid Input",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Calculate total
            String priceNumeric = price.replaceAll("[^0-9]", "");
            int unitPrice = Integer.parseInt(priceNumeric);
            int total = unitPrice * tickets;
            
            // Show confirmation
            int confirm = JOptionPane.showConfirmDialog(this,
                "Confirm Booking:\n\n" +
                "Flight: " + flightName + "\n" +
                "From: " + fromCity + "\n" +
                "To: " + toCity + "\n" +
                "Departure: " + departureTime + "\n" +
                "Arrival: " + arrivalTime + "\n" +
                "Duration: " + duration + "\n" +
                "Tickets: " + tickets + " x " + price + "\n" +
                "Total: " + String.format("%,d", total) + " RS\n\n" +
                "Proceed to payment?",
                "Confirm Booking",
                JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                processPayment(flightName, fromCity, toCity, total, tickets);
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Please enter a valid number.",
                "Invalid Input",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void processPayment(String flightName, String fromCity, String toCity, 
                               int total, int tickets) {
        // Create payment dialog
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel title = new JLabel("Payment for " + flightName, JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setForeground(new Color(0, 102, 204));
        
        panel.add(new JLabel("Payment Method:"));
        JComboBox<String> paymentMethod = new JComboBox<>(new String[]{
            "Credit Card", "Debit Card", "Digital Wallet", "Bank Transfer", "Cash"
        });
        panel.add(paymentMethod);
        
        panel.add(new JLabel("Card Number:"));
        JTextField cardNumber = new JTextField();
        panel.add(cardNumber);
        
        panel.add(new JLabel("Expiry Date (MM/YY):"));
        JTextField expiryDate = new JTextField();
        panel.add(expiryDate);
        
        panel.add(new JLabel("CVV:"));
        JPasswordField cvv = new JPasswordField();
        panel.add(cvv);
        
        panel.add(new JLabel("Total Amount:"));
        JLabel totalLabel = new JLabel(String.format("%,d", total) + " RS");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14));
        totalLabel.setForeground(Color.RED);
        panel.add(totalLabel);
        
        int option = JOptionPane.showConfirmDialog(this, panel, 
            "Payment Details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (option == JOptionPane.OK_OPTION) {
            // Simple validation
            if (cardNumber.getText().trim().isEmpty() || 
                expiryDate.getText().trim().isEmpty() || 
                cvv.getPassword().length == 0) {
                JOptionPane.showMessageDialog(this, 
                    "Please fill in all payment details.", 
                    "Incomplete Details", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Generate booking ID
            String bookingId = "BK" + String.format("%05d", (int)(Math.random() * 100000));
            
            // Show success message
            JOptionPane.showMessageDialog(this,
                "Payment Successful!\n\n" +
                "Booking ID: " + bookingId + "\n" +
                "Flight: " + flightName + "\n" +
                "Route: " + fromCity + " → " + toCity + "\n" +
                "Tickets: " + tickets + "\n" +
                "Total: " + String.format("%,d", total) + " RS\n" +
                "Payment Method: " + paymentMethod.getSelectedItem() + "\n\n" +
                "Booking confirmed. Have a safe flight!",
                "Payment Confirmed",
                JOptionPane.INFORMATION_MESSAGE);
            
            // Add to booking history
            String bookingRecord = bookingId + " - " + flightName + " - " + 
                                 fromCity + " to " + toCity + " - " + 
                                 tickets + " Passenger(s) - " + 
                                 String.format("%,d", total) + " RS - Confirmed";
            bookingHistory.add(bookingRecord);
            
            // Add to payment history
            String paymentRecord = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + 
                " - " + bookingId + " - " + 
                String.format("%,d", total) + " RS - " + 
                paymentMethod.getSelectedItem() + " - Completed";
            paymentHistory.add(paymentRecord);
            
            // Add notification
            String notificationMsg = "Booking " + bookingId + " for " + flightName + 
                                   " to " + toCity + " confirmed";
            notifications.add(0, notificationMsg);
            
            // Log the transaction
            logger.info("Booking confirmed: " + bookingId + " - " + flightName + 
                       " - " + fromCity + " to " + toCity + " - Total: " + total + " RS");
            
        } else {
            JOptionPane.showMessageDialog(this, 
                "Payment cancelled. Booking not completed.", 
                "Payment Cancelled", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
   private void setDefaultFlightData() {
        // Set flight data
        source1.setText("Kathmandu"); destination1.setText("Pokhara");
        source2.setText("Kathmandu"); destination2.setText("Janakpur");
        source3.setText("Lumbini"); destination3.setText("Kathmandu");
        source4.setText("Bhaktapur"); destination4.setText("Kathmandu");
        source5.setText("Koteshwor"); destination5.setText("Chitwan");
        source6.setText("Kathmandu"); destination6.setText("Mustang");
        source7.setText("Pokhara"); destination7.setText("Baneshwor");
        source8.setText("Kathmandu"); destination8.setText("Lalitpur");
        source9.setText("Kathmandu"); destination9.setText("Dulikhel");
        
        // Set flight times
        sourcetime1.setText("5:00 AM"); destinationtime1.setText("7:00 AM");
        sourcetime2.setText("9:00 AM"); destinationtime2.setText("11:30 AM");
        sourcetime3.setText("2:00 PM"); destinationtime3.setText("4:45 PM");
        sourcetime4.setText("6:00 AM"); destinationtime4.setText("7:45 AM");
        sourcetime5.setText("3:00 PM"); destinationtime5.setText("5:15 PM");
        sourcetime6.setText("7:00 PM"); destinationtime6.setText("9:30 PM");
        sourcetime7.setText("12:00 PM"); destinationtime7.setText("2:30 PM");
        sourcetime8.setText("8:00 AM"); destinationtime8.setText("10:30 AM");
        sourcetime9.setText("9:00 AM"); destinationtime9.setText("11:00 AM");
        
        // Set flight durations
        flighttimeduration1.setText("Duration: 2hr");
        flighttimeduration2.setText("Duration: 2hr - 30m");
        flighttimeduration3.setText("Duration: 2hr - 45m");
        flighttimeduration4.setText("Duration: 1hr - 45m");
        flighttimeduration5.setText("Duration: 2hr - 15m");
        flighttimeduration6.setText("Duration: 2hr - 30m");
        flighttimeduration7.setText("Duration: 2hr - 30m");
        flighttimeduration8.setText("Duration: 2hr - 30m");
        flighttimeduration9.setText("Duration: 2hr");
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        back = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        logout = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        userprofile = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        settings = new javax.swing.JLabel();
        dashboard = new javax.swing.JLabel();
        flights = new javax.swing.JLabel();
        bagdetails = new javax.swing.JLabel();
        notification = new javax.swing.JLabel();
        payment = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        feedback = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        searchflight = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        to = new javax.swing.JTextField();
        date = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        ticket = new javax.swing.JSpinner();
        jLabel39 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        from = new javax.swing.JTextField();
        mountainflights = new javax.swing.JTextField();
        roundtrip = new javax.swing.JTextField();
        oneway = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel97 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        flighttimeduration2 = new javax.swing.JLabel();
        destinationtime1 = new javax.swing.JLabel();
        source1 = new javax.swing.JLabel();
        destination1 = new javax.swing.JLabel();
        sourcetime1 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        source2 = new javax.swing.JLabel();
        sourcetime2 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        flighttimeduration1 = new javax.swing.JLabel();
        destination2 = new javax.swing.JLabel();
        destinationtime2 = new javax.swing.JLabel();
        paymant2 = new java.awt.Button();
        source3 = new javax.swing.JLabel();
        sourcetime3 = new javax.swing.JLabel();
        destination3 = new javax.swing.JLabel();
        destinationtime3 = new javax.swing.JLabel();
        jLabel144 = new javax.swing.JLabel();
        flighttimeduration3 = new javax.swing.JLabel();
        paymant3 = new java.awt.Button();
        paymant1 = new java.awt.Button();
        jPanel7 = new javax.swing.JPanel();
        jLabel98 = new javax.swing.JLabel();
        paymant4 = new java.awt.Button();
        jLabel120 = new javax.swing.JLabel();
        flighttimeduration5 = new javax.swing.JLabel();
        destinationtime4 = new javax.swing.JLabel();
        source4 = new javax.swing.JLabel();
        destination4 = new javax.swing.JLabel();
        sourcetime4 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        source5 = new javax.swing.JLabel();
        sourcetime5 = new javax.swing.JLabel();
        jLabel125 = new javax.swing.JLabel();
        flighttimeduration4 = new javax.swing.JLabel();
        destination5 = new javax.swing.JLabel();
        destinationtime5 = new javax.swing.JLabel();
        paymant5 = new java.awt.Button();
        source6 = new javax.swing.JLabel();
        sourcetime6 = new javax.swing.JLabel();
        destination6 = new javax.swing.JLabel();
        destinationtime6 = new javax.swing.JLabel();
        jLabel149 = new javax.swing.JLabel();
        flighttimeduration6 = new javax.swing.JLabel();
        paymant6 = new java.awt.Button();
        jPanel8 = new javax.swing.JPanel();
        jLabel99 = new javax.swing.JLabel();
        paymant7 = new java.awt.Button();
        jLabel127 = new javax.swing.JLabel();
        flighttimeduration8 = new javax.swing.JLabel();
        destinationtime7 = new javax.swing.JLabel();
        source7 = new javax.swing.JLabel();
        destination7 = new javax.swing.JLabel();
        sourcetime7 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        source8 = new javax.swing.JLabel();
        sourcetime8 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        flighttimeduration7 = new javax.swing.JLabel();
        destination8 = new javax.swing.JLabel();
        paymant8 = new java.awt.Button();
        source9 = new javax.swing.JLabel();
        sourcetime9 = new javax.swing.JLabel();
        destination9 = new javax.swing.JLabel();
        destinationtime9 = new javax.swing.JLabel();
        jLabel154 = new javax.swing.JLabel();
        flighttimeduration9 = new javax.swing.JLabel();
        paymant9 = new java.awt.Button();
        destinationtime8 = new javax.swing.JLabel();
        multicity = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(99, 71, 67));
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(126, 88, 88));

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Uuanchai group.png"))); // NOI18N

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back (1) (1).png"))); // NOI18N
        jLabel23.setText("jLabel23");

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-notification-20.png"))); // NOI18N

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-chat-message-20.png"))); // NOI18N

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-back-arrow-25.png"))); // NOI18N
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(270, 270, 270)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(405, 405, 405))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(167, 167, 167)))
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel23)
                        .addGap(19, 19, 19))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(24, 24, 24)
                                .addComponent(back))
                            .addComponent(jLabel21))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel3.setBackground(new java.awt.Color(0, 153, 255));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-dashboard-24.png"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-plane-25.png"))); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-bags-25.png"))); // NOI18N

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-user-30.png"))); // NOI18N

        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-logout-40.png"))); // NOI18N
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutMouseClicked(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-notification-30.png"))); // NOI18N

        jLabel10.setText("Dashboard");

        jLabel12.setText("Flights");

        jLabel13.setText("Baggage Detaiils");

        jLabel14.setText("Payments");

        jLabel16.setText("Profile");

        jLabel17.setText("Notification");

        jLabel18.setText("Settings");

        jLabel19.setText("Logout");

        userprofile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/man (1).png"))); // NOI18N
        userprofile.setText("jLabel24");
        userprofile.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                userprofileFocusGained(evt);
            }
        });
        userprofile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userprofileMouseClicked(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-notification-20.png"))); // NOI18N

        settings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-settings-40 (1).png"))); // NOI18N
        settings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                settingsMouseClicked(evt);
            }
        });

        dashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-dashboard-layout-40.png"))); // NOI18N
        dashboard.setText("jLabel11");
        dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboardMouseClicked(evt);
            }
        });

        flights.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-plane-40.png"))); // NOI18N
        flights.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                flightsMouseClicked(evt);
            }
        });

        bagdetails.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-bags-40.png"))); // NOI18N
        bagdetails.setText("jLabel26");

        notification.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-notification-40.png"))); // NOI18N
        notification.setText("jLabel29");
        notification.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                notificationFocusGained(evt);
            }
        });
        notification.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                notificationMouseClicked(evt);
            }
        });

        payment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-wallet-50.png"))); // NOI18N
        payment.setText("jLabel15");
        payment.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                paymentFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                paymentFocusLost(evt);
            }
        });
        payment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paymentMouseClicked(evt);
            }
        });

        jLabel6.setText("Feedback");

        feedback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-user-40.png"))); // NOI18N
        feedback.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                feedbackMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(notification, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(flights)
                                    .addComponent(bagdetails, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(userprofile, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(21, 21, 21))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(feedback, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel6)
                            .addComponent(jLabel17)
                            .addComponent(jLabel16)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(payment, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(167, 167, 167))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(29, 29, 29))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(151, 151, 151)
                                .addComponent(jLabel5))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(settings)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(logout)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dashboard)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(flights))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel12)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bagdetails)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(payment)
                    .addComponent(jLabel14))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel6))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addComponent(feedback)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(notification)
                                .addComponent(jLabel17))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(userprofile)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3)
                            .addGap(97, 97, 97))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 175, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(settings, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(8, 8, 8)))
                .addGap(22, 22, 22)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(logout)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jLabel4)
                        .addContainerGap(148, Short.MAX_VALUE))))
        );

        jPanel4.setBackground(new java.awt.Color(0, 204, 255));

        searchflight.setText("                                                                                                                                                                                                             Search Flights ");
        searchflight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchflightActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(0, 153, 255));

        to.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toActionPerformed(evt);
            }
        });

        date.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dateFocusGained(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel37.setText("From:");

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel38.setText("To:");

        ticket.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ticketFocusGained(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel39.setText("Result");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel30.setText("Date:");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel31.setText("Ticket:");

        from.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fromActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(from, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 246, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addGap(100, 100, 100)
                                .addComponent(ticket, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel38)
                                .addGap(45, 45, 45)
                                .addComponent(to, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(167, 167, 167))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(to, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37)
                    .addComponent(jLabel38)
                    .addComponent(from, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ticket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jLabel39)
                .addContainerGap())
        );

        mountainflights.setText("                            Mountain Flight");
        mountainflights.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mountainflightsActionPerformed(evt);
            }
        });

        roundtrip.setText("                                Round trip");
        roundtrip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundtripActionPerformed(evt);
            }
        });

        oneway.setText("                          One way");
        oneway.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onewayActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(51, 153, 255));

        jLabel97.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Nepal_Airlines (2).png"))); // NOI18N

        jLabel116.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel116.setText("Nepal airlines");

        flighttimeduration2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        flighttimeduration2.setText("Duration: 2hr - 30m");
        flighttimeduration2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                flighttimeduration2FocusGained(evt);
            }
        });

        destinationtime1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        destinationtime1.setText("7:00 AM");
        destinationtime1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                destinationtime1FocusGained(evt);
            }
        });

        source1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        source1.setText("Kathmandu");
        source1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                source1FocusGained(evt);
            }
        });

        destination1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        destination1.setText("Pokhara");
        destination1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                destination1FocusGained(evt);
            }
        });

        sourcetime1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        sourcetime1.setText("5:00 AM");
        sourcetime1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                sourcetime1FocusGained(evt);
            }
        });

        jLabel101.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Nepal_Airlines (2).png"))); // NOI18N

        jLabel102.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Nepal_Airlines (2).png"))); // NOI18N

        source2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        source2.setText("Kathmandu");
        source2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                source2FocusGained(evt);
            }
        });

        sourcetime2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        sourcetime2.setText("9:00 AM");
        sourcetime2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                sourcetime2FocusGained(evt);
            }
        });

        jLabel118.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel118.setText("Nepal airlines");

        flighttimeduration1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        flighttimeduration1.setText("Duration: 2hr");
        flighttimeduration1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                flighttimeduration1FocusGained(evt);
            }
        });

        destination2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        destination2.setText("Janakpur");
        destination2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                destination2FocusGained(evt);
            }
        });

        destinationtime2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        destinationtime2.setText("11:30 AM");
        destinationtime2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                destinationtime2FocusGained(evt);
            }
        });

        paymant2.setActionCommand("25k RS Book Now");
        paymant2.setBackground(new java.awt.Color(0, 0, 255));
        paymant2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        paymant2.setLabel("45k RS Book Now");
        paymant2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymant2ActionPerformed(evt);
            }
        });

        source3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        source3.setText("lumbini");
        source3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                source3FocusGained(evt);
            }
        });

        sourcetime3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        sourcetime3.setText("2:00 PM");
        sourcetime3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                sourcetime3FocusGained(evt);
            }
        });

        destination3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        destination3.setText("Kathmandu");
        destination3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                destination3FocusGained(evt);
            }
        });

        destinationtime3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        destinationtime3.setText("4:45 PM");
        destinationtime3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                destinationtime3FocusGained(evt);
            }
        });

        jLabel144.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel144.setText("Nepal airlines");

        flighttimeduration3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        flighttimeduration3.setText("Duration: 2hr- 45m");
        flighttimeduration3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                flighttimeduration3FocusGained(evt);
            }
        });

        paymant3.setBackground(new java.awt.Color(0, 0, 255));
        paymant3.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        paymant3.setLabel("20k RS Book now");
        paymant3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymant3ActionPerformed(evt);
            }
        });

        paymant1.setActionCommand("25k RS Book Now");
        paymant1.setBackground(new java.awt.Color(0, 0, 255));
        paymant1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        paymant1.setLabel("40k RS Book Now");
        paymant1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymant1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel97)
                            .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(source1)
                            .addComponent(source2)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sourcetime1)
                                    .addComponent(sourcetime2))))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(jLabel118))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addComponent(paymant2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(51, 51, 51)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel116)
                                            .addComponent(flighttimeduration1))))
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(flighttimeduration2)
                                        .addComponent(paymant1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(33, 33, 33))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sourcetime3)
                            .addComponent(source3))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel144)
                                .addGap(57, 57, 57))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(paymant3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(flighttimeduration3))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(destination2)
                    .addComponent(destination3)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(destination1)
                        .addComponent(destinationtime1))
                    .addComponent(destinationtime2)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(destinationtime3)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel97)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(source1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sourcetime1))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel116)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(flighttimeduration1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(paymant1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(destination1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(destinationtime1)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel101)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(source2)
                                    .addComponent(destination2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sourcetime2)
                                    .addComponent(destinationtime2))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel102)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(source3)
                                    .addComponent(destination3, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sourcetime3)
                                    .addComponent(destinationtime3)))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel118)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(flighttimeduration2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(paymant2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(jLabel144)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(flighttimeduration3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(paymant3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        paymant2.getAccessibleContext().setAccessibleName("30k RS Book Now");

        jPanel7.setBackground(new java.awt.Color(51, 153, 255));

        jLabel98.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Nepal_Airlines (2).png"))); // NOI18N

        paymant4.setBackground(new java.awt.Color(0, 0, 255));
        paymant4.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        paymant4.setLabel("20K RS Book Now");
        paymant4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymant4ActionPerformed(evt);
            }
        });

        jLabel120.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel120.setText("Nepal airlines");

        flighttimeduration5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        flighttimeduration5.setText("Duration: 2h - 15m");
        flighttimeduration5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                flighttimeduration5FocusGained(evt);
            }
        });

        destinationtime4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        destinationtime4.setText("7:45 AM");
        destinationtime4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                destinationtime4FocusGained(evt);
            }
        });

        source4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        source4.setText("Bhaktapur");
        source4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                source4FocusGained(evt);
            }
        });

        destination4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        destination4.setText("Kathmandu");
        destination4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                destination4FocusGained(evt);
            }
        });

        sourcetime4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        sourcetime4.setText("6:00 AM");
        sourcetime4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                sourcetime4FocusGained(evt);
            }
        });

        jLabel103.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Nepal_Airlines (2).png"))); // NOI18N

        jLabel104.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Nepal_Airlines (2).png"))); // NOI18N

        source5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        source5.setText("Koteshwor");
        source5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                source5FocusGained(evt);
            }
        });

        sourcetime5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        sourcetime5.setText("3:00 PM");
        sourcetime5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                sourcetime5FocusGained(evt);
            }
        });

        jLabel125.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel125.setText("Nepal airlines");

        flighttimeduration4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        flighttimeduration4.setText("Duration: 1h - 45m");
        flighttimeduration4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                flighttimeduration4FocusGained(evt);
            }
        });

        destination5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        destination5.setText("Chitwan");
        destination5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                destination5FocusGained(evt);
            }
        });

        destinationtime5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        destinationtime5.setText("5:15 PM");
        destinationtime5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                destinationtime5FocusGained(evt);
            }
        });

        paymant5.setBackground(new java.awt.Color(0, 0, 255));
        paymant5.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        paymant5.setLabel("45K RS Book Now");
        paymant5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymant5ActionPerformed(evt);
            }
        });

        source6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        source6.setText("Kathmandu");
        source6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                source6FocusGained(evt);
            }
        });

        sourcetime6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        sourcetime6.setText("7:00 PM");
        sourcetime6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                sourcetime6FocusGained(evt);
            }
        });

        destination6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        destination6.setText("Mustang");
        destination6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                destination6FocusGained(evt);
            }
        });

        destinationtime6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        destinationtime6.setText("9:30 PM");
        destinationtime6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                destinationtime6FocusGained(evt);
            }
        });

        jLabel149.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel149.setText("Nepal airlines");

        flighttimeduration6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        flighttimeduration6.setText("Duration: 2h - 30m");
        flighttimeduration6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                flighttimeduration6FocusGained(evt);
            }
        });

        paymant6.setBackground(new java.awt.Color(0, 0, 255));
        paymant6.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        paymant6.setLabel("30k RS Book Now");
        paymant6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymant6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel98)
                                .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(source4)
                                .addComponent(source5)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(sourcetime4)
                                        .addComponent(sourcetime5))))
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGap(43, 43, 43)
                                    .addComponent(jLabel120))
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGap(33, 33, 33)
                                    .addComponent(flighttimeduration4))
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGap(45, 45, 45)
                                    .addComponent(jLabel125))
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGap(21, 21, 21)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(paymant5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(flighttimeduration5)))))
                        .addComponent(paymant4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(flighttimeduration6)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(source6)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(sourcetime6)))
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(paymant6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGap(44, 44, 44)
                                    .addComponent(jLabel149))))))
                .addGap(33, 33, 33)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(destinationtime5)
                        .addGap(14, 14, 14))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(destination5)
                                        .addComponent(destinationtime4))
                                    .addGap(9, 9, 9))
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addComponent(destination6)
                                    .addGap(6, 6, 6))))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(destination4)
                            .addGap(1, 1, 1))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                            .addComponent(destinationtime6)
                            .addGap(10, 10, 10))))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel120)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(flighttimeduration4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(paymant4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(source4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sourcetime4))
                            .addComponent(jLabel98)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(destination4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(destinationtime4)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel103)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(source5)
                                    .addComponent(destination5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sourcetime5)
                                    .addComponent(destinationtime5))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel104)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(source6)
                                    .addComponent(destination6, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sourcetime6)
                                    .addComponent(destinationtime6)))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel125)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(flighttimeduration5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(paymant5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(jLabel149)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(flighttimeduration6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(paymant6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(51, 153, 255));

        jLabel99.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Nepal_Airlines (2).png"))); // NOI18N

        paymant7.setBackground(new java.awt.Color(0, 0, 255));
        paymant7.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        paymant7.setLabel("35k RS Book Now");
        paymant7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymant7ActionPerformed(evt);
            }
        });

        jLabel127.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel127.setText("Nepal airlines");

        flighttimeduration8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        flighttimeduration8.setText("Duration: 2hr - 30m");
        flighttimeduration8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                flighttimeduration8FocusGained(evt);
            }
        });

        destinationtime7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        destinationtime7.setText("2:30 PM");
        destinationtime7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                destinationtime7FocusGained(evt);
            }
        });

        source7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        source7.setText("Pokhara");
        source7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                source7FocusGained(evt);
            }
        });

        destination7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        destination7.setText("Baneshwor");
        destination7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                destination7FocusGained(evt);
            }
        });

        sourcetime7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        sourcetime7.setText("12:00 PM");
        sourcetime7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                sourcetime7FocusGained(evt);
            }
        });

        jLabel105.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Nepal_Airlines (2).png"))); // NOI18N

        jLabel106.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Nepal_Airlines (2).png"))); // NOI18N

        source8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        source8.setText("Kathmandu");
        source8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                source8FocusGained(evt);
            }
        });

        sourcetime8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        sourcetime8.setText("8:00 AM");
        sourcetime8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                sourcetime8FocusGained(evt);
            }
        });

        jLabel132.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel132.setText("Nepal airlines");

        flighttimeduration7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        flighttimeduration7.setText("Duration: 2h - 30m");
        flighttimeduration7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                flighttimeduration7FocusGained(evt);
            }
        });

        destination8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        destination8.setText("Lalitpur");
        destination8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                destination8FocusGained(evt);
            }
        });

        paymant8.setActionCommand("40k RS Book Now");
        paymant8.setBackground(new java.awt.Color(0, 0, 255));
        paymant8.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        paymant8.setLabel("40k RS Book Now");
        paymant8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymant8ActionPerformed(evt);
            }
        });

        source9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        source9.setText("Kathmandu");
        source9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                source9FocusGained(evt);
            }
        });

        sourcetime9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        sourcetime9.setText("9:00 AM");
        sourcetime9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                sourcetime9FocusGained(evt);
            }
        });

        destination9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        destination9.setText("Dulikhel");
        destination9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                destination9FocusGained(evt);
            }
        });

        destinationtime9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        destinationtime9.setText("11:00 AM");
        destinationtime9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                destinationtime9FocusGained(evt);
            }
        });

        jLabel154.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel154.setText("Nepal airlines");

        flighttimeduration9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        flighttimeduration9.setText("Duration: 2hr");
        flighttimeduration9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                flighttimeduration9FocusGained(evt);
            }
        });

        paymant9.setBackground(new java.awt.Color(0, 0, 255));
        paymant9.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        paymant9.setLabel("50k RS Book Now");
        paymant9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymant9ActionPerformed(evt);
            }
        });

        destinationtime8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        destinationtime8.setText("10:30 AM");
        destinationtime8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                destinationtime8FocusGained(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel99)
                            .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(source8)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sourcetime8)
                                    .addComponent(sourcetime7)
                                    .addComponent(source7))))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabel127))
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addGap(30, 30, 30)
                                    .addComponent(flighttimeduration7))
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addGap(52, 52, 52)
                                    .addComponent(jLabel132))
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addGap(31, 31, 31)
                                    .addComponent(flighttimeduration8))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                    .addGap(21, 21, 21)
                                    .addComponent(paymant8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(paymant7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(source9)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(sourcetime9)))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(flighttimeduration9)
                                    .addComponent(jLabel154)))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(paymant9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(destination7)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(destinationtime7)
                                    .addComponent(destinationtime9)
                                    .addComponent(destination9))))
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(destinationtime8)
                            .addComponent(destination8))
                        .addGap(24, 24, 24))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel127)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(flighttimeduration7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(paymant7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(source7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sourcetime7))
                            .addComponent(jLabel99)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(destination7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(destinationtime7)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel105)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(source8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sourcetime8)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel106)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(source9)
                                    .addComponent(destination9, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sourcetime9)
                                    .addComponent(destinationtime9)))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel132)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(flighttimeduration8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(paymant8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(jLabel154)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(flighttimeduration9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(paymant9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(destination8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(destinationtime8)
                                .addGap(175, 175, 175)))))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        multicity.setText("                           Multi City");
        multicity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                multicityActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                    .addComponent(mountainflights, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(multicity, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(roundtrip, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(oneway, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(searchflight, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1226, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(215, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mountainflights, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(roundtrip, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(oneway, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(multicity, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchflight, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(660, 660, 660))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 781, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1550, 890);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void onewayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onewayActionPerformed
        // Set one-way flight mode
        oneway.setBackground(new Color(0, 102, 204));
        oneway.setForeground(Color.WHITE);
        roundtrip.setBackground(Color.WHITE);
        roundtrip.setForeground(Color.BLACK);
        mountainflights.setBackground(Color.WHITE);
        mountainflights.setForeground(Color.BLACK);
        multicity.setBackground(Color.WHITE);
        multicity.setForeground(Color.BLACK);
        
        JOptionPane.showMessageDialog(this, 
            "One-way flight selected.\n\n" +
            "Only departure date required.\n" +
            "No return date needed.",
            "One Way Flight",
            JOptionPane.INFORMATION_MESSAGE);
        
        logger.info("One-way flight mode selected");
    }//GEN-LAST:event_onewayActionPerformed

    private void roundtripActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundtripActionPerformed
         // Set round-trip flight mode
        roundtrip.setBackground(new Color(0, 102, 204));
        roundtrip.setForeground(Color.WHITE);
        oneway.setBackground(Color.WHITE);
        oneway.setForeground(Color.BLACK);
        mountainflights.setBackground(Color.WHITE);
        mountainflights.setForeground(Color.BLACK);
        multicity.setBackground(Color.WHITE);
        multicity.setForeground(Color.BLACK);
        
        // Ask for return date
        String returnDate = JOptionPane.showInputDialog(this,
            "Enter return date (DD/MM/YYYY):",
            "Round Trip - Return Date",
            JOptionPane.QUESTION_MESSAGE);
        
        if (returnDate != null && !returnDate.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Round trip selected!\n" +
                "Departure: " + date.getText() + "\n" +
                "Return: " + returnDate,
                "Round Trip",
                JOptionPane.INFORMATION_MESSAGE);
            logger.info("Round trip selected with return date: " + returnDate);
        }
    }//GEN-LAST:event_roundtripActionPerformed

    private void mountainflightsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mountainflightsActionPerformed
        // Set mountain flights mode
        mountainflights.setBackground(new Color(0, 102, 204));
        mountainflights.setForeground(Color.WHITE);
        oneway.setBackground(Color.WHITE);
        oneway.setForeground(Color.BLACK);
        roundtrip.setBackground(Color.WHITE);
        roundtrip.setForeground(Color.BLACK);
        multicity.setBackground(Color.WHITE);
        multicity.setForeground(Color.BLACK);
        
        // Show mountain flight destinations
        String[] destinations = {
            "Mount Everest Base Camp",
            "Annapurna Base Camp",
            "Langtang Valley",
            "Mardi Himal",
            "Manaslu Circuit"
        };
        
        String selected = (String) JOptionPane.showInputDialog(this,
            "Select mountain destination:",
            "Mountain Flights",
            JOptionPane.QUESTION_MESSAGE,
            null,
            destinations,
            destinations[0]);
        
        if (selected != null) {
            to.setText(selected);
            JOptionPane.showMessageDialog(this,
                "Mountain flight to " + selected + " selected!\n" +
                "Duration: 1-2 hours\n" +
                "Price: 50,000 - 100,000 RS",
                "Mountain Flight",
                JOptionPane.INFORMATION_MESSAGE);
            logger.info("Mountain flight selected to: " + selected);
        }
    }//GEN-LAST:event_mountainflightsActionPerformed

    private void searchflightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchflightActionPerformed
         // Simple search implementation
        String departure = from.getText().trim();
        String arrival = to.getText().trim();
        String travelDate = date.getText().trim();
        int passengers = (int) ticket.getValue();
        
        if (departure.isEmpty() || arrival.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter both departure and arrival locations",
                "Missing Information",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (departure.equalsIgnoreCase(arrival)) {
            JOptionPane.showMessageDialog(this,
                "Departure and arrival cannot be the same",
                "Invalid Route",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (travelDate.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please select a travel date",
                "Missing Date",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Show search results
        String message = String.format(
            "Flight Search Results:\n\n" +
            "Departure: %s\n" +
            "Arrival: %s\n" +
            "Date: %s\n" +
            "Passengers: %d\n\n" +
            "Available Flights:\n" +
            "1. Nepal Airlines - 6:00 AM - 40,000 RS\n" +
            "2. Buddha Air - 9:30 AM - 45,000 RS\n" +
            "3. Yeti Airlines - 2:15 PM - 38,000 RS\n\n" +
            "Select a flight to book.",
            departure, arrival, travelDate, passengers
        );
        
        JOptionPane.showMessageDialog(this, message, 
            "Search Results", JOptionPane.INFORMATION_MESSAGE);
        logger.info("Flight search: " + departure + " to " + arrival);
    }//GEN-LAST:event_searchflightActionPerformed

    private void dateFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateFocusGained
         if (date.getText().equals("Date") || date.getText().isEmpty()) {
            date.setToolTipText("Enter date as DD/MM/YYYY or click to select");
            date.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        }
    }//GEN-LAST:event_dateFocusGained

    private void toActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toActionPerformed
         // Auto-format and validate arrival city
        String toText = to.getText().trim().toUpperCase();
        to.setText(toText);
        
        // Popular destinations suggestion
        String[] popular = {"KATHMANDU", "POKHARA", "JANAKPUR", "LUMBINI", "MUSTANG", "CHITWAN"};
        for (String city : popular) {
            if (toText.startsWith(city.substring(0, 3))) {
                to.setText(city);
                break;
            }
        }
    }//GEN-LAST:event_toActionPerformed
  
    private void ticketFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ticketFocusGained
        ticket.setToolTipText("Select number of passengers (1-10)");
        // Show group discount information
        if ((int) ticket.getValue() >= 4) {
            JOptionPane.showMessageDialog(this,
                "Group discount available!\n" +
                "4+ passengers: 10% discount\n" +
                "8+ passengers: 15% discount",
                "Group Discount",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_ticketFocusGained

    private void paymant9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymant9ActionPerformed
         // Flight 9: Kathmandu to Dulikhel
        handleFlightBooking9();
    }//GEN-LAST:event_paymant9ActionPerformed

    private void flighttimeduration9FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_flighttimeduration9FocusGained
         flighttimeduration9.setToolTipText("Flight Duration: 2 hours");
    }//GEN-LAST:event_flighttimeduration9FocusGained

    private void destinationtime9FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_destinationtime9FocusGained
         destinationtime9.setToolTipText("Arrival Time: 11:00 AM");
    }//GEN-LAST:event_destinationtime9FocusGained

    private void destination9FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_destination9FocusGained
         destination9.setToolTipText("Arrival: Dulikhel Airport");
    }//GEN-LAST:event_destination9FocusGained

    private void sourcetime9FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sourcetime9FocusGained
       sourcetime9.setToolTipText("Departure Time: 9:00 AM");
    }//GEN-LAST:event_sourcetime9FocusGained

    private void source9FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_source9FocusGained
        source9.setToolTipText("Departure: Tribhuvan International Airport (KTM)");
    }//GEN-LAST:event_source9FocusGained

    private void paymant8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymant8ActionPerformed
        // Flight 8: Kathmandu to Lalitpur
        handleFlightBooking8();
    }//GEN-LAST:event_paymant8ActionPerformed
   
    private void flighttimeduration7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_flighttimeduration7FocusGained
         flighttimeduration7.setToolTipText("Flight Duration: 2 hours 30 minutes");
    }//GEN-LAST:event_flighttimeduration7FocusGained

    private void source8FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_source8FocusGained
        source8.setToolTipText("Departure: Tribhuvan International Airport (KTM)");
    }//GEN-LAST:event_source8FocusGained

    private void sourcetime7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sourcetime7FocusGained
        sourcetime7.setToolTipText("Departure Time: 12:00 PM");
    }//GEN-LAST:event_sourcetime7FocusGained

    private void destination7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_destination7FocusGained
        destination7.setToolTipText("Arrival: Baneshwor Helipad");
    }//GEN-LAST:event_destination7FocusGained

    private void source7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_source7FocusGained
       source7.setToolTipText("Departure: Pokhara Airport (PKR)");
    }//GEN-LAST:event_source7FocusGained

    private void destinationtime7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_destinationtime7FocusGained
         destinationtime7.setToolTipText("Arrival Time: 2:30 PM");
    }//GEN-LAST:event_destinationtime7FocusGained

    private void paymant7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymant7ActionPerformed
          handleFlightBooking7();
    }//GEN-LAST:event_paymant7ActionPerformed

    private void paymant6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymant6ActionPerformed
         handleFlightBooking6();
    }//GEN-LAST:event_paymant6ActionPerformed

    private void flighttimeduration6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_flighttimeduration6FocusGained
       flighttimeduration6.setToolTipText("Flight Duration: 2 hours 30 minutes");
    }//GEN-LAST:event_flighttimeduration6FocusGained

    private void destinationtime6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_destinationtime6FocusGained
        destinationtime6.setToolTipText("Arrival Time: 9:30 PM");
    }//GEN-LAST:event_destinationtime6FocusGained

    private void destination6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_destination6FocusGained
         destination6.setToolTipText("Arrival: Mustang Airport");
    }//GEN-LAST:event_destination6FocusGained

    private void sourcetime6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sourcetime6FocusGained
        sourcetime6.setToolTipText("Departure Time: 7:00 PM");
    }//GEN-LAST:event_sourcetime6FocusGained

    private void source6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_source6FocusGained
         source6.setToolTipText("Departure: Tribhuvan International Airport (KTM)");
    }//GEN-LAST:event_source6FocusGained

    private void paymant5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymant5ActionPerformed
       handleFlightBooking5();
    }//GEN-LAST:event_paymant5ActionPerformed

    private void destinationtime5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_destinationtime5FocusGained
        destinationtime5.setToolTipText("Arrival Time: 5:15 PM");
    }//GEN-LAST:event_destinationtime5FocusGained

    private void destination5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_destination5FocusGained
       destination5.setToolTipText("Arrival: Chitwan Airport"); 
    }//GEN-LAST:event_destination5FocusGained

    private void sourcetime5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sourcetime5FocusGained
         sourcetime5.setToolTipText("Departure Time: 3:00 PM");
    }//GEN-LAST:event_sourcetime5FocusGained

    private void source5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_source5FocusGained
        source5.setToolTipText("Departure: Koteshwor Helipad");
    }//GEN-LAST:event_source5FocusGained

    private void sourcetime4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sourcetime4FocusGained
        sourcetime4.setToolTipText("Departure Time: 6:00 AM");
    }//GEN-LAST:event_sourcetime4FocusGained

    private void source4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_source4FocusGained
       source4.setToolTipText("Departure: Bhaktapur Airport");
    }//GEN-LAST:event_source4FocusGained

    private void flighttimeduration5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_flighttimeduration5FocusGained
        flighttimeduration5.setToolTipText("Flight Duration: 2 hours 15 minutes");
    }//GEN-LAST:event_flighttimeduration5FocusGained

    private void paymant4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymant4ActionPerformed
         // Flight 4: Bhaktapur to Kathmandu
            handleFlightBooking4();
    }//GEN-LAST:event_paymant4ActionPerformed

    private void paymant3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymant3ActionPerformed
         // Flight 3: Kunbini to Kathmandu
         handleFlightBooking3();
    }//GEN-LAST:event_paymant3ActionPerformed

    private void flighttimeduration3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_flighttimeduration3FocusGained
         flighttimeduration3.setToolTipText("Flight Duration: 2 hours 45 minutes");
    }//GEN-LAST:event_flighttimeduration3FocusGained

    private void destinationtime3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_destinationtime3FocusGained
         destinationtime3.setToolTipText("Arrival Time: 4:45 PM");
    }//GEN-LAST:event_destinationtime3FocusGained

    private void destination3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_destination3FocusGained
        destination3.setToolTipText("Arrival: Tribhuvan International Airport (KTM)");
    }//GEN-LAST:event_destination3FocusGained

    private void sourcetime3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sourcetime3FocusGained
         sourcetime3.setToolTipText("Departure Time: 2:00 PM");
    }//GEN-LAST:event_sourcetime3FocusGained

    private void source3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_source3FocusGained
         source3.setToolTipText("Departure: Lumbini Airport (LUA)");
    }//GEN-LAST:event_source3FocusGained

    private void destinationtime2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_destinationtime2FocusGained
        destinationtime2.setToolTipText("Arrival Time: 11:30 AM");
    }//GEN-LAST:event_destinationtime2FocusGained

    private void destination2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_destination2FocusGained
        destination2.setToolTipText("Arrival: Janakpur Airport (JKR)");
    }//GEN-LAST:event_destination2FocusGained

    private void flighttimeduration1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_flighttimeduration1FocusGained
        flighttimeduration1.setToolTipText("Flight Duration: 2 hours");
    }//GEN-LAST:event_flighttimeduration1FocusGained

    private void sourcetime2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sourcetime2FocusGained
        sourcetime2.setToolTipText("Departure Time: 9:00 AM");
    }//GEN-LAST:event_sourcetime2FocusGained

    private void source2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_source2FocusGained
        source2.setToolTipText("Departure: Tribhuvan International Airport (KTM)");
    }//GEN-LAST:event_source2FocusGained

    private void sourcetime1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sourcetime1FocusGained
        sourcetime1.setToolTipText("Departure Time: 5:00 AM");
    }//GEN-LAST:event_sourcetime1FocusGained

    private void destination1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_destination1FocusGained
        destination1.setToolTipText("Arrival: Pokhara Airport (PKR)");
    }//GEN-LAST:event_destination1FocusGained

    private void source1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_source1FocusGained
        source1.setToolTipText("Departure: Tribhuvan International Airport (KTM)");
    }//GEN-LAST:event_source1FocusGained

    private void flighttimeduration2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_flighttimeduration2FocusGained
        flighttimeduration2.setToolTipText("Flight Duration: 2 hours 30 minutes");
    }//GEN-LAST:event_flighttimeduration2FocusGained

    private void flighttimeduration8FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_flighttimeduration8FocusGained
       flighttimeduration8.setToolTipText("Flight Duration: 2 hours 30 minutes");
    }//GEN-LAST:event_flighttimeduration8FocusGained

    private void flighttimeduration4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_flighttimeduration4FocusGained
        flighttimeduration4.setToolTipText("Flight Duration: 1 hour 45 minutes");
    }//GEN-LAST:event_flighttimeduration4FocusGained

    private void destinationtime4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_destinationtime4FocusGained
         destinationtime4.setToolTipText("Arrival Time: 7:45 AM");
    }//GEN-LAST:event_destinationtime4FocusGained

    private void destination4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_destination4FocusGained
         destination4.setToolTipText("Arrival: Tribhuvan International Airport (KTM)");
    }//GEN-LAST:event_destination4FocusGained

    private void sourcetime8FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sourcetime8FocusGained
       sourcetime8.setToolTipText("Departure Time: 8:00 AM");
    }//GEN-LAST:event_sourcetime8FocusGained

    private void userprofileFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_userprofileFocusGained
         showProfileView();
    }//GEN-LAST:event_userprofileFocusGained

    private void notificationFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_notificationFocusGained
        showNotificationsView();
    }//GEN-LAST:event_notificationFocusGained

    private void paymentFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_paymentFocusGained
         showPaymentsView();
    }//GEN-LAST:event_paymentFocusGained

    private void dashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardMouseClicked
          showDashboardView();
    }//GEN-LAST:event_dashboardMouseClicked

    private void flightsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_flightsMouseClicked
          showFlightsView();
    }//GEN-LAST:event_flightsMouseClicked

    private void paymentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paymentMouseClicked
          showPaymentsView();
    }//GEN-LAST:event_paymentMouseClicked

    private void paymentFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_paymentFocusLost
        // TODO add your handling code here:
       
    }//GEN-LAST:event_paymentFocusLost

    private void notificationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notificationMouseClicked
          showNotificationsView();
    }//GEN-LAST:event_notificationMouseClicked

    private void userprofileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userprofileMouseClicked
        showProfileView();
    }//GEN-LAST:event_userprofileMouseClicked

    private void settingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsMouseClicked
       showSettingsView();
    }//GEN-LAST:event_settingsMouseClicked

    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
         int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to logout?",
            "Confirm Logout",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            // Save session data
            saveSessionData();
            
            // Clear user session
            UserSession.getInstance().logout();
            
            // Return to login screen
            java.awt.EventQueue.invokeLater(() -> {
                Login login = new Login();
                login.setVisible(true);
                login.setLocationRelativeTo(null);
                this.dispose();
            });
            
            logger.info("User logged out");
        }
    }//GEN-LAST:event_logoutMouseClicked

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
         // Go back to previous view or main dashboard
        if (currentView.equals("flights")) {
            showDashboardView();
        } else {
            showFlightsView();
        }// Go back to previous view or main dashboard
        if (currentView.equals("flights")) {
            showDashboardView();
        } else {
            showFlightsView();
        }
    }//GEN-LAST:event_backMouseClicked

    private void multicityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_multicityActionPerformed
        // TODO add your handling code here:
        multicity.setBackground(new Color(0, 102, 204));
        multicity.setForeground(Color.WHITE);
        oneway.setBackground(Color.WHITE);
        oneway.setForeground(Color.BLACK);
        roundtrip.setBackground(Color.WHITE);
        roundtrip.setForeground(Color.BLACK);
        mountainflights.setBackground(Color.WHITE);
        mountainflights.setForeground(Color.BLACK);
        
        // Show multi-city booking dialog
        JOptionPane.showMessageDialog(this,
            "Multi-City Booking:\n\n" +
            "Plan complex itineraries with multiple stops.\n\n" +
            "Example:\n" +
            "1. Kathmandu → Pokhara\n" +
            "2. Pokhara → Janakpur\n" +
            "3. Janakpur → Kathmandu\n\n" +
            "This feature is currently under development.",
            "Multi-City Booking",
            JOptionPane.INFORMATION_MESSAGE);
        
        logger.info("Multi-city booking selected");
    }//GEN-LAST:event_multicityActionPerformed

    private void saveSessionData() {
        try {
            FileWriter fw = new FileWriter("session_data.txt", true);
            fw.write("User: " + UserSession.getInstance().getUserEmail() + "\n");
            fw.write("Logout Time: " + 
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\n");
            fw.write("Current View: " + currentView + "\n");
            fw.write("========================\n");
            fw.close();
        } catch (IOException e) {
            logger.warning("Failed to save session data: " + e.getMessage());
        }
    }
    private void destinationtime1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_destinationtime1FocusGained
         destinationtime1.setToolTipText("Arrival Time: 7:00 AM");
    }//GEN-LAST:event_destinationtime1FocusGained

    private void fromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fromActionPerformed
        // TODO add your handling code here:
        // Auto-format departure city
        String fromText = from.getText().trim().toUpperCase();
        from.setText(fromText);
        
        // Auto-focus to arrival field
        to.requestFocus();
        to.selectAll();
    }//GEN-LAST:event_fromActionPerformed

    private void destination8FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_destination8FocusGained
        destination8.setToolTipText("Arrival: Lalitpur Airport");
    }//GEN-LAST:event_destination8FocusGained

    private void destinationtime8FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_destinationtime8FocusGained
        // TODO add your handling code here:
         destinationtime8.setToolTipText("Arrival Time: 10:30 AM");
    }//GEN-LAST:event_destinationtime8FocusGained

    private void feedbackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_feedbackMouseClicked
        // TODO add your handling code here:
          // Simple feedback dialog option
    JTextArea feedbackArea = new JTextArea(5, 30);
    feedbackArea.setLineWrap(true);
    feedbackArea.setWrapStyleWord(true);
    
    // Ask for rating
    JComboBox<String> ratingCombo = new JComboBox<>(new String[]{
        "⭐ - Poor", "⭐⭐ - Fair", "⭐⭐⭐ - Good", "⭐⭐⭐⭐ - Very Good", "⭐⭐⭐⭐⭐ - Excellent"
    });
    
    JPanel panel = new JPanel(new BorderLayout(5, 5));
    panel.add(new JLabel("Please rate your experience:"), BorderLayout.NORTH);
    panel.add(ratingCombo, BorderLayout.CENTER);
    panel.add(new JScrollPane(feedbackArea), BorderLayout.SOUTH);
    
    int result = JOptionPane.showConfirmDialog(
        this, 
        panel, 
        "Submit Feedback", 
        JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.PLAIN_MESSAGE
    );
    
    if (result == JOptionPane.OK_OPTION && !feedbackArea.getText().trim().isEmpty()) {
        String rating = (String) ratingCombo.getSelectedItem();
        String feedback = feedbackArea.getText().trim();
        
        // Save feedback to list
        String feedbackRecord = "Feedback - " + 
            new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()) + 
            " - " + rating + " - " + feedback;
        
        // Add to notifications
        notifications.add(0, "Feedback submitted: " + rating);
        
        JOptionPane.showMessageDialog(
            this, 
            "Thank you for your feedback!\nRating: " + rating + "\nWe appreciate your input.",
            "Feedback Submitted",
            JOptionPane.INFORMATION_MESSAGE
        );
        
        logger.info("Feedback submitted: " + rating);
    }
    }//GEN-LAST:event_feedbackMouseClicked

    private void paymant2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymant2ActionPerformed
        // Flight 2: Kathmandu to Janakpur
        handleFlightBooking2();
    }//GEN-LAST:event_paymant2ActionPerformed

    private void paymant1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymant1ActionPerformed
        // TODO add your handling code here:
        handleFlightBooking1();
    }//GEN-LAST:event_paymant1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel back;
    private javax.swing.JLabel bagdetails;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel dashboard;
    private javax.swing.JTextField date;
    private javax.swing.JLabel destination1;
    private javax.swing.JLabel destination2;
    private javax.swing.JLabel destination3;
    private javax.swing.JLabel destination4;
    private javax.swing.JLabel destination5;
    private javax.swing.JLabel destination6;
    private javax.swing.JLabel destination7;
    private javax.swing.JLabel destination8;
    private javax.swing.JLabel destination9;
    private javax.swing.JLabel destinationtime1;
    private javax.swing.JLabel destinationtime2;
    private javax.swing.JLabel destinationtime3;
    private javax.swing.JLabel destinationtime4;
    private javax.swing.JLabel destinationtime5;
    private javax.swing.JLabel destinationtime6;
    private javax.swing.JLabel destinationtime7;
    private javax.swing.JLabel destinationtime8;
    private javax.swing.JLabel destinationtime9;
    private javax.swing.JLabel feedback;
    private javax.swing.JLabel flights;
    private javax.swing.JLabel flighttimeduration1;
    private javax.swing.JLabel flighttimeduration2;
    private javax.swing.JLabel flighttimeduration3;
    private javax.swing.JLabel flighttimeduration4;
    private javax.swing.JLabel flighttimeduration5;
    private javax.swing.JLabel flighttimeduration6;
    private javax.swing.JLabel flighttimeduration7;
    private javax.swing.JLabel flighttimeduration8;
    private javax.swing.JLabel flighttimeduration9;
    private javax.swing.JTextField from;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel logout;
    private javax.swing.JTextField mountainflights;
    private javax.swing.JTextField multicity;
    private javax.swing.JLabel notification;
    private javax.swing.JTextField oneway;
    private java.awt.Button paymant1;
    private java.awt.Button paymant2;
    private java.awt.Button paymant3;
    private java.awt.Button paymant4;
    private java.awt.Button paymant5;
    private java.awt.Button paymant6;
    private java.awt.Button paymant7;
    private java.awt.Button paymant8;
    private java.awt.Button paymant9;
    private javax.swing.JLabel payment;
    private javax.swing.JTextField roundtrip;
    private javax.swing.JTextField searchflight;
    private javax.swing.JLabel settings;
    private javax.swing.JLabel source1;
    private javax.swing.JLabel source2;
    private javax.swing.JLabel source3;
    private javax.swing.JLabel source4;
    private javax.swing.JLabel source5;
    private javax.swing.JLabel source6;
    private javax.swing.JLabel source7;
    private javax.swing.JLabel source8;
    private javax.swing.JLabel source9;
    private javax.swing.JLabel sourcetime1;
    private javax.swing.JLabel sourcetime2;
    private javax.swing.JLabel sourcetime3;
    private javax.swing.JLabel sourcetime4;
    private javax.swing.JLabel sourcetime5;
    private javax.swing.JLabel sourcetime6;
    private javax.swing.JLabel sourcetime7;
    private javax.swing.JLabel sourcetime8;
    private javax.swing.JLabel sourcetime9;
    private javax.swing.JSpinner ticket;
    private javax.swing.JTextField to;
    private javax.swing.JLabel userprofile;
    // End of variables declaration//GEN-END:variables
  // Main method for DashBoard
    public static void main(String[] args) {
        try {
            // Set look and feel
            javax.swing.UIManager.setLookAndFeel(
                javax.swing.UIManager.getSystemLookAndFeelClassName());
            
            // Create and display the form
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    // Create test user
                    model.UserSession session = model.UserSession.getInstance();
                    session.login(1, "john.doe@example.com");
                    
                    // Create dashboard
                    DashBoard dashboard = new DashBoard();
                    dashboard.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
                    dashboard.setTitle("Flight Booking Dashboard");
                    dashboard.setLocationRelativeTo(null);
                    
                    // Create controller
                    new controller.DashBoardController(dashboard);
                    
                    dashboard.setVisible(true);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
