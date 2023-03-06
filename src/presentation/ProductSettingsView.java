package presentation;

import javax.swing.*;

public class ProductSettingsView extends JFrame {

    private JPanel panel;
    private JTable viewAllProductsTable;
    private JButton viewAllProductsButton;
    private JButton backButton;
    private JButton exitButton;
    private JTextField priceTextField;
    private JTextField quantityTextField;
    private JTextField productNameTextField;
    private JTextField productIDTextField;
    private JButton addProductButton;
    private JButton editProductButton;
    private JButton deleteProductButton;

    public ProductSettingsView() {

        super("Product Settings");
        setContentPane(panel);
        setSize(700, 550);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //frame on the center
        setVisible(true);

        ProductSettingsController controller = new ProductSettingsController(this);

        //add button
        controller.addProductButton(addProductButton);
        controller.editProductButton(editProductButton);
        controller.deleteProductButton(deleteProductButton);
        controller.viewAllProductsButton(viewAllProductsButton);
        
        //exit application
        controller.exit(exitButton);

        //back button
        controller.backProducts(backButton);
    }

    /**
     * Method that gets an instance of JTable with all the products
     * @return instance of a JTable
     */
    public JTable getViewAllProductsTable() {
        return viewAllProductsTable;
    }

    /**
     * Method that sets an instance of JTable with all the products
     * @param viewAllProductsTable
     */
    public void setViewAllProductsTable(JTable viewAllProductsTable) {
        this.viewAllProductsTable = viewAllProductsTable;
    }

    /**
     * Method that gets the price from a text field
     * @return instance of a JTextField
     */
    public JTextField getPriceTextField() {
        return priceTextField;
    }

    /**
     * Method sets the price into a text field
     * @param priceTextField
     */
    public void setPriceTextField(JTextField priceTextField) {
        this.priceTextField = priceTextField;
    }

    /**
     * Method that gets the quantity from a text field
     * @return instance of a JTextField
     */
    public JTextField getQuantityTextField() {
        return quantityTextField;
    }

    /**
     * Method that sets the quantity into a text field
     * @param quantityTextField
     */
    public void setQuantityTextField(JTextField quantityTextField) {
        this.quantityTextField = quantityTextField;
    }

    /**
     * Method that gets the product name from a text field
     * @return instance of JTextField
     */
    public JTextField getProductNameTextField() {
        return productNameTextField;
    }

    /**
     * Method that sets the product name into a text field
     * @param productNameTextField
     */
    public void setProductNameTextField(JTextField productNameTextField) {
        this.productNameTextField = productNameTextField;
    }

    /**
     * Method that gets the product ID from a text field
     * @return instance of a JTextField
     */
    public JTextField getProductIDTextField() {
        return productIDTextField;
    }

    /**
     * Method that sets the product ID into a text field
     * @param productIDTextField
     */
    public void setProductIDTextField(JTextField productIDTextField) {
        this.productIDTextField = productIDTextField;
    }
}
