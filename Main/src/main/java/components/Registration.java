package components;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The <b>Registration</b> class is used to register new users by establishing a
 * connection using the {@link DBConnection} class.
 */
public class Registration {

    /**
     * It registers a new user by using SQL queries and a connection with the
     * database through the {@link DBConnection} class.
     *
     * @param accountNum the user's account number.
     * @param userId the user's ID.
     * @param name the user's name.
     * @param password the user's account password.
     * @param phoneNum the user's phone number.
     * @param email the user's email.
     */
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
            } finally {
                try {
                    connection.close();
                } catch (SQLException exception) {
                }
            }
        }
    }
}
