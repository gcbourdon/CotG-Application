package game.views;

import game.GameView;
import game.controllers.AppController;
import game.graphics.ProfileIcon;
import game.models.User;
import lombok.Data;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.security.DomainCombiner;

@Data
public class MainMenuView implements GameView {
    //main components
    private JFrame mainFrame;
    private JPanel mainPanel;
    private AppController mainController;

    //labels
    private JLabel paddingLabel;
    private JLabel usernameLabel;
    private JLabel profileLabel;
    private JLabel friendsLabel;

    //fields

    //panes
    private JScrollPane chatScrollPane;

    //panels
    private JPanel paddingPanel;
    private JPanel userPanel;
    private JPanel menuBarPanel;
    private JPanel chatPanel;
    private JPanel topSectionPanel;

    //buttons
    private JButton playButton;
    private JButton cardVaultButton;
    private JButton rulesButton;
    private JButton settingsButton;
    private JButton storeButton;

    //graphics
    ProfileIcon profileIcon;

    //views

    //data
    User user;

    public MainMenuView(JFrame mainFrame, JPanel mainPanel, AppController mainController, User user) {
        this.mainFrame = mainFrame;
        this.mainPanel = mainPanel;
        this.mainController = mainController;
        this.user = user;
    }

    public void renderView() {
        mainController.resetMainFrame();

        createUserPanel(); //create user panel
        createMenuPanel(); //create menu bar panel
        createChatPane(); //create the chat scroll pane

        //padding panel
        paddingPanel = new JPanel();
        paddingPanel.setPreferredSize(new Dimension(200, 200));

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(userPanel, BorderLayout.WEST);
        mainPanel.add(menuBarPanel, BorderLayout.CENTER);
        mainPanel.add(chatScrollPane, BorderLayout.EAST);
        mainFrame.setVisible(true);
    }

    private void createUserPanel() {
        //user panel configs
        userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.PAGE_AXIS));

        //padding label
        paddingPanel = new JPanel();
        paddingPanel.setPreferredSize(new Dimension(20, 30));

        //profile icon
        profileIcon = new ProfileIcon(20, 20,100, 100);

        //username label
        usernameLabel = new JLabel(String.format("  %s", user.getUsername()));
        usernameLabel.setPreferredSize(new Dimension(250, 30));
        usernameLabel.setFont(new Font("Sans-serif", Font.BOLD, 20));

        //add components
        userPanel.add(usernameLabel);
        userPanel.add(profileIcon);
    }

    private void createMenuPanel() {
        //menu bar panel configs
        menuBarPanel = new JPanel();
        menuBarPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 10));
        //menuBarPanel.setBackground(Color.BLACK);

        //play button
        playButton = new JButton("Play");
        playButton.setPreferredSize(new Dimension(100, 35));

        //card button
        cardVaultButton = new JButton("Card Vault");
        cardVaultButton.setPreferredSize(new Dimension(100, 35));

        //rules button
        rulesButton = new JButton("Rules");
        rulesButton.setPreferredSize(new Dimension(100, 35));

        //settings button
        settingsButton = new JButton("Settings");
        settingsButton.setPreferredSize(new Dimension(100, 35));

        //store button
        storeButton = new JButton("Store");
        storeButton.setPreferredSize(new Dimension(100, 35));

        menuBarPanel.add(playButton);
        menuBarPanel.add(cardVaultButton);
        menuBarPanel.add(rulesButton);
        menuBarPanel.add(settingsButton);
        menuBarPanel.add(storeButton);
    }

    private void createChatPane() {
        chatPanel = new JPanel();
        chatPanel.setPreferredSize(new Dimension(300, 2000));
        chatPanel.setBackground(new Color(208, 208, 208));

        friendsLabel = new JLabel("Friends List");
        friendsLabel.setPreferredSize(new Dimension(120, 30));
        friendsLabel.setFont(new Font("Sans-serif", Font.PLAIN, 20));

        chatPanel.add(friendsLabel);

        chatScrollPane = new JScrollPane(chatPanel);
        chatScrollPane.getVerticalScrollBar().setUnitIncrement(15); //mouse sensitivity increased on scroll
    }

    public void renderMainMenuView() {

    }
}
