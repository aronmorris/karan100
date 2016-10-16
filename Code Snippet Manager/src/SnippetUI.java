import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextArea;


public class SnippetUI {

	private SnippetIO ioHandler;
	
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SnippetUI window = new SnippetUI();
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
	public SnippetUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * This is autogenerated except where commented
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 784, 31);
		frame.getContentPane().add(menuBar);
		
		JMenuItem menuExitButton = new JMenuItem("Exit");
		menuExitButton.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		menuBar.add(menuExitButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 42, 400, 358);
		frame.getContentPane().add(scrollPane);
		
		//Below, a listener has been added to the JList, which will copy the text selected from the list to the user's clipboard for copy/paste operations
		DefaultListModel<String> model = new DefaultListModel<String>();
		
		JList<String> listOfSnippets = new JList<String>(model);
		scrollPane.setViewportView(listOfSnippets);
		
		SnippetReader listListener = new SnippetReader(listOfSnippets);
		
		listOfSnippets.addMouseListener(listListener);
		
		
		JTextArea txtAreaSnippetSubmitter = new JTextArea();
		txtAreaSnippetSubmitter.setBounds(420, 42, 354, 200);
		frame.getContentPane().add(txtAreaSnippetSubmitter);
		
		JButton btnSubmitSnippet = new JButton("Submit");
		btnSubmitSnippet.setBounds(420, 253, 89, 23);
		frame.getContentPane().add(btnSubmitSnippet);
		
		btnSubmitSnippet.addActionListener(new SnippetWriter(txtAreaSnippetSubmitter, model));
		
		JButton btnDeleteListEntry = new JButton("Delete");
		btnDeleteListEntry.setBounds(420, 287, 89, 23);
		frame.getContentPane().add(btnDeleteListEntry);
		btnDeleteListEntry.addActionListener(new ListItemDeletionListener(model, listListener));
		
		
		ioHandler = new SnippetIO(model);
		
		ioHandler.writeToModel();
		
		//adding shutdown hook to write the model's contents to the file
		//Thought: Replace with a save button instead to prevent unwanted modification on exit
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				ioHandler.writeToFile();
			}
		}));
		
		
	}
}
