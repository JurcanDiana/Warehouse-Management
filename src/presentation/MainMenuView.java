package presentation;

import javax.swing.*;

public class MainMenuView extends JFrame {

    private JPanel panel;

    private JButton productSettingsButton;
    private JButton orderSettingsButton;
    private JButton exitButton;
    private JButton clientSettingsButton;
    private JLabel titleOfWarehouse;

    public MainMenuView() {

        super("Warehouse Management");
        setContentPane(panel);
        setSize(500, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //frame on the center
        setVisible(true);

        MainMenuController controller = new MainMenuController(this);

        //exit application
        controller.exit(exitButton);

        //other windows instantiation
        controller.clientSettings(clientSettingsButton);
        controller.productSettings(productSettingsButton);
        controller.orderSettings(orderSettingsButton);
    }
}
