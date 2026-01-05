/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class BookingModel {
    private int flightId;
    private int userId;
    private int numPassengers;
    private double totalAmount;
    private String pnr;

    // getters and setters
    public int getFlightId() { return flightId; }
    public void setFlightId(int flightId) { this.flightId = flightId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getNumPassengers() { return numPassengers; }
    public void setNumPassengers(int numPassengers) { this.numPassengers = numPassengers; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public String getPnr() { return pnr; }
    public void setPnr(String pnr) { this.pnr = pnr; }
}