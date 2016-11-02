import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.text.JTextComponent;

/**
 * Takes the text out of the description and time fields and makes them returnable to whoever calls for them
 * @author Aron
 *
 */
public class ButtonListener implements ActionListener {

	private final JTextComponent text;
	
	private String extractedString;
	
	public ButtonListener(JTextComponent component) {
		
		text = component;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		extractedString = text.getText();
		
	}
	//return true IFF text isnt null or blank
	public boolean hasText() {
		return (!(extractedString.equals(null) || extractedString.equals(""))); //return true IFF text isnt null or blank
	}
	
	public String getText() {
		
		String temp = extractedString;
		
		extractedString = "";
		
		return temp;
	}

}
