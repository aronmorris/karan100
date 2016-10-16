import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;


public class ListItemDeletionListener implements ActionListener {

	private final DefaultListModel<String> model;
	
	private final SnippetReader listMouseListener;
	
	public ListItemDeletionListener(DefaultListModel<String> model, SnippetReader reader) {
		this.model = model;
		listMouseListener = reader;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		int selected = listMouseListener.getSelectedIndex();
		
		if (selected != -1) {
			model.remove(selected);
		}
		
	}

	
	
}
