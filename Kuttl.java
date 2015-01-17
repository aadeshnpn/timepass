/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kuttl;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author tensor
 */
public class Kuttl {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Connection c = null;
      try {
         Class.forName("org.postgresql.Driver");
         DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/diginotes",
            "diginotes", "student");
         
     } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }
      System.out.println("Opened database successfully");
    }
}
