/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * @author A plus
 */
public class FlightModel {
    private int id; // Database Primary Key (Cancel garna chaincha)
    private String from, to, date, depTime, arrTime, status;

    // Updated Constructor
    public FlightModel(int id, String from, String to, String date, String depTime, String arrTime, String status) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.date = date;
        this.depTime = depTime;
        this.arrTime = arrTime;
        this.status = status;
    }

    // Getters
    public int getId() { return id; } // Naya getter
    public String getStatus() { return status; } // Status check garna
    public String getFrom() { return from; }
    public String getTo() { return to; }
    public String getDate() { return date; }
    public String getDepTime() { return depTime; }
    public String getArrTime() { return arrTime; }
}
