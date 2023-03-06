package presentation;

import bll.ProductBLL;
import model.Product;

import javax.swing.*;
import java.util.List;

public class OrderSettingsView extends JFrame {

    private JPanel panel;
    private JComboBox clientComboBox;
    private JComboBox productComboBox;
    private JButton addOrderButton;
    private JButton backButton;
    private JButton exitButton;
    private JTextField quantityTextField;

    public OrderSettingsView() {

        super("Order Settings");
        setContentPane(panel);
        setSize(500, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);//frame on the center
        setVisible(true);

        OrderSettingsController controller = new OrderSettingsController(this);

        //combo boxes
        controller.getProductsComboBox();
        controller.getClientsComboBox();
        controller.addOrderButton(addOrderButton);

        //exit application
        controller.exit(exitButton);

        //back button
        controller.backOrders(backButton);
    }

    /**
     * Method that gets the client selected
     * @return String of selected information
     */
    public String getComboBoxClient() {
        return clientComboBox.getSelectedItem().toString();
    }

    /**
     * Method that gets the product selected
     * @return String of selected product
     */
    public String getComboBoxProduct() {
        return productComboBox.getSelectedItem().toString();
    }

    /**
     * Method that gets the client info from the combo box
     * @return instance of JComboBox
     */
    public JComboBox getClientComboBox() {
        return clientComboBox;
    }

    /**
     * Method that gets the product info from the combo box
     * @return instance of JComboBox
     */
    public JComboBox getProductComboBox() {
        return productComboBox;
    }

    /**
     * Method that gets quantity from a text field
     * @return instance of JTextField
     */
    public JTextField getQuantityTextField() {
        return quantityTextField;
    }
}
