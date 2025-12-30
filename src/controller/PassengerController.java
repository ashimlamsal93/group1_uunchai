/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.PassengerDAO;
import model.PassengerModel;
import database.MySqlConnection; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JOptionPane;
import view.PassengerForm; 

public class PassengerController {
    private final PassengerDAO passengerDao;
    private final PassengerForm bookingView;

    public PassengerController(PassengerForm bookingView) {
        this.bookingView = bookingView;

       
        MySqlConnection db = new MySqlConnection();
        
        
        Connection conn = db.openConnection(); 
        
        if (conn == null) {
            JOptionPane.showMessageDialog(bookingView, "Database Connection Failed!");
        }

        this.passengerDao = new PassengerDAO(conn);

        // Listeners 
        this.bookingView.addContinueListener(new ContinueListener());
        this.bookingView.addCancelListener(new CancelListener());
    }

    private class ContinueListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String title = bookingView.getComboTitle().getSelectedItem().toString();
                String name = bookingView.getTxtFullName().getText().trim();
                String ageStr = bookingView.getTxtAge().getText().trim();
                String traveller = bookingView.getComboTraveller().getSelectedItem().toString();
                String email = bookingView.getTxtEmail().getText().trim();
                String contact = bookingView.getTxtContact().getText().trim();
                boolean agreed = bookingView.getChkTerms().isSelected();

                if (!agreed) {
                    JOptionPane.showMessageDialog(bookingView, "Please agree to Terms & Conditions");
                    return;
                }
                
                if (name.isEmpty() || ageStr.isEmpty()) {
                    JOptionPane.showMessageDialog(bookingView, "Name and Age are required!");
                    return;
                }

                int age = Integer.parseInt(ageStr);
                PassengerModel passenger = new PassengerModel(title, name, age, traveller, contact, email);

                if (passengerDao.insertBooking(passenger)) {
                    JOptionPane.showMessageDialog(bookingView, "Data Saved Successfully!");
                    // Payment window add here
                    
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(bookingView, "Please enter a valid number for Age.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(bookingView, "Error: " + ex.getMessage());
            }
        }
    }

    private class CancelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int confirm = JOptionPane.showConfirmDialog(bookingView, 
                    "Are you sure you want to cancel?", "Exit", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                bookingView.dispose(); 
            }
        }
    }
}