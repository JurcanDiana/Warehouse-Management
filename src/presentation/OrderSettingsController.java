package presentation;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import model.Client;
import model.Orders;
import model.Product;

import javax.swing.*;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 *  private int orderID;
 *     private int clientID;
 *     private int productID;
 *     private double orderTotal;
 *     private int orderQuantity;
 */

public class OrderSettingsController {

    private OrderSettingsView orderSettingsView;

    private OrderBLL orderBLL;
    private ProductBLL productBLL;
    private ClientBLL clientBLL;

    private int orderID;
    private String clientName;
    private String productName;
    private double orderTotal;
    private int orderQuantity;

    public OrderSettingsController(OrderSettingsView orderSettingsView) {
        this.orderSettingsView = orderSettingsView;
        this.orderBLL = new OrderBLL();
        this.productBLL = new ProductBLL();
        this.clientBLL = new ClientBLL();
    }

    /**
     * Method that gets the order information
     */
    public void getOrderInformation() {
        this.clientName = orderSettingsView.getComboBoxClient();
        this.productName = orderSettingsView.getComboBoxProduct();
        this.orderQuantity = parseInt(orderSettingsView.getQuantityTextField().getText());
        this.orderTotal = this.orderQuantity * getSelectedProduct().getProductPrice();
    }

    /**
     * Method that selects the ordered product
     * @return product
     */
    private Product getSelectedProduct() {
        return productBLL.findProductByName(this.productName);
    }

    /**
     * ADD ORDER BUTTON
     */

    /**
     * Method that implements the functionality of the add order button
     * @param addOrderButton
     */
    public void addOrderButton(JButton addOrderButton) {
        addOrderButton.addActionListener(e -> {
            getOrderInformation();

            if(productBLL.findProductById(getSelectedProduct().getProductID()).getQuantity() >= orderQuantity) {
                orderBLL.insertOrder(new Orders(clientName, productName, orderTotal, orderQuantity));

                productBLL.update(new Product(
                        getSelectedProduct().getProductID(),
                        getSelectedProduct().getProductName(),
                        getSelectedProduct().getQuantity() - orderQuantity,
                        (int) getSelectedProduct().getProductPrice()),
                        getSelectedProduct().getProductID());
            }
            else {
                System.out.println("Not enough stock :( ");
            }
        });
    }

    /**
     * COMBO BOXES
     */

    /**
     * Method that selects data of a product from a combo box
     */
    public void getProductsComboBox() {
        List<Product> products = productBLL.findAll();
        for (Product product: products) {
            orderSettingsView.getProductComboBox().addItem(product.getProductName());
        }
    }

    /**
     * Method that selects data of a client from a combo box
     */
    public void getClientsComboBox() {
        List<Client> clients = clientBLL.findAll();
        for (Client client: clients) {
            orderSettingsView.getClientComboBox().addItem(client.getClientName());
        }
    }

    /**
     * FUNCTIONALITY OF BACK AND EXIT BUTTONS
     */

    /**
     * Method that implements the functionality of the back button
     * @param backOrdersButton
     */
    public void backOrders(JButton backOrdersButton) {
        backOrdersButton.addActionListener(e -> {
            MainMenuView mainMenuView = new MainMenuView();
            mainMenuView.setVisible(true);
            orderSettingsView.setVisible(false);
        });
    }

    /**
     * Method that implements the functionality of the exit button
     * @param exitButton
     */
    public void exit(JButton exitButton) {
        exitButton.addActionListener(e -> System.exit(0));
    }
}
