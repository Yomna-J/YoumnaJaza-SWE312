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

public class DBOperations {

    Connection connection = DBConnection.connectDB();

    public enum type {
        WITHDRAW,
        DEPOSIT,
        TRANSFER
    }

    public double getBalance(int accountNum) {
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
            }
        }     
        return balance;
    }

    public void setBalance(int accountNum, double balance) {
        if (connection != null) {
            try {
                PreparedStatement statment = (PreparedStatement) connection.prepareStatement("UPDATE accounts SET balance = ? WHERE account_num = ?");
                statment.setDouble(1, balance);
                statment.setInt(2, accountNum);
                statment.executeUpdate();

                // return balance;
            } catch (SQLException exception) {
                Logger.getLogger(gui.HomePage.class.getName()).log(Level.SEVERE, null, exception);
            }
        }
    }

    public void recordTransaction(int transcactionId, int accountNum,int toAccountNum ,String type, double amount, java.sql.Date date, java.sql.Time time) {
        if (connection != null) {
            try {
                PreparedStatement statment = (PreparedStatement) connection.prepareStatement("INSERT INTO transactions(transaction_id,account_num,to_account_num,type,amount,date,time) VALUES(?,?,?,?,?,?,?)");
                statment.setInt(1, transcactionId);
                statment.setInt(2, accountNum);
                statment.setInt(3, toAccountNum);
                statment.setString(4, type);
                statment.setDouble(5, amount);
                statment.setDate(6, date);
                statment.setTime(7, time);
                statment.executeUpdate();
            } catch (SQLException exception) {
                Logger.getLogger(gui.HomePage.class.getName()).log(Level.SEVERE, null, exception);
            }
        }
    }

    public String getName(int accountNum) {
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
            }
        }
        return name;
    }

    public int getNumOfTransactions(int accountNum) {
        int numOfTransactions = 0;
        if (connection != null) {
            try {
                PreparedStatement statment = (PreparedStatement) connection.prepareStatement("SELECT COUNT(*) AS rowcount FROM transactions WHERE date IN ( SELECT date FROM transactions GROUP BY date HAVING count(*) > 1 ) AND account_num = ?");
                statment.setInt(1, accountNum);
                ResultSet result = statment.executeQuery();
                while (result.next()) {
                    numOfTransactions = result.getInt("rowcount");
                }
            } catch (SQLException exception) {
                Logger.getLogger(gui.HomePage.class.getName()).log(Level.SEVERE, null, exception);
            }
        }
        return numOfTransactions;
    }

    public java.sql.ResultSet getAllTransactions(int accountNum) {
       ResultSet result =null;
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
    
    public void addBeneficiary(int accountNum, String name, int beneficiaryAccountNum, String beneficiaryName){
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
            }
        }
    }
    
    
    public java.sql.ResultSet getAllBeneficiaries(int accountNum) {
       ResultSet result =null;
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
    public boolean isRigesteredBeneficiary(int accountNum, int beneficiaryAccountNum){
         if (connection != null) {
            try {
                PreparedStatement statment = (PreparedStatement) connection.prepareStatement("SELECT beneficiary_account_num FROM beneficiaries WHERE account_num = ? AND beneficiary_account_num = ?");
                statment.setInt(1, accountNum);
                statment.setInt(2, beneficiaryAccountNum);
                 ResultSet result = statment.executeQuery();
                 if(result.next()){ 
                     return true;
                 }else{
                     return false;
                 }
                 
            } catch (SQLException exception) {
                Logger.getLogger(gui.HomePage.class.getName()).log(Level.SEVERE, null, exception);
            }
        }
         return false;
    }
    
    public void closeConnection(){
        try{
        connection.close();
        }catch(SQLException exception){
            
        }
    }
     
}
