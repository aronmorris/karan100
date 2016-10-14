import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JTextField;


public class SnippetWriter implements ActionListener {

	private final JTextField textField;
	private final DefaultListModel<String> listModel;
	
	public SnippetWriter(JTextField field, DefaultListModel<String> list) {
		textField = field;
		listModel = list;
	}
	
	private String retrieveText() {
		
		return textField.getText();
	}

	//adds the text written in the text field to the list if the entry wasn't null or empty
	@Override
	public void actionPerformed(ActionEvent arg0) {

		String textRetrieved = retrieveText();
		
		if (textRetrieved.equals(null) || textRetrieved.equals("")) {
			return;
		}
		
		listModel.addElement(retrieveText());
		
	}
	
	
	
}
