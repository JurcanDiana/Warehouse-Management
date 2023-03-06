package presentation;

import javax.swing.*;

public class MainMenuController {

    private MainMenuView mainMenuView;
    private ClientSettingsView clientSettingsView;
    private ProductSettingsView productSettingsView;
    private OrderSettingsView orderSettingsView;

    /**
     * MAIN MENU
     */

    public MainMenuController(MainMenuView mainMenuView) {
        this.mainMenuView = mainMenuView;
    }

    /**
     * Method that implements the exit button
     * @param exitButton
     */
    public void exit(JButton exitButton) {
        exitButton.addActionListener(e -> System.exit(0));
    }

    /**
     * Method that implements the client settings button
     * @param clientSettingsButton
     */
    public void clientSettings(JButton clientSettingsButton) {
        clientSettingsButton.addActionListener(e -> {
            ClientSettingsView clientSettings = new ClientSettingsView();
            mainMenuView.setVisible(false);
        });
    }

    /**
     * Method that implements the product settings button
     * @param productSettingsButton
     */
    public void productSettings(JButton productSettingsButton) {
        productSettingsButton.addActionListener(e -> {
            ProductSettingsView productSettings = new ProductSettingsView();
            mainMenuView.setVisible(false);
        });
    }

    /**
     * Method that implements the order settings button
     * @param orderSettingsButton
     */
    public void orderSettings(JButton orderSettingsButton) {
        orderSettingsButton.addActionListener(e -> {
            OrderSettingsView orderSettings = new OrderSettingsView();
            mainMenuView.setVisible(false);
        });
    }

}
