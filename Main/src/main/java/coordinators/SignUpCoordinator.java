/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coordinators;

import components.Registration;
import java.util.Random;

public class SignUpCoordinator extends GeneralCoordinator {

   
    private static int userId = 0;

    public SignUpCoordinator() {
        createAccountNum();
        createUserId();
    }

    public boolean isPhoneNum(String phoneNum) { // 10-digits
        return phoneNum.matches("[0-9]{10}");
    }

    public boolean passMatches(char[] pass1Array, char[] pass2Array) { //During signingUp to check password & repeat pass
        String pass1 = charArrayToString(pass1Array);
        String pass2 = charArrayToString(pass2Array);
        return pass1.equals(pass2);
    }

    public boolean isEmail(String email) {
        return email.matches("[a-zA-Z0-9_%+-]+@[a-zA-Z.-]+\\.[a-zA-Z]{2,}");
    }

    private void createAccountNum() { // 6-digits
        int min = 100000;
        int max = 900000;
        accountNum = min + GENERATOR.nextInt(max);
    }

    public int getAccountNum() {
        return accountNum;
    }
    
    private void createUserId(){ // 8-digits
        int min = 10000000;
        int max = 90000000;
        userId = min + GENERATOR.nextInt(max);
    }
    
      public int getUserId() {
        return userId;
    } 
    public void register(String name, String password, String phoneNum, String email) {
        new Registration(accountNum,userId, name, password, phoneNum, email);
    }

   
}
