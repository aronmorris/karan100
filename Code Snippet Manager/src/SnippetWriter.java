import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class SnippetWriter implements ActionListener {

	private final JTextArea textArea;
	private final DefaultListModel<String> listModel;
	
	public SnippetWriter(JTextArea field, DefaultListModel<String> list) {
		textArea = field;
		listModel = list;
	}
	
	private String retrieveText() {
		
		return textArea.getText();
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
