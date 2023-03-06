package model;

public class Client {

	private int clientID;
	private String clientName;
	private String clientAddress;

	public Client() {
	}

	public Client(int id, String name, String address) {
		super();
		this.clientID = id;
		this.clientName = name;
		this.clientAddress = address;
	}

	public Client(String name, String address) {
		super();
		this.clientName = name;
		this.clientAddress = address;
	}

	/**
	 * Method that gets the ID of the client
	 * @return ID of client
	 */
	public int getClientID() {
		return clientID;
	}

	/**
	 * Method that sets the client ID
	 * @param clientID the new value of the ID
	 */
	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	/**
	 * Method that gets the name of the client
	 * @return client name
	 */
	public String getClientName() {
		return clientName;
	}

	/**
	 * Method that sets the name of the client
	 * @param clientName the new value of the name
	 */
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	/**
	 * Method that gets the address of the client
	 * @return client address
	 */
	public String getClientAddress() {
		return clientAddress;
	}

	/**
	 * Method that sets the client address
	 * @param clientAddress the new value of the address
	 */
	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	/**
	 * Method that displays a message regarding the information of the clients
	 * @return String with all the information
	 */
	@Override
	public String toString() {
		return "Client: " + clientName +
				", address: " + clientAddress;
	}

}
