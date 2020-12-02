/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author youmna
 */
public class Registration {

    public Registration(int accountNum, int userId, String name, String password, String phoneNum, String email) {
        Connection connection = DBConnection.connectDB();

        if (connection != null) {
            try {
                PreparedStatement statment = (PreparedStatement) connection.prepareStatement("INSERT INTO users (user_id,name,password,phone_num,email) VALUES(?,?,?,?,?)");
                statment.setInt(1, userId);
                statment.setString(2, name);
                statment.setString(3, password);
                statment.setString(4, phoneNum);
                statment.setString(5, email);
                statment.executeUpdate();
                double balance = 0.00;
                statment = (PreparedStatement) connection.prepareStatement("INSERT INTO accounts (account_num,user_id,name,balance) VALUES(?,?,?,?)");
                statment.setInt(1, accountNum);
                statment.setInt(2, userId);
                statment.setString(3, name);
                statment.setDouble(4, balance);
                statment.executeUpdate();
            } catch (SQLException exception) {
                Logger.getLogger(gui.SignUpPage.class.getName()).log(Level.SEVERE, null, exception);
            }finally{
                try{
                    connection.close();
                }catch(SQLException exception){}
            }
        }

    }
}
