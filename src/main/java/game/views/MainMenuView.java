package game.views;

import game.GameView;
import game.controllers.AppController;
import lombok.Data;

import javax.swing.*;

@Data
public class MainMenuView implements GameView {
    private JFrame mainFrame;
    private JPanel mainPanel;
    private AppController mainController;

    public MainMenuView(JFrame mainFrame, JPanel mainPanel, AppController mainController) {
        this.mainFrame = mainFrame;
        this.mainPanel = mainPanel;
        this.mainController = mainController;
    }

    public void renderView() {

    }

    public void renderMainMenuView() {

    }
}
