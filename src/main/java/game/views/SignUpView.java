package game.views;

import game.GameView;
import game.controllers.AppController;
import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

@Data
public class SignUpView implements GameView {
    //main components
    private AppController mainController;
    private JFrame mainFrame;
    private JPanel mainPanel;

    //labels
    private JLabel signUpLabel;
    private JLabel usernameLabel;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JLabel confirmPasswordLabel;

    //text fields
    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    //panels
    private JPanel usernamePanel;
    private JPanel emailPanel;
    private JPanel passwordPanel;
    private JPanel confirmPasswordPanel;
    private JPanel signUpPanel;
    private JPanel buttonPanel;

    //buttons
    private JButton backButton;
    private JButton forwardButton;

    //viewsV
    private VerificationView verificationView;

    public SignUpView(JFrame mainFrame, JPanel mainPanel, AppController mainController) {
        this.mainController = mainController;
        this.mainFrame = mainFrame;
        this.mainPanel = mainPanel;
        this.mainController.setSignUpView(this);
    }

    public void renderView() {
        mainController.resetMainFrame(); //resetting main frame and panel

        //create each different panel for account information fields
        usernameLabel = new JLabel("Username *");
        usernameField = new JTextField(15);
        usernamePanel = new JPanel();
        createPanel(usernameField, usernamePanel, usernameLabel);

        emailLabel = new JLabel("Email *");
        emailField = new JTextField(15);
        emailPanel = new JPanel();
        createPanel(emailField, emailPanel, emailLabel);

        passwordLabel = new JLabel("Password * ");
        passwordField = new JPasswordField(15);
        passwordPanel = new JPanel();
        createPanel(passwordField, passwordPanel, passwordLabel);

        confirmPasswordLabel = new JLabel("Confirm password *");
        confirmPasswordField = new JPasswordField(15);
        confirmPasswordPanel = new JPanel();
        createPanel(confirmPasswordField, confirmPasswordPanel, confirmPasswordLabel);

        createSignUpPanel(); //create sign up panel

        mainPanel.add(signUpPanel);
        mainFrame.setContentPane(mainPanel);
        mainFrame.setVisible(true);
    }

