/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package group1_uunchai;
import controller.UserController;
import view.Registration;

public class Group1_Uunchai {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            Registration view = new Registration();
            UserController controller = new UserController(view);
            controller.open();
        });
    }
}