package game;

import game.controllers.AppController;

public class Main {
    static AppController appController = new AppController();

    public static void main(String[] args) {
        appController.startApp();
    }
}
