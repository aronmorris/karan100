import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GuestbookUI {

	private JFrame frame;
	private JTextField textField;
	private JList<Node<Post>> postList;
	private DefaultListModel<Node<Post>> listModel;
	private JButton viewComments, previousContent, submitPost;

	private Node<Post> root = null;
	
	private Node<Post> previousContext;
	
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
		
		submitPost = new JButton("Submit");
		springLayout.putConstraint(SpringLayout.NORTH, submitPost, 12, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, submitPost, 244, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(submitPost);
		
		submitPost.addActionListener(new SubmissionListener());
		
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
		
		listModel = new DefaultListModel<>();
		
		postList = new JList<Node<Post>>(listModel);
		scrollPane.setViewportView(postList);
		postList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		postList.setLayoutOrientation(JList.VERTICAL);
		
		postList.addListSelectionListener(new PostSelectionListener());
				
		viewComments = new JButton("Comments");
		springLayout.putConstraint(SpringLayout.NORTH, viewComments, 0, SpringLayout.NORTH, scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, viewComments, 0, SpringLayout.WEST, submitPost);
		frame.getContentPane().add(viewComments);
		viewComments.addActionListener(new CommentSelectListener());
		
		previousContent = new JButton("Previous");
		springLayout.putConstraint(SpringLayout.NORTH, previousContent, 6, SpringLayout.SOUTH, viewComments);
		springLayout.putConstraint(SpringLayout.EAST, viewComments, 0, SpringLayout.EAST, previousContent);
		springLayout.putConstraint(SpringLayout.WEST, previousContent, 0, SpringLayout.WEST, submitPost);
		frame.getContentPane().add(previousContent);
			
	}
	
	
	class SubmissionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Post p = new Post(textField.getText());
			
			if (root == null) {
				root = new Node<Post>(p);
				previousContext = root;
			}
			
			if (postList.isSelectionEmpty()) {
				previousContext.addChild(p);
				listModel.addElement(new Node<Post>(p));
				
			}
			
			else {
				postList.getSelectedValue().addChild(p);
			}
			
		}
		
	}
	
	class CommentSelectListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!postList.isSelectionEmpty()) {
			
				Node<Post> n = postList.getSelectedValue();
				
				if (n.hasChildren()) {
					listModel.clear();
					previousContext = n;
					for (Node<Post> np : n.getChildren()) {
						listModel.addElement(np);
					}
				}
				
			}
			
		}
		
	}
	
	class PreviousContentListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}

	}
	
	class PostSelectionListener implements ListSelectionListener {
		
		public void valueChanged(ListSelectionEvent e) {
		    if (e.getValueIsAdjusting() == false) { //only care about when the user is done doing things to the list
	
		        if (postList.getSelectedIndex() == -1) {
		        //No selection, disable fire button.
		        	viewComments.setEnabled(false);
		        	previousContent.setEnabled(false);
		        	
	
		        } else {
		        //Selection, enable the fire button.
		       
		        	previousContent.setEnabled(true);
		            viewComments.setEnabled(true);
		            
		        }
		    }
		}
	}
	
}
