package bll.validators;

import model.Orders;

public class OrderQuantityValidator implements Validator<Orders> {

    private static final int MIN_QUANTITY = 0;

    /**
     * Method that checks if the quantity if positive
     * @param order that we want to validate
     * @throws IllegalArgumentException if quantity limit is not respected
     */
    @Override
    public boolean validate(Orders order) {

        if (order.getOrderQuantity() < MIN_QUANTITY) {
            throw new IllegalArgumentException("The quantity limit is not respected!");
        }
        return false;
    }
}
