/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package group1_uunchai;


import controller.FlightController;
import controller.UserController;
import database.Database;
import database.MySqlConnection;
import view.FlightBookingHistory;
import view.Registration;



public class Group1_Uunchai {

    public static void main(String[] args) {


//        Registration reg = new Registration();
//        UserController userC = new UserController(reg);
//        userC.open();
//    }}
        // Optional: open registration form
        // Registration register = new Registration();
//    }
//}




//    public static void main(String[] args) {
      
         FlightBookingHistory flight = new FlightBookingHistory();
         FlightController fc = new FlightController(flight);
         flight.setVisible(true);
      }}