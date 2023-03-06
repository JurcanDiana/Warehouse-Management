package bll;

import bll.validators.Validator;
import dao.ClientDAO;
import model.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * CLIENT ID
 * CLIENT NAME
 * CLIENT ADDRESS
 */

public class ClientBLL {

	private List<Validator<Client>> validators;
	private ClientDAO clientDAO;

	public ClientBLL() {
		validators = new ArrayList<Validator<Client>>();
		clientDAO = new ClientDAO();
	}

	/**
	 * Method that searches for a client in the database by the id specified
	 * @param id - of the product the user wants
	 * @return the product or null if there is nothing found
	 */
	public Client findClientById(int id) {
		Client client = clientDAO.findById(id);
		if (client == null) {
			throw new NoSuchElementException("The client with id = " + id + " was not found!");
		}
		return client;
	}

	/**
	 * Method that searches for a client in the database by the name specified
	 * @param name - of the client the user wants
	 * @return the client or null if there is nothing found
	 */
	public Client findClientByName(String name) {
		Client client = clientDAO.findByName(name);
		if (client == null) {
			throw new NoSuchElementException("The client with name = " + name + " was not found!");
		}
		return client;
	}

	/**
	 * Method that creates a list of all clients that exist in the database
	 * @return list of all clients
	 */
	public ArrayList<Client> findAll() {
		ArrayList<Client> listOfClients = clientDAO.findAll();
		if(listOfClients == null) {
			throw new NoSuchElementException("The list of clients is empty!");
		}
		return listOfClients;
	}

	/**
	 * Method that insert a new client in the database
	 * @param client that we want to insert
	 * @return int
	 */
	public int insertClient(Client client){
		return clientDAO.insert(client);
	}

	/**
	 * Method that updates a client from the database
	 * @param client that we want to update
	 * @param id whose client we want updated
	 * @return
	 */
	public boolean update(Client client, int id) {
		return clientDAO.update(client, id);
	}

	/**
	 * Method that deletes a client from a database
	 * @param id whose client we want to delete
	 */
	public boolean delete(int id) {
		return clientDAO.delete(id);
	}

	/**
	 * Method that selects every client from the database
	 * @return a list of all clients
	 */
	public List<Client> selectAll(){
		return clientDAO.findAll();
	}

	/**
	 * Method that fills the JTable with the corresponding client information
	 * @param model
	 * @param table
	 */
	public void viewAllClients(DefaultTableModel model, JTable table) {

		String[] columns = {"clientID", "clientName", "clientAddress" };

		for(String column: columns) {
			model.addColumn(column);
		}

		List<Client> clients = selectAll();

		for (Client client: clients) {
			model.addRow(new Object[]{String.valueOf(client.getClientID()),
					String.valueOf(client.getClientName()),
					String.valueOf(client.getClientAddress())});
		}

		table.setModel(model);
	}

}
