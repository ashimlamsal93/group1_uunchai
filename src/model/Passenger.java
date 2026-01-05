/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Passenger {
    private String fullName;
    private int age;
    private String gender;

    public Passenger(String fullName, int age, String gender) {
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
    }

    // getters
    public String getFullName() { return fullName; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
}