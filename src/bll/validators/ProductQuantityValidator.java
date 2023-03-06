package bll.validators;

import model.Product;

public class ProductQuantityValidator implements Validator<Product> {

    private static final int MIN_QUANTITY = 0;
    private static final int MAX_QUANTITY = 9999;

    /**
     * Method that checks if the quantity of a product is within a range
     * @param product that we want to validate
     * @throws IllegalArgumentException if the quantity limit is not respected
     */
    @Override
    public boolean validate(Product product) {

        if (product.getQuantity() < MIN_QUANTITY || product.getQuantity() > MAX_QUANTITY) {
            throw new IllegalArgumentException("The quantity limit is not respected!");
        }
        return false;
    }
}
