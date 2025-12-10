/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group1_uunchai;
import database.Database;

import database.MySqlConnection;



/**

 *

 * @author User

 */

public class Group1_Uunchai {



    /**

     * @param args the command line arguments

     */

    public static void main(String[] args) {

        // TODO code application logic here

       Database db = new MySqlConnection();

       if(db.openConnection() !=null){

           System.out.println("Connectioon succesful");

         

       }else{

           System.out.println("Not successful");

       }

    }

    

}