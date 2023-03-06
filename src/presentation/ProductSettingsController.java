package presentation;

import bll.ProductBLL;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class ProductSettingsController {

    private ProductSettingsView productSettingsView;
    private ProductBLL productBLL;
    private int productID;
    private String productName;
    private int quantity;
    private double productPrice;

    public ProductSettingsController(ProductSettingsView productSettingsView) {
        this.productSettingsView = productSettingsView;
        this.productBLL = new ProductBLL();
    }

    /**
     * Method that gets the product information
     */
    public void getProductInformation() {
        this.productID = parseInt(productSettingsView.getProductIDTextField().getText());
        this.productName = productSettingsView.getProductNameTextField().getText();
        this.quantity = parseInt(productSettingsView.getQuantityTextField().getText());
        this.productPrice = parseDouble(productSettingsView.getPriceTextField().getText());
    }

    /**
     * FUNCTIONALITY OF ADD/EDIT/DELETE BUTTONS
     */

    /**
     * Method that implements the functionality of the add product button
     * @param addProductButton
     */
    public void addProductButton(JButton addProductButton) {
        addProductButton.addActionListener(e -> {
            getProductInformation();
            productBLL.insertProduct(new Product(productID, productName, quantity, productPrice));
        });
    }

    /**
     * Method that implements the functionality of the edit order button
     * @param editProductButton
     */
    public void editProductButton(JButton editProductButton) {
        editProductButton.addActionListener(e -> {
            getProductInformation();
            productBLL.update(new Product(productID, productName, quantity, productPrice), productID);
        });
    }

    /**
     * Method that implements the functionality of the delete product button
     * @param deleteProductButton
     */
    public void deleteProductButton(JButton deleteProductButton) {
        deleteProductButton.addActionListener(e -> {
            getProductInformation();
            productBLL.delete(productID);
        });
    }

    /**
     * Method that implements the functionality of the view all products button
     * @param viewAllProductsButton
     */
    public void viewAllProductsButton(JButton viewAllProductsButton) {
        viewAllProductsButton.addActionListener(e -> {
            DefaultTableModel model = new DefaultTableModel();
            JTable table = productSettingsView.getViewAllProductsTable();

            productBLL.viewAllProducts(model, table);
        });
    }

    /**
     * FUNCTIONALITY OF BACK AND EXIT BUTTONS
     */

    /**
     * Method that implements the functionality of the back button
     * @param backProductsButton
     */
    public void backProducts(JButton backProductsButton) {
        backProductsButton.addActionListener(e -> {
            MainMenuView mainMenuView = new MainMenuView();
            mainMenuView.setVisible(true);
            productSettingsView.setVisible(false);
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
