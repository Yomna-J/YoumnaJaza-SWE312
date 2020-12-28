package coordinators;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import components.SigningIn;
import java.io.IOException;
import java.util.Random;

/**
 * The <b>GeneralCoordinator</b> class is a super class that contains the main
 * methods of checking the user's input and sign in assets.
 *
 * @author youmna
 */
public class GeneralCoordinator {

    /**
     * An object of the Random class that uses the current time as a seed.
     */
    protected static final Random GENERATOR = new Random(System.currentTimeMillis());
    /**
     * The integer value of the user account number.
     */
    protected static int accountNum = 0;
    /**
     * An object of the {@link SigingIn} class.
     */
    private SigningIn signingIn = new SigningIn();
    /**
     * The string value of the OTP (One Time Password).
     */
    private String otp;

    /**
     * Checks if the input consists of letters and white space.
     *
     * @param input a string value to be checked.
     * @return true if input contains only letters and white space; false
     * otherwise.
     */
    public boolean isAlphabetic(String input) {
        return input.matches("[a-zA-Z\\s]+");
    }

    /**
     * Converts a character array to a string value.
     *
     * @param charArray an array with character values.
     * @return the string value of adding the array characters.
     */
    public String charArrayToString(char[] charArray) {
        String result = "";
        for (int i = 0; i < charArray.length; i++) {
            result += charArray[i];
        }
        return result;
    }

    /**
     * Checks if the string input is an integer number.
     *
     * @param text a string value to be checked.
     * @return true if the string text is an integer value; false otherwise.
     */
    public boolean isNumeric(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    /**
     * Checks if the string input is a double number.
     *
     * @param text a string value to be checked.
     * @return true if the string text is a double value; false otherwise.
     */
    public boolean isMoneyAmount(String text) {
        try {
            Double.parseDouble(text);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    /**
     * Checks if the credentials are correct.
     *
     * @param accountNum user's account number.
     * @param password user's account password.
     * @return true if the credentials are correct; false otherwise.
     */
    public boolean areValidCredentials(int accountNum, String password) {
        return signingIn.signIn(accountNum, password);
    }

    /**
     * Sets the account number to passed arguments.
     *
     * @param accountNum user's account number.
     */
    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }

    /**
     * Creates a random OTP(One Time Password) of 8 digits.
     */
    private void createOtp() {
        final String CAPITAL_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String SMALL_CHARS = "abcdefghijklmnopqrstuvwxyz";
        final String NUMBERS = "0123456789";
        final String SYMBOLS = "!@#$%^&*_=";
        final int OTP_LENGTH = 8;
        String values = CAPITAL_CHARS + SMALL_CHARS + NUMBERS + SYMBOLS;
        char[] otp = new char[OTP_LENGTH];
        for (int i = 0; i < OTP_LENGTH; i++) {
            otp[i] = values.charAt(GENERATOR.nextInt(values.length()));
        }
        this.otp = charArrayToString(otp);
    }

    /**
     * Checks if the passed string input matches the OTP.
     *
     * @param otp string value of the OTP to be checked.
     * @return true if the passed OTP matches the generated one.
     */
    public boolean otpMatches(String otp) {
        return this.otp.equals(otp);
    }

    /**
     * Checks if the account number exists.
     *
     * @param accountNum user's account number.
     * @return true if the account number exists in the database; false
     * otherwise.
     */
    public boolean isRigestered(int accountNum) {
        return signingIn.isRigesteredAccountNum(accountNum);
    }

    /**
     * Sends an email using SendGrid API to the passed integer value of the
     * account number and returns the status of the sending the email.
     *  
     * @param accountNum the receiver account number.
     * @return true if the email is sent successfully; false otherwise.
     */
    public boolean sendEmail(int accountNum) {
        createOtp();
        String email = signingIn.getEmail(accountNum);
        Email from = new Email("------------------Hidden-------------");
        Email to = new Email(email);
        String subject = "Banking System One time password";
        Content content = new Content("text/plain", "Your one time password is: " + otp);
        Mail mail = new Mail(from, subject, to, content);
        SendGrid sg = new SendGrid("------------Hidden---------------");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            return true;
        } catch (IOException exception) {
            return false;
        }
    }

}
