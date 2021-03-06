package gui;

import coordinators.GeneralCoordinator;
import javax.swing.JOptionPane;

/**
 * The <b>SignInPage</b> JFrame is the main page of the program that allows the
 * user to sign in or to register.
 */
public class SignInPage extends javax.swing.JFrame {

    private GeneralCoordinator gCoordinator = new GeneralCoordinator();

    /**
     * Creates new form
     */
    public SignInPage() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        accountNumLabel = new javax.swing.JLabel();
        passLabel = new javax.swing.JLabel();
        accountNumField = new javax.swing.JTextField();
        passField = new javax.swing.JPasswordField();
        signInBtn = new javax.swing.JButton();
        joinUsLabel = new javax.swing.JLabel();
        signUpBtn = new javax.swing.JButton();
        useOtpBtn = new javax.swing.JButton();
        orLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sign In");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(347, 352));

        accountNumLabel.setText("Account Number");

        passLabel.setText("Password");

        accountNumField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountNumFieldActionPerformed(evt);
            }
        });

        passField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passFieldActionPerformed(evt);
            }
        });

        signInBtn.setText("Sign in");
        signInBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signInBtnActionPerformed(evt);
            }
        });

        joinUsLabel.setText("Don't have an account ! Join us");

        signUpBtn.setText("Sign up");
        signUpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signUpBtnActionPerformed(evt);
            }
        });

        useOtpBtn.setText("Use one time password");
        useOtpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                useOtpBtnActionPerformed(evt);
            }
        });

        orLabel.setText("Or");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(accountNumLabel)
                    .addComponent(passLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(accountNumField)
                    .addComponent(passField, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(82, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(signUpBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(signInBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(joinUsLabel)
                    .addComponent(orLabel)
                    .addComponent(useOtpBtn))
                .addGap(96, 96, 96))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(accountNumLabel)
                    .addComponent(accountNumField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passLabel)
                    .addComponent(passField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(signInBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(orLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(useOtpBtn)
                .addGap(49, 49, 49)
                .addComponent(joinUsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(signUpBtn)
                .addGap(52, 52, 52))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Allows the user to sign in.
     *
     * @param evt
     */
    private void signInBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signInBtnActionPerformed

        if (!gCoordinator.isNumeric(accountNumField.getText())) {
            JOptionPane.showMessageDialog(this, "Invalid Account Number", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (passField.getPassword().length == 0) {
            JOptionPane.showMessageDialog(this, "Password can't be Empty", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int accountNum = Integer.parseInt(accountNumField.getText());
            String password = gCoordinator.charArrayToString(passField.getPassword());
            if (gCoordinator.areValidCredentials(accountNum, password) == true) {
                gCoordinator.setAccountNum(accountNum);
                this.dispose();
                new HomePage().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Account Number or Password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_signInBtnActionPerformed
    /**
     * Switches the page to the registration page.
     *
     * @param evt
     */
    private void signUpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signUpBtnActionPerformed
        this.dispose();
        new SignUpPage().setVisible(true);
    }//GEN-LAST:event_signUpBtnActionPerformed

    private void accountNumFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountNumFieldActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_accountNumFieldActionPerformed

    private void passFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passFieldActionPerformed

    /**
     * Allows the user to sign in using an OTP.
     *
     * @param evt
     */
    private void useOtpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_useOtpBtnActionPerformed
        if (!gCoordinator.isNumeric(accountNumField.getText()) || !gCoordinator.isRigestered(Integer.parseInt(accountNumField.getText()))) {
            JOptionPane.showMessageDialog(this, "Invalid Account Number", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int accountNum = Integer.parseInt(accountNumField.getText());
            if (gCoordinator.sendEmail(accountNum)) {
                JOptionPane.showMessageDialog(this, "Password was sent to your email successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                String otp = JOptionPane.showInputDialog(this, "Enter the password", "Enter OTP", JOptionPane.PLAIN_MESSAGE);

                if (gCoordinator.otpMatches(otp)) {
                    gCoordinator.setAccountNum(accountNum);
                    this.dispose();
                    new HomePage().setVisible(true);
                } else if (otp != null) {
                    JOptionPane.showMessageDialog(this, "Wrong password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Error with the rigestered email", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }

    }//GEN-LAST:event_useOtpBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SignUpPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SignUpPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SignUpPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignUpPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignInPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField accountNumField;
    private javax.swing.JLabel accountNumLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel joinUsLabel;
    private javax.swing.JLabel orLabel;
    private javax.swing.JPasswordField passField;
    private javax.swing.JLabel passLabel;
    private javax.swing.JButton signInBtn;
    private javax.swing.JButton signUpBtn;
    private javax.swing.JButton useOtpBtn;
    // End of variables declaration//GEN-END:variables
}
