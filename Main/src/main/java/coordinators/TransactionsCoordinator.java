package coordinators;

import components.BusinessRules;
import components.Transactions;
import java.sql.ResultSet;
import javax.swing.table.TableModel;

/**
 * The <b>TransactionsCoordinator</b> class is a sub class of
 * {@link GeneralCoordinator} class it contains methods that check the user's
 * input from {@link gui.HomePage} and if it meets the business rules through
 * {@link BusinessRules} and a connection to the database through
 * {@link Transactions} class.
 *
 */
public class TransactionsCoordinator extends GeneralCoordinator {

    /**
     * The integer value of a transaction ID.
     */
    private int transcactionID;
    /**
     * The {@link java.util.Date} value of the current date.
     */
    private java.util.Date utilDate;
    /**
     * The {@link java.sql.Date} value of the current date.
     */
    private java.sql.Date sqlDate;

    /**
     * The {@link java.sql.Date} value of the current time.
     */
    private java.sql.Time sqlTime;

    /**
     * Checks if the withdrawal meets the business rules and it has been done
     * successfully, and records the transaction.
     *
     * @param amount the double value of amount of money to withdraw.
     * @return true if the withdrawal transaction meets the business rules and
     * has been done successfully; false otherwise.
     */
    public boolean withdraw(double amount) {
        double balance = getBalance();
        if (BusinessRules.isValidWithdraw(balance, amount) && isAllowed()) {
            createTransactionID();
            balance -= amount;
            Transactions.setBalance(accountNum, balance);
            Transactions.recordTransaction(transcactionID, accountNum, accountNum,
                    Transactions.type.WITHDRAWAL.name(), amount, sqlDate, sqlTime);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Retrieves the signed in user's balance.
     *
     * @return the double value of the user's balance.
     */
    public double getBalance() {
        return Transactions.getBalance(accountNum);
    }

    /**
     * Retrieves the signed in user's name.
     *
     * @return the string value of the user's name.
     */
    public String getName() {
        return Transactions.getName(accountNum);
    }

    /**
     * Generates a random 9-digits transaction ID.
     */
    public void createTransactionID() {
        int min = 100000000;
        int max = 900000000;
        transcactionID = min + GENERATOR.nextInt(max);
        utilDate = new java.util.Date();
        sqlDate = new java.sql.Date(utilDate.getTime());
        sqlTime = new java.sql.Time(utilDate.getTime());
    }

    /**
     * Checks if the deposit meets the business rules and it has been done
     * successfully, and records the transaction.
     *
     * @param amount the double value of the amount of money to deposit.
     * @return true if the deposit transaction meets the business rules and has
     * been done successfully; false otherwise.
     */
    public boolean deposit(double amount) {
        double balance = getBalance();
        if (BusinessRules.isValidDeposit(amount) && isAllowed()) {
            createTransactionID();
            balance += amount;
            Transactions.setBalance(accountNum, balance);
            Transactions.recordTransaction(transcactionID, accountNum, accountNum, Transactions.type.DEPOSIT.name(), amount, sqlDate, sqlTime);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the number of transactions done by a user per day meets the
     * business rules.
     *
     * @return true if the number of transactions done by a user per day meets
     * the business rules; false otherwise.
     */
    private boolean isAllowed() {
        return BusinessRules.isValidTransaction(Transactions.getNumOfTransactions(accountNum));
    }

    /**
     * Creates a transactions table that contains all the transactions done by a
     * user.
     *
     * @return a {@link TableModel} object.
     */
    public TableModel buildTransactionsTable() {
        ResultSet result = Transactions.getAllTransactions(accountNum);
        return Transactions.resultSetToTableModel(result);
    }

    /**
     * Adds a beneficiary to a user's account.
     *
     * @param beneficiaryAccountNum the integer value of the beneficiary's
     * account number.
     * @param beneficiaryName the string value of the beneficiary's name.
     */
    public void addBeneficiary(int beneficiaryAccountNum, String beneficiaryName) {
        Transactions.addBeneficiary(accountNum, getName(), beneficiaryAccountNum, beneficiaryName);
    }

    /**
     * Creates a beneficiaries table that contains all the beneficiaries and
     * their account number.
     *
     * @return a {@link TableModel} object.
     */
    public TableModel buildBeneficiariesTable() {
        ResultSet result = Transactions.getAllBeneficiaries(accountNum);
        return Transactions.resultSetToTableModel(result);
    }

    /**
     * Checks if a beneficiary is added to the user's beneficiaries.
     *
     * @param beneficiaryAccountNum the integer value of the beneficiary's
     * account number.
     * @return true if the beneficiary is added to the user's beneficiaries.
     */
    public boolean isBeneficiary(int beneficiaryAccountNum) {
        return Transactions.isBeneficiary(accountNum, beneficiaryAccountNum);
    }

    /**
     * Checks if the transfer meets the business rules and it has been done
     * successfully, and records the transaction.
     *
     * @param amount the double value of amount of money to transfer.
     * @param beneficiaryAccountNum integer value of the beneficiary's account
     * number.
     * @return true if the transfer transaction meets the business rules and
     * has been done successfully; false otherwise.
     */
    public boolean transferTo(double amount, int beneficiaryAccountNum) {
        double balance = getBalance();
        double beneficiaryBalance = Transactions.getBalance(beneficiaryAccountNum);

        if (isAllowed() && isBeneficiary(beneficiaryAccountNum) && BusinessRules.isValidTransfer(balance, amount)) {
            createTransactionID();
            balance -= amount;
            Transactions.setBalance(accountNum, balance);
            beneficiaryBalance += amount;
            Transactions.setBalance(beneficiaryAccountNum, beneficiaryBalance);
            Transactions.recordTransaction(transcactionID, accountNum, beneficiaryAccountNum, Transactions.type.TRANSFER.name(), amount, sqlDate, sqlTime);
            return true;
        } else {
            return false;
        }
    }
}
