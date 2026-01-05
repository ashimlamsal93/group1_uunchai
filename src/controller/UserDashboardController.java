/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.FlightDao;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class UserDashboardController {

    private final FlightDao flightDao;

    public UserDashboardController() {
        flightDao = new FlightDao();
    }

    public ResultSet searchFlights(String from, String to, String date) {
        return flightDao.searchFlights(from, to, date);
    }

    // Mock booking (tomorrow we make it real)
    public void bookFlight(int flightId, int travellers, double totalPrice) {
        String pnr = "UUNCH" + flightId + (int)(Math.random() * 1000);
        JOptionPane.showMessageDialog(null,
            """
            Booking Successful! \ud83c\udf89
            PNR: """ + pnr + "\n" +
            "Travellers: " + travellers + "\n" +
            "Total: NPR " + totalPrice + "\n" +
            "Check your email for ticket.",
            "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}