    //used to create a panel of user information
    private void createPanel(JTextField textField, JPanel panel, JLabel label) {
        //text field configs
        textField.setPreferredSize(new Dimension(35, 30));
        textField.setFont(new Font("Sans-serif", Font.PLAIN, 15));

        //text field panel
        panel.setPreferredSize(new Dimension(250, 60));
        panel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 0));
        //panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(label);
        panel.add(textField);
    }

    private void createSignUpPanel() {
        createButtonPanel(); //create button panel

        JLabel paddingLabel = new JLabel();
        paddingLabel.setPreferredSize(new Dimension(60, 50));

        signUpLabel = new JLabel("Sign Up");
        signUpLabel.setPreferredSize(new Dimension(200, 50));
        signUpLabel.setFont(new Font("Sans-serif", Font.BOLD, 25));

        //sign up panel configs
        signUpPanel = new JPanel();
        signUpPanel.setPreferredSize(new Dimension(400, 500));
        signUpPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        //signUpPanel.setBackground(Color.GRAY);

        //add components
        signUpPanel.add(paddingLabel);
        signUpPanel.add(signUpLabel);
        signUpPanel.add(usernamePanel);
        signUpPanel.add(emailPanel);
        signUpPanel.add(passwordPanel);
        signUpPanel.add(confirmPasswordPanel);
        signUpPanel.add(buttonPanel);
    }

    private void createButtonPanel() {
        JLabel paddingLabel = new JLabel();
        paddingLabel.setPreferredSize(new Dimension(125, 60));

        ImageIcon backArrow = new ImageIcon("src\\main\\java\\Game\\Assets\\back_arrow.png");
        backButton = new JButton("", backArrow);
        backButton.setPreferredSize(new Dimension(45, 35));
        setBackButtonListener();

        ImageIcon forwardArrow = new ImageIcon("src\\main\\java\\Game\\Assets\\forward_arrow.png");
        forwardButton = new JButton("", forwardArrow);
        forwardButton.setPreferredSize(new Dimension(45, 35));
        setForwardButtonListener();

        buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(250, 60));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        buttonPanel.add(backButton);
        buttonPanel.add(paddingLabel);
        buttonPanel.add(forwardButton);
    }

    private void setUsernameFieldListener() {

    }

    private void setEmailFieldListener() {

    }

    private void setPasswordFieldListener() {

    }

    private void setConfirmPasswordListener() {

    }

    private void setBackButtonListener() {
        //action listener for back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //clear main frame
                mainController.resetMainFrame();
                mainController.renderView(mainController.getLoginView());
            }
        });
    }

    private void setForwardButtonListener() {
        //action listener for forward button
        forwardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean validAccount = true;

                String password1 = new String(passwordField.getPassword());
                String password2 = new String(confirmPasswordField.getPassword());

                //erasing the old content on sign up panel to add error messages
                signUpPanel.removeAll();
                signUpPanel.add(signUpLabel);

                if(!usernameLongEnough(usernameField.getText())) {
                    signUpPanel.add(getErrorLabel("username must be between 6-20 characters"));
                    signUpPanel.add(usernamePanel);
                    validAccount = false;

                } else if(!validateUsername(usernameField.getText())) {
                    signUpPanel.add(getErrorLabel("username can only contain alphanumeric characters"));
                    signUpPanel.add(usernamePanel);
                    validAccount = false;

                } else { //username is valid
                    //add the username panel with no errors
                    signUpPanel.add(usernamePanel);
                }

                if(!validateEmail(emailField.getText())) {
                    signUpPanel.add(getErrorLabel("email must be a valid email address including '.'"));
                    signUpPanel.add(emailPanel);
                    validAccount = false;

                } else { //email is valid
                    //add the email panel with no errors
                    signUpPanel.add(emailPanel);
                }

                if(!passwordLongEnough(new String(passwordField.getPassword()))) {
                    //add components
                    signUpPanel.add(getErrorLabel("password must be at least 8 characters in length"));
                    signUpPanel.add(passwordPanel);
                    validAccount = false;

                } else if(!passwordHasSpecialChar(new String(passwordField.getPassword()))) {
                    signUpPanel.add(getErrorLabel("password must contain at least one special character"));
                    signUpPanel.add(getErrorLabel("special characters include @$!%*?&#"));
                    signUpPanel.add(passwordPanel);
                    validAccount = false;

                } else if(!validatePassword(new String(passwordField.getPassword()))) {
                    signUpPanel.add(getErrorLabel("password must contain an upper and lower case character"));
                    signUpPanel.add(getErrorLabel("special characters include @$!%*?&#"));
                    signUpPanel.add(passwordPanel);
                    validAccount = false;

                } else { //password is valid
                    //add the password panel with no errors
                    signUpPanel.add(passwordPanel);
                }

                if(!(password1.equals(password2))) {
                    signUpPanel.add(getErrorLabel("passwords do not match"));
                    signUpPanel.add(confirmPasswordPanel);
                    validAccount = false;

                } else { //passwords match
                    //add the password confirmation field with no errors
                    signUpPanel.add(confirmPasswordPanel);
                }

                signUpPanel.add(buttonPanel); //add button panel back

                if(validAccount) {
                    //render the verification view
                    verificationView = new VerificationView(mainFrame, mainPanel, mainController);
                    verificationView.renderVerificationView(emailField.getText());

                } else {
                    //re-render the main frame and panel with the sign up errors
                    mainPanel.add(signUpPanel);
                    mainFrame.setContentPane(mainPanel);
                    mainFrame.setVisible(true);
                }
            }
        });
    }

    private boolean sendVerificationCode(String userEmail) {
        String recipient = userEmail;
        String sender = "gcbourdon@gmail.com";

        String host = "localhost";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Now set the actual message
            message.setText("This is actual message");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
            return true;
        } catch (MessagingException mex) {
            mex.printStackTrace();
            return false;
        }
    }

    private JLabel getErrorLabel(String errorMessage) {
        JLabel errorLabel = new JLabel(errorMessage);
        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(new Font("Sans-serif", Font.PLAIN, 15));
        return errorLabel;
    }

    private boolean usernameLongEnough(String username) {
        return username.length() >= 6;
    }

    private boolean validateUsername(String username) {
        //username must have the following criteria:
        //6-20 characters long
        //must only contain alphabetical characters and alphanumeric values

        String regex = "^[a-zA-Z0-9]{6,20}$";
        return username.matches(regex);
    }

    private boolean validateEmail(String email) {
        //must be a valid email address

        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    private boolean passwordLongEnough(String password) {
        return password.length() >= 8;
    }

    private boolean passwordHasSpecialChar(String password) {
        String regex = "^(?=.*[@$!%*?&#]).{8,}$";
        return password.matches(regex);
    }

    private boolean validatePassword(String password) {
        //password must be 8 or more characters
        //must contain a special symbol: @$!%*?&
        //must contain a lowercase letter
        //must contain an uppercase letter

        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password.matches(regex);
    }
}
