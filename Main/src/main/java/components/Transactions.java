/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * The <b>Transactions</b> class contains all methods needed for transactions.It
 * establishes a connection with the database through {@link DBConnection}
 * class.
 *
 */
public class Transactions {

    /**
     * The <b>type</b> enum contains all the types of
     * transactions(Withdrawal,Deposit, and Transfer).
     */
    public enum type {
        WITHDRAWAL,
        DEPOSIT,
        TRANSFER
    }

    /**
     * Retrieves the user's balance from the account number.
     *
     * @param accountNum the user's account number.
     * @return the double value of the user's balance.
     */
    public static double getBalance(int accountNum) {
        Connection connection = DBConnection.connectDB();
        double balance = 0;
        if (connection != null) {
            try {
                PreparedStatement statment = (PreparedStatement) connection.prepareStatement("SELECT balance FROM accounts WHERE account_num = ?");
                statment.setInt(1, accountNum);
                ResultSet result = statment.executeQuery();
                while (result.next()) {
                    balance = result.getDouble("balance");
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
        return balance;
    }

    /**
     * Changes the user's balance to the new passed value.
     *
     * @param accountNum the user's account number.
     * @param balance the new user's balance.
     */
    public static void setBalance(int accountNum, double balance) {
        Connection connection = DBConnection.connectDB();
        if (connection != null) {
            try {
                PreparedStatement statment = (PreparedStatement) connection.prepareStatement("UPDATE accounts SET balance = ? WHERE account_num = ?");
                statment.setDouble(1, balance);
                statment.setInt(2, accountNum);
                statment.executeUpdate();
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
    }

    /**
     * Inserts the transaction details such as account number, type, date, and
     * time in the database.
     *
     * @param transcactionID the transaction ID.
     * @param accountNum the integer value of the account number performed the
     * transaction.
     * @param toAccountNum the integer value of the account number which the
     * transaction is performed on.
     * @param type the string value of the transaction type.
     * @param amount the double of money.
     * @param date the {@link java.sql.Date} value of the transaction date.
     * @param time the {@link java.sql.Time} value of the transaction time.
     */
    public static void recordTransaction(int transcactionID, int accountNum, int toAccountNum, String type, double amount, java.sql.Date date, java.sql.Time time) {
        Connection connection = DBConnection.connectDB();

        if (connection != null) {
            try {
                PreparedStatement statment = (PreparedStatement) connection.prepareStatement("INSERT INTO transactions(transaction_id,account_num,to_account_num,type,amount,date,time) VALUES(?,?,?,?,?,?,?)");
                statment.setInt(1, transcactionID);
                statment.setInt(2, accountNum);
                statment.setInt(3, toAccountNum);
                statment.setString(4, type);
                statment.setDouble(5, amount);
                statment.setDate(6, date);
                statment.setTime(7, time);
                statment.executeUpdate();
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
    }

    /**
     * Retrieves the user's name according to the passed account number.
     *
     * @param accountNum the user's account number.
     * @return the string value of the user's name.
     */
    public static String getName(int accountNum) {
        Connection connection = DBConnection.connectDB();
        String name = "";
        if (connection != null) {
            try {
                PreparedStatement statment = (PreparedStatement) connection.prepareStatement("SELECT name FROM accounts WHERE account_num = ?");
                statment.setInt(1, accountNum);
                ResultSet result = statment.executeQuery();
                while (result.next()) {
                    name = result.getString("name");
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
        return name;
    }

    /**
     * Retrieves the number of transactions done by a user.
     *
     * @param accountNum the user's account number.
     * @return the integer value of the number of transactions.
     */
    public static int getNumOfTransactions(int accountNum) {
        Connection connection = DBConnection.connectDB();
        int numOfTransactions = 0;
        if (connection != null) {
            try {
                PreparedStatement statment = (PreparedStatement) connection.prepareStatement("SELECT COUNT(*) AS rowcount FROM transactions WHERE date IN ( SELECT date FROM transactions GROUP BY date HAVING count(*) > 1 AND date= \"2020-12-04\") AND account_num = ?");
                statment.setInt(1, accountNum);
                ResultSet result = statment.executeQuery();
                while (result.next()) {
                    numOfTransactions = result.getInt("rowcount");
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
        return numOfTransactions;
    }

    /**
     * Retrieves all the transactions and their details done by a user.
     *
     * @param accountNum the user's account number.
     * @return a {@link java.sql.ResultSet} that contains all detailed
     * transactions.
     */
    public static java.sql.ResultSet getAllTransactions(int accountNum) {
        Connection connection = DBConnection.connectDB();

        ResultSet result = null;
        if (connection != null) {
            try {
                PreparedStatement statment = (PreparedStatement) connection.prepareStatement("SELECT account_num as \"From Account No.\", type AS Type,amount AS Amount,to_account_num as \"To Account No.\",date AS Date,time AS Time FROM transactions WHERE account_num = ? or to_account_num = ?");
                statment.setInt(1, accountNum);
                statment.setInt(2, accountNum);
                result = statment.executeQuery();
            } catch (SQLException exception) {
                Logger.getLogger(gui.HomePage.class.getName()).log(Level.SEVERE, null, exception);
            }
        }
        return result;
    }

    /**
     * Converts a {@link java.sql.ResultSet} object to a {@link TableModel}.
     *
     * @param result a {@link java.sql.ResultSet} object.
     * @return a {@link TableModel} object that contains the ResultSet data.
     */
    public static TableModel resultSetToTableModel(ResultSet result) {
        try {
            ResultSetMetaData metaData = result.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            Vector columnNames = new Vector();
            for (int column = 0; column < numberOfColumns; column++) {
                columnNames.addElement(metaData.getColumnLabel(column + 1));
            }
            Vector rows = new Vector();
            while (result.next()) {
                Vector newRow = new Vector();
                for (int i = 1; i <= numberOfColumns; i++) {
                    newRow.addElement(result.getObject(i));
                }
                rows.addElement(newRow);
            }
            return new DefaultTableModel(rows, columnNames);
        } catch (Exception exception) {
            return null;
        }
    }

    /**
     * Adds a beneficiary to a user's account.
     *
     * @param accountNum the user's account number.
     * @param name the user's name.
     * @param beneficiaryAccountNum the beneficiary's account number.
     * @param beneficiaryName the beneficiary's name.
     */
    public static void addBeneficiary(int accountNum, String name, int beneficiaryAccountNum, String beneficiaryName) {
        Connection connection = DBConnection.connectDB();
        if (connection != null) {
            try {
                PreparedStatement statment = (PreparedStatement) connection.prepareStatement("INSERT INTO beneficiaries (account_num, name, beneficiary_account_num, beneficiary_name) VALUES (?, ?, ?, ?)");
                statment.setInt(1, accountNum);
                statment.setString(2, name);
                statment.setInt(3, beneficiaryAccountNum);
                statment.setString(4, beneficiaryName);
                statment.executeUpdate();
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
    }

    /**
     * Retrieves all the user's beneficiaries and their account number.
     *
     * @param accountNum the user's account number.
     * @return a {@link java.sql.ResultSet} that contains all beneficiaries and
     * their account number.
     */
    public static java.sql.ResultSet getAllBeneficiaries(int accountNum) {
        Connection connection = DBConnection.connectDB();
        ResultSet result = null;
        if (connection != null) {
            try {
                PreparedStatement statment = (PreparedStatement) connection.prepareStatement("SELECT beneficiary_account_num as \"Beneficiary Account No.\", beneficiary_name AS \"Beneficiary Name\" FROM beneficiaries WHERE account_num = ?");
                statment.setInt(1, accountNum);
                result = statment.executeQuery();
            } catch (SQLException exception) {
                Logger.getLogger(gui.HomePage.class.getName()).log(Level.SEVERE, null, exception);
            }
        }
        return result;
    }

    /**
     * Checks if the beneficiary is added to a user's beneficiaries list.
     *
     * @param accountNum the user's account number.
     * @param beneficiaryAccountNum the beneficiary's account number.
     * @return true if the beneficiary's account number is added to the user's
     * beneficiaries; false otherwise.
     */
    public static boolean isBeneficiary(int accountNum, int beneficiaryAccountNum) {
        Connection connection = DBConnection.connectDB();
        if (connection != null) {
            try {
                PreparedStatement statment = (PreparedStatement) connection.prepareStatement("SELECT beneficiary_account_num FROM beneficiaries WHERE account_num = ? AND beneficiary_account_num = ?");
                statment.setInt(1, accountNum);
                statment.setInt(2, beneficiaryAccountNum);
                ResultSet result = statment.executeQuery();
                if (result.next()) {
                    return true;
                } else {
                    return false;
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
}
