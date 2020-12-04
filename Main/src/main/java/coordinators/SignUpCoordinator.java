package coordinators;

import components.Registration;

/**
 * The <b>SignUpCoordinator</b> class is a sub class of
 * {@link GeneralCoordinator} class, it contains methods that check the user's
 * input from {@link SignUpPage} and a connection to the database through
 * {@link Registration} class.
 *
 * @author youmna
 */
public class SignUpCoordinator extends GeneralCoordinator {

    /**
     * The integer value of the user's ID.
     */
    private static int userID = 0;

    /**
     * Generates a random account number and a user ID each time an object is
     * created.
     */
    public SignUpCoordinator() {
        createAccountNum();
        createUserId();
    }

    /**
     * Check if the string input consists of 10 integers.
     *
     * @param phoneNum the string value of a phone number to check.
     * @return true if the phone number is a 10-digits integer number; false
     * otherwise.
     */
    public boolean isPhoneNum(String phoneNum) {
        return phoneNum.matches("[0-9]{10}");
    }

    /**
     * Compares the string values of the 2 passed arrays of characters using
     * RegEx.
     *
     * @param pass1Array a characters array.
     * @param pass2Array a characters array.
     * @return true if the string values of the arrays are equals.
     */
    public boolean passMatches(char[] pass1Array, char[] pass2Array) { //During signingUp to check password & repeat pass
        String pass1 = charArrayToString(pass1Array);
        String pass2 = charArrayToString(pass2Array);
        return pass1.equals(pass2);
    }

    /**
     * Checks if the passed string meets the email criteria.
     *
     * @param email the sting value of the email.
     * @return true if the input matches the RegEx; false otherwise.
     */
    public boolean isEmail(String email) {
        return email.matches("[a-zA-Z0-9_%+-]+@[a-zA-Z.-]+\\.[a-zA-Z]{2,}");
    }

    /**
     * Creates a random 6-digits account number.
     */
    private void createAccountNum() {
        int min = 100000;
        int max = 900000;
        accountNum = min + GENERATOR.nextInt(max);
    }

    /**
     * A getter for the account number.
     *
     * @return the integer value of the account number.
     */
    public int getAccountNum() {
        return accountNum;
    }

    /**
     * Creates a random 8-digits user ID.
     */
    private void createUserId() {
        int min = 10000000;
        int max = 90000000;
        userID = min + GENERATOR.nextInt(max);
    }
    
    /**
     * A getter for the user ID.
     * @return 
     */
    public int getUserID() {
        return userID;
    }
    /**
     * Creates a new account and insert it in the database using {@link Registration} class.
     * @param name user's name.
     * @param password user's password.
     * @param phoneNum user's phone number.
     * @param email user's email.
     */
    public void register(String name, String password, String phoneNum, String email) {
        new Registration(accountNum, userID, name, password, phoneNum, email);
    }

}
