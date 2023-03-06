package start;
import presentation.MainMenuView;

import java.sql.SQLException;
import java.util.logging.Logger;

public class Start {
	protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

	public static void main(String[] args) throws SQLException {
		MainMenuView mainMenu = new MainMenuView();
	}

}
