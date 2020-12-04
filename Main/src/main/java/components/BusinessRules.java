package components;

/**
 * The <b>BusinessRules</b> class contains the business rules specified by the bank.
 */
public class BusinessRules {

    /**
     * The maximum double amount of money per each withdrawal operation.
     */
    private static final double MAX_WITHDRAW = 5000.0;
    /**
     * The minimum double amount of money per each withdrawal operation.
     */
    private static final double MIN_WITHDRAW = 50.0;
    /**
     * The maximum double amount of money per each deposit operation.
     */
    private static final double MAX_DEPOSIT = 12000.0;
    /**
     * The minimum double amount of money per each deposit operation.
     */
    private static final double MIN_DEPOSIT = 100.0;
    /**
     * The maximum double amount of money per each transfer operation.
     */
    private static final double MAX_TRANSFER = 10000.0;
    /**
     * The minimum double amount of money per each transfer operation.
     */
    private static final double MIN_TRANSFER = 1000.0;
    /**
     * The maximum number of transactions per day.
     */
    private static final int NUM_OF_ALLOWED_TRANSACTIONS = 5;

    /**
     * Checks if the user's balance is larger than the withdrawal amount.
     *
     * @param balance the user's balance.
     * @param amount the amount of money to withdraw.
     * @return true if the withdrawal transaction is allowed; false otherwise.
     */
    public static boolean isValidWithdraw(double balance, double amount) {
        return balance >= amount && amount <= MAX_WITHDRAW && amount >= MIN_WITHDRAW;
    }

    /**
     * Checks if the user's balance is larger than the amount.
     *
     * @param balance the user's balance.
     * @param amount the amount of money to transfer.
     * @return true if the transfer transaction is allowed; false otherwise.
     */
    public static boolean isValidTransfer(double balance, double amount) {

        return balance >= amount && amount <= MAX_TRANSFER && amount >= MIN_TRANSFER;
    }

    /**
     * Checks if the amount of money to deposit meets the banking rules.
     *
     * @param amount the amount of money to deposit.
     * @return true if the amount meets the banking rules; false otherwise.
     */
    public static boolean isValidDeposit(double amount) {
        return amount <= MAX_DEPOSIT && amount >= MIN_DEPOSIT;
    }

    /**
     * Compares the passed number of transactions to the number of allowed transactions per day.
     *
     * @param numOfTransactions the number of transactions per day.
     * @return true if the number of transactions is less than or equal to the number of
     * allowed transactions; false otherwise.
     */
    public static boolean isValidTransaction(int numOfTransactions) {
        return numOfTransactions < NUM_OF_ALLOWED_TRANSACTIONS;
    }

}
