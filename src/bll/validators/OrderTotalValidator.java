package bll.validators;

import model.Orders;

public class OrderTotalValidator implements Validator<Orders> {

    private static final int MIN_PRICE = 0;

    /**
     * Method that checks if the order total is positive
     * @param order that we want validated
     * @throws IllegalArgumentException if order total limit is not respected
     */
    @Override
    public boolean validate(Orders order) {

        if (order.getOrderTotal() < MIN_PRICE) {
            throw new IllegalArgumentException("The order total limit is not respected!");
        }
        return false;
    }
}
