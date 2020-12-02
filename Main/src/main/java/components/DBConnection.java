/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.sql.*;

public class DBConnection {

    static final String DB_URL = "jdbc:mysql://localhost/banking_sys";
    static final String USERNAME = "root";
    static final String PASS = "";
       
    public static Connection connectDB() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASS);
            return connection;
        } catch (Exception exception) {
            return null;
        }
    }
    
    
}
