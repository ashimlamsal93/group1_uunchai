/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package group1_uunchai;

// import view.AdminLogin;
import controller.RegistrationController;
// import view.Userprofile;
import view.Registration;



public class Group1_Uunchai {
    public static void main(String []args) {
            Registration reg = new Registration();
        RegistrationController userC = new RegistrationController(reg);
        userC.open();
    }}
      
