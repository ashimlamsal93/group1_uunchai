/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.FlightModel;
import view.FlightBookingHistory;
import dao.FlightDAO;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FlightController {

    private final FlightBookingHistory view;
    private final FlightDAO dao;

    public FlightController(FlightBookingHistory view) {
        this.view = view;
        this.dao = new FlightDAO();

        // Load data and initialize UI
        loadHistory();
        
        // Setup button listeners (Back button, etc.)
        initNavigation();
    }

    private void initNavigation() {
        JButton backBtn = view.getBtnBack(); 
        if (backBtn != null) {
            backBtn.addActionListener(e -> {
                view.dispose(); 
                System.out.println("Navigating back to Dashboard...");
            });
        }
    }

    public final void loadHistory() {
        JPanel container = (JPanel) view.getFlightListPanel();
        if (container == null) return;

        container.removeAll();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        // --- DEBUGGING: Check console if data is actually coming from DB ---
        System.out.println("Attempting to fetch history for User ID: 1");
        List<FlightModel> flights = dao.getAllHistory(1);
        System.out.println("Flights retrieved from Database: " + flights.size());

        if (flights.isEmpty()) {
            JLabel emptyMsg = new JLabel("No booking history found.");
            emptyMsg.setFont(new Font("Arial", Font.BOLD, 14));
            emptyMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
            container.add(Box.createVerticalGlue()); // Push to center
            container.add(emptyMsg);
            container.add(Box.createVerticalGlue());
        } else {
            for (FlightModel f : flights) {
                container.add(createFlightRow(f));
                container.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing
            }
        }

        container.revalidate();
        container.repaint();
    }

    private JPanel createFlightRow(FlightModel f) {
        // Row layout: 4 columns (From, Flight Status/Icon, To, Action Button)
        JPanel row = new JPanel(new GridLayout(1, 4)); 
        row.setBackground(new Color(135, 190, 255));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        row.setPreferredSize(new Dimension(containerWidth(), 80));
        row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));

        // Data Labels
        row.add(new JLabel("<html><center><b>" + f.getFrom() + "</b><br>" + f.getDepTime() + "</center></html>", SwingConstants.CENTER));
        row.add(new JLabel("<html><center>" + f.getDate() + "<br>••• ✈ •••</center></html>", SwingConstants.CENTER));
        row.add(new JLabel("<html><center><b>" + f.getTo() + "</b><br>" + f.getArrTime() + "</center></html>", SwingConstants.CENTER));

        // --- CANCEL BUTTON LOGIC ---
        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setFocusPainted(false);
        cancelBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Disable button if flight is already cancelled
        if ("CANCELLED".equalsIgnoreCase(f.getStatus())) {
            cancelBtn.setText("Cancelled");
            cancelBtn.setEnabled(false);
            cancelBtn.setBackground(Color.LIGHT_GRAY);
        } else {
            cancelBtn.setBackground(new Color(220, 53, 69)); // Bootstrap Danger Red
            cancelBtn.setForeground(Color.WHITE);
            
            cancelBtn.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(row, 
                    "Are you sure you want to cancel this booking?", "Confirm", JOptionPane.YES_NO_OPTION);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    boolean success = dao.cancelFlightById(f.getId()); 
                    if (success) {
                        JOptionPane.showMessageDialog(row, "Booking Cancelled Successfully!");
                        loadHistory(); // Refresh the list
                    } else {
                        JOptionPane.showMessageDialog(row, "Error: Could not cancel booking.", "Database Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        }

        JPanel btnPanel = new JPanel(new GridBagLayout());
        btnPanel.setOpaque(false);
        btnPanel.add(cancelBtn);
        row.add(btnPanel);

        return row;
    }

    private int containerWidth() {
        int w = view.getFlightListPanel().getWidth();
        return (w <= 0) ? 800 : w;
    }
}