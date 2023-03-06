package bll.validators;

import model.Product;

public class ProductPriceValidator implements Validator<Product> {

    private static final int MIN_PRICE = 0;

    /**
     * Method that checks if the product price is positive
     * @param product that we want to validate
     * @throws IllegalArgumentException if the price limit is not respected
     */
    @Override
    public boolean validate(Product product) {

        if (product.getProductPrice() < MIN_PRICE) {
            throw new IllegalArgumentException("The price limit is not respected!");
        }
        return false;
    }
}
