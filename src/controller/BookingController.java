/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.BookingDao;
import model.Passenger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BookingController {
    private BookingDao bookingDao;
    private static final Random random = new Random();

    public BookingController() {
        this.bookingDao = new BookingDao();
    }

    /**
     * Processes the booking and returns the generated PNR if successful
     * @param userId User ID from currentUser
     * @param flightId Selected flight ID
     * @param numPassengers Number of travellers
     * @param totalAmount Calculated total price
     * @param passengers List of Passenger objects
     * @return PNR string if booking saved, null if failed
     */
    public String confirmBooking(int userId, int flightId, int numPassengers, double totalAmount, List<Passenger> passengers) {
        // Save booking to database
        boolean saved = bookingDao.createBooking(userId, flightId, numPassengers, totalAmount, passengers);

        if (saved) {
            // Generate unique PNR: UUNCH + 6-digit random number
            String pnr = "UUNCH" + String.format("%06d", random.nextInt(1000000));
            return pnr;
        } else {
            return null; // booking failed
        }
    }
}