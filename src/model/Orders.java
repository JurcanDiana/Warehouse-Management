package model;

public class Orders {

    private int orderID;
    private String clientName;
    private String productName;
    private double orderTotal;
    private int orderQuantity;

    public Orders() {

    }

    public Orders(String clientID, String productID, double orderTotal, int orderQuantity) {
        super();
        this.clientName = clientID;
        this.productName = productID;
        this.orderTotal = orderTotal;
        this.orderQuantity = orderQuantity;
    }

    /**
     * Method that returns the order ID
     * @return orderID
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     * Method that sets the order ID
     * @param orderID the new value of the ID
     */
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    /**
     * Method that gets the client name
     * @return String with the name of the client
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * Method that sets the client name
     * @param clientName the new value of the name
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * Method that gets the product name
     * @return String containing the name of the product
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Method that sets the product name
     * @param productName the new value of the name
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Method that gets the total of the order
     * @return orderTotal
     */
    public double getOrderTotal() {
        return orderTotal;
    }

    /**
     * Method that sets the total of the order
     * @param orderTotal the new value of the total
     */
    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    /**
     * Method that gets the quantity of the order
     * @return the quantity
     */
    public int getOrderQuantity() {
        return orderQuantity;
    }

    /**
     * Method that sets the quantity of the order
     * @param orderQuantity the new value of the quantity
     */
    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    /**
     * Method that displays a message regarding the information of the orders
     * @return String with all the information
     */
    @Override
    public String toString() {
        return "Order: " + orderID +
                ", for client: " + clientName +
                ", product: " + productName +
                ", quantity: " + orderQuantity +
                ", total: " + orderTotal;
    }
}
