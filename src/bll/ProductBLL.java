package bll;

import bll.validators.ProductPriceValidator;
import bll.validators.ProductQuantityValidator;
import bll.validators.Validator;
import dao.ProductDAO;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * PRODUCT ID
 * PRODUCT NAME
 * PRODUCT QUANTITY
 * PRODUCT PRICE
 */

public class ProductBLL {

    private List<Validator<Product>> validators;
    private ProductDAO productDAO;

    public ProductBLL() {
        validators = new ArrayList<Validator<Product>>();

        validators.add((Validator<Product>) new ProductQuantityValidator());
        validators.add((Validator<Product>) new ProductPriceValidator());

        productDAO = new ProductDAO();
    }

    /**
     * Method that searches for a product in the database by the id specified
     * @param id - of the product the user wants
     * @return the product or null if there is nothing found
     */
    public Product findProductById(int id) {
        Product product = productDAO.findById(id);
        if (product == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return product;
    }

    /**
     * Method that searches for a product in the database by the name specified
     * @param name - of the product the user wants
     * @return the product or null if there is nothing found
     */
    public Product findProductByName(String name) {
        Product product = productDAO.findByName(name);
        if (product == null) {
            throw new NoSuchElementException("The product with name =" + name + " was not found!");
        }
        return product;
    }

    /**
     * Method that creates a list of all products that exist in the database
     * @return list of all products
     */
    public ArrayList<Product> findAll() {
        ArrayList<Product> listOfProducts = productDAO.findAll();
        if(listOfProducts == null) {
            throw new NoSuchElementException("The list of products is empty!");
        }
        return listOfProducts;
    }

    /**
     * Method that insert a new product in the database
     * @param product that we want to insert
     * @return int
     */
    public int insertProduct(Product product){
        checkValidators(product);
        return productDAO.insert(product);
    }

    /**
     * Method that updates a product from the database
     * @param product that we want to update
     * @param id whose product we want updated
     * @return
     */
    public boolean update(Product product, int id) {
        checkValidators(product);
        return productDAO.update(product, id);
    }

    /**
     * Method that deletes a product from a database
     * @param id whose product we want to delete
     */
    public boolean delete(int id) {
        return productDAO.delete(id);
    }

    /**
     * Method that selects every product from the database
     * @return a list of all products
     */
    public List<Product> selectAll(){
        return productDAO.findAll();
    }

    /**
     * Method that fills the JTable with the corresponding product information
     * @param model
     * @param table
     */
    public void viewAllProducts(DefaultTableModel model, JTable table) {
        String[] columns = {"productID", "productName", "productAddress" };

        for(String column: columns) {
            model.addColumn(column);
        }

        List<Product> products = selectAll();

        for (Product product: products) {
            model.addRow(new Object[]{String.valueOf(product.getProductID()),
                    String.valueOf(product.getProductName()),
                    String.valueOf(product.getQuantity()),
                    String.valueOf(product.getProductPrice())});
        }

        table.setModel(model);
    }

    /**
     * check validators
     */

    /**
     * Method that checks certain conditions from the validators classes
     * @param product we want validated
     * @return true if every product is validated
     */
    public boolean checkValidators(Product product) {
        for (Validator<Product> validator: validators) {
            if(!validator.validate(product)) {
                return false;
            }
        }
        return true;
    }


}
