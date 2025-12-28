package model;

public class FlightModel {
    private int flightId;
    private String flightNumber;
    private String airline;
    private String sourceCity;
    private String destinationCity;
    private String departureTime;
    private String arrivalTime;
    private String duration;
    private double price;
    private int availableSeats;
    
    // Default constructor
    public FlightModel() {
    }
    
    // Parameterized constructor
    public FlightModel(int flightId, String flightNumber, String airline, 
                      String sourceCity, String destinationCity, 
                      String departureTime, String arrivalTime, 
                      String duration, double price, int availableSeats) {
        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.sourceCity = sourceCity;
        this.destinationCity = destinationCity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.duration = duration;
        this.price = price;
        this.availableSeats = availableSeats;
    }
    
    // Getters and Setters
    public int getFlightId() { return flightId; }
    public void setFlightId(int flightId) { this.flightId = flightId; }
    
    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }
    
    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }
    
    public String getSourceCity() { return sourceCity; }
    public void setSourceCity(String sourceCity) { this.sourceCity = sourceCity; }
    
    public String getDestinationCity() { return destinationCity; }
    public void setDestinationCity(String destinationCity) { this.destinationCity = destinationCity; }
    
    public String getDepartureTime() { return departureTime; }
    public void setDepartureTime(String departureTime) { this.departureTime = departureTime; }
    
    public String getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(String arrivalTime) { this.arrivalTime = arrivalTime; }
    
    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    
    public int getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }
    
    @Override
    public String toString() {
        return String.format("%s: %s → %s (%s - %s) - $%.2f", 
            flightNumber, sourceCity, destinationCity, departureTime, arrivalTime, price);
    }
}