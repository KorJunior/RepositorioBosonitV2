package org.example.classdatabase;

import java.sql.Connection;
import java.sql.DriverManager;


public class DbFunctions {
    Connection conn = null;
   public Connection connect_to_db() {
      try {
             conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/concesionario", "junior", "1234");
            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (Exception e) {
          System.out.println(e);
      }
      return conn;
   }
}
