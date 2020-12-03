/**
 *The package contains the main components of the Banking system
 */
package components;

/**
 * Contains the business rules specified by the bank
 */
public class BusinessRules {

    /**
     * The maximum double amount of money per each withdrawal operation.
     */
    private final static double maxWithdraw = 5000.0;
    /**
     * The minimum double amount of money per each withdrawal operation.
     */
    private final static double minWithdraw = 50.0;
    /**
     * The maximum double amount of money per each deposit operation.
     */
    private final static double maxDeposit = 12000.0;
    /**
     * The minimum double amount of money per each deposit operation.
     */
    private final static double minDeposit = 100.0;
    /**
     * The maximum double amount of money per each transfer operation.
     */
    private final static double maxTransfer = 10000.0;
    /**
     * The minimum double amount of money per each transfer operation.
     */
    private final static double minTransfer = 1000.0;
    /**
     * The maximum number of transactions per day.
     */
    private final static int numOfAllowedTransactions = 5;

    /**
     * Checks if the user's balance is larger than the amount.
     *
     * @param balance a user account's balance.
     * @param amount amount to withdraw.
     * @return true if the withdrawal is allowed; false otherwise.
     */
    public static boolean isValidWithdraw(double balance, double amount) {
        return balance >= amount && amount <= maxWithdraw && amount >= minWithdraw;
    }

    /**
     * Checks if the user's balance is larger than the amount.
     *
     * @param balance a user account's balance.
     * @param amount amount to transfer.
     * @return true if the transfer is allowed; false otherwise.
     */
    public static boolean isValidTransfer(double balance, double amount) {

        return balance >= amount && amount <= maxTransfer && amount >= minTransfer;
    }

    /**
     * Checks if this number of transactions is valid according to the banking
     * rules.
     *
     * @param numOfTransactions number of transactions per day
     * @return true if number of transactions is less than or equal number of
     * allowed transactions; false otherwise.
     */
    public static boolean isValidTransaction(int numOfTransactions) {
        return numOfTransactions < numOfAllowedTransactions;
    }

    /**
     * Checks if the amount meets the banking rules.
     *
     * @param amount amount to deposit.
     * @return true if the amount meets the banking rules; false otherwise.
     */
    public static boolean isValidDeposit(double amount) {
        return amount <= maxDeposit && amount >= minDeposit;
    }
}
