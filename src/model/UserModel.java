<<<<<<< HEAD
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class UserModel {

    private String username;
    private String email;
    private String password;
    private String contact;

    public UserModel() {}

    public UserModel(String username, String email, String password, String contact) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.contact = contact;
    }

    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getContact() { return contact; }

    public void setUsername(String username) { this.username = username; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setContact(String contact) { this.contact = contact; }
=======
package model;

public class UserModel {
    private int userId;
    private String email;
    private String password;
    
    public UserModel() {
    }
    
    public UserModel(int userId, String email, String password) {
        this.userId = userId;
        this.email = email;
        this.password = password;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString() {
        return "UserModel{userId=" + userId + ", email='" + email + "'}";
    }
>>>>>>> origin/Ashish
}