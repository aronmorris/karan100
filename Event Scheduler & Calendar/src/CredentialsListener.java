import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.text.JTextComponent;

/**
 * Simply takes the login info for the database used for events
 * @author Aron
 *
 */
public class CredentialsListener implements ActionListener {

	private JTextComponent username;
	private JTextComponent password;
	
	public CredentialsListener(JTextComponent user, JTextComponent pw) {
		username = user;
		password = pw;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) { //logs into the db server - note, this can take a bit of time so it may be good to put on its own thread later
		
		DatabaseManager.setCredentials(username.getText(), password.getText());
		
	}

	
	
}
