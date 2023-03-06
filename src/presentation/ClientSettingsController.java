package presentation;

import bll.ClientBLL;
import model.Client;
import start.Start;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;

public class ClientSettingsController {

    protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

    private ClientSettingsView clientSettingsView;
    private ClientBLL clientBLL;
    private int clientID;
    private String clientName;
    private String clientAddress;

    public ClientSettingsController(ClientSettingsView clientSettingsView) {
        this.clientSettingsView = clientSettingsView;
        this.clientBLL = new ClientBLL();
    }

    /**
     * Method that gets all the client information
     */
    public void getClientInformation() {
        this.clientID = parseInt(clientSettingsView.getClientIDTextField().getText());
        this.clientName = clientSettingsView.getClientNameTextField().getText();
        this.clientAddress = clientSettingsView.getClientAddressTextField().getText();
    }

    /**
     * FUNCTIONALITY OF ADD/EDIT/DELETE BUTTONS
     */

    /**
     * Method that implements the functionality of the add client button
     * @param addClientButton
     */
    public void addClientButton(JButton addClientButton) {
        addClientButton.addActionListener(e -> {
            getClientInformation();
            clientBLL.insertClient(new Client(clientID, clientName, clientAddress));
        });
    }

    /**
     * Method that implements the functionality of the edit client button
     * @param editClientButton
     */
    public void editClientButton(JButton editClientButton) {
        editClientButton.addActionListener(e -> {
            getClientInformation();
            clientBLL.update(new Client(clientID, clientName, clientAddress), clientID);
        });
    }

    /**
     * Method that implements the functionality of the delete client button
     * @param deleteClientButton
     */
    public void deleteClientButton(JButton deleteClientButton) {
        deleteClientButton.addActionListener(e -> {
            getClientInformation();
            clientBLL.delete(clientID);
        });
    }

    /**
     * Method that implements the functionality of the view all clients button
     * @param viewAllClientsButton
     */
    public void viewAllClientsButton(JButton viewAllClientsButton) {
        viewAllClientsButton.addActionListener(e -> {

            DefaultTableModel model = new DefaultTableModel();
            JTable table = clientSettingsView.getViewAllClientsTable();

            clientBLL.viewAllClients(model, table);
        });
    }

    /**
     * FUNCTIONALITY OF BACK AND EXIT BUTTONS
     */

    /**
     * Method that implements the functionality of the back button
     * @param backClientsButton
     */
    public void backClients(JButton backClientsButton) {
        backClientsButton.addActionListener(e -> {
            MainMenuView mainMenuView = new MainMenuView();
            mainMenuView.setVisible(true);
            clientSettingsView.setVisible(false);
        });
    }

    /**
     * Method that implements the functionality of the exit button
     * @param exitButton
     */
    public void exit(JButton exitButton) {
        exitButton.addActionListener(e -> System.exit(0));
    }
    

}
