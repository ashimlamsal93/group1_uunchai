/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author A plus
 */
public class FlightModel {
    private String from, to, date, depTime, arrTime;

    public FlightModel(String from, String to, String date, String depTime, String arrTime) {
        this.from = from;
        this.to = to;
        this.date = date;
        this.depTime = depTime;
        this.arrTime = arrTime;
    }

    // Getters
    public String getFrom() { return from; }
    public String getTo() { return to; }
    public String getDate() { return date; }
    public String getDepTime() { return depTime; }
    public String getArrTime() { return arrTime; }
}
