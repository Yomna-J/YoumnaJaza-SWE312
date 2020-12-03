package components;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author youmna
 */
public class SigningIn {

    private Connection connection = DBConnection.connectDB();
    public boolean userSignIn(int accountNum, String password) {
        int userId;
        
        if (connection != null) {
            try {
                PreparedStatement statment = (PreparedStatement) connection.prepareStatement("Select user_id FROM accounts WHERE account_num = ?");
                statment.setInt(1, accountNum);
                ResultSet result = statment.executeQuery();
                if (result.next()) {
                    userId = result.getInt("user_id");
                    statment = (PreparedStatement) connection.prepareStatement("Select * from users WHERE user_id = ? AND password = ? ");
                    statment.setInt(1, userId);
                    statment.setString(2, password);
                    result = statment.executeQuery();
                    if (result.next()) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } catch (SQLException exception) {
                Logger.getLogger(gui.SignInPage.class.getName()).log(Level.SEVERE, null, exception);
            } finally {
                try {
                    connection.close();
                } catch (SQLException exception) {

                }
            }
        }
        return false;
    }
    
    public boolean isRigesteredAccountNum(int accountNum){
        if (connection != null) {
            try {
                PreparedStatement statment = (PreparedStatement) connection.prepareStatement("SELECT account_num FROM accounts WHERE account_num = ?");
                statment.setInt(1, accountNum);
                ResultSet result = statment.executeQuery();
                if (result.next()) {

                        return true;
                    } else {
                        return false;
                    }
            } catch (SQLException exception) {
                Logger.getLogger(gui.HomePage.class.getName()).log(Level.SEVERE, null, exception);
            }
        }return false;
    }
    public String getEmail(int accountNum){
     int userId;
        if (connection != null) {
            try {
                PreparedStatement statment = (PreparedStatement) connection.prepareStatement("SELECT user_id FROM accounts WHERE account_num = ?");
                statment.setInt(1, accountNum);
                ResultSet result = statment.executeQuery();
                if (result.next()) {
                    userId = result.getInt("user_id");
                    statment = (PreparedStatement) connection.prepareStatement("SELECT email FROM users WHERE user_id = ? ");
                    statment.setInt(1, userId);
                    result = statment.executeQuery();
                    if (result.next()) {
                        String email = result.getString("email");
                        return email;

                    } else {
                        return null;
                    }
                } 
            } catch (SQLException exception) {
                Logger.getLogger(gui.SignInPage.class.getName()).log(Level.SEVERE, null, exception);
            } finally {
                try {
                    connection.close();
                } catch (SQLException exception) {

                }
            }
        }
      return null;
    }

}
