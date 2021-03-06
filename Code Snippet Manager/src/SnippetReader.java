import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;

/**
 * SnippetReader reads the selected snippet from a generated JList element
 * and adds its text to the user's clipboard for pasting elsewhere
 * @author Aron
 *
 */
public class SnippetReader implements MouseListener {

	private final JList<String> snippetList; //list of code snippets the user has saved to the program
	
	SnippetReader(final JList<String> list) { 
		super();
		this.snippetList = list;
	}
	
	//Adds the selected snippet of code to the user's clipboard
	public static void addSelectionToClipboard(String snippet) {
		
		StringSelection selection = new StringSelection(snippet);
		
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		
		clipboard.setContents(selection, selection);
		
	}
	
	public int getSelectedIndex() {
		return snippetList.getSelectedIndex();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		addSelectionToClipboard(snippetList.getSelectedValue());
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
