package components;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The <b>SigningIn</b> class contains some methods and SQL queries sued to sign
 * in and to establish a connection with {@link DBConnection}.
 */
public class SigningIn {

    /**
     * Checks if the credentials of the user are correct by using SQL query.
     *
     * @param accountNum the user's account number.
     * @param password the user's account password.
     * @return true if the credentials are correct; false otherwise.
     */
    public static boolean signIn(int accountNum, String password) {
        Connection connection = DBConnection.connectDB();
        int userId = getUserId(accountNum);
        if (connection != null && userId != 0) {
            try {
                PreparedStatement statment = (PreparedStatement) connection.prepareStatement("Select * from users WHERE user_id = ? AND password = ? ");
                statment.setInt(1, userId);
                statment.setString(2, password);
                ResultSet result = statment.executeQuery();
                if (result.next()) {
                    return true;
                }
            } catch (SQLException exception) {
                Logger.getLogger(gui.SignInPage.class.getName()).log(Level.SEVERE, null, exception);
            } finally {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    //Ignored
                }
            }
        }
        return false;
    }

    /**
     * Checks if the account number exists in the database.
     *
     * @param accountNum the user account number
     * @return true if the account number exists; false otherwise.
     */
    public static boolean isRigesteredAccountNum(int accountNum) {
        Connection connection = DBConnection.connectDB();
        if (connection != null) {
            try {
                PreparedStatement statment = (PreparedStatement) connection.prepareStatement("SELECT account_num FROM accounts WHERE account_num = ?");
                statment.setInt(1, accountNum);
                ResultSet result = statment.executeQuery();
                if (result.next()) {
                    return true;
                }
            } catch (SQLException exception) {
                Logger.getLogger(gui.HomePage.class.getName()).log(Level.SEVERE, null, exception);
            } finally {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    //Ignored
                }
            }
        }
        return false;
    }

    /**
     * Retrieve the email of a specific account number.
     *
     * @param accountNum the user account number.
     * @return string value of the email that belongs to that account number.
     */
    public static String getEmail(int accountNum) {
        Connection connection = DBConnection.connectDB();
        int userId = getUserId(accountNum);
        if (connection != null && userId != 0) {
            try {
                PreparedStatement statment = (PreparedStatement) connection.prepareStatement("SELECT email FROM users WHERE user_id = ? ");
                statment.setInt(1, userId);
                ResultSet result = statment.executeQuery();
                if (result.next()) {
                    String email = result.getString("email");
                    return email;
                } else {
                    return null;
                }
            } catch (SQLException exception) {
                Logger.getLogger(gui.SignInPage.class.getName()).log(Level.SEVERE, null, exception);
            } finally {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    //Ignored
                }
            }
        }
        return null;
    }

    /**
     * Used by some methods in this class to retrieve the user id of a specific
     * account number.
     *
     * @param accountNum the user's account number.
     * @return the integer value of the user id that belongs to that account
     * number.
     */
    private static int getUserId(int accountNum) {
        Connection connection = DBConnection.connectDB();
        int userId = 0;
        if (connection != null) {
            try {
                PreparedStatement statment = (PreparedStatement) connection.prepareStatement("SELECT user_id FROM accounts WHERE account_num = ?");
                statment.setInt(1, accountNum);
                ResultSet result = statment.executeQuery();
                if (result.next()) {
                    userId = result.getInt("user_id");
                    return userId;
                }
            } catch (SQLException exception) {
                Logger.getLogger(gui.SignInPage.class.getName()).log(Level.SEVERE, null, exception);
            } finally {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    //Ignored
                }
            }
        }
        return userId;
    }
}
