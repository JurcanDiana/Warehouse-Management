package model;

public class Product {

    private int productID;
    private String productName;
    private int quantity;
    private double productPrice;

    public Product() {

    }

    public Product(int productID, String productName, int quantity, double productPrice) {
        super();
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.productPrice = productPrice;
    }

    /**
     * Method that gets the product ID
     * @return ID of product
     */
    public int getProductID() {
        return productID;
    }

    /**
     * Method that sets the product ID
     * @param productID the new value of ID
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**
     * Method that gets the product name
     * @return name of product
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Method that sets the product name
     * @param productName the new value of product name
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Method that gets the quantity of a product
     * @return quantity of products
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Method that sets the quantity of a product
     * @param quantity the new value of quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Method that gets the product price
     * @return product price
     */
    public double getProductPrice() {
        return productPrice;
    }

    /**
     * Method that sets the product price
     * @param productPrice the new value of the price
     */
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * Method that displays a message regarding the information of the products
     * @return String with all the information
     */
    @Override
    public String toString() {
        return "Product: " + productName +
                ", quantity: " + quantity +
                ", price: " + productPrice;
    }
}
