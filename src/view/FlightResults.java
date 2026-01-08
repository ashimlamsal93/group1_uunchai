/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;
import model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.*;

public class FlightResults extends JFrame {

    private JPanel resultsPanel;
    private ResultSet rs;
    private User currentUser;
    private int travellers;
    private String returnDate; // null for one-way

    // Updated constructor
    public FlightResults(ResultSet resultSet, User user, int numTravellers, String retDate) {
        this.rs = resultSet;
        this.currentUser = user;
        this.travellers = numTravellers;
        this.returnDate = retDate;

        initComponents();
        setTitle("Flight Results - Uunchai");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        customizeUI();
        loadResults();
    }

    private void customizeUI() {
        flightResults.removeAll();
        flightResults.setLayout(null);
        flightResults.setBackground(new Color(153, 204, 255));

        // Top bar
        JPanel topBar = new JPanel();
        topBar.setBackground(new Color(51, 153, 255));
        topBar.setBounds(0, 0, 2000, 80);
        topBar.setLayout(null);

        JLabel title = new JLabel("Available Flights");
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        title.setBounds(50, 20, 600, 40);
        topBar.add(title);

        // === ADD THIS BACK BUTTON ===
        JButton btnBack = new JButton("←");
        btnBack.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(new Color(51, 153, 255));
        btnBack.setBorderPainted(false);
        btnBack.setFocusPainted(false);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.setBounds(1200, 20, 200, 40);

        // Action: close this window (goes back to dashboard which is still open underneath)
        btnBack.addActionListener(e -> dispose());

        topBar.add(btnBack);
        // ============================

        flightResults.add(topBar);

        // Rest of your code (resultsPanel, scroll, etc.)
        resultsPanel = new JPanel();
        resultsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 30));
        resultsPanel.setBackground(new Color(153, 204, 255));

        JScrollPane scroll = new JScrollPane(resultsPanel);
        scroll.setBounds(50, 100, 1400, 700);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        flightResults.add(scroll);

        revalidate();
        repaint();
    }

    private void loadResults() {
        try {
            boolean found = false;

            // NO rs.beforeFirst() — just read directly
            while (rs.next()) {
                found = true;
                resultsPanel.add(createFlightCard(rs));
            }

            if (!found) {
                JLabel noFlight = new JLabel("No flights found for this route and date");
                noFlight.setFont(new Font("Segoe UI", Font.BOLD, 28));
                noFlight.setForeground(Color.RED);
                noFlight.setHorizontalAlignment(SwingConstants.CENTER);
                resultsPanel.add(noFlight);
            }

            resultsPanel.revalidate();
            resultsPanel.repaint();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading flight data: " + e.getMessage());
            e.printStackTrace();
        }
    }

   private JPanel createFlightCard(ResultSet rs) throws SQLException {
        JPanel card = new JPanel();
        card.setLayout(null);
        card.setPreferredSize(new Dimension(400, 220));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2));

        // Logo
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/images/uunchai_logo.png"));
        Image scaledLogo = logoIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(scaledLogo));
        logo.setBounds(20, 40, 120, 120);
        card.add(logo);

        // Airline Name
        JLabel airline = new JLabel(rs.getString("airline_name"));
        airline.setFont(new Font("Segoe UI", Font.BOLD, 20));
        airline.setBounds(150, 20, 240, 30);
        card.add(airline);

        // Route
        String route = rs.getString("source") + " → " + rs.getString("destination");
        JLabel routeLabel = new JLabel(route);
        routeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        routeLabel.setBounds(150, 60, 240, 30);
        card.add(routeLabel);

        // Time
        String depTime = rs.getString("departure_datetime").substring(11, 16);
        String arrTime = rs.getString("arrival_datetime").substring(11, 16);
        JLabel time = new JLabel(depTime + " → " + arrTime);
        time.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        time.setBounds(150, 90, 240, 30);
        card.add(time);

        // Price & Book Button - PRICE BIG, "Book Now" SMALL
        double price = rs.getDouble("price");
        int flightId = rs.getInt("flight_id");

        // Separate "Book Now" and price for different sizes
        String priceStr = "NPR " + String.format("%,.0f", price);

        JButton bookBtn = new JButton("<html>Book Now - <b><font size='6'>" + priceStr + "</font></b></html>");
        bookBtn.setFont(new Font("Segoe UI", Font.PLAIN, 16));  // base font small
        bookBtn.setBackground(new Color(51, 153, 255));
        bookBtn.setForeground(Color.WHITE);
        bookBtn.setFocusPainted(false);
        bookBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Right side with good margin
        bookBtn.setBounds(140, 140, 240, 60);

        bookBtn.addActionListener(e -> {
            Booking bookingForm = new Booking(
                currentUser,
                flightId,
                route,
                depTime + " → " + arrTime,
                price * travellers,
                travellers
            );
            bookingForm.setVisible(true);
        });

        card.add(bookBtn);

        return card;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        flightResults = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout flightResultsLayout = new javax.swing.GroupLayout(flightResults);
        flightResults.setLayout(flightResultsLayout);
        flightResultsLayout.setHorizontalGroup(
            flightResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 710, Short.MAX_VALUE)
        );
        flightResultsLayout.setVerticalGroup(
            flightResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 543, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(flightResults, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(flightResults, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel flightResults;
    // End of variables declaration//GEN-END:variables
}
