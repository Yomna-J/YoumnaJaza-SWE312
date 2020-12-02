/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coordinators;

import components.BusinessRules;
import components.DBOperations;
import java.sql.ResultSet;
import javax.swing.table.TableModel;

/**
 *
 * @author youmna
 */
public class TransactionCoordinator extends GeneralCoordinator {

    
    private int transcactionId;

    private java.util.Date utilDate;
    private java.sql.Date sqlDate;
    private java.sql.Time sqlTime;
    private BusinessRules bRules = new BusinessRules();
    private DBOperations DBOps = new DBOperations();

    public boolean withdraw(double amount) {
        double balance = DBOps.getBalance(accountNum);
        if (bRules.isValidWithdraw(balance, amount) && isAllowed()) {
            createTransactionID();
            balance -= amount;
            DBOps.setBalance(accountNum, balance);
            DBOps.recordTransaction(transcactionId, accountNum, accountNum, DBOperations.type.WITHDRAW.name(), amount, sqlDate, sqlTime);
            return true;
        } else {
            return false;
        }
    }

    public double getBalance() {
        return DBOps.getBalance(accountNum);
    }

    public String getName() {
        return DBOps.getName(accountNum);
    }

    public void createTransactionID() {
        int min = 100000000;
        int max = 900000000;
        transcactionId = min + GENERATOR.nextInt(max);
        utilDate = new java.util.Date();
        sqlDate = new java.sql.Date(utilDate.getTime());
        sqlTime = new java.sql.Time(utilDate.getTime());
    }

    public boolean deposit(double amount) {
        double balance = DBOps.getBalance(accountNum);
        if (bRules.isValidDeposit(amount) && isAllowed()) {
            createTransactionID();
            balance += amount;
            DBOps.setBalance(accountNum, balance);
            DBOps.recordTransaction(transcactionId, accountNum, accountNum, DBOperations.type.DEPOSIT.name(), amount, sqlDate, sqlTime);
            return true;
        } else {
            return false;
        }
    }

    private boolean isAllowed() {
        return bRules.isValidTransaction(DBOps.getNumOfTransactions(accountNum));
    }

    public TableModel buildTransactionsTable() {
        ResultSet result = DBOps.getAllTransactions(accountNum);
        return DBOperations.resultSetToTableModel(result);
    }

    public void addBeneficiary(int beneficiaryAccountNum, String beneficiaryName) {
        DBOps.addBeneficiary(accountNum, getName(), beneficiaryAccountNum, beneficiaryName);
    }


    public TableModel buildBeneficiariesTable() {
        ResultSet result = DBOps.getAllBeneficiaries(accountNum);
        return DBOperations.resultSetToTableModel(result);
    }

    public boolean isBeneficiary(int beneficiaryAccountNum) {
        return DBOps.isRigesteredBeneficiary(accountNum, beneficiaryAccountNum);
    }

    public boolean transferTo(double amount, int beneficiaryAccountNum) {
        double balance = DBOps.getBalance(accountNum);
        double beneficiaryBalance = DBOps.getBalance(beneficiaryAccountNum);

        if (isAllowed() && isBeneficiary(beneficiaryAccountNum) && bRules.isValidTransfer(balance, amount)) {
            createTransactionID();
            balance -= amount;
            DBOps.setBalance(accountNum, balance);
            beneficiaryBalance += amount;
            DBOps.setBalance(beneficiaryAccountNum, beneficiaryBalance);
            DBOps.recordTransaction(transcactionId, accountNum, beneficiaryAccountNum, DBOperations.type.TRANSFER.name(), amount, sqlDate, sqlTime);
            return true;
        } else {
            return false;
        }
    }

    public void logOut() {
        accountNum = 0;
        DBOps.closeConnection();
    }

    

}
