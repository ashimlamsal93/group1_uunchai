/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class PassengerModel {
    private int passengerId;
    private int bookingId;
    private String title;
    private String fullName;
    private String traveller;
    private int age;
    private String contact;
    private String email;

    // Default Constructor
    public PassengerModel() {}

    // Constructor for creating a NEW booking (IDs not yet known)
    public PassengerModel(String title, String full_Name, int age, String Traveller, String contact, String email) {
        this.title = title;
        this.fullName = full_Name;
        this.traveller = Traveller;
        this.age = age;
        this.contact = contact;
        this.email = email;
    }

    // Getters and Setters
    public int getPassengerId() { return passengerId; }
    public void setPassengerId(int passengerId) { this.passengerId = passengerId; }

    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public String getTraveller() {return traveller;}
    public void setTraveller(String traveller) {this.traveller = traveller;}
    
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}