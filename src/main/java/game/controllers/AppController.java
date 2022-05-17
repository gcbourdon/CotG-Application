package game.controllers;

import game.GameView;
import game.views.LoginView;
import game.views.MainMenuView;
import game.views.SignUpView;
import game.views.VerificationView;
import lombok.Data;

import javax.swing.*;
import java.awt.*;

//class used to control the flow of the client application using views
@Data
public class AppController {
    //main components of the client
    private JFrame mainFrame;
    private JPanel mainPanel;

    //all views for the app
    private LoginView loginView;
    private SignUpView signUpView;
    private VerificationView verificationView;
    private MainMenuView mainMenuView;

    private final int MAX_WIDTH = 2560;

    public AppController() {

    }

    //starting the client application
    public void startApp() {
        //main frame configs
        mainFrame = new JFrame("CotG"); //main frame for the client
        mainPanel = new JPanel();

        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, MAX_WIDTH, 10));

        mainFrame.setSize(1280, 720);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLayout(new FlowLayout());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setContentPane(mainPanel);

        //rendering the login screen
        this.loginView = new LoginView(mainFrame, mainPanel, this);
        renderView(loginView);
    }

    public void resetMainFrame() {
        //remove all components and re-render the main panel
        mainPanel.removeAll();
        mainFrame.setContentPane(mainPanel);
        mainFrame.setVisible(true);
    }

    public void renderView(GameView gameView) {
        gameView.renderView();
    }
}
