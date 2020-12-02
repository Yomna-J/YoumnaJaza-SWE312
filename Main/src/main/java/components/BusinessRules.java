/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

/**
 *
 * @author youmna
 */
public class BusinessRules {

    private double maxWithdraw = 5000.0;
    private double minWithdraw = 50.0;
    private double maxDeposit = 12000.0;
    private double minDeposit = 100.0;
    private double maxTransfer = 10000.0;
    private double minTransfer = 1000.0;
    private int numOfAllowedTransactions = 5;

    public boolean isValidWithdraw(double balance, double amount) {
        return balance >= amount && amount <= maxWithdraw && amount >= minWithdraw;
    }

    public boolean isValidTransfer(double balance, double amount) {

        return balance >= amount && amount <= maxTransfer && amount >= minTransfer;
    }

    public boolean isValidTransaction(int numOfTransactions) {
        return numOfTransactions < numOfAllowedTransactions;
    }

    public boolean isValidDeposit(double amount) {
        return amount <= maxDeposit && amount >= minDeposit;
    }
}
