package presentation;

import javax.swing.*;

public class ClientSettingsView extends JFrame {
    private JPanel panel;
    private JTextField clientAddressTextField;
    private JTextField clientNameTextField;
    private JTextField clientIDTextField;
    private JButton addClientButton;
    private JButton editClientButton;
    private JButton deleteClientButton;
    private JButton viewAllClientsButton;
    private JTable viewAllClientsTable;
    private JButton backButton;
    private JButton exitButton;

    public ClientSettingsView() {

        super("Client Settings");
        setContentPane(panel);
        setSize(550, 550);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //frame on the center
        setVisible(true);

        ClientSettingsController controller = new ClientSettingsController(this);

        //add button
        controller.addClientButton(addClientButton);
        controller.editClientButton(editClientButton);
        controller.deleteClientButton(deleteClientButton);
        controller.viewAllClientsButton(viewAllClientsButton);

        //exit application
        controller.exit(exitButton);

        //back button
        controller.backClients(backButton);
    }

    /**
     * Method that gets the client address from a text field
     * @return client address
     */
    public JTextField getClientAddressTextField() {
        return clientAddressTextField;
    }

    /**
     * Method that sets the client address
     * @param clientAddressTextField
     */
    public void setClientAddressTextField(JTextField clientAddressTextField) {
        this.clientAddressTextField = clientAddressTextField;
    }

    /**
     * Method that gets the client name from a text field
     * @return client name
     */
    public JTextField getClientNameTextField() {
        return clientNameTextField;
    }

    /**
     * Method that sets the client name
     * @param clientNameTextField
     */
    public void setClientNameTextField(JTextField clientNameTextField) {
        this.clientNameTextField = clientNameTextField;
    }

    /**
     * Method that gets the client ID from a text field
     * @return client ID
     */
    public JTextField getClientIDTextField() {
        return clientIDTextField;
    }

    /**
     * Method that sets the client ID
     * @param clientIDTextField
     */
    public void setClientIDTextField(JTextField clientIDTextField) {
        this.clientIDTextField = clientIDTextField;
    }

    /**
     * Method that gets instance of JTable in which clients are displayed
     * @return JTable of all clients
     */
    public JTable getViewAllClientsTable() {
        return viewAllClientsTable;
    }

    /**
     * Method that sets instance of JTable
     * @param viewAllClientsTable
     */
    public void setViewAllClientsTable(JTable viewAllClientsTable) {
        this.viewAllClientsTable = viewAllClientsTable;
    }
}
