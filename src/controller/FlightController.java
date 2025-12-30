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
// import view.Dashboard; 

public class FlightController {

    private final FlightBookingHistory view;
    private final FlightDAO dao;

    public FlightController(FlightBookingHistory view) {
        this.view = view;
        this.dao = new FlightDAO();

        
        loadHistory();

      
        initNavigation();
    }

    private void initNavigation() {
       
        JButton backbtn = view.getBtnBack(); 
        
        if (backbtn != null) {
            backbtn.addActionListener(e -> {
               
                view.dispose(); 
                
//                (Optional)
                // new Dashboard().setVisible(true);
                
                System.out.println("Back button clicked: Navigating to Dashboard");
            });
        }
    }

    public final void loadHistory() {
        JPanel container = (JPanel) view.getFlightListPanel();
        if (container == null) return;

        container.removeAll();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        
        
        List<FlightModel> flights = dao.getAllHistory(1);

        if (flights.isEmpty()) {
            JLabel emptyMsg = new JLabel("No booking history found.");
            emptyMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
            container.add(emptyMsg);
        } else {
            for (FlightModel f : flights) {
                container.add(createFlightRow(f));
               
                container.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }

        container.revalidate();
        container.repaint();
    }

    private JPanel createFlightRow(FlightModel f) {
        
        JPanel row = new JPanel(new GridLayout(1, 3));
        row.setBackground(new Color(135, 190, 255));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        row.setPreferredSize(new Dimension(containerWidth(), 80));
        row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));

       
        row.add(new JLabel("<html><center><b>" + f.getFrom() + "</b><br>" + f.getDepTime() + "</center></html>", SwingConstants.CENTER));
        row.add(new JLabel("<html><center>" + f.getDate() + "<br>••• ✈ •••</center></html>", SwingConstants.CENTER));
        row.add(new JLabel("<html><center><b>" + f.getTo() + "</b><br>" + f.getArrTime() + "</center></html>", SwingConstants.CENTER));

        return row;
    }

    private int containerWidth() {
        int w = view.getFlightListPanel().getWidth();
        return (w <= 0) ? 800 : w;
    }
}