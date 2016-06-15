import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;


public class GuestbookUI {

	private JFrame frame;
	private JTextField textField;
	private JList<Post> postList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuestbookUI window = new GuestbookUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GuestbookUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JButton submitPost = new JButton("Submit");
		springLayout.putConstraint(SpringLayout.NORTH, submitPost, 12, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, submitPost, 244, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(submitPost);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, 11, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textField, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textField, -6, SpringLayout.WEST, submitPost);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel postLabel = new JLabel("Latest Posts");
		springLayout.putConstraint(SpringLayout.NORTH, postLabel, 6, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.WEST, postLabel, 10, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(postLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 6, SpringLayout.SOUTH, postLabel);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 207, SpringLayout.SOUTH, postLabel);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 238, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(scrollPane);
		
		DefaultListModel<Post> listModel = new DefaultListModel<>();
		
		postList = new JList<Post>(listModel);
		scrollPane.setViewportView(postList);
		postList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		postList.setLayoutOrientation(JList.VERTICAL);
			
		JButton btnViewComments = new JButton("Comments");
		springLayout.putConstraint(SpringLayout.NORTH, btnViewComments, 0, SpringLayout.NORTH, scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, btnViewComments, 0, SpringLayout.WEST, submitPost);
		frame.getContentPane().add(btnViewComments);
		
		JButton btnPreviousContent = new JButton("Previous");
		springLayout.putConstraint(SpringLayout.NORTH, btnPreviousContent, 6, SpringLayout.SOUTH, btnViewComments);
		springLayout.putConstraint(SpringLayout.EAST, btnViewComments, 0, SpringLayout.EAST, btnPreviousContent);
		springLayout.putConstraint(SpringLayout.WEST, btnPreviousContent, 0, SpringLayout.WEST, submitPost);
		frame.getContentPane().add(btnPreviousContent);
	}
	
	public void valueChanged(ListSelectionEvent e) {
	    if (e.getValueIsAdjusting() == false) { //only care about when the user is done doing things to the list

	        if (postList.getSelectedIndex() == -1) {
	        //No selection, disable fire button.
	            fireButton.setEnabled(false);

	        } else {
	        //Selection, enable the fire button.
	            fireButton.setEnabled(true);
	        }
	    }
	}
	
}
