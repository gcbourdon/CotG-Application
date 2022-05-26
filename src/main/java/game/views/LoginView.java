package game.views;

import game.GameView;
import game.controllers.AppController;
import game.models.User;
import lombok.Data;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@Data
public class LoginView implements GameView {
    private AppController mainController;
    private JFrame mainFrame;
    private JPanel mainPanel;
    private JLabel cotgLabel;
    private JLabel signInLabel;
    private JLabel signUpLabel;

    private JTextField usernameField;
    private JPasswordField passwordField;

    private JPanel cotgLogoPanel;
    private JPanel usernamePanel;
    private JPanel passwordPanel;
    private JPanel signInPanel;
    private JPanel signUpPanel;

    private JButton signUpButton;

    //views
    private SignUpView signUpView;
    private MainMenuView mainMenuView;

    //data
    private User user;

    private final int MAX_WIDTH = 2560;

    public LoginView(JFrame mainFrame, JPanel mainPanel, AppController mainController) {

        this.mainFrame = mainFrame;
        this.mainPanel = mainPanel;
        this.mainController = mainController;
        this.mainController.setLoginView(this);
        this.user = new User();
    }

    public void renderView() {
        createLogoPanel(); //create logo panel
        createSignInPanel(); //create sign in panel
        createSignUpMessagePanel();

        //main panel configs
        mainPanel.add(cotgLogoPanel);
        mainPanel.add(signInPanel);
        mainPanel.add(signUpPanel);

        //displaying the mainframe at the end so all components are rendered
        mainFrame.setVisible(true);
    }

    //used to create the CotG log panel
    private void createLogoPanel() {
        ImageIcon cotgLogo = new ImageIcon("src\\main\\java\\Game\\Assets\\CotG_Logo_electricblue.png"); // CotG icon
        cotgLabel = new JLabel(); //game label for the login screen
        cotgLabel.setPreferredSize(new Dimension(800, 260));
        cotgLabel.setIcon(cotgLogo);

        //logo panel configs
        cotgLogoPanel = new JPanel();
        cotgLogoPanel.setPreferredSize(new Dimension(800, 300));
        cotgLogoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        cotgLogoPanel.add(cotgLabel);
    }

    //used to create the sign in panel
    private void createSignInPanel() {
        createUsernamePanel(); //create username panel
        createPasswordPanel(); //create password panel

        JLabel paddingLabel = new JLabel();
        paddingLabel.setPreferredSize(new Dimension(60, 50));

        signInLabel = new JLabel("Sign in");
        signInLabel.setPreferredSize(new Dimension(100, 50));
        signInLabel.setFont(new Font("Sans-serif", Font.BOLD, 25));

        signInPanel = new JPanel();
        signInPanel.setPreferredSize(new Dimension(260, 200));
        signInPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        signInPanel.add(paddingLabel);
        signInPanel.add(signInLabel);
        signInPanel.add(usernamePanel);
        signInPanel.add(passwordPanel);
    }

    //used to create the sign up panel
    private void createSignUpMessagePanel() {
        signUpPanel = new JPanel();
        signUpPanel.setPreferredSize(new Dimension(500, 30));
        signUpPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

        //sign up label
        signUpLabel = new JLabel("Don't have an account? Sign up here: ");
        signUpLabel.setFont(new Font("Sans-serif", Font.PLAIN, 15));

        //sign up button
        signUpButton = new JButton("Create Account");
        signUpButton.setPreferredSize(new Dimension(130, 30));
        setSignUpButtonListener();

        signUpPanel.add(signUpLabel);
        signUpPanel.add(signUpButton);
    }

    //used to create the username panel
    private void createUsernamePanel() {
        usernameField = new JTextField(15);

        //username field configs
        usernameField.setPreferredSize(new Dimension(35, 30));
        usernameField.setFont(new Font("Sans-serif", Font.PLAIN, 15));
        setUsernameFieldListener();

        //username panel
        usernamePanel = new JPanel();
        usernamePanel.setPreferredSize(new Dimension(250, 60));
        usernamePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 0));
        usernamePanel.add(new JLabel("Username "));
        usernamePanel.add(usernameField);
    }

    //used to create the password panel
    private void createPasswordPanel() {
        passwordField = new JPasswordField(15);

        //password field configs
        passwordField.setPreferredSize(new Dimension(35, 30));
        passwordField.setFont(new Font("Sans-serif", Font.PLAIN, 15));
        setPasswordFieldListener();

        //password panel
        passwordPanel = new JPanel();
        passwordPanel.setPreferredSize(new Dimension(250, 60));
        passwordPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 0));
        passwordPanel.add(new JLabel("Password "));
        passwordPanel.add(passwordField);
    }

    private void setUsernameFieldListener() {
        //mouse event listener for username field
        this.usernameField.addMouseListener(new MouseListener() {
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

        //action event listener for username field
        this.usernameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO -> this gets triggered when the user presses the enter key so submit the sign in request here
                System.out.println(usernameField.getText());
                User user = new User();
                user.setUsername(usernameField.getText());

                if(mainMenuView == null) {

                    mainMenuView = new MainMenuView(mainFrame, mainPanel, mainController, user);
                }
                mainController.renderView(mainMenuView);

            }
        });
    }

    private void setPasswordFieldListener() {
        //mouse event listener for password field
        this.passwordField.addMouseListener(new MouseListener() {
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

        //action event listener for password field
        this.passwordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getActionCommand());
                //TODO -> this gets triggered when the user presses the enter key so submit the sign in request here

                user.setPassword(new String(passwordField.getPassword()));

                if(mainMenuView == null) {
                    mainMenuView = new MainMenuView(mainFrame, mainPanel, mainController, user);
                }
                mainController.renderView(mainMenuView);
            }
        });
    }

    private void setSignUpButtonListener() {
        //action listener for sign up button
        this.signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO -> take user to sign-up view to create an account

                //this series will remove everything from the main panel and is a nice transition into next view

                //rendering the sign-up view for a user to create an account
                signUpView = new SignUpView(mainFrame, mainPanel, mainController);
                mainController.renderView(signUpView);
            }
        });
    }
}

