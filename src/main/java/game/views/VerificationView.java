package game.views;

import game.GameView;
import game.controllers.AppController;
import lombok.Data;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Properties;

@Data
public class VerificationView implements GameView {
    //main components
    private JFrame mainFrame;
    private JPanel mainPanel;
    private AppController mainController;

    //labels
    private JLabel emailVerificationLabel;
    private JLabel enterCodeLabel;
    private JLabel successMessage;
    private JLabel loginLabel;
    private JLabel congratsMessage;

    //fields
    private JTextField verifyField;

    //panels
    private JPanel verificationPanel;
    private JPanel buttonPanel;
    private JPanel codePanel;
    private JPanel successPanel;
    private JPanel loginPanel;

    //buttons
    private JButton sendButton;
    private JButton loginButton;
    private JButton backButton;

    //views
    private SignUpView signUpView;


    //user information
    String userEmail;

    public VerificationView(JFrame mainFrame, JPanel mainPanel, AppController mainController) {
        this.mainFrame = mainFrame;
        this.mainPanel = mainPanel;
        this.mainController = mainController;
        this.userEmail = null;
        this.mainController.setVerificationView(this);
    }

    public void renderView() {
        mainController.resetMainFrame();
        this.userEmail = userEmail;

        createVerificationPanel(userEmail);
    }

    public void renderVerificationView(String userEmail) {
        mainController.resetMainFrame();
        this.userEmail = userEmail;

        createVerificationPanel(userEmail);
    }

    private void createVerificationPanel(String userEmail) {
        verificationPanel = new JPanel();
        verificationPanel.setPreferredSize(new Dimension(2000, 200));
        verificationPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));

        //padding labels
        JLabel padding1 = new JLabel();
        padding1.setPreferredSize(new Dimension(200, 50));

        JLabel padding2 = new JLabel();
        padding2.setPreferredSize(new Dimension(30, 50));

        //back button
        ImageIcon backArrow = new ImageIcon("src\\main\\java\\Game\\Assets\\back_arrow.png");
        backButton = new JButton("", backArrow);
        backButton.setPreferredSize(new Dimension(45, 35));
        setBackButtonListener();


        //verify code label
        emailVerificationLabel = new JLabel(String.format("Send a verification code to '%s' ?", userEmail));
        emailVerificationLabel.setPreferredSize(new Dimension(500, 50));
        emailVerificationLabel.setFont(new Font("Sans-serif", Font.PLAIN, 20));

        createSendButtonPanel(); //create send button panel
        createCodePanel(); //create verification code panel

        verificationPanel.add(padding1);
        verificationPanel.add(backButton);
        verificationPanel.add(padding2);
        verificationPanel.add(emailVerificationLabel);
        verificationPanel.add(buttonPanel);
        verificationPanel.add(codePanel);

        mainPanel.add(verificationPanel);
        mainFrame.setContentPane(mainPanel);
        mainFrame.setVisible(true);
    }

    private void createSendButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(2000, 80));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 15));

        //send button
        sendButton = new JButton("send code");
        sendButton.setPreferredSize(new Dimension(100, 30));
        setSendButtonListener();

        buttonPanel.add(sendButton);
        //buttonPanel.add(loginButton);
    }

    private void createSuccessPanel() {
        successPanel = new JPanel();
        successPanel.setPreferredSize(new Dimension(500, 100));
        successPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JLabel padding = new JLabel();
        padding.setPreferredSize(new Dimension(1000, 30));

        congratsMessage = new JLabel("Congratulations!");
        congratsMessage.setPreferredSize(new Dimension(200, 30));
        congratsMessage.setFont(new Font("Sans-serif", Font.BOLD, 20));

        successMessage = new JLabel("Your account was successfully created");
        successMessage.setPreferredSize(new Dimension(300, 30));
        successMessage.setFont(new Font("Sans-serif", Font.PLAIN, 15));

        successPanel.add(congratsMessage);
        successPanel.add(successMessage);
    }

    private void createLoginPanel() {
        loginPanel = new JPanel();
        loginPanel.setPreferredSize(new Dimension(2000, 80));
        loginPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));

        loginLabel = new JLabel("It's your turn to conqueror the grid! ");
        loginLabel.setPreferredSize(new Dimension(250, 30));
        loginLabel.setFont(new Font("Sans-serif", Font.PLAIN, 15));

        loginButton = new JButton("sign in");
        loginButton.setPreferredSize(new Dimension(100, 30));
        setLoginButtonListener();

        loginPanel.add(loginLabel);
        loginPanel.add(loginButton);
    }

    private void createCodePanel() {
        codePanel = new JPanel();
        codePanel.setPreferredSize(new Dimension(2000, 80));
        codePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

        enterCodeLabel = new JLabel("enter code: ");
        enterCodeLabel.setPreferredSize(new Dimension(100, 30));
        enterCodeLabel.setFont(new Font("Sans-serif", Font.PLAIN, 15));

        verifyField = new JTextField(15);
        verifyField.setPreferredSize(new Dimension(500, 30));
        verifyField.setFont(new Font("Sans-serif", Font.PLAIN, 15));
        setVerifyFieldListener();

        codePanel.add(enterCodeLabel);
        codePanel.add(verifyField);
    }

    private void setSendButtonListener() {
        //action listener for send button
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO -> send the verification code to the user email
                //sendVerificationCode();
            }
        });
    }

    private void setLoginButtonListener() {
        //action listener for login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainController.resetMainFrame();
                mainController.renderView(mainController.getLoginView());
            }
        });
    }

    private void setBackButtonListener() {
        //action listener for the back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainController.resetMainFrame();
                mainController.renderView(mainController.getSignUpView());
            }
        });
    }

    private void setVerifyFieldListener() {

        //mouse listener for verify field
        verifyField.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        //action listener for verify field
        verifyField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(verifyCode(e.getActionCommand())) {
                    createSuccessPanel();
                    createLoginPanel();

                    mainPanel.add(successPanel);
                    mainPanel.add(loginPanel);
                    mainFrame.setContentPane(mainPanel);
                    mainFrame.setVisible(true);
                }
            }
        });
    }

    private boolean sendVerificationCode() {
        String recipient = this.userEmail;
        String sender = "test@gmail.com";

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

    private boolean verifyCode(String code) {
        //TODO -> implement the logic to verify a code by retrieving it from cache or DB
        return true;
    }
}
