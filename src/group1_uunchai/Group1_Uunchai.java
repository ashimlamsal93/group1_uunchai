/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package group1_uunchai;


import view.Reset_Password;

import controller.UserController;
import database.Database;
import database.MySqlConnection;
import view.Registration;


public class Group1_Uunchai {

    public static void main(String[] args) {

        // java.awt.EventQueue.invokeLater(() -> {
        //     new Reset_Password().setVisible(true);
        // });


        Registration reg = new Registration();
        UserController userC = new UserController(reg);
        userC.open();
        // Optional: open registration form
        // Registration register = new Registration();
//    }
//}




//    public static void main(String[] args) {
      

    }
}