/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package group1_uunchai;

import view.Login;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Group1_Uunchai {

    public static void main(String[] args) {

        // ✅ Set Nimbus Look and Feel safely
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException |
                 InstantiationException |
                 IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {

            System.err.println("⚠ Unable to set Nimbus Look & Feel");
        }


        // ✅ Always launch Swing UI on EDT
        SwingUtilities.invokeLater(() -> {

            Login login = new Login();

            login.setTitle("Uunchai - Login");
            login.setLocationRelativeTo(null); // center
            login.setResizable(false);
            login.setVisible(true);


            System.out.println("===========================================");
            System.out.println("Uunchai - Air Ticket Booking System");
            System.out.println("Application started successfully");
            System.out.println("===========================================");
        });
    }
}

