package bll;

import bll.validators.OrderQuantityValidator;
import bll.validators.OrderTotalValidator;
import bll.validators.Validator;
import dao.OrderDAO;
import model.Client;
import model.Orders;
import model.Orders;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * ORDER ID
 * CLIENT ID
 * PRODUCT ID
 * ORDER QUANTITY
 * ORDER TOTAL
 */

public class OrderBLL {

    private List<Validator<Orders>> validators;
    private OrderDAO orderDAO;

    public OrderBLL() {
        validators = new ArrayList<Validator<Orders>>();

        validators.add((Validator<Orders>) new OrderTotalValidator());
        validators.add((Validator<Orders>) new OrderQuantityValidator());

        orderDAO = new OrderDAO();
    }

    /**
     * Method that inserts a new order in the database
     * @param order we want inserted
     */
    public int insertOrder(Orders order){
        checkValidators(order);
        return orderDAO.insert(order);
    }

    /**
     * check validators
     */

    /**
     * Method that checks certain conditions from the validators classes
     * @param order we want validated
     * @return true if every order is validated
     */
    public boolean checkValidators(Orders order) {
        for (Validator<Orders> validator: validators) {
            if(!validator.validate(order)) {
                return false;
            }
        }
        return true;
    }

}